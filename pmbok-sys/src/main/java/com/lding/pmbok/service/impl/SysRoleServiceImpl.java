package com.lding.pmbok.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lding.pmbok.common.cache.Caches;
import com.lding.pmbok.common.enhance.MyLambdaQueryWrapper;
import com.lding.pmbok.common.enhance.MyPage;
import com.lding.pmbok.common.mapStruct.MapStructs;
import com.lding.pmbok.common.util.Streams;
import com.lding.pmbok.common.util.Strings;
import com.lding.pmbok.mapper.SysRoleMapper;
import com.lding.pmbok.mapper.SysUserRoleMapper;
import com.lding.pmbok.pojo.po.SysRole;
import com.lding.pmbok.pojo.po.SysRoleResource;
import com.lding.pmbok.pojo.po.SysUserRole;
import com.lding.pmbok.pojo.vo.PageVo;
import com.lding.pmbok.pojo.vo.req.save.SysRoleReqSaveVo;
import com.lding.pmbok.pojo.vo.req.select.SysRolePageReqVo;
import com.lding.pmbok.pojo.vo.resp.SysRoleVo;
import com.lding.pmbok.service.SysRoleResourceService;
import com.lding.pmbok.service.SysRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {
    @Resource
    private SysUserRoleMapper userRoleMapper;
    @Resource
    private SysRoleResourceService roleResourceService;

    @Override
    @Transactional(readOnly = true)
    public PageVo<SysRoleVo> getTargetList(SysRolePageReqVo reqVo) {
        MyLambdaQueryWrapper<SysRole> wrapper = new MyLambdaQueryWrapper<>();
        wrapper.like(reqVo.getKeyword(), SysRole::getName);
        wrapper.orderByDesc(SysRole::getId);
        return baseMapper
                .selectPage(new MyPage<>(reqVo), wrapper)
                .buildVo(MapStructs.INSTANCE::po2vo);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Integer> listIds(Integer userId) {
        if (userId == null || userId <= 0) return null;

        MyLambdaQueryWrapper<SysUserRole> wrapper = new MyLambdaQueryWrapper<>();
        wrapper.select(SysUserRole::getRoleId);
        wrapper.eq(SysUserRole::getUserId, userId);

        List<Object> ids = userRoleMapper.selectObjs(wrapper);
        return Streams.map(ids, (id) -> (Integer) id);
    }

    @Override
    public boolean saveOrUpdate(SysRoleReqSaveVo reqVo) {
        // ??????PO
        SysRole po = MapStructs.INSTANCE.reqSaveVo2po(reqVo);

        // ??????????????????
        if (!saveOrUpdate(po)) return false;

        Integer id = reqVo.getId();
        if (id != null && id > 0) {
            MyLambdaQueryWrapper<SysUserRole> wrapper = new MyLambdaQueryWrapper<>();
            wrapper.select(SysUserRole::getUserId);
            wrapper.eq(SysUserRole::getRoleId, id);
            List<Object> userIds = userRoleMapper.selectObjs(wrapper);
            if (!CollectionUtils.isEmpty(userIds)) {
                for (Object userId : userIds) {
                    // ??????????????????????????????????????????????????????token????????????????????????????????????
                    Caches.removeToken(Caches.get(userId));
                }
            }

            // ???????????????????????????????????????
            roleResourceService.removeByRoleId(id);
        }

        // ??????????????????
        String resourceIdsStr = reqVo.getResourceIds();
        if (Strings.isEmpty(resourceIdsStr)) return true;

        String[] resourceIds = resourceIdsStr.split(",");
        List<SysRoleResource> roleResources = new ArrayList<>();
        Integer roleId = po.getId();
        for (String resourceId : resourceIds) { // ??????SysUserRole??????
            SysRoleResource roleResource = new SysRoleResource();
            roleResource.setRoleId(roleId);
            roleResource.setResourceId(Integer.parseInt(resourceId));
            roleResources.add(roleResource);
        }
        return roleResourceService.saveBatch(roleResources);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SysRole> listByUserId(Integer userId) {
        if (userId == null || userId <= 0) return null;
        List<Integer> ids = listIds(userId);
        if (CollectionUtils.isEmpty(ids)) return null;

        MyLambdaQueryWrapper<SysRole> wrapper = new MyLambdaQueryWrapper<>();
        wrapper.in(SysRole::getId, ids);

        return  baseMapper.selectList(wrapper);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SysRole> list() {
        MyLambdaQueryWrapper<SysRole> wrapper = new MyLambdaQueryWrapper<>();
        wrapper.orderByDesc(SysRole::getId);
        return super.list(wrapper);
    }
}


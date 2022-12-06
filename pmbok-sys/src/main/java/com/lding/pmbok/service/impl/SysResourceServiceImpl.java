package com.lding.pmbok.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lding.pmbok.common.enhance.MyLambdaQueryWrapper;
import com.lding.pmbok.common.enhance.MyPage;
import com.lding.pmbok.common.mapStruct.MapStructs;
import com.lding.pmbok.common.util.Constants;
import com.lding.pmbok.common.util.Streams;
import com.lding.pmbok.mapper.SysResourceMapper;
import com.lding.pmbok.mapper.SysRoleResourceMapper;
import com.lding.pmbok.pojo.po.SysResource;
import com.lding.pmbok.pojo.po.SysRoleResource;
import com.lding.pmbok.pojo.vo.PageVo;
import com.lding.pmbok.pojo.vo.req.select.SysResourcePageReqVo;
import com.lding.pmbok.pojo.vo.resp.SysResourceTreeVo;
import com.lding.pmbok.pojo.vo.resp.SysResourceVo;
import com.lding.pmbok.service.SysResourceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import javax.annotation.Resource;
import java.util.*;

@Service
@Transactional
public class SysResourceServiceImpl extends ServiceImpl<SysResourceMapper, SysResource> implements SysResourceService {
    @Resource
    private SysRoleResourceMapper roleResourceMapper;

    @Override
    @Transactional(readOnly = true)
    public PageVo<SysResourceVo> getTargetList(SysResourcePageReqVo reqVo) {
        MyLambdaQueryWrapper<SysResource> wrapper = new MyLambdaQueryWrapper<>();
        wrapper.like(reqVo.getKeyword(),
                SysResource::getName,
                SysResource::getUri,
                SysResource::getPermission);
        wrapper.orderByDesc(SysResource::getId);
        return baseMapper
                .selectPage(new MyPage<>(reqVo), wrapper)
                .buildVo(MapStructs.INSTANCE::po2vo);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SysResourceVo> listParents() {
        MyLambdaQueryWrapper<SysResource> wrapper = new MyLambdaQueryWrapper<>();
        wrapper.ne(SysResource::getType, Constants.SysResourceType.BTN);
        wrapper.orderByAsc(SysResource::getType).orderByDesc(SysResource::getId);
        return Streams.map(baseMapper.selectList(wrapper), MapStructs.INSTANCE::po2vo);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SysResourceTreeVo> listTree() {
        // 里面存放的是树状结构的VO（最外层是目录类型的资源对象）
        List<SysResourceTreeVo> vos = new ArrayList<>();

        // 存放已经从po转换成功的vo
        Map<Integer, SysResourceTreeVo> doneVos = new HashMap<>();

        MyLambdaQueryWrapper<SysResource> wrapper = new MyLambdaQueryWrapper<>();
        wrapper.orderByAsc(SysResource::getType);
        // 里面存放的是从数据库中查出来的PO
        List<SysResource> pos = baseMapper.selectList(wrapper);
        for (SysResource po : pos) {
            // po转vo
            SysResourceTreeVo vo = po2treeVo(po);

            // 将vo存放到doneVos中
            doneVos.put(vo.getId(), vo);

            Integer type = po.getType();
            if (type == Constants.SysResourceType.DIR) { // 目录
                vos.add(vo);
            } else { // 菜单、按钮
                // 从doneVos中取出父VO
                SysResourceTreeVo parentVo = doneVos.get(po.getParentId());
                List<SysResourceTreeVo> children = parentVo.getChildren();
                if (children == null) {
                    parentVo.setChildren(children = new ArrayList<>());
                }
                children.add(vo);
            }
        }

        return vos;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Integer> listIds(Integer roleId) {
        if (roleId == null || roleId <= 0) return null;

        MyLambdaQueryWrapper<SysRoleResource> wrapper = new MyLambdaQueryWrapper<>();
        wrapper.select(SysRoleResource::getResourceId);
        wrapper.eq(SysRoleResource::getRoleId, roleId);

        List<Object> ids = roleResourceMapper.selectObjs(wrapper);
        return Streams.map(ids, (id) -> (Integer) id);
    }

    private List<Integer> listIds(List<Integer> roleIds) {
        MyLambdaQueryWrapper<SysRoleResource> wrapper = new MyLambdaQueryWrapper<>();
        wrapper.select(SysRoleResource::getResourceId);
        wrapper.in(SysRoleResource::getRoleId, roleIds);

        List<Object> ids = roleResourceMapper.selectObjs(wrapper);
        return Streams.map(new HashSet<>(ids), (id) -> (Integer) id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SysResource> listByRoleIds(List<Integer> roleIds) {
        if (CollectionUtils.isEmpty(roleIds)) return null;

        List<Integer> ids = listIds(roleIds);
        if (CollectionUtils.isEmpty(ids)) return null;

        MyLambdaQueryWrapper<SysResource> wrapper = new MyLambdaQueryWrapper<>();
        wrapper.in(SysResource::getId, ids);
        return baseMapper.selectList(wrapper);
    }

    private SysResourceTreeVo po2treeVo(SysResource po) {
        SysResourceTreeVo treeVo = new SysResourceTreeVo();
        treeVo.setId(po.getId());
        treeVo.setTitle(po.getName());
        return treeVo;
    }
}


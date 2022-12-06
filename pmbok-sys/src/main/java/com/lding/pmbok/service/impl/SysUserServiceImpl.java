package com.lding.pmbok.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lding.pmbok.common.cache.Caches;
import com.lding.pmbok.common.enhance.MyLambdaQueryWrapper;
import com.lding.pmbok.common.enhance.MyPage;
import com.lding.pmbok.common.mapStruct.MapStructs;
import com.lding.pmbok.common.message.CodeMsg;
import com.lding.pmbok.common.util.Constants;
import com.lding.pmbok.common.util.JsonVos;
import com.lding.pmbok.common.util.Streams;
import com.lding.pmbok.common.util.Strings;
import com.lding.pmbok.mapper.SysUserMapper;
import com.lding.pmbok.pojo.dto.SysUserDto;
import com.lding.pmbok.pojo.po.SysResource;
import com.lding.pmbok.pojo.po.SysRole;
import com.lding.pmbok.pojo.po.SysUser;
import com.lding.pmbok.pojo.po.SysUserRole;
import com.lding.pmbok.pojo.vo.LoginVo;
import com.lding.pmbok.pojo.vo.PageVo;
import com.lding.pmbok.pojo.vo.req.LoginReqVo;
import com.lding.pmbok.pojo.vo.req.save.SysUserReqSaveVo;
import com.lding.pmbok.pojo.vo.req.select.SysUserPageReqVo;
import com.lding.pmbok.pojo.vo.resp.SysUserVo;
import com.lding.pmbok.service.SysResourceService;
import com.lding.pmbok.service.SysRoleService;
import com.lding.pmbok.service.SysUserRoleService;
import com.lding.pmbok.service.SysUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {
    @Resource
    private SysUserRoleService userRoleService;
    @Resource
    private SysRoleService roleService;
    @Resource
    private SysResourceService resourceService;

    @Override
    @Transactional(readOnly = true)
    public PageVo<SysUserVo> getTargetList(SysUserPageReqVo reqVo) {
        MyLambdaQueryWrapper<SysUser> wrapper = new MyLambdaQueryWrapper<>();
        wrapper.like(reqVo.getKeyword(), SysUser::getNickname, SysUser::getUsername);
        wrapper.orderByDesc(SysUser::getId);
        return baseMapper
                .selectPage(new MyPage<>(reqVo), wrapper)
                .buildVo(MapStructs.INSTANCE::po2vo);
    }

    @Override
    public boolean saveOrUpdate(SysUserReqSaveVo reqVo) {
        // 转成PO
        SysUser po = MapStructs.INSTANCE.reqSaveVo2po(reqVo);

        // 保存用户信息
        if (!saveOrUpdate(po)) return false;

        Integer id = reqVo.getId();
        if (id != null && id > 0) { // 如果是做更新
            // 将更新成功的用户从缓存中移除（让token失效，用户必须重新登录）
            Caches.removeToken(Caches.get(id));

            // 删除当前用户的所有角色信息
            userRoleService.removeByUserId(reqVo.getId());
        }

        // 保存角色信息
        String roleIdsStr = reqVo.getRoleIds();
        if (Strings.isEmpty(roleIdsStr)) return true;

        String[] roleIds = roleIdsStr.split(",");
        List<SysUserRole> userRoles = new ArrayList<>();
        Integer userId = po.getId();
        for (String roleId : roleIds) { // 构建SysUserRole对象
            SysUserRole userRole = new SysUserRole();
            userRole.setUserId(userId);
            userRole.setRoleId(Integer.parseInt(roleId));
            userRoles.add(userRole);
        }
        return userRoleService.saveBatch(userRoles);
    }

    @Override
    public LoginVo login(LoginReqVo reqVo) {
        // 根据用户名查询用户
        MyLambdaQueryWrapper<SysUser> wrapper = new MyLambdaQueryWrapper<>();
        wrapper.eq(SysUser::getUsername, reqVo.getUsername());
        SysUser po = baseMapper.selectOne(wrapper);

        // 用户名不存在
        if (po == null) {
            return JsonVos.raise(CodeMsg.WRONG_USERNAME);
        }

        // 密码不正确
        if (!po.getPassword().equals(reqVo.getPassword())) {
            return JsonVos.raise(CodeMsg.WRONG_PASSWORD);
        }

        // 账号锁定
        if (po.getStatus() == Constants.SysUserStatus.LOCKED) {
            return JsonVos.raise(CodeMsg.USER_LOCKED);
        }

        // 更新登录时间
        po.setLoginTime(new Date());
        baseMapper.updateById(po);

        // 生成Token，发送Token给用户
        String token = UUID.randomUUID().toString();

        // 存储token到缓存中
        SysUserDto dto = new SysUserDto();
        dto.setUser(po);
        // 根据用户id查询所有的角色：sys_role，sys_user_role
        List<SysRole> roles = roleService.listByUserId(po.getId());

        // 根据角色id查询所有的资源：sys_resource、sys_role_resource
        if (!CollectionUtils.isEmpty(roles)) {
            dto.setRoles(roles);

            List<Integer> roleIds = Streams.map(roles, SysRole::getId);
            List<SysResource> resources = resourceService.listByRoleIds(roleIds);
            dto.setResources(resources);
        }

        Caches.putToken(token, dto);

        // 返回给客户端的具体数据
        LoginVo vo = MapStructs.INSTANCE.po2loginVo(po);
        vo.setToken(token);
        return vo;
    }
}


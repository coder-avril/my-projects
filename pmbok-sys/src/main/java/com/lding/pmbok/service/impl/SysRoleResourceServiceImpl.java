package com.lding.pmbok.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lding.pmbok.common.enhance.MyLambdaQueryWrapper;
import com.lding.pmbok.mapper.SysRoleResourceMapper;
import com.lding.pmbok.pojo.po.SysRoleResource;
import com.lding.pmbok.service.SysRoleResourceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SysRoleResourceServiceImpl extends ServiceImpl<SysRoleResourceMapper, SysRoleResource> implements SysRoleResourceService {
    @Override
    public boolean removeByRoleId(Integer roleId) {
        if (roleId == null || roleId <= 0) return false;
        MyLambdaQueryWrapper<SysRoleResource> wrapper = new MyLambdaQueryWrapper<>();
        wrapper.eq(SysRoleResource::getRoleId, roleId);
        return baseMapper.delete(wrapper) > 0;
    }
}


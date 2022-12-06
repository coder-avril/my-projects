package com.lding.pmbok.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lding.pmbok.common.enhance.MyLambdaQueryWrapper;
import com.lding.pmbok.mapper.SysUserRoleMapper;
import com.lding.pmbok.pojo.po.SysUserRole;
import com.lding.pmbok.service.SysUserRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements SysUserRoleService {
    @Override
    public boolean removeByUserId(Integer userId) {
        if (userId == null || userId <= 0) return false;
        MyLambdaQueryWrapper<SysUserRole> wrapper = new MyLambdaQueryWrapper<>();
        wrapper.eq(SysUserRole::getUserId, userId);
        return baseMapper.delete(wrapper) > 0;
    }
}


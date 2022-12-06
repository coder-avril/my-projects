package com.lding.pmbok.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lding.pmbok.pojo.po.SysUserRole;

public interface SysUserRoleService extends IService<SysUserRole> {
    boolean removeByUserId(Integer userId);
}


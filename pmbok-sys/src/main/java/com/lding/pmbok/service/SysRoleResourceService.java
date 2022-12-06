package com.lding.pmbok.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lding.pmbok.pojo.po.SysRoleResource;

public interface SysRoleResourceService extends IService<SysRoleResource> {
    boolean removeByRoleId(Integer roleId);
}


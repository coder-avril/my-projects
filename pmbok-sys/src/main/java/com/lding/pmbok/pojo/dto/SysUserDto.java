package com.lding.pmbok.pojo.dto;

import com.lding.pmbok.pojo.po.SysResource;
import com.lding.pmbok.pojo.po.SysRole;
import com.lding.pmbok.pojo.po.SysUser;
import lombok.Data;
import java.util.List;

@Data
public class SysUserDto {
    private SysUser user;
    private List<SysRole> roles;
    private List<SysResource> resources;
}

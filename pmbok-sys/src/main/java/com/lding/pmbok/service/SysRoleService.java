package com.lding.pmbok.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lding.pmbok.pojo.po.SysRole;
import com.lding.pmbok.pojo.vo.PageVo;
import com.lding.pmbok.pojo.vo.req.save.SysRoleReqSaveVo;
import com.lding.pmbok.pojo.vo.req.select.SysRolePageReqVo;
import com.lding.pmbok.pojo.vo.resp.SysRoleVo;
import java.util.List;

public interface SysRoleService extends IService<SysRole> {
    PageVo<SysRoleVo> getTargetList(SysRolePageReqVo reqVo);

    List<Integer> listIds(Integer userId);

    boolean saveOrUpdate(SysRoleReqSaveVo reqVo);

    List<SysRole> listByUserId(Integer userId);
}


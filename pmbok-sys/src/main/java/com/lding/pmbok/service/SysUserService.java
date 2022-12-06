package com.lding.pmbok.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lding.pmbok.pojo.po.SysUser;
import com.lding.pmbok.pojo.vo.LoginVo;
import com.lding.pmbok.pojo.vo.PageVo;
import com.lding.pmbok.pojo.vo.req.LoginReqVo;
import com.lding.pmbok.pojo.vo.req.save.SysUserReqSaveVo;
import com.lding.pmbok.pojo.vo.req.select.SysUserPageReqVo;
import com.lding.pmbok.pojo.vo.resp.SysUserVo;

public interface SysUserService extends IService<SysUser> {
    PageVo<SysUserVo> getTargetList(SysUserPageReqVo reqVo);

    boolean saveOrUpdate(SysUserReqSaveVo reqVo);

    LoginVo login(LoginReqVo reqVo);
}

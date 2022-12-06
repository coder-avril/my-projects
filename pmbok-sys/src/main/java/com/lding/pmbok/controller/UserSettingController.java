package com.lding.pmbok.controller;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lding.pmbok.pojo.po.SysUser;
import com.lding.pmbok.pojo.vo.req.save.SysUserReqSaveVo;
import com.lding.pmbok.service.SysUserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import java.util.function.Function;

@RestController
@RequestMapping("/userSetting")
public class UserSettingController extends BaseController<SysUser, SysUserReqSaveVo> {
    @Resource
    private SysUserService service;

    @Override
    protected IService<SysUser> getService() {
        return service;
    }

    @Override
    protected Function<SysUserReqSaveVo, SysUser> getFunction() {
        return vo -> {
            SysUser sysUser = service.getById(vo.getId());
            sysUser.setNickname(vo.getNickname());
            sysUser.setRemark(vo.getRemark());
            return sysUser;
        };
    }
}

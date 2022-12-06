package com.lding.pmbok.controller;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lding.pmbok.common.message.CodeMsg;
import com.lding.pmbok.common.util.JsonVos;
import com.lding.pmbok.pojo.po.SysUser;
import com.lding.pmbok.pojo.vo.req.PasswordReqVo;
import com.lding.pmbok.service.SysUserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import java.util.function.Function;

@RestController
@RequestMapping("/userPassword")
public class UserPasswordController extends BaseController<SysUser, PasswordReqVo> {
    @Resource
    private SysUserService service;

    @Override
    protected IService<SysUser> getService() {
        return service;
    }

    @Override
    protected Function<PasswordReqVo, SysUser> getFunction() {
        return vo -> {
            SysUser sysUser = service.getById(vo.getId());
            if (!vo.getOldPassword().equals(sysUser.getPassword())) return JsonVos.raise(CodeMsg.WRONG_OLD_PASSWORD);
            sysUser.setPassword(vo.getNewPassword());
            return sysUser;
        };
    }
}

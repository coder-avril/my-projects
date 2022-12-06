package com.lding.pmbok.controller;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lding.pmbok.common.cache.Caches;
import com.lding.pmbok.common.mapStruct.MapStructs;
import com.lding.pmbok.common.message.CodeMsg;
import com.lding.pmbok.common.shiro.TokenFilter;
import com.lding.pmbok.common.util.Constants;
import com.lding.pmbok.common.util.JsonVos;
import com.lding.pmbok.pojo.po.SysUser;
import com.lding.pmbok.pojo.vo.DataJsonVo;
import com.lding.pmbok.pojo.vo.JsonVo;
import com.lding.pmbok.pojo.vo.LoginVo;
import com.lding.pmbok.pojo.vo.ViewJsonVo;
import com.lding.pmbok.pojo.vo.req.LoginReqVo;
import com.lding.pmbok.pojo.vo.req.save.SysUserReqSaveVo;
import com.lding.pmbok.pojo.vo.req.select.SysUserPageReqVo;
import com.lding.pmbok.pojo.vo.resp.SysUserVo;
import com.lding.pmbok.service.SysUserService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.function.Function;

@RestController
@RequestMapping("/sysUsers")
public class SysUserController extends BaseController<SysUser, SysUserReqSaveVo> {
    @Resource
    private SysUserService service;

    /**
     * 分页查询
     * @param reqVo 分页请求参数
     * @return ViewJsonVo<SysUserVo> 分页数据
     */
    @GetMapping
    @RequiresPermissions(Constants.Permission.SYS_USER_LIST)
    public ViewJsonVo<SysUserVo> list(SysUserPageReqVo reqVo) {
        return JsonVos.ok(service.getTargetList(reqVo));
    }

    /**
     * 登录
     */
    @PostMapping("/login")
    public DataJsonVo<LoginVo> login(LoginReqVo reqVo) {
        return JsonVos.ok(service.login(reqVo));
    }

    /**
     * 退出登录
     */
    @PostMapping("/logout")
    public JsonVo logout(@RequestHeader(TokenFilter.HEADER_TOKEN) String token) {
        Caches.removeToken(token);
        return JsonVos.ok();
    }

    @Override
    @RequiresPermissions(value = {
            Constants.Permission.SYS_USER_ADD,
            Constants.Permission.SYS_USER_UPDATE
    }, logical = Logical.AND)
    public JsonVo save(SysUserReqSaveVo reqSaveVo) {
        if (service.saveOrUpdate(reqSaveVo)) {
            return JsonVos.ok(CodeMsg.SAVE_OK);
        } else {
            return JsonVos.raise(CodeMsg.SAVE_ERROR);
        }
    }

    @Override
    @RequiresPermissions(Constants.Permission.SYS_USER_REMOVE)
    public JsonVo remove(String id) {
        return super.remove(id);
    }

    @Override
    protected IService<SysUser> getService() {
        return service;
    }

    @Override
    protected Function<SysUserReqSaveVo, SysUser> getFunction() {
        return MapStructs.INSTANCE::reqSaveVo2po;
    }
}


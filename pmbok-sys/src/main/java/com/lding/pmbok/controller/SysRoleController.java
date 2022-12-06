package com.lding.pmbok.controller;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lding.pmbok.common.mapStruct.MapStructs;
import com.lding.pmbok.common.message.CodeMsg;
import com.lding.pmbok.common.util.Constants;
import com.lding.pmbok.common.util.JsonVos;
import com.lding.pmbok.common.util.Streams;
import com.lding.pmbok.pojo.po.SysRole;
import com.lding.pmbok.pojo.vo.DataJsonVo;
import com.lding.pmbok.pojo.vo.JsonVo;
import com.lding.pmbok.pojo.vo.ViewJsonVo;
import com.lding.pmbok.pojo.vo.req.save.SysRoleReqSaveVo;
import com.lding.pmbok.pojo.vo.req.select.SysRolePageReqVo;
import com.lding.pmbok.pojo.vo.resp.SysRoleVo;
import com.lding.pmbok.service.SysRoleService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;
import java.util.function.Function;

@RestController
@RequestMapping("/sysRoles")
public class SysRoleController extends BaseController<SysRole, SysRoleReqSaveVo> {
    @Resource
    private SysRoleService service;

    /**
     * 分页查询
     * @param reqVo 分页请求参数
     * @return ViewJsonVo<SysRoleVo> 分页数据
     */
    @GetMapping
    @RequiresPermissions(Constants.Permission.SYS_ROLE_LIST)
    public ViewJsonVo<SysRoleVo> list(SysRolePageReqVo reqVo) {
        return JsonVos.ok(service.getTargetList(reqVo));
    }

    /**
     * 查询所有
     * @return DataJsonVo<List<SysRoleVo>> 所有数据集合
     */
    @GetMapping("/list")
    @RequiresPermissions(Constants.Permission.SYS_ROLE_LIST)
    public DataJsonVo<List<SysRoleVo>> list() {
        return JsonVos.ok(Streams.map(service.list(), MapStructs.INSTANCE::po2vo));
    }

    /**
     * 根据用户id获取角色id
     */
    @GetMapping("/ids")
    @RequiresPermissions(Constants.Permission.SYS_ROLE_LIST)
    public DataJsonVo<List<Integer>> ids(Integer userId) {
        return JsonVos.ok(service.listIds(userId));
    }

    @Override
    @RequiresPermissions(value = {
            Constants.Permission.SYS_ROLE_ADD,
            Constants.Permission.SYS_ROLE_UPDATE
    }, logical = Logical.AND)
    public JsonVo save(SysRoleReqSaveVo reqSaveVo) {
        if (service.saveOrUpdate(reqSaveVo)) {
            return JsonVos.ok(CodeMsg.SAVE_OK);
        } else {
            return JsonVos.raise(CodeMsg.SAVE_ERROR);
        }
    }

    @Override
    @RequiresPermissions(Constants.Permission.SYS_ROLE_REMOVE)
    public JsonVo remove(String id) {
        return super.remove(id);
    }

    @Override
    protected IService<SysRole> getService() {
        return service;
    }

    @Override
    protected Function<SysRoleReqSaveVo, SysRole> getFunction() {
        return MapStructs.INSTANCE::reqSaveVo2po;
    }
}


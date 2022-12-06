package com.lding.pmbok.controller;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lding.pmbok.common.mapStruct.MapStructs;
import com.lding.pmbok.common.util.Constants;
import com.lding.pmbok.common.util.JsonVos;
import com.lding.pmbok.common.util.Streams;
import com.lding.pmbok.pojo.po.SysResource;
import com.lding.pmbok.pojo.vo.DataJsonVo;
import com.lding.pmbok.pojo.vo.JsonVo;
import com.lding.pmbok.pojo.vo.ViewJsonVo;
import com.lding.pmbok.pojo.vo.req.save.SysResourceReqSaveVo;
import com.lding.pmbok.pojo.vo.req.select.SysResourcePageReqVo;
import com.lding.pmbok.pojo.vo.resp.SysResourceTreeVo;
import com.lding.pmbok.pojo.vo.resp.SysResourceVo;
import com.lding.pmbok.service.SysResourceService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import java.util.List;
import java.util.function.Function;

@RestController
@RequestMapping("/sysResources")
public class SysResourceController extends BaseController<SysResource, SysResourceReqSaveVo> {
    @Resource
    private SysResourceService service;

    /**
     * 分页查询
     */
    @GetMapping
    @RequiresPermissions(Constants.Permission.SYS_RESOURCE_LIST)
    public ViewJsonVo<SysResourceVo> list(SysResourcePageReqVo reqVo) {
        return JsonVos.ok(service.getTargetList(reqVo));
    }

    /**
     * 根据用户id获取角色id
     */
    @GetMapping("/ids")
    @RequiresPermissions(Constants.Permission.SYS_RESOURCE_LIST)
    public DataJsonVo<List<Integer>> ids(Integer roleId) {
        return JsonVos.ok(service.listIds(roleId));
    }

    /**
     * 查询所有
     */
    @GetMapping("/list")
    @RequiresPermissions(Constants.Permission.SYS_RESOURCE_LIST)
    public DataJsonVo<List<SysResourceVo>> list() {
        return JsonVos.ok(Streams.map(service.list(), MapStructs.INSTANCE::po2vo));
    }

    /**
     * 查询所有（树状结构结构展示）
     */
    @GetMapping("/listTree")
    @RequiresPermissions(Constants.Permission.SYS_RESOURCE_LIST)
    public DataJsonVo<List<SysResourceTreeVo>> listTree() {
        return JsonVos.ok(service.listTree());
    }

    /**
     * 查询所有的父资源（目录、菜单）
     */
    @GetMapping("/listParents")
    @RequiresPermissions(Constants.Permission.SYS_RESOURCE_LIST)
    public DataJsonVo<List<SysResourceVo>> listParents() {
        return JsonVos.ok(service.listParents());
    }

    @Override
    @RequiresPermissions(value = {
            Constants.Permission.SYS_RESOURCE_ADD,
            Constants.Permission.SYS_RESOURCE_UPDATE
    }, logical = Logical.AND)
    public JsonVo save(SysResourceReqSaveVo sysResourceReqVo) {
        return super.save(sysResourceReqVo);
    }

    @Override
    @RequiresPermissions(Constants.Permission.SYS_RESOURCE_REMOVE)
    public JsonVo remove(String id) {
        return super.remove(id);
    }

    @Override
    protected IService<SysResource> getService() {
        return service;
    }

    @Override
    protected Function<SysResourceReqSaveVo, SysResource> getFunction() {
        return MapStructs.INSTANCE::reqSaveVo2po;
    }
}

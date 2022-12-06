package com.lding.pmbok.controller;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lding.pmbok.common.mapStruct.MapStructs;
import com.lding.pmbok.common.util.Constants;
import com.lding.pmbok.common.util.JsonVos;
import com.lding.pmbok.pojo.po.DictItem;
import com.lding.pmbok.pojo.vo.JsonVo;
import com.lding.pmbok.pojo.vo.ViewJsonVo;
import com.lding.pmbok.pojo.vo.req.save.DictItemReqSaveVo;
import com.lding.pmbok.pojo.vo.req.select.DictItemPageReqVo;
import com.lding.pmbok.pojo.vo.resp.DictItemVo;
import com.lding.pmbok.service.DictItemService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import java.util.function.Function;

@RestController
@RequestMapping("/dictItems")
public class DictItemController extends BaseController<DictItem, DictItemReqSaveVo> {
    @Resource
    private DictItemService service;

    /**
     * 分页查询
     * @param query 分页请求参数
     * @return ViewJsonVo<DictItemVo> 分页数据
     */
    @GetMapping
    @RequiresPermissions(Constants.Permission.DICT_ITEM_LIST)
    public ViewJsonVo<DictItemVo> list(DictItemPageReqVo query) {
        return JsonVos.ok(service.getTargetList(query));
    }

    @Override
    @RequiresPermissions(value = {
            Constants.Permission.DICT_ITEM_ADD,
            Constants.Permission.DICT_ITEM_UPDATE
    }, logical = Logical.AND)
    public JsonVo save(DictItemReqSaveVo reqVo) {
        // 这里的方法重写单纯为了添加权限控制
        return super.save(reqVo);
    }

    @Override
    @RequiresPermissions(Constants.Permission.DICT_ITEM_REMOVE)
    public JsonVo remove(String id) {
        // 这里的方法重写单纯为了添加权限控制
        return super.remove(id);
    }

    @Override
    protected IService<DictItem> getService() {
        return service;
    }

    @Override
    protected Function<DictItemReqSaveVo, DictItem> getFunction() {
        return MapStructs.INSTANCE::reqSaveVo2po;
    }
}

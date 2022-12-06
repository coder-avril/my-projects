package com.lding.pmbok.controller;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lding.pmbok.common.mapStruct.MapStructs;
import com.lding.pmbok.common.util.Constants;
import com.lding.pmbok.common.util.JsonVos;
import com.lding.pmbok.common.util.Streams;
import com.lding.pmbok.pojo.po.DictType;
import com.lding.pmbok.pojo.vo.DataJsonVo;
import com.lding.pmbok.pojo.vo.JsonVo;
import com.lding.pmbok.pojo.vo.ViewJsonVo;
import com.lding.pmbok.pojo.vo.req.save.DictTypeReqSaveVo;
import com.lding.pmbok.pojo.vo.req.select.DictTypePageReqVo;
import com.lding.pmbok.pojo.vo.resp.DictTypeVo;
import com.lding.pmbok.service.DictTypeService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import java.util.List;
import java.util.function.Function;

@RestController
@RequestMapping("/dictTypes")
public class DictTypeController extends BaseController<DictType, DictTypeReqSaveVo> {
    @Resource
    private DictTypeService service;

    /**
     * 分页查询
     * @param reqVo 分页请求参数
     * @return ViewJsonVo<DictItemVo> 分页数据
     */
    @GetMapping
    @RequiresPermissions(Constants.Permission.DICT_TYPE_LIST)
    public ViewJsonVo<DictTypeVo> list(DictTypePageReqVo reqVo) {
        return JsonVos.ok(service.getTargetList(reqVo));
    }

    /**
     * 查询所有
     * @return DataJsonVo<List<DictTypeVo>> 全体数据
     */
    @GetMapping("/list")
    @RequiresPermissions(Constants.Permission.DICT_TYPE_LIST)
    public DataJsonVo<List<DictTypeVo>> list() {
        return JsonVos.ok(Streams.map(service.list(), MapStructs.INSTANCE::po2vo));
    }

    @Override
    @RequiresPermissions(value = {
            Constants.Permission.DICT_TYPE_ADD,
            Constants.Permission.DICT_TYPE_UPDATE
    }, logical = Logical.AND)
    public JsonVo save(DictTypeReqSaveVo dictTypeReqVo) {
        return super.save(dictTypeReqVo);
    }

    @Override
    @RequiresPermissions(Constants.Permission.DICT_TYPE_REMOVE)
    public JsonVo remove(String id) {
        return super.remove(id);
    }

    @Override
    protected IService<DictType> getService() {
        return service;
    }

    @Override
    protected Function<DictTypeReqSaveVo, DictType> getFunction() {
        return MapStructs.INSTANCE::reqSaveVo2po;
    }
}

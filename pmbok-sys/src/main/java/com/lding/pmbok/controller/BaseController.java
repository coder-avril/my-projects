package com.lding.pmbok.controller;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lding.pmbok.common.message.CodeMsg;
import com.lding.pmbok.common.util.JsonVos;
import com.lding.pmbok.pojo.vo.JsonVo;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.Arrays;
import java.util.function.Function;

@Validated
public abstract class BaseController<Po, ReqSaveVo> {
    protected abstract IService<Po> getService();
    protected abstract Function<ReqSaveVo, Po> getFunction();

    // 删除一条或多条数据
    @PostMapping("/remove")
    public JsonVo remove(@NotBlank(message = "id不能为空") @RequestParam String id) {
        if (getService().removeByIds(Arrays.asList(id.split(",")))) {
            return JsonVos.ok(CodeMsg.REMOVE_OK);
        } else {
            return JsonVos.raise(CodeMsg.REMOVE_ERROR);
        }
    }

    // 添加或更新
    @PostMapping("/save")
    public JsonVo save(@Valid ReqSaveVo reqSaveVo) {
        Po po = getFunction().apply(reqSaveVo);
        if (getService().saveOrUpdate(po)) {
            return JsonVos.ok(CodeMsg.SAVE_OK);
        } else {
            return JsonVos.raise(CodeMsg.SAVE_ERROR);
        }
    }
}

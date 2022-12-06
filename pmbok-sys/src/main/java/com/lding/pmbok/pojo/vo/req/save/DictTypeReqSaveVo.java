package com.lding.pmbok.pojo.vo.req.save;

import lombok.Data;
import javax.validation.constraints.NotBlank;

@Data
public class DictTypeReqSaveVo {
    // id【大于0代表更新，否则代表添加】
    private Integer id;

    @NotBlank(message = "名称不能为空")
    // 名称【不能为空】
    private String name;

    @NotBlank(message = "值不能为空")
    // 值【不能为空】
    private String value;

    // 简介
    private String intro;
}

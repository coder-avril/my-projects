package com.lding.pmbok.pojo.vo.req.save;

import com.lding.pmbok.common.validator.BoolNumber;
import lombok.Data;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class DictItemReqSaveVo {
    // id【大于0代表更新，否则代表添加】
    private Integer id;

    // 名称【不能为空】
    @NotBlank(message = "名称不能为空")
    private String name;

    // 值【不能为空】
    @NotBlank(message = "值不能为空")
    private String value;

    // 序号【不能是负数，值越大，就排在越前面，默认0】
    @Min(value = 0, message = "序号不能是负数")
    @Max(value = 9999, message = "序号不能超过四位数")
    private Integer sn;

    // 是否禁用【0代表不禁用（启用），1代表禁用，默认0】
    @BoolNumber(message = "是否禁用只能是0和1")
    private Short disabled;

    // 数据字典类型的id
    @NotNull(message = "类型不能为空")
    private Integer typeId;
}

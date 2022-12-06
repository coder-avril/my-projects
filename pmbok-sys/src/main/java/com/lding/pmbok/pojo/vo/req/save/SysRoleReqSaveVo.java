package com.lding.pmbok.pojo.vo.req.save;

import lombok.Data;
import javax.validation.constraints.NotBlank;

@Data
public class SysRoleReqSaveVo {
    // id【大于0代表更新，否则代表添加】
    private Integer id;

    // "名称【不能为空】"
    @NotBlank(message = "名称不能为空")
    private String name;

    // 资源id【多个id之间用逗号,隔开】
    private String resourceIds;
}

package com.lding.pmbok.pojo.vo.req.save;

import lombok.Data;
import org.hibernate.validator.constraints.Range;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
public class SysResourceReqSaveVo {
    // id【大于0代表更新，否则代表添加】
    private Short id;

    // 名称【不能为空】
    @NotBlank(message = "名称不能为空")
    private String name;

    // 链接
    private String uri;

    // 权限标识
    private String permission;

    // 类型【0是目录，1是菜单，2是按钮】
    @Range(min = 0, max = 2, message = "类型只能是0、1、2")
    private Short type;

    // 图标
    private String icon;

    // 序号【不能是负数，值越大，就排在越前面，默认0】
    @Max(value = 9999, message = "序号不能超过四位数")
    @Min(value = 0, message = "序号不能是负数")
    private Short sn;

    // 父资源id【如果是目录，父资源id为0；如果不是目录，父资源id大于0；默认0】
    private Short parentId;
}

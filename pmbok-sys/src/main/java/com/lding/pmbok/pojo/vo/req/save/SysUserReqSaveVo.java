package com.lding.pmbok.pojo.vo.req.save;

import lombok.Data;
import org.hibernate.validator.constraints.Range;
import javax.validation.constraints.NotBlank;

@Data
public class SysUserReqSaveVo {
    // id【大于0代表更新，否则代表添加】
    private Integer id;

    // 昵称【不能为空】
    @NotBlank(message = "昵称不能为空")
    private String nickname;

    // 用户名【不能为空】
    @NotBlank(message = "用户名不能为空")
    private String username;

    // 密码【如果为空，说明不修改密码】
    private String password;

    // 状态【0是正常，1是锁定，默认0】
    @Range(min = 0, max = 1, message = "状态只能是0和1")
    private Short status;

    // 角色id【多个id之间用逗号,隔开】
    private String roleIds;

    // 备注信息
    private String remark;
}

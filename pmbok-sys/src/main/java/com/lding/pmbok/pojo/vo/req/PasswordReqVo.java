package com.lding.pmbok.pojo.vo.req;

import lombok.Data;
import javax.validation.constraints.NotBlank;

@Data
public class PasswordReqVo {
    // ID
    @NotBlank
    private String id;

    // 新密码
    @NotBlank
    private String newPassword;

    // 旧密码
    @NotBlank
    private String oldPassword;
}

package com.lding.pmbok.pojo.vo.req;
import lombok.Data;
import javax.validation.constraints.NotBlank;

@Data
public class LoginReqVo {
    // 用户名
    @NotBlank
    private String username;

    // 密码
    @NotBlank
    private String password;

    // 验证码
    @NotBlank
    private String captcha;
}

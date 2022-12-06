package com.lding.pmbok.pojo.vo;

import lombok.Data;

/**
 * 面向客户端的登录成功后的结果Vo
 *  登录成功后会保存在用户本地的Token中
 */
@Data
public class LoginVo {
    // id
    private Integer id;

    // 昵称
    private String nickname;

    // 用户名
    private String username;

    // 备考信息
    private String remark;

    // 登录令牌
    private String token;
}

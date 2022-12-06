package com.lding.pmbok.pojo.vo.resp;

import lombok.Data;

/**
 * 系统用户
 */
@Data
public class SysUserVo {
    // id
    private Integer id;

    // 昵称
    private String nickname;

    // 用户名
    private String username;

    // 账号的状态【0是正常，1是锁定】
    private Short status;

    // 最后一次登录的时间
    private Long loginTime;

    // 备注信息
    private String remark;
}

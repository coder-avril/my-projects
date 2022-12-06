package com.lding.pmbok.pojo.vo.resp;

import lombok.Data;

/**
 * 系统资源
 */
@Data
public class SysResourceVo {
    // id
    private Short id;

    // 名称
    private String name;

    // 链接
    private String uri;

    // 权限标识
    private String permission;

    // 类型【0是目录，1是菜单，2是按钮】
    private Short type;

    // 图标
    private String icon;

    // 序号
    private Short sn;

    // 父资源id
    private Short parentId;
}

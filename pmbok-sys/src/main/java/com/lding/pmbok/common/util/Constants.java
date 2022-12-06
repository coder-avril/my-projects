package com.lding.pmbok.common.util;

public class Constants {
    // 页面表格默认的size
    public static final int DEFAULT_SIZE = 10;
    // 表格默认的最小size
    public static final int DEFAULT_MIN_SIZE = 1;

    public static class SysUserStatus {
        public static final int NORMAL = 0;
        public static final int LOCKED = 1;
    }

    public static class SysResourceType {
        public static final int DIR = 0;
        public static final int MENU = 1;
        public static final int BTN = 2;
    }

    /**
     * 权限定义
     */
    public static class Permission {
        /* 用户管理 */
        public static final String SYS_USER_LIST = "sysUser:list";
        public static final String SYS_USER_ADD = "sysUser:add";
        public static final String SYS_USER_UPDATE = "sysUser:update";
        public static final String SYS_USER_REMOVE = "sysUser:remove";

        /* 角色 */
        public static final String SYS_ROLE_LIST = "sysRole:list";
        public static final String SYS_ROLE_ADD = "sysRole:add";
        public static final String SYS_ROLE_UPDATE = "sysRole:update";
        public static final String SYS_ROLE_REMOVE = "sysRole:remove";

        /* 资源 */
        public static final String SYS_RESOURCE_LIST = "sysResource:list";
        public static final String SYS_RESOURCE_ADD = "sysResource:add";
        public static final String SYS_RESOURCE_UPDATE = "sysResource:update";
        public static final String SYS_RESOURCE_REMOVE = "sysResource:remove";

        /* 字典项目 */
        public static final String DICT_ITEM_LIST = "dictItem:list";
        public static final String DICT_ITEM_ADD = "dictItem:add";
        public static final String DICT_ITEM_UPDATE = "dictItem:update";
        public static final String DICT_ITEM_REMOVE = "dictItem:remove";

        /* 字典类型 */
        public static final String DICT_TYPE_LIST = "dictType:list";
        public static final String DICT_TYPE_ADD = "dictType:add";
        public static final String DICT_TYPE_UPDATE = "dictType:update";
        public static final String DICT_TYPE_REMOVE = "dictType:remove";

    }
}

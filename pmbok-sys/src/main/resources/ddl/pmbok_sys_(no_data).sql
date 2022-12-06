/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 80028
 Source Host           : localhost:3306
 Source Schema         : pmbok_sys

 Target Server Type    : MySQL
 Target Server Version : 80028
 File Encoding         : 65001

 Date: 06/12/2022 16:56:03
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for dict_item
-- ----------------------------
DROP TABLE IF EXISTS `dict_item`;
CREATE TABLE `dict_item`  (
  `id` smallint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '名称',
  `value` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '值',
  `sn` smallint UNSIGNED NOT NULL DEFAULT 0 COMMENT '排列顺序，默认0。值越大，就排在越前面',
  `disabled` tinyint UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否禁用。0代表不禁用（启用），1代表禁用',
  `type_id` smallint UNSIGNED NOT NULL DEFAULT 0 COMMENT '所属的类型',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `dict_item_name_type_id_uindex`(`name`, `type_id`) USING BTREE,
  UNIQUE INDEX `dict_item_value_type_id_uindex`(`value`, `type_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '数据字典-项目' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for dict_type
-- ----------------------------
DROP TABLE IF EXISTS `dict_type`;
CREATE TABLE `dict_type`  (
  `id` smallint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '名称',
  `value` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '值',
  `intro` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '简介',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `dict_type_name_uindex`(`name`) USING BTREE,
  UNIQUE INDEX `dict_type_value_uindex`(`value`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 141 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '数据字典-类型' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for sys_resource
-- ----------------------------
DROP TABLE IF EXISTS `sys_resource`;
CREATE TABLE `sys_resource`  (
  `id` tinyint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '名称',
  `uri` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '链接地址',
  `permission` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '权限标识',
  `type` tinyint UNSIGNED NOT NULL DEFAULT 0 COMMENT '资源类型（0是目录，1是菜单，2是目录）',
  `icon` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '图标',
  `sn` tinyint UNSIGNED NOT NULL DEFAULT 0 COMMENT '序号',
  `parent_id` tinyint UNSIGNED NOT NULL DEFAULT 0 COMMENT '父资源id',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `sys_resource_parent_id_name_uindex`(`parent_id`, `name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 57 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '资源' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` tinyint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '角色名称',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `sys_role_name_uindex`(`name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for sys_role_resource
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_resource`;
CREATE TABLE `sys_role_resource`  (
  `role_id` tinyint UNSIGNED NOT NULL DEFAULT 0 COMMENT '角色id',
  `resource_id` tinyint UNSIGNED NOT NULL DEFAULT 0 COMMENT '资源id',
  PRIMARY KEY (`resource_id`, `role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色-资源' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` smallint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `nickname` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `username` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '登录用的用户名',
  `password` char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '登录用的密码',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建的时间',
  `login_time` datetime NULL DEFAULT NULL COMMENT '最后一次登录的时间',
  `status` tinyint UNSIGNED NOT NULL DEFAULT 0 COMMENT '账号的状态，0是正常，1是锁定',
  `remark` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `sys_user_username_uindex`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户（可以登录后台系统的）' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `role_id` tinyint UNSIGNED NOT NULL DEFAULT 0 COMMENT '角色id',
  `user_id` smallint UNSIGNED NOT NULL DEFAULT 0 COMMENT '用户id',
  PRIMARY KEY (`user_id`, `role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户-角色' ROW_FORMAT = DYNAMIC;

SET FOREIGN_KEY_CHECKS = 1;

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

 Date: 06/12/2022 16:55:13
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
-- Records of dict_item
-- ----------------------------
INSERT INTO `dict_item` VALUES (1, '程序员', 'coder', 8, 1, 1);
INSERT INTO `dict_item` VALUES (2, '教师', 'teacher', 0, 0, 1);
INSERT INTO `dict_item` VALUES (3, '司机', 'driver', 6, 1, 1);
INSERT INTO `dict_item` VALUES (5, '学员', 'student', 666, 0, 97);
INSERT INTO `dict_item` VALUES (8, '科目2', '2', 0, 0, 115);
INSERT INTO `dict_item` VALUES (9, '科目3', '3', 0, 0, 115);
INSERT INTO `dict_item` VALUES (16, 'gaga', 'sss', 0, 0, 13);
INSERT INTO `dict_item` VALUES (17, 'gdfgdfg', 'dff', 0, 1, 13);

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
-- Records of dict_type
-- ----------------------------
INSERT INTO `dict_type` VALUES (1, '职业', 'job', '一份工作');
INSERT INTO `dict_type` VALUES (13, '哈哈12', 'haha12', '12');
INSERT INTO `dict_type` VALUES (14, '哈哈13', 'haha13', '13');
INSERT INTO `dict_type` VALUES (15, '哈哈14', 'haha14', '14');
INSERT INTO `dict_type` VALUES (16, '哈哈15', 'haha15', '15');
INSERT INTO `dict_type` VALUES (17, '哈哈16', 'haha16', '16');
INSERT INTO `dict_type` VALUES (18, '哈哈17', 'haha17', '17');
INSERT INTO `dict_type` VALUES (19, '哈哈18', 'haha18', '18');
INSERT INTO `dict_type` VALUES (20, '哈哈19', 'haha19', '19');
INSERT INTO `dict_type` VALUES (21, '哈哈20', 'haha20', '20');
INSERT INTO `dict_type` VALUES (22, '哈哈21', 'haha21', '21');
INSERT INTO `dict_type` VALUES (23, '哈哈22', 'haha22', '22');
INSERT INTO `dict_type` VALUES (24, '哈哈23', 'haha23', '23');
INSERT INTO `dict_type` VALUES (25, '哈哈24', 'haha24', '24');
INSERT INTO `dict_type` VALUES (26, '哈哈25', 'haha25', '25');
INSERT INTO `dict_type` VALUES (27, '哈哈26', 'haha26', '26');
INSERT INTO `dict_type` VALUES (28, '哈哈27', 'haha27', '27');
INSERT INTO `dict_type` VALUES (29, '哈哈28', 'haha28', '28');
INSERT INTO `dict_type` VALUES (30, '哈哈29', 'haha29', '29');
INSERT INTO `dict_type` VALUES (31, '哈哈30', 'haha30', '30');
INSERT INTO `dict_type` VALUES (32, '哈哈31', 'haha31', '31');
INSERT INTO `dict_type` VALUES (33, '哈哈32', 'haha32', '32');
INSERT INTO `dict_type` VALUES (34, '哈哈33', 'haha33', '33');
INSERT INTO `dict_type` VALUES (35, '哈哈34', 'haha34', '34');
INSERT INTO `dict_type` VALUES (36, '哈哈35', 'haha35', '35');
INSERT INTO `dict_type` VALUES (37, '哈哈36', 'haha36', '36');
INSERT INTO `dict_type` VALUES (38, '哈哈37', 'haha37', '37');
INSERT INTO `dict_type` VALUES (39, '哈哈38', 'haha38', '38');
INSERT INTO `dict_type` VALUES (40, '哈哈39', 'haha39', '39');
INSERT INTO `dict_type` VALUES (41, '哈哈40', 'haha40', '40');
INSERT INTO `dict_type` VALUES (42, '哈哈41', 'haha41', '41');
INSERT INTO `dict_type` VALUES (43, '哈哈42', 'haha42', '42');
INSERT INTO `dict_type` VALUES (44, '哈哈43', 'haha43', '43');
INSERT INTO `dict_type` VALUES (45, '哈哈44', 'haha44', '44');
INSERT INTO `dict_type` VALUES (46, '哈哈45', 'haha45', '45');
INSERT INTO `dict_type` VALUES (47, '哈哈46', 'haha46', '46');
INSERT INTO `dict_type` VALUES (48, '哈哈47', 'haha47', '47');
INSERT INTO `dict_type` VALUES (49, '哈哈48', 'haha48', '48');
INSERT INTO `dict_type` VALUES (50, '哈哈49', 'haha49', '49');
INSERT INTO `dict_type` VALUES (51, '哈哈50', 'haha50', '50');
INSERT INTO `dict_type` VALUES (52, '哈哈51', 'haha51', '51');
INSERT INTO `dict_type` VALUES (53, '哈哈52', 'haha52', '52');
INSERT INTO `dict_type` VALUES (54, '哈哈53', 'haha53', '53');
INSERT INTO `dict_type` VALUES (55, '哈哈54', 'haha54', '54');
INSERT INTO `dict_type` VALUES (56, '哈哈55', 'haha55', '55');
INSERT INTO `dict_type` VALUES (57, '哈哈56', 'haha56', '56');
INSERT INTO `dict_type` VALUES (58, '哈哈57', 'haha57', '57');
INSERT INTO `dict_type` VALUES (59, '哈哈58', 'haha58', '58');
INSERT INTO `dict_type` VALUES (60, '哈哈59', 'haha59', '59');
INSERT INTO `dict_type` VALUES (61, '哈哈60', 'haha60', '60');
INSERT INTO `dict_type` VALUES (62, '哈哈61', 'haha61', '61');
INSERT INTO `dict_type` VALUES (63, '哈哈62', 'haha62', '62');
INSERT INTO `dict_type` VALUES (64, '哈哈63', 'haha63', '63');
INSERT INTO `dict_type` VALUES (65, '哈哈64', 'haha64', '64');
INSERT INTO `dict_type` VALUES (66, '哈哈65', 'haha65', '65');
INSERT INTO `dict_type` VALUES (67, '哈哈66', 'haha66', '66');
INSERT INTO `dict_type` VALUES (68, '哈哈67', 'haha67', '67');
INSERT INTO `dict_type` VALUES (69, '哈哈68', '756567657', '68');
INSERT INTO `dict_type` VALUES (70, '哈哈69', 'haha69', '69');
INSERT INTO `dict_type` VALUES (71, '哈哈70', 'haha70', '70');
INSERT INTO `dict_type` VALUES (72, '哈哈71', 'haha71', '71');
INSERT INTO `dict_type` VALUES (73, '哈哈72', 'haha72', '72');
INSERT INTO `dict_type` VALUES (74, '哈哈73', 'haha73', '73');
INSERT INTO `dict_type` VALUES (75, '哈哈74', 'haha74', '74');
INSERT INTO `dict_type` VALUES (76, '哈哈75', 'haha75', '75');
INSERT INTO `dict_type` VALUES (77, '哈哈76', 'haha76', '76');
INSERT INTO `dict_type` VALUES (78, '哈哈77', 'haha77', '77');
INSERT INTO `dict_type` VALUES (84, '哈哈83', 'haha83', '83');
INSERT INTO `dict_type` VALUES (85, '哈哈84', 'haha84', '84');
INSERT INTO `dict_type` VALUES (86, '哈哈85', 'haha85', '85');
INSERT INTO `dict_type` VALUES (87, '哈哈86', 'haha86', '86');
INSERT INTO `dict_type` VALUES (88, '哈哈87', 'haha87', '87');
INSERT INTO `dict_type` VALUES (89, '哈哈88', 'haha88', '88');
INSERT INTO `dict_type` VALUES (90, '哈哈89', 'haha89', '89');
INSERT INTO `dict_type` VALUES (91, '哈哈90', 'haha90', '90');
INSERT INTO `dict_type` VALUES (92, '哈哈91', 'haha91', '91');
INSERT INTO `dict_type` VALUES (93, '哈哈92', 'haha92', '92');
INSERT INTO `dict_type` VALUES (94, '哈哈93', 'haha93', '93');
INSERT INTO `dict_type` VALUES (95, '哈哈94', 'haha94', '94');
INSERT INTO `dict_type` VALUES (96, '哈哈95', 'haha95', '95');
INSERT INTO `dict_type` VALUES (97, '哈哈96', 'haha96', '96');
INSERT INTO `dict_type` VALUES (115, '科2科3课程类型', 'course_type', '科2科3课');
INSERT INTO `dict_type` VALUES (127, 'kan', 'k', 'dddddsfds');
INSERT INTO `dict_type` VALUES (139, 'kk', 'kkkk', 'kkkkkkkkkk');

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
-- Records of sys_resource
-- ----------------------------
INSERT INTO `sys_resource` VALUES (1, '用户管理', '', '', 0, 'fa fa-gears', 0, 0);
INSERT INTO `sys_resource` VALUES (2, '用户', 'page/sys/user/list.html', '', 1, 'fa fa-user-circle', 0, 1);
INSERT INTO `sys_resource` VALUES (3, '查询', '', 'sysUser:list', 2, '', 0, 2);
INSERT INTO `sys_resource` VALUES (4, '添加', '', 'sysUser:add', 2, '', 0, 2);
INSERT INTO `sys_resource` VALUES (5, '修改', '', 'sysUser:update', 2, '', 0, 2);
INSERT INTO `sys_resource` VALUES (6, '删除', '', 'sysUser:remove', 2, '', 0, 2);
INSERT INTO `sys_resource` VALUES (7, '角色', 'page/sys/role/list.html', '', 1, 'fa fa-user', 1, 1);
INSERT INTO `sys_resource` VALUES (8, '查询', '', 'sysRole:list', 2, '', 0, 7);
INSERT INTO `sys_resource` VALUES (9, '添加', '', 'sysRole:add', 2, '', 0, 7);
INSERT INTO `sys_resource` VALUES (10, '修改', '', 'sysRole:update', 2, '', 0, 7);
INSERT INTO `sys_resource` VALUES (11, '删除', '', 'sysRole:remove', 2, '', 0, 7);
INSERT INTO `sys_resource` VALUES (12, '资源', 'page/sys/resource/list.html', '', 1, 'fa fa-key', 2, 1);
INSERT INTO `sys_resource` VALUES (13, '查询', '', 'sysResource:list', 2, '', 0, 12);
INSERT INTO `sys_resource` VALUES (14, '添加', '', 'sysResource:add', 2, '', 0, 12);
INSERT INTO `sys_resource` VALUES (15, '修改', '', 'sysResource:update', 2, '', 0, 12);
INSERT INTO `sys_resource` VALUES (16, '删除', '', 'sysResource:remove', 2, '', 0, 12);
INSERT INTO `sys_resource` VALUES (17, '数据字典', '', '', 0, 'fa fa-newspaper-o', 1, 0);
INSERT INTO `sys_resource` VALUES (18, '类型', 'page/metadata/dictType/list.html', '', 1, 'fa fa-cube', 0, 17);
INSERT INTO `sys_resource` VALUES (19, '查询', '', 'dictType:list', 2, '', 0, 18);
INSERT INTO `sys_resource` VALUES (20, '添加', '', 'dictType:add', 2, '', 0, 18);
INSERT INTO `sys_resource` VALUES (21, '修改', '', 'dictType:update', 2, '', 0, 18);
INSERT INTO `sys_resource` VALUES (22, '删除', '', 'dictType:remove', 2, '', 0, 18);
INSERT INTO `sys_resource` VALUES (23, '条目', 'page/metadata/dictItem/list.html', '', 1, 'fa fa-cubes', 1, 17);
INSERT INTO `sys_resource` VALUES (24, '查询', '', 'dictItem:list', 2, '', 0, 23);
INSERT INTO `sys_resource` VALUES (25, '添加', '', 'dictItem:add', 2, '', 0, 23);
INSERT INTO `sys_resource` VALUES (26, '修改', '', 'dictItem:update', 2, '', 0, 23);
INSERT INTO `sys_resource` VALUES (27, '删除', '', 'dictItem:remove', 2, '', 0, 23);

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
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, 'GM');
INSERT INTO `sys_role` VALUES (4, '客服');
INSERT INTO `sys_role` VALUES (2, '销售经理');

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
-- Records of sys_role_resource
-- ----------------------------
INSERT INTO `sys_role_resource` VALUES (1, 3);
INSERT INTO `sys_role_resource` VALUES (2, 3);
INSERT INTO `sys_role_resource` VALUES (4, 3);
INSERT INTO `sys_role_resource` VALUES (1, 4);
INSERT INTO `sys_role_resource` VALUES (2, 4);
INSERT INTO `sys_role_resource` VALUES (1, 5);
INSERT INTO `sys_role_resource` VALUES (2, 5);
INSERT INTO `sys_role_resource` VALUES (1, 6);
INSERT INTO `sys_role_resource` VALUES (1, 8);
INSERT INTO `sys_role_resource` VALUES (2, 8);
INSERT INTO `sys_role_resource` VALUES (4, 8);
INSERT INTO `sys_role_resource` VALUES (1, 9);
INSERT INTO `sys_role_resource` VALUES (2, 9);
INSERT INTO `sys_role_resource` VALUES (1, 10);
INSERT INTO `sys_role_resource` VALUES (2, 10);
INSERT INTO `sys_role_resource` VALUES (1, 11);
INSERT INTO `sys_role_resource` VALUES (1, 13);
INSERT INTO `sys_role_resource` VALUES (2, 13);
INSERT INTO `sys_role_resource` VALUES (4, 13);
INSERT INTO `sys_role_resource` VALUES (1, 14);
INSERT INTO `sys_role_resource` VALUES (2, 14);
INSERT INTO `sys_role_resource` VALUES (1, 15);
INSERT INTO `sys_role_resource` VALUES (2, 15);
INSERT INTO `sys_role_resource` VALUES (1, 16);
INSERT INTO `sys_role_resource` VALUES (1, 19);
INSERT INTO `sys_role_resource` VALUES (2, 19);
INSERT INTO `sys_role_resource` VALUES (4, 19);
INSERT INTO `sys_role_resource` VALUES (1, 20);
INSERT INTO `sys_role_resource` VALUES (2, 20);
INSERT INTO `sys_role_resource` VALUES (1, 21);
INSERT INTO `sys_role_resource` VALUES (2, 21);
INSERT INTO `sys_role_resource` VALUES (1, 22);
INSERT INTO `sys_role_resource` VALUES (1, 24);
INSERT INTO `sys_role_resource` VALUES (2, 24);
INSERT INTO `sys_role_resource` VALUES (4, 24);
INSERT INTO `sys_role_resource` VALUES (1, 25);
INSERT INTO `sys_role_resource` VALUES (2, 25);
INSERT INTO `sys_role_resource` VALUES (1, 26);
INSERT INTO `sys_role_resource` VALUES (2, 26);
INSERT INTO `sys_role_resource` VALUES (1, 27);

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
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (7, '雷', 'admin', 'e10adc3949ba59abbe56e057f20f883e', '2022-05-03 01:25:03', '2022-12-06 16:51:07', 0, '牛2');
INSERT INTO `sys_user` VALUES (19, '猪二渣', 'lin', '0276cbf4fbfedd53ba48720a58df77e3', '2022-12-05 20:40:30', '2022-12-05 21:03:22', 0, '你好');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `role_id` tinyint UNSIGNED NOT NULL DEFAULT 0 COMMENT '角色id',
  `user_id` smallint UNSIGNED NOT NULL DEFAULT 0 COMMENT '用户id',
  PRIMARY KEY (`user_id`, `role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户-角色' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (1, 7);
INSERT INTO `sys_user_role` VALUES (1, 9);
INSERT INTO `sys_user_role` VALUES (4, 10);
INSERT INTO `sys_user_role` VALUES (4, 17);
INSERT INTO `sys_user_role` VALUES (4, 19);

SET FOREIGN_KEY_CHECKS = 1;

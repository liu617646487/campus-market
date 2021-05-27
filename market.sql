/*
 Navicat Premium Data Transfer

 Source Server         : MySQL
 Source Server Type    : MySQL
 Source Server Version : 50724
 Source Host           : localhost:3306
 Source Schema         : market

 Target Server Type    : MySQL
 Target Server Version : 50724
 File Encoding         : 65001

 Date: 27/05/2021 17:20:07
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin_database_bak
-- ----------------------------
DROP TABLE IF EXISTS `admin_database_bak`;
CREATE TABLE `admin_database_bak`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '更新时间',
  `filename` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文件名',
  `filepath` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文件路径',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 47 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin_database_bak
-- ----------------------------
INSERT INTO `admin_database_bak` VALUES (46, '2021-05-21 23:17:50', '2021-05-21 23:17:50', 'market_20210521231749.sql', 'E:/market/backup/');

-- ----------------------------
-- Table structure for admin_menu
-- ----------------------------
DROP TABLE IF EXISTS `admin_menu`;
CREATE TABLE `admin_menu`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '更新时间',
  `icon` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '图标',
  `name` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '菜单名称',
  `sort` int(11) NOT NULL COMMENT '排序',
  `url` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单url',
  `parent_id` bigint(20) NULL DEFAULT NULL COMMENT '父菜单id',
  `is_button` bit(1) NOT NULL COMMENT '是否按钮 1-是；0-否',
  `is_show` bit(1) NOT NULL COMMENT '是否显示 1-是；0-否',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FKck0mctr73qlq7wigmed2qbtxv`(`parent_id`) USING BTREE,
  CONSTRAINT `FKck0mctr73qlq7wigmed2qbtxv` FOREIGN KEY (`parent_id`) REFERENCES `admin_menu` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 55 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin_menu
-- ----------------------------
INSERT INTO `admin_menu` VALUES (1, '2021-01-07 21:18:53', '2021-01-07 22:03:03', 'mdi-settings', '系统设置', 1, '', NULL, b'0', b'1');
INSERT INTO `admin_menu` VALUES (2, '2021-01-07 21:19:06', '2021-01-08 22:05:27', 'mdi-view-list', '菜单管理', 2, '/admin/menu/list', 1, b'0', b'1');
INSERT INTO `admin_menu` VALUES (3, '2021-01-07 21:19:20', '2021-01-13 10:49:56', 'mdi-plus', '新增', 3, '/admin/menu/add', 2, b'0', b'1');
INSERT INTO `admin_menu` VALUES (4, '2021-01-07 21:19:37', '2021-01-13 11:16:15', 'mdi-pencil', '编辑', 5, 'edit(\'/admin/menu/edit\')', 2, b'1', b'1');
INSERT INTO `admin_menu` VALUES (5, '2021-01-07 21:22:59', '2021-01-13 11:17:43', 'mdi-close', '删除', 4, 'del(\'/admin/menu/delete\')', 2, b'1', b'1');
INSERT INTO `admin_menu` VALUES (8, '2021-01-08 22:05:14', '2021-01-08 22:07:55', 'mdi-account-settings-variant', '角色管理', 6, '/admin/role/list', 1, b'0', b'1');
INSERT INTO `admin_menu` VALUES (9, '2021-01-08 22:06:54', '2021-01-13 10:50:04', 'mdi-account-plus', '新增', 7, '/admin/role/add', 8, b'0', b'1');
INSERT INTO `admin_menu` VALUES (10, '2021-01-08 22:07:42', '2021-01-13 11:32:47', 'mdi-account-edit', '编辑', 8, 'edit(\'/admin/role/edit\')', 8, b'1', b'1');
INSERT INTO `admin_menu` VALUES (11, '2021-01-08 22:08:38', '2021-01-13 11:33:08', 'mdi-account-remove', '删除', 10, 'del(\'/admin/role/delete\')', 8, b'1', b'1');
INSERT INTO `admin_menu` VALUES (12, '2021-01-10 16:56:05', '2021-01-13 09:40:32', 'mdi-account-multiple', '用户管理', 0, '/admin/user/list', 1, b'0', b'1');
INSERT INTO `admin_menu` VALUES (13, '2021-01-10 16:58:02', '2021-01-13 10:50:20', 'mdi-account-plus', '新增', 0, '/admin/user/add', 12, b'0', b'1');
INSERT INTO `admin_menu` VALUES (14, '2021-01-10 16:59:00', '2021-01-13 11:33:29', 'mdi-account-edit', '编辑', 0, 'edit(\'/admin/user/edit\')', 12, b'1', b'1');
INSERT INTO `admin_menu` VALUES (15, '2021-01-12 15:26:03', '2021-01-13 11:37:49', 'mdi-account-minus', '删除', 0, 'del(\'/admin/user/delete\')', 12, b'1', b'1');
INSERT INTO `admin_menu` VALUES (23, '2021-01-13 17:17:49', '2021-01-13 17:17:49', 'mdi-arrow-up-bold-circle', '上传图片', 0, '/upload/upload_photo', 12, b'0', b'0');
INSERT INTO `admin_menu` VALUES (25, '2021-01-14 09:30:30', '2021-01-14 09:48:23', 'mdi-tag', '日志管理', 0, '/admin/operator_log/list', 1, b'0', b'1');
INSERT INTO `admin_menu` VALUES (26, '2021-01-14 09:34:08', '2021-01-14 09:34:08', 'mdi-tag-remove', '删除', 0, 'del(\'/admin/operator_log/delete\')', 25, b'1', b'1');
INSERT INTO `admin_menu` VALUES (27, '2021-01-14 09:35:31', '2021-01-14 10:27:48', 'mdi-delete-circle', '清空日志', 0, 'delAll(\'/admin/operator_log/deleteAll\')', 25, b'1', b'1');
INSERT INTO `admin_menu` VALUES (28, '2021-01-14 11:38:05', '2021-01-14 11:40:24', 'mdi-database', '数据备份', 0, '/admin/database_bak/list', 1, b'0', b'1');
INSERT INTO `admin_menu` VALUES (29, '2021-01-14 11:41:10', '2021-01-14 14:43:10', 'mdi-database-plus', '备份', 0, 'add(\'/admin/database_bak/add\')', 28, b'1', b'1');
INSERT INTO `admin_menu` VALUES (30, '2021-01-14 11:42:04', '2021-01-14 11:42:04', 'mdi-database-minus', '删除', 0, 'del(\'/admin/database_bak/delete\')', 28, b'1', b'1');
INSERT INTO `admin_menu` VALUES (32, '2021-01-14 22:03:48', '2021-01-14 22:04:53', 'mdi-set-all', '还原', 0, 'restore(\'/admin/database_bak/restore\')', 28, b'1', b'1');
INSERT INTO `admin_menu` VALUES (33, '2021-01-22 19:30:39', '2021-01-22 19:30:39', 'mdi-drawing', '物品管理', 0, '', NULL, b'0', b'1');
INSERT INTO `admin_menu` VALUES (34, '2021-01-22 19:36:23', '2021-01-22 20:03:24', 'mdi-apps', '分类管理', 0, '/admin/goods_category/list', 33, b'0', b'1');
INSERT INTO `admin_menu` VALUES (35, '2021-01-22 19:40:36', '2021-01-22 20:03:35', 'mdi-guitar-pick', '添加', 0, '/admin/goods_category/add', 34, b'0', b'1');
INSERT INTO `admin_menu` VALUES (36, '2021-01-22 19:42:45', '2021-01-22 20:03:43', 'mdi-bookmark-plus-outline', '编辑', 0, 'edit(\'/admin/goods_category/edit\')', 34, b'1', b'1');
INSERT INTO `admin_menu` VALUES (37, '2021-01-22 19:44:36', '2021-01-22 20:03:51', 'mdi-backspace', '删除', 0, 'del(\'/admin/goods_category/delete\')', 34, b'1', b'1');
INSERT INTO `admin_menu` VALUES (38, '2021-01-22 19:47:11', '2021-01-22 20:04:01', 'mdi-blur', '物品管理', 0, '/admin/goods/list', 33, b'0', b'1');
INSERT INTO `admin_menu` VALUES (39, '2021-03-04 20:38:14', '2021-03-04 20:38:14', 'mdi-menu-up', '上架', 0, 'up(\'/admin/goods/up_down\')', 38, b'1', b'1');
INSERT INTO `admin_menu` VALUES (40, '2021-03-04 20:48:31', '2021-03-04 20:48:31', 'mdi-menu-down', '下架', 0, 'down(\'/admin/goods/up_down\')', 38, b'1', b'1');
INSERT INTO `admin_menu` VALUES (41, '2021-03-04 20:54:48', '2021-03-04 20:54:48', 'mdi-close', '删除', 0, 'del(\'/admin/goods/delete\')', 38, b'1', b'1');
INSERT INTO `admin_menu` VALUES (42, '2021-03-08 19:56:02', '2021-03-08 19:56:02', 'mdi-checkbox-blank-circle', '推荐', 0, 'recommend(\'/admin/goods/recommend\')', 38, b'1', b'1');
INSERT INTO `admin_menu` VALUES (43, '2021-03-08 19:56:38', '2021-03-08 19:56:38', 'mdi-circle-outline', '取消推荐', 0, 'unrecommend(\'/admin/goods/recommend\')', 38, b'1', b'1');
INSERT INTO `admin_menu` VALUES (44, '2021-03-08 21:30:32', '2021-03-08 21:30:32', 'mdi-gesture', '求购物品管理', 0, '/admin/wanted_goods/list', 33, b'0', b'1');
INSERT INTO `admin_menu` VALUES (45, '2021-03-08 21:33:47', '2021-03-08 21:33:47', 'mdi-humble-bundle', '删除', 0, 'del(\'/admin/wanted_goods/delete\')', 44, b'1', b'1');
INSERT INTO `admin_menu` VALUES (46, '2021-03-09 20:11:45', '2021-03-09 20:13:47', 'mdi-buffer', '学生管理', 0, '', NULL, b'0', b'1');
INSERT INTO `admin_menu` VALUES (47, '2021-03-09 20:13:31', '2021-03-09 20:13:31', 'mdi-account-circle', '学生列表', 0, '/admin/student/list', 46, b'0', b'1');
INSERT INTO `admin_menu` VALUES (48, '2021-03-09 20:18:39', '2021-03-09 20:44:22', 'mdi-account-settings-variant', '冻结', 0, 'freeze(\'/admin/student/update_status\')', 47, b'1', b'1');
INSERT INTO `admin_menu` VALUES (49, '2021-03-09 20:21:18', '2021-03-09 20:44:32', 'mdi-account-key', '启用', 0, 'open(\'/admin/student/update_status\')', 47, b'1', b'1');
INSERT INTO `admin_menu` VALUES (50, '2021-03-09 20:21:51', '2021-03-09 20:21:51', 'mdi-account-minus', '删除', 0, 'del(\'/admin/student/delete\')', 47, b'1', b'1');
INSERT INTO `admin_menu` VALUES (51, '2021-03-10 20:18:59', '2021-03-10 20:18:59', 'mdi-ghost', '举报管理', 0, '', NULL, b'0', b'1');
INSERT INTO `admin_menu` VALUES (52, '2021-03-10 20:19:45', '2021-03-10 20:19:45', 'mdi-page-layout-footer', '举报列表', 0, '/admin/report_goods/list', 51, b'0', b'1');
INSERT INTO `admin_menu` VALUES (54, '2021-03-10 20:23:29', '2021-03-10 20:23:29', 'mdi-ethernet-cable-off', '删除', 0, 'del(\'/admin/report_goods/delete\')', 52, b'1', b'1');

-- ----------------------------
-- Table structure for admin_operator_log
-- ----------------------------
DROP TABLE IF EXISTS `admin_operator_log`;
CREATE TABLE `admin_operator_log`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '更新时间',
  `content` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '日志内容',
  `operator` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '操作者',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 510 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin_operator_log
-- ----------------------------
INSERT INTO `admin_operator_log` VALUES (323, '2021-01-14 15:33:13', '2021-01-14 15:33:13', '编辑一个用户，用户名：织梦行云', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (324, '2021-01-14 15:33:36', '2021-01-14 15:33:36', '编辑一个用户，用户名：admin', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (325, '2021-01-14 15:33:47', '2021-01-14 15:33:47', '编辑一个用户，用户名：test01', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (326, '2021-01-14 15:35:48', '2021-01-14 15:35:48', '用户【织梦行云】于【2021-01-14 15:35:48】登录系统！', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (327, '2021-01-14 15:36:21', '2021-01-14 15:36:21', '编辑一个用户，用户名：admin', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (328, '2021-01-14 20:26:29', '2021-01-14 20:26:29', '用户【织梦行云】于【2021-01-14 20:26:29】登录系统！', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (329, '2021-01-14 20:51:09', '2021-01-14 20:51:09', '数据库备份成功,备份文件信息DatabaseBak{filename=\'base_20210114.sql\', filepath=\'E:/base/backup/\'}', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (330, '2021-01-14 21:00:01', '2021-01-14 21:00:01', '数据库备份成功,备份文件信息DatabaseBak{filename=\'base_20210114210000.sql\', filepath=\'E:/base/backup/\'}', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (331, '2021-01-14 21:00:06', '2021-01-14 21:00:06', '数据库备份成功,备份文件信息DatabaseBak{filename=\'base_20210114210006.sql\', filepath=\'E:/base/backup/\'}', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (332, '2021-01-14 21:27:47', '2021-01-14 21:27:47', '用户【织梦行云】于【2021-01-14 21:27:46】登录系统！', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (333, '2021-01-14 21:33:45', '2021-01-14 21:33:45', '数据库备份成功,备份文件信息DatabaseBak{filename=\'base_20210114213344.sql\', filepath=\'E:/base/backup/\'}', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (334, '2021-01-14 21:33:47', '2021-01-14 21:33:47', '数据库备份成功,备份文件信息DatabaseBak{filename=\'base_20210114213347.sql\', filepath=\'E:/base/backup/\'}', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (335, '2021-01-14 21:33:49', '2021-01-14 21:33:49', '数据库备份成功,备份文件信息DatabaseBak{filename=\'base_20210114213348.sql\', filepath=\'E:/base/backup/\'}', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (336, '2021-01-14 21:54:03', '2021-01-14 21:54:03', '数据库备份成功,备份文件信息DatabaseBak{filename=\'base_20210114215403.sql\', filepath=\'E:/base/backup/\'}', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (337, '2021-01-14 22:03:48', '2021-01-14 22:03:48', '添加一个菜单【Menu{name=\'还原\', parent=Menu{name=\'数据备份\', parent=Menu{name=\'系统设置\', parent=null, url=\'\', icon=\'mdi-settings\', sort=1, isbutton=false, isshow=true}, url=\'/admin/database_bak/list\', icon=\'mdi-database\', sort=0, isbutton=false, isshow=true}, url=\'restor(\'/admin/database_bak/restor\')\', icon=\'mdi-set-all\', sort=0, isbutton=true, isshow=true}】', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (338, '2021-01-14 22:04:53', '2021-01-14 22:04:53', '修改一个菜单【Menu{name=\'还原\', parent=Menu{name=\'数据备份\', parent=Menu{name=\'系统设置\', parent=null, url=\'\', icon=\'mdi-settings\', sort=1, isbutton=false, isshow=true}, url=\'/admin/database_bak/list\', icon=\'mdi-database\', sort=0, isbutton=false, isshow=true}, url=\'restore(\'/admin/database_bak/restore\')\', icon=\'mdi-set-all\', sort=0, isbutton=true, isshow=true}】', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (339, '2021-01-14 22:09:08', '2021-01-14 22:09:08', '用户【织梦行云】于【2021-01-14 22:09:07】登录系统！', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (340, '2021-01-14 22:09:23', '2021-01-14 22:09:23', '数据库备份成功,备份文件信息DatabaseBak{filename=\'base_20210114220922.sql\', filepath=\'E:/base/backup/\'}', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (341, '2021-01-14 22:09:34', '2021-01-14 22:09:34', '数据库备份成功,备份文件信息DatabaseBak{filename=\'base_20210114220934.sql\', filepath=\'E:/base/backup/\'}', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (342, '2021-01-14 22:09:36', '2021-01-14 22:09:36', '数据库备份成功,备份文件信息DatabaseBak{filename=\'base_20210114220935.sql\', filepath=\'E:/base/backup/\'}', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (343, '2021-01-14 22:09:45', '2021-01-14 22:09:45', '数据库备份成功,备份文件信息DatabaseBak{filename=\'base_20210114220944.sql\', filepath=\'E:/base/backup/\'}', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (344, '2021-01-14 22:10:07', '2021-01-14 22:10:07', '修改一个角色【超管】', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (345, '2021-01-14 22:10:16', '2021-01-14 22:10:16', '用户【织梦行云】于【2021-01-14 22:10:16】登录系统！', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (346, '2021-01-14 22:13:01', '2021-01-14 22:13:01', '数据库备份成功,备份文件信息DatabaseBak{filename=\'base_20210114221300.sql\', filepath=\'E:/base/backup/\'}', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (347, '2021-01-14 22:13:05', '2021-01-14 22:13:05', '数据库备份成功,备份文件信息DatabaseBak{filename=\'base_20210114221305.sql\', filepath=\'E:/base/backup/\'}', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (348, '2021-01-14 22:23:21', '2021-01-14 22:23:21', '数据库备份成功,备份文件信息DatabaseBak{filename=\'base_20210114222320.sql\', filepath=\'E:/base/backup/\'}', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (349, '2021-01-14 22:24:56', '2021-01-14 22:24:56', '数据库还原成功,还原文件信息DatabaseBak{filename=\'base_20210114222320.sql\', filepath=\'E:/base/backup/\'}', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (350, '2021-01-15 08:57:34', '2021-01-15 08:57:34', '用户【织梦行云】于【2021-01-15 08:57:34】登录系统！', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (351, '2021-01-15 08:57:45', '2021-01-15 08:57:45', '数据库备份成功,备份文件信息DatabaseBak{filename=\'base_20210115085745.sql\', filepath=\'E:/base/backup/\'}', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (352, '2021-01-15 14:12:45', '2021-01-15 14:12:45', '用户【admin】于【2021-01-15 14:12:44】登录系统！', 'admin');
INSERT INTO `admin_operator_log` VALUES (353, '2021-01-15 14:13:51', '2021-01-15 14:13:51', '用户【织梦行云】于【2021-01-15 14:13:50】登录系统！', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (354, '2021-01-15 14:35:32', '2021-01-15 14:35:32', '数据库备份成功,备份文件信息DatabaseBak{filename=\'base_20210115143531.sql\', filepath=\'E:/base/backup/\'}', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (355, '2021-01-15 15:52:39', '2021-01-15 15:52:39', '用户【织梦行云】于【2021-01-15 15:52:39】登录系统！', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (356, '2021-01-15 15:53:02', '2021-01-15 15:53:02', '数据库备份成功,备份文件信息DatabaseBak{filename=\'base_20210115155301.sql\', filepath=\'E:/base/backup/\'}', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (357, '2021-01-15 15:59:16', '2021-01-15 15:59:16', '数据库备份成功,备份文件信息DatabaseBak{filename=\'base_20210115155915.sql\', filepath=\'E:/base/backup/\'}', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (358, '2021-01-15 16:07:54', '2021-01-15 16:07:54', '自动定时任务，数据库备份成功！DatabaseBak{filename=\'base_20210115160753.sql\', filepath=\'E:/base/backup/\'}', '未知（获取登录用户失败）');
INSERT INTO `admin_operator_log` VALUES (359, '2021-01-15 16:07:59', '2021-01-15 16:07:59', '自动定时任务，数据库备份成功！DatabaseBak{filename=\'base_20210115160758.sql\', filepath=\'E:/base/backup/\'}', '未知（获取登录用户失败）');
INSERT INTO `admin_operator_log` VALUES (360, '2021-01-15 16:08:04', '2021-01-15 16:08:04', '自动定时任务，数据库备份成功！DatabaseBak{filename=\'base_20210115160803.sql\', filepath=\'E:/base/backup/\'}', '未知（获取登录用户失败）');
INSERT INTO `admin_operator_log` VALUES (361, '2021-01-21 20:22:22', '2021-01-21 20:22:22', '用户【织梦行云】于【2021-01-21 20:22:22】登录系统！', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (362, '2021-01-21 20:22:33', '2021-01-21 20:22:33', '删除一个用户，用户ID：7', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (363, '2021-01-21 20:27:25', '2021-01-21 20:27:25', '编辑一个用户，用户名：test01', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (364, '2021-01-21 21:27:39', '2021-01-21 21:27:39', '用户【织梦行云】于【2021-01-21 21:27:38】登录系统！', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (365, '2021-01-22 19:28:37', '2021-01-22 19:28:37', '用户【织梦行云】于【2021-01-22 19:28:36】登录系统！', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (366, '2021-01-22 19:30:39', '2021-01-22 19:30:39', '添加一个菜单【Menu{name=\'物品管理\', parent=null, url=\'\', icon=\'mdi-drawing\', sort=0, isbutton=false, isshow=true}】', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (367, '2021-01-22 19:30:52', '2021-01-22 19:30:52', '修改一个角色【超管】', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (368, '2021-01-22 19:31:10', '2021-01-22 19:31:10', '用户【织梦行云】于【2021-01-22 19:31:10】登录系统！', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (369, '2021-01-22 19:36:23', '2021-01-22 19:36:23', '添加一个菜单【Menu{name=\'分类管理\', parent=Menu{name=\'物品管理\', parent=null, url=\'\', icon=\'mdi-drawing\', sort=0, isbutton=false, isshow=true}, url=\'/home/goods_category/list\', icon=\'mdi-apps\', sort=0, isbutton=false, isshow=true}】', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (370, '2021-01-22 19:40:36', '2021-01-22 19:40:36', '添加一个菜单【Menu{name=\'添加\', parent=Menu{name=\'分类管理\', parent=Menu{name=\'物品管理\', parent=null, url=\'\', icon=\'mdi-drawing\', sort=0, isbutton=false, isshow=true}, url=\'/home/goods_category/list\', icon=\'mdi-apps\', sort=0, isbutton=false, isshow=true}, url=\'/home/goods_category/add\', icon=\'mdi-guitar-pick\', sort=0, isbutton=false, isshow=true}】', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (371, '2021-01-22 19:42:45', '2021-01-22 19:42:45', '添加一个菜单【Menu{name=\'编辑\', parent=Menu{name=\'分类管理\', parent=Menu{name=\'物品管理\', parent=null, url=\'\', icon=\'mdi-drawing\', sort=0, isbutton=false, isshow=true}, url=\'/home/goods_category/list\', icon=\'mdi-apps\', sort=0, isbutton=false, isshow=true}, url=\'edit(\'/home/goods_category/edit\')\', icon=\'mdi-bookmark-plus-outline\', sort=0, isbutton=true, isshow=true}】', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (372, '2021-01-22 19:44:36', '2021-01-22 19:44:36', '添加一个菜单【Menu{name=\'删除\', parent=Menu{name=\'分类管理\', parent=Menu{name=\'物品管理\', parent=null, url=\'\', icon=\'mdi-drawing\', sort=0, isbutton=false, isshow=true}, url=\'/home/goods_category/list\', icon=\'mdi-apps\', sort=0, isbutton=false, isshow=true}, url=\'del(\'/home/goods_category/delete\')\', icon=\'mdi-backspace\', sort=0, isbutton=true, isshow=true}】', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (373, '2021-01-22 19:47:11', '2021-01-22 19:47:11', '添加一个菜单【Menu{name=\'物品管理\', parent=Menu{name=\'物品管理\', parent=null, url=\'\', icon=\'mdi-drawing\', sort=0, isbutton=false, isshow=true}, url=\'/home/goods/list\', icon=\'mdi-blur\', sort=0, isbutton=false, isshow=true}】', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (374, '2021-01-22 19:47:25', '2021-01-22 19:47:25', '修改一个角色【超管】', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (375, '2021-01-22 19:47:32', '2021-01-22 19:47:32', '用户【织梦行云】于【2021-01-22 19:47:32】登录系统！', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (376, '2021-01-22 20:03:24', '2021-01-22 20:03:24', '修改一个菜单【Menu{name=\'分类管理\', parent=Menu{name=\'物品管理\', parent=null, url=\'\', icon=\'mdi-drawing\', sort=0, isbutton=false, isshow=true}, url=\'/admin/goods_category/list\', icon=\'mdi-apps\', sort=0, isbutton=false, isshow=true}】', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (377, '2021-01-22 20:03:35', '2021-01-22 20:03:35', '修改一个菜单【Menu{name=\'添加\', parent=Menu{name=\'分类管理\', parent=Menu{name=\'物品管理\', parent=null, url=\'\', icon=\'mdi-drawing\', sort=0, isbutton=false, isshow=true}, url=\'/admin/goods_category/list\', icon=\'mdi-apps\', sort=0, isbutton=false, isshow=true}, url=\'/admin/goods_category/add\', icon=\'mdi-guitar-pick\', sort=0, isbutton=false, isshow=true}】', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (378, '2021-01-22 20:03:43', '2021-01-22 20:03:43', '修改一个菜单【Menu{name=\'编辑\', parent=Menu{name=\'分类管理\', parent=Menu{name=\'物品管理\', parent=null, url=\'\', icon=\'mdi-drawing\', sort=0, isbutton=false, isshow=true}, url=\'/admin/goods_category/list\', icon=\'mdi-apps\', sort=0, isbutton=false, isshow=true}, url=\'edit(\'/admin/goods_category/edit\')\', icon=\'mdi-bookmark-plus-outline\', sort=0, isbutton=true, isshow=true}】', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (379, '2021-01-22 20:03:51', '2021-01-22 20:03:51', '修改一个菜单【Menu{name=\'删除\', parent=Menu{name=\'分类管理\', parent=Menu{name=\'物品管理\', parent=null, url=\'\', icon=\'mdi-drawing\', sort=0, isbutton=false, isshow=true}, url=\'/admin/goods_category/list\', icon=\'mdi-apps\', sort=0, isbutton=false, isshow=true}, url=\'del(\'/admin/goods_category/delete\')\', icon=\'mdi-backspace\', sort=0, isbutton=true, isshow=true}】', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (380, '2021-01-22 20:04:01', '2021-01-22 20:04:01', '修改一个菜单【Menu{name=\'物品管理\', parent=Menu{name=\'物品管理\', parent=null, url=\'\', icon=\'mdi-drawing\', sort=0, isbutton=false, isshow=true}, url=\'/admin/goods/list\', icon=\'mdi-blur\', sort=0, isbutton=false, isshow=true}】', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (381, '2021-01-22 21:17:26', '2021-01-22 21:17:26', '用户【织梦行云】于【2021-01-22 21:17:26】登录系统！', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (382, '2021-01-22 21:23:25', '2021-01-22 21:23:25', '用户【织梦行云】于【2021-01-22 21:23:24】登录系统！', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (383, '2021-01-22 21:38:42', '2021-01-22 21:38:42', '用户【织梦行云】于【2021-01-22 21:38:41】登录系统！', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (384, '2021-01-22 21:48:26', '2021-01-22 21:48:26', '用户【织梦行云】于【2021-01-22 21:48:25】登录系统！', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (385, '2021-01-22 21:50:04', '2021-01-22 21:50:04', '用户【织梦行云】于【2021-01-22 21:50:03】登录系统！', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (386, '2021-01-22 22:27:52', '2021-01-22 22:27:52', '编辑一个用户，用户名：test01', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (387, '2021-01-22 22:28:05', '2021-01-22 22:28:05', '编辑一个用户，用户名：test01', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (388, '2021-01-22 22:36:42', '2021-01-22 22:36:42', '编辑一个用户，用户名：test01', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (389, '2021-01-22 22:39:42', '2021-01-22 22:39:42', '编辑一个用户，用户名：织梦行云', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (390, '2021-01-22 22:40:01', '2021-01-22 22:40:01', '编辑一个用户，用户名：admin', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (391, '2021-01-22 22:40:24', '2021-01-22 22:40:24', '编辑一个用户，用户名：test01', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (392, '2021-01-25 16:50:42', '2021-01-25 16:50:42', '用户【织梦行云】于【2021-01-25 16:50:41】登录系统！', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (393, '2021-01-25 17:26:47', '2021-01-25 17:26:47', '用户【织梦行云】于【2021-01-25 17:26:46】登录系统！', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (394, '2021-01-25 19:28:33', '2021-01-25 19:28:33', '用户【织梦行云】于【2021-01-25 19:28:32】登录系统！', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (395, '2021-01-25 20:08:17', '2021-01-25 20:08:17', '用户【织梦行云】于【2021-01-25 20:08:16】登录系统！', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (396, '2021-01-25 20:59:11', '2021-01-25 20:59:11', '用户【织梦行云】于【2021-01-25 20:59:11】登录系统！', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (397, '2021-01-25 21:42:04', '2021-01-25 21:42:04', '用户【织梦行云】于【2021-01-25 21:42:03】登录系统！', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (398, '2021-01-27 14:06:23', '2021-01-27 14:06:23', '用户【织梦行云】于【2021-01-27 14:06:23】登录系统！', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (399, '2021-01-27 14:07:19', '2021-01-27 14:07:19', '用户【admin】于【2021-01-27 14:07:19】登录系统！', 'admin');
INSERT INTO `admin_operator_log` VALUES (400, '2021-01-27 14:08:18', '2021-01-27 14:08:18', '用户【织梦行云】于【2021-01-27 14:08:17】登录系统！', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (401, '2021-01-27 14:26:40', '2021-01-27 14:26:40', '用户【织梦行云】于【2021-01-27 14:26:40】登录系统！', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (402, '2021-01-27 17:12:50', '2021-01-27 17:12:50', '用户【织梦行云】于【2021-01-27 17:12:49】登录系统！', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (403, '2021-02-05 20:38:27', '2021-02-05 20:38:27', '用户【织梦行云】于【2021-02-05 20:38:26】登录系统！', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (404, '2021-02-18 19:56:31', '2021-02-18 19:56:31', '用户【织梦行云】于【2021-02-18 19:56:31】登录系统！', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (405, '2021-02-25 16:27:22', '2021-02-25 16:27:22', '用户【织梦行云】于【2021-02-25 16:27:22】登录系统！', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (406, '2021-02-25 16:28:13', '2021-02-25 16:28:13', '用户【织梦行云】于【2021-02-25 16:28:12】登录系统！', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (407, '2021-03-01 18:55:45', '2021-03-01 18:55:45', '用户【织梦行云】于【2021-03-01 18:55:44】登录系统！', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (408, '2021-03-03 11:12:15', '2021-03-03 11:12:15', '用户【织梦行云】于【2021-03-03 11:12:14】登录系统！', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (409, '2021-03-03 11:12:52', '2021-03-03 11:12:52', '数据库备份成功,备份文件信息DatabaseBak{filename=\'market_20210303111251.sql\', filepath=\'G:/idea WorkSpace/毕业设计/campus-market/src/main/resources/backup\'}', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (410, '2021-03-03 11:24:26', '2021-03-03 11:24:26', '数据库备份成功,备份文件信息DatabaseBak{filename=\'market_20210303112417.sql\', filepath=\'G:/idea WorkSpace/毕业设计/campus-market/src/main/resources/backup\'}', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (411, '2021-03-03 11:27:55', '2021-03-03 11:27:55', '数据库备份成功,备份文件信息DatabaseBak{filename=\'market_20210303112612.sql\', filepath=\'G:/idea WorkSpace/毕业设计/campus-market/src/main/resources/backup\'}', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (412, '2021-03-03 11:28:31', '2021-03-03 11:28:31', '数据库备份成功,备份文件信息DatabaseBak{filename=\'market_20210303112820.sql\', filepath=\'G:/idea WorkSpace/毕业设计/campus-market/src/main/resources/backup/\'}', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (413, '2021-03-03 11:38:53', '2021-03-03 11:38:53', '数据库备份成功,备份文件信息DatabaseBak{filename=\'market_20210303113042.sql\', filepath=\'G:/idea WorkSpace/毕业设计/campus-market/src/main/resources/backup/\'}', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (414, '2021-03-03 11:40:55', '2021-03-03 11:40:55', '用户【织梦行云】于【2021-03-03 11:40:54】登录系统！', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (415, '2021-03-03 11:41:19', '2021-03-03 11:41:19', '数据库备份成功,备份文件信息DatabaseBak{filename=\'market_20210303114103.sql\', filepath=\'G:/idea WorkSpace/毕业设计/backup/\'}', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (416, '2021-03-03 11:43:54', '2021-03-03 11:43:54', '数据库备份成功,备份文件信息DatabaseBak{filename=\'market_20210303114147.sql\', filepath=\'G:/idea WorkSpace/毕业设计/backup/\'}', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (417, '2021-03-03 11:46:02', '2021-03-03 11:46:02', '数据库备份成功,备份文件信息DatabaseBak{filename=\'market_20210303114601.sql\', filepath=\'E:/market/backup/\'}', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (418, '2021-03-03 11:53:56', '2021-03-03 11:53:56', '用户【织梦行云】于【2021-03-03 11:53:56】登录系统！', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (419, '2021-03-04 19:45:37', '2021-03-04 19:45:37', '用户【织梦行云】于【2021-03-04 19:45:37】登录系统！', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (420, '2021-03-04 20:36:12', '2021-03-04 20:36:12', '用户【织梦行云】于【2021-03-04 20:36:11】登录系统！', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (421, '2021-03-04 20:38:14', '2021-03-04 20:38:14', '添加一个菜单【Menu{name=\'上架\', parent=Menu{name=\'物品管理\', parent=Menu{name=\'物品管理\', parent=null, url=\'\', icon=\'mdi-drawing\', sort=0, isbutton=false, isshow=true}, url=\'/admin/goods/list\', icon=\'mdi-blur\', sort=0, isbutton=false, isshow=true}, url=\'up(\'/admin/goods/up_down\')\', icon=\'mdi-menu-up\', sort=0, isbutton=true, isshow=true}】', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (422, '2021-03-04 20:48:31', '2021-03-04 20:48:31', '添加一个菜单【Menu{name=\'下架\', parent=Menu{name=\'物品管理\', parent=Menu{name=\'物品管理\', parent=null, url=\'\', icon=\'mdi-drawing\', sort=0, isbutton=false, isshow=true}, url=\'/admin/goods/list\', icon=\'mdi-blur\', sort=0, isbutton=false, isshow=true}, url=\'down(\'/admin/goods/up_down\')\', icon=\'mdi-menu-down\', sort=0, isbutton=true, isshow=true}】', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (423, '2021-03-04 20:54:48', '2021-03-04 20:54:48', '添加一个菜单【Menu{name=\'删除\', parent=Menu{name=\'物品管理\', parent=Menu{name=\'物品管理\', parent=null, url=\'\', icon=\'mdi-drawing\', sort=0, isbutton=false, isshow=true}, url=\'/admin/goods/list\', icon=\'mdi-blur\', sort=0, isbutton=false, isshow=true}, url=\'del(\'/admin/goods/delete\')\', icon=\'mdi-close\', sort=0, isbutton=true, isshow=true}】', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (424, '2021-03-04 21:05:57', '2021-03-04 21:05:57', '修改一个角色【超管】', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (425, '2021-03-04 21:30:12', '2021-03-04 21:30:12', '修改一个角色【超管】', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (426, '2021-03-04 21:30:25', '2021-03-04 21:30:25', '编辑一个用户，用户名：织梦行云', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (427, '2021-03-04 21:44:10', '2021-03-04 21:44:10', '修改一个角色【超管】', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (428, '2021-03-04 21:45:16', '2021-03-04 21:45:16', '用户【织梦行云】于【2021-03-04 21:45:16】登录系统！', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (429, '2021-03-04 21:45:32', '2021-03-04 21:45:32', '修改一个角色【超管】', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (430, '2021-03-04 21:45:44', '2021-03-04 21:45:44', '用户【织梦行云】于【2021-03-04 21:45:43】登录系统！', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (431, '2021-03-04 22:45:28', '2021-03-04 22:45:28', '用户【织梦行云】于【2021-03-04 22:45:28】登录系统！', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (432, '2021-03-05 14:28:21', '2021-03-05 14:28:21', '用户【织梦行云】于【2021-03-05 14:28:20】登录系统！', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (433, '2021-03-05 22:22:06', '2021-03-05 22:22:06', '用户【织梦行云】于【2021-03-05 22:22:05】登录系统！', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (434, '2021-03-08 19:42:14', '2021-03-08 19:42:14', '用户【织梦行云】于【2021-03-08 19:42:14】登录系统！', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (435, '2021-03-08 19:56:02', '2021-03-08 19:56:02', '添加一个菜单【Menu{name=\'推荐\', parent=Menu{name=\'物品管理\', parent=Menu{name=\'物品管理\', parent=null, url=\'\', icon=\'mdi-drawing\', sort=0, isbutton=false, isshow=true}, url=\'/admin/goods/list\', icon=\'mdi-blur\', sort=0, isbutton=false, isshow=true}, url=\'recommend(\'/admin/goods/recommend\')\', icon=\'mdi-checkbox-blank-circle\', sort=0, isbutton=true, isshow=true}】', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (436, '2021-03-08 19:56:38', '2021-03-08 19:56:38', '添加一个菜单【Menu{name=\'取消推荐\', parent=Menu{name=\'物品管理\', parent=Menu{name=\'物品管理\', parent=null, url=\'\', icon=\'mdi-drawing\', sort=0, isbutton=false, isshow=true}, url=\'/admin/goods/list\', icon=\'mdi-blur\', sort=0, isbutton=false, isshow=true}, url=\'unrecommend(\'/admin/goods/recommend\')\', icon=\'mdi-circle-outline\', sort=0, isbutton=true, isshow=true}】', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (437, '2021-03-08 20:01:35', '2021-03-08 20:01:35', '修改一个角色【超管】', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (438, '2021-03-08 20:01:52', '2021-03-08 20:01:52', '用户【织梦行云】于【2021-03-08 20:01:52】登录系统！', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (439, '2021-03-08 20:10:37', '2021-03-08 20:10:37', '用户【织梦行云】于【2021-03-08 20:10:37】登录系统！', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (440, '2021-03-08 21:29:42', '2021-03-08 21:29:42', '用户【织梦行云】于【2021-03-08 21:29:41】登录系统！', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (441, '2021-03-08 21:30:32', '2021-03-08 21:30:32', '添加一个菜单【Menu{name=\'求购物品管理\', parent=Menu{name=\'物品管理\', parent=null, url=\'\', icon=\'mdi-drawing\', sort=0, isbutton=false, isshow=true}, url=\'/admin/wanted_goods/list\', icon=\'mdi-gesture\', sort=0, isbutton=false, isshow=true}】', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (442, '2021-03-08 21:33:47', '2021-03-08 21:33:47', '添加一个菜单【Menu{name=\'删除\', parent=Menu{name=\'求购物品管理\', parent=Menu{name=\'物品管理\', parent=null, url=\'\', icon=\'mdi-drawing\', sort=0, isbutton=false, isshow=true}, url=\'/admin/wanted_goods/list\', icon=\'mdi-gesture\', sort=0, isbutton=false, isshow=true}, url=\'del(\'/admin/wanted_goods/delete\')\', icon=\'mdi-humble-bundle\', sort=0, isbutton=true, isshow=true}】', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (443, '2021-03-08 21:34:34', '2021-03-08 21:34:34', '修改一个角色【超管】', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (444, '2021-03-08 21:34:44', '2021-03-08 21:34:44', '用户【织梦行云】于【2021-03-08 21:34:44】登录系统！', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (445, '2021-03-09 19:35:47', '2021-03-09 19:35:47', '用户【织梦行云】于【2021-03-09 19:35:47】登录系统！', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (446, '2021-03-09 20:08:36', '2021-03-09 20:08:36', '用户【织梦行云】于【2021-03-09 20:08:35】登录系统！', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (447, '2021-03-09 20:11:45', '2021-03-09 20:11:45', '添加一个菜单【Menu{name=\'学生管理\', parent=null, url=\'/admin/student/list\', icon=\'mdi-buffer\', sort=0, isbutton=false, isshow=true}】', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (448, '2021-03-09 20:13:31', '2021-03-09 20:13:31', '添加一个菜单【Menu{name=\'学生列表\', parent=Menu{name=\'学生管理\', parent=null, url=\'/admin/student/list\', icon=\'mdi-buffer\', sort=0, isbutton=false, isshow=true}, url=\'/admin/student/list\', icon=\'mdi-account-circle\', sort=0, isbutton=false, isshow=true}】', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (449, '2021-03-09 20:13:47', '2021-03-09 20:13:47', '修改一个菜单【Menu{name=\'学生管理\', parent=null, url=\'\', icon=\'mdi-buffer\', sort=0, isbutton=false, isshow=true}】', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (450, '2021-03-09 20:18:39', '2021-03-09 20:18:39', '添加一个菜单【Menu{name=\'冻结\', parent=Menu{name=\'学生列表\', parent=Menu{name=\'学生管理\', parent=null, url=\'\', icon=\'mdi-buffer\', sort=0, isbutton=false, isshow=true}, url=\'/admin/student/list\', icon=\'mdi-account-circle\', sort=0, isbutton=false, isshow=true}, url=\'freeze(\'/admin/student/updata_status\')\', icon=\'mdi-account-settings-variant\', sort=0, isbutton=true, isshow=true}】', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (451, '2021-03-09 20:21:18', '2021-03-09 20:21:18', '添加一个菜单【Menu{name=\'启用\', parent=Menu{name=\'学生列表\', parent=Menu{name=\'学生管理\', parent=null, url=\'\', icon=\'mdi-buffer\', sort=0, isbutton=false, isshow=true}, url=\'/admin/student/list\', icon=\'mdi-account-circle\', sort=0, isbutton=false, isshow=true}, url=\'open(\'/admin/student/updata_status\')\', icon=\'mdi-account-key\', sort=0, isbutton=true, isshow=true}】', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (452, '2021-03-09 20:21:51', '2021-03-09 20:21:51', '添加一个菜单【Menu{name=\'删除\', parent=Menu{name=\'学生列表\', parent=Menu{name=\'学生管理\', parent=null, url=\'\', icon=\'mdi-buffer\', sort=0, isbutton=false, isshow=true}, url=\'/admin/student/list\', icon=\'mdi-account-circle\', sort=0, isbutton=false, isshow=true}, url=\'del(\'/admin/student/delete\')\', icon=\'mdi-account-minus\', sort=0, isbutton=true, isshow=true}】', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (453, '2021-03-09 20:22:24', '2021-03-09 20:22:24', '修改一个角色【超管】', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (454, '2021-03-09 20:22:35', '2021-03-09 20:22:35', '用户【织梦行云】于【2021-03-09 20:22:34】登录系统！', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (455, '2021-03-09 20:44:22', '2021-03-09 20:44:22', '修改一个菜单【Menu{name=\'冻结\', parent=Menu{name=\'学生列表\', parent=Menu{name=\'学生管理\', parent=null, url=\'\', icon=\'mdi-buffer\', sort=0, isbutton=false, isshow=true}, url=\'/admin/student/list\', icon=\'mdi-account-circle\', sort=0, isbutton=false, isshow=true}, url=\'freeze(\'/admin/student/update_status\')\', icon=\'mdi-account-settings-variant\', sort=0, isbutton=true, isshow=true}】', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (456, '2021-03-09 20:44:32', '2021-03-09 20:44:32', '修改一个菜单【Menu{name=\'启用\', parent=Menu{name=\'学生列表\', parent=Menu{name=\'学生管理\', parent=null, url=\'\', icon=\'mdi-buffer\', sort=0, isbutton=false, isshow=true}, url=\'/admin/student/list\', icon=\'mdi-account-circle\', sort=0, isbutton=false, isshow=true}, url=\'open(\'/admin/student/update_status\')\', icon=\'mdi-account-key\', sort=0, isbutton=true, isshow=true}】', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (457, '2021-03-09 21:30:54', '2021-03-09 21:30:54', '用户【织梦行云】于【2021-03-09 21:30:53】登录系统！', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (458, '2021-03-10 19:15:19', '2021-03-10 19:15:19', '用户【织梦行云】于【2021-03-10 19:15:18】登录系统！', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (459, '2021-03-10 19:42:53', '2021-03-10 19:42:53', '用户【织梦行云】于【2021-03-10 19:42:52】登录系统！', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (460, '2021-03-10 20:18:03', '2021-03-10 20:18:03', '用户【织梦行云】于【2021-03-10 20:18:02】登录系统！', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (461, '2021-03-10 20:18:59', '2021-03-10 20:18:59', '添加一个菜单【Menu{name=\'举报管理\', parent=null, url=\'\', icon=\'mdi-ghost\', sort=0, isbutton=false, isshow=true}】', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (462, '2021-03-10 20:19:45', '2021-03-10 20:19:45', '添加一个菜单【Menu{name=\'举报列表\', parent=Menu{name=\'举报管理\', parent=null, url=\'\', icon=\'mdi-ghost\', sort=0, isbutton=false, isshow=true}, url=\'/admin/report_goods/list\', icon=\'mdi-page-layout-footer\', sort=0, isbutton=false, isshow=true}】', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (463, '2021-03-10 20:20:26', '2021-03-10 20:20:26', '添加一个菜单【Menu{name=\'删除\', parent=Menu{name=\'举报管理\', parent=null, url=\'\', icon=\'mdi-ghost\', sort=0, isbutton=false, isshow=true}, url=\'del(\'/admin/report_goods/delete\')\', icon=\'mdi-ethernet-cable-off\', sort=0, isbutton=true, isshow=true}】', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (464, '2021-03-10 20:21:08', '2021-03-10 20:21:08', '修改一个角色【超管】', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (465, '2021-03-10 20:21:22', '2021-03-10 20:21:22', '用户【织梦行云】于【2021-03-10 20:21:22】登录系统！', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (466, '2021-03-10 20:23:03', '2021-03-10 20:23:03', '修改一个角色【超管】', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (467, '2021-03-10 20:23:12', '2021-03-10 20:23:12', '删除一个菜单ID【53】', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (468, '2021-03-10 20:23:29', '2021-03-10 20:23:29', '添加一个菜单【Menu{name=\'删除\', parent=Menu{name=\'举报列表\', parent=Menu{name=\'举报管理\', parent=null, url=\'\', icon=\'mdi-ghost\', sort=0, isbutton=false, isshow=true}, url=\'/admin/report_goods/list\', icon=\'mdi-page-layout-footer\', sort=0, isbutton=false, isshow=true}, url=\'del(\'/admin/report_goods/delete\')\', icon=\'mdi-ethernet-cable-off\', sort=0, isbutton=true, isshow=true}】', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (469, '2021-03-10 20:23:42', '2021-03-10 20:23:42', '修改一个角色【超管】', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (470, '2021-03-10 20:23:48', '2021-03-10 20:23:48', '用户【织梦行云】于【2021-03-10 20:23:48】登录系统！', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (471, '2021-03-10 20:58:00', '2021-03-10 20:58:00', '用户【织梦行云】于【2021-03-10 20:57:59】登录系统！', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (472, '2021-03-10 21:07:26', '2021-03-10 21:07:26', '用户【织梦行云】于【2021-03-10 21:07:25】登录系统！', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (473, '2021-03-10 21:39:41', '2021-03-10 21:39:41', '用户【织梦行云】于【2021-03-10 21:39:41】登录系统！', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (474, '2021-03-15 19:31:35', '2021-03-15 19:31:35', '用户【织梦行云】于【2021-03-15 19:31:35】登录系统！', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (475, '2021-03-20 19:54:38', '2021-03-20 19:54:38', '用户【织梦行云】于【2021-03-20 19:54:38】登录系统！', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (476, '2021-03-21 13:49:13', '2021-03-21 13:49:13', '用户【织梦行云】于【2021-03-21 13:49:12】登录系统！', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (477, '2021-03-21 13:57:48', '2021-03-21 13:57:48', '用户【织梦行云】于【2021-03-21 13:57:47】登录系统！', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (478, '2021-03-21 15:55:44', '2021-03-21 15:55:44', '用户【织梦行云】于【2021-03-21 15:55:43】登录系统！', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (479, '2021-03-21 21:43:27', '2021-03-21 21:43:27', '用户【织梦行云】于【2021-03-21 21:43:27】登录系统！', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (480, '2021-03-22 20:56:11', '2021-03-22 20:56:11', '用户【织梦行云】于【2021-03-22 20:56:11】登录系统！', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (481, '2021-03-23 20:24:16', '2021-03-23 20:24:16', '用户【织梦行云】于【2021-03-23 20:24:15】登录系统！', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (482, '2021-03-23 20:50:39', '2021-03-23 20:50:39', '用户【织梦行云】于【2021-03-23 20:50:38】登录系统！', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (483, '2021-03-24 19:24:21', '2021-03-24 19:24:21', '用户【织梦行云】于【2021-03-24 19:24:20】登录系统！', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (484, '2021-03-24 20:19:50', '2021-03-24 20:19:50', '用户【织梦行云】于【2021-03-24 20:19:50】登录系统！', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (485, '2021-03-24 20:50:37', '2021-03-24 20:50:37', '用户【织梦行云】于【2021-03-24 20:50:36】登录系统！', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (486, '2021-03-24 21:40:13', '2021-03-24 21:40:13', '数据库备份成功,备份文件信息DatabaseBak{filename=\'market_20210324214012.sql\', filepath=\'E:/market/backup/\'}', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (487, '2021-04-24 12:47:39', '2021-04-24 12:47:39', '用户【织梦行云】于【2021-04-24 12:47:39】登录系统！', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (488, '2021-05-11 13:49:06', '2021-05-11 13:49:06', '用户【织梦行云】于【2021-05-11 13:49:05】登录系统！', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (489, '2021-05-11 14:18:14', '2021-05-11 14:18:14', '用户【织梦行云】于【2021-05-11 14:18:13】登录系统！', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (490, '2021-05-11 14:20:46', '2021-05-11 14:20:46', '用户【织梦行云】于【2021-05-11 14:20:45】登录系统！', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (491, '2021-05-11 14:22:51', '2021-05-11 14:22:51', '用户【织梦行云】于【2021-05-11 14:22:50】登录系统！', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (492, '2021-05-11 18:40:15', '2021-05-11 18:40:15', '用户【织梦行云】于【2021-05-11 18:40:15】登录系统！', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (493, '2021-05-11 18:43:00', '2021-05-11 18:43:00', '用户【织梦行云】于【2021-05-11 18:42:59】登录系统！', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (494, '2021-05-12 21:26:55', '2021-05-12 21:26:55', '用户【织梦行云】于【2021-05-12 21:26:54】登录系统！', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (495, '2021-05-14 23:59:22', '2021-05-14 23:59:22', '用户【admin】于【2021-05-14 23:59:22】登录系统！', 'admin');
INSERT INTO `admin_operator_log` VALUES (496, '2021-05-15 00:05:15', '2021-05-15 00:05:15', '修改一个角色【普通管理员(正常)】', 'admin');
INSERT INTO `admin_operator_log` VALUES (497, '2021-05-15 00:05:45', '2021-05-15 00:05:45', '用户【admin】于【2021-05-15 00:05:44】登录系统！', 'admin');
INSERT INTO `admin_operator_log` VALUES (498, '2021-05-15 00:06:26', '2021-05-15 00:06:26', '编辑一个用户，用户名：admin', 'admin');
INSERT INTO `admin_operator_log` VALUES (499, '2021-05-15 00:10:12', '2021-05-15 00:10:12', '添加一个用户，用户名：admin1', 'admin');
INSERT INTO `admin_operator_log` VALUES (500, '2021-05-17 22:08:52', '2021-05-17 22:08:52', '用户【织梦行云】于【2021-05-17 22:08:52】登录系统！', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (501, '2021-05-19 16:20:17', '2021-05-19 16:20:17', '用户【织梦行云】于【2021-05-19 16:20:17】登录系统！', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (502, '2021-05-21 18:07:55', '2021-05-21 18:07:55', '用户【织梦行云】于【2021-05-21 18:07:55】登录系统！', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (503, '2021-05-21 22:03:03', '2021-05-21 22:03:03', '用户【织梦行云】于【2021-05-21 22:03:03】登录系统！', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (504, '2021-05-21 22:06:43', '2021-05-21 22:06:43', '用户【织梦行云】于【2021-05-21 22:06:42】登录系统！', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (505, '2021-05-21 22:15:46', '2021-05-21 22:15:46', '数据库备份成功,备份文件信息DatabaseBak{filename=\'market_20210521221545.sql\', filepath=\'E:/market/backup/\'}', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (506, '2021-05-21 23:14:03', '2021-05-21 23:14:03', '用户【织梦行云】于【2021-05-21 23:14:03】登录系统！', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (507, '2021-05-21 23:16:45', '2021-05-21 23:16:45', '用户【织梦行云】于【2021-05-21 23:16:45】登录系统！', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (508, '2021-05-21 23:17:50', '2021-05-21 23:17:50', '数据库备份成功,备份文件信息DatabaseBak{filename=\'market_20210521231749.sql\', filepath=\'E:/market/backup/\'}', '织梦行云');
INSERT INTO `admin_operator_log` VALUES (509, '2021-05-22 11:08:00', '2021-05-22 11:08:00', '用户【织梦行云】于【2021-05-22 11:07:59】登录系统！', '织梦行云');

-- ----------------------------
-- Table structure for admin_role
-- ----------------------------
DROP TABLE IF EXISTS `admin_role`;
CREATE TABLE `admin_role`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '更改时间',
  `name` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名称',
  `remark` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '备注',
  `status` int(11) NULL DEFAULT NULL COMMENT '状态1-启用；0-未启用',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin_role
-- ----------------------------
INSERT INTO `admin_role` VALUES (1, '2021-01-09 16:50:15', '2021-03-10 20:23:42', '超管', '超级管理员拥有所有的权限', 1);
INSERT INTO `admin_role` VALUES (2, '2021-01-09 16:54:03', '2021-05-15 00:05:15', '普通管理员(正常)', '普通管理员只有部分权限', 1);
INSERT INTO `admin_role` VALUES (8, '2021-01-13 11:59:29', '2021-01-13 11:59:37', '普通管理员（未启用）', '', 0);
INSERT INTO `admin_role` VALUES (9, '2021-01-13 11:59:53', '2021-01-13 11:59:53', '测试角色（无权限）', '', 1);
INSERT INTO `admin_role` VALUES (10, '2021-01-13 13:03:50', '2021-01-13 13:03:50', '测试角色02', '', 1);

-- ----------------------------
-- Table structure for admin_role_authorities
-- ----------------------------
DROP TABLE IF EXISTS `admin_role_authorities`;
CREATE TABLE `admin_role_authorities`  (
  `role_id` bigint(20) NOT NULL COMMENT '角色表id',
  `authorities_id` bigint(20) NOT NULL COMMENT '菜单表id',
  INDEX `FK6u89bs02tdutw9xbi5usbd6oa`(`authorities_id`) USING BTREE,
  INDEX `FKhcyeg218v4gul05clcma4wq4v`(`role_id`) USING BTREE,
  CONSTRAINT `FK6u89bs02tdutw9xbi5usbd6oa` FOREIGN KEY (`authorities_id`) REFERENCES `admin_menu` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKhcyeg218v4gul05clcma4wq4v` FOREIGN KEY (`role_id`) REFERENCES `admin_role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin_role_authorities
-- ----------------------------
INSERT INTO `admin_role_authorities` VALUES (8, 1);
INSERT INTO `admin_role_authorities` VALUES (8, 8);
INSERT INTO `admin_role_authorities` VALUES (8, 9);
INSERT INTO `admin_role_authorities` VALUES (8, 10);
INSERT INTO `admin_role_authorities` VALUES (8, 11);
INSERT INTO `admin_role_authorities` VALUES (8, 12);
INSERT INTO `admin_role_authorities` VALUES (8, 13);
INSERT INTO `admin_role_authorities` VALUES (8, 14);
INSERT INTO `admin_role_authorities` VALUES (8, 15);
INSERT INTO `admin_role_authorities` VALUES (10, 1);
INSERT INTO `admin_role_authorities` VALUES (10, 12);
INSERT INTO `admin_role_authorities` VALUES (10, 14);
INSERT INTO `admin_role_authorities` VALUES (1, 1);
INSERT INTO `admin_role_authorities` VALUES (1, 2);
INSERT INTO `admin_role_authorities` VALUES (1, 3);
INSERT INTO `admin_role_authorities` VALUES (1, 4);
INSERT INTO `admin_role_authorities` VALUES (1, 5);
INSERT INTO `admin_role_authorities` VALUES (1, 8);
INSERT INTO `admin_role_authorities` VALUES (1, 9);
INSERT INTO `admin_role_authorities` VALUES (1, 10);
INSERT INTO `admin_role_authorities` VALUES (1, 11);
INSERT INTO `admin_role_authorities` VALUES (1, 12);
INSERT INTO `admin_role_authorities` VALUES (1, 13);
INSERT INTO `admin_role_authorities` VALUES (1, 14);
INSERT INTO `admin_role_authorities` VALUES (1, 15);
INSERT INTO `admin_role_authorities` VALUES (1, 23);
INSERT INTO `admin_role_authorities` VALUES (1, 25);
INSERT INTO `admin_role_authorities` VALUES (1, 26);
INSERT INTO `admin_role_authorities` VALUES (1, 27);
INSERT INTO `admin_role_authorities` VALUES (1, 28);
INSERT INTO `admin_role_authorities` VALUES (1, 29);
INSERT INTO `admin_role_authorities` VALUES (1, 30);
INSERT INTO `admin_role_authorities` VALUES (1, 32);
INSERT INTO `admin_role_authorities` VALUES (1, 33);
INSERT INTO `admin_role_authorities` VALUES (1, 34);
INSERT INTO `admin_role_authorities` VALUES (1, 35);
INSERT INTO `admin_role_authorities` VALUES (1, 36);
INSERT INTO `admin_role_authorities` VALUES (1, 37);
INSERT INTO `admin_role_authorities` VALUES (1, 38);
INSERT INTO `admin_role_authorities` VALUES (1, 39);
INSERT INTO `admin_role_authorities` VALUES (1, 40);
INSERT INTO `admin_role_authorities` VALUES (1, 41);
INSERT INTO `admin_role_authorities` VALUES (1, 42);
INSERT INTO `admin_role_authorities` VALUES (1, 43);
INSERT INTO `admin_role_authorities` VALUES (1, 44);
INSERT INTO `admin_role_authorities` VALUES (1, 45);
INSERT INTO `admin_role_authorities` VALUES (1, 46);
INSERT INTO `admin_role_authorities` VALUES (1, 47);
INSERT INTO `admin_role_authorities` VALUES (1, 48);
INSERT INTO `admin_role_authorities` VALUES (1, 49);
INSERT INTO `admin_role_authorities` VALUES (1, 50);
INSERT INTO `admin_role_authorities` VALUES (1, 51);
INSERT INTO `admin_role_authorities` VALUES (1, 52);
INSERT INTO `admin_role_authorities` VALUES (1, 54);
INSERT INTO `admin_role_authorities` VALUES (2, 1);
INSERT INTO `admin_role_authorities` VALUES (2, 8);
INSERT INTO `admin_role_authorities` VALUES (2, 9);
INSERT INTO `admin_role_authorities` VALUES (2, 10);
INSERT INTO `admin_role_authorities` VALUES (2, 11);
INSERT INTO `admin_role_authorities` VALUES (2, 12);
INSERT INTO `admin_role_authorities` VALUES (2, 13);
INSERT INTO `admin_role_authorities` VALUES (2, 14);
INSERT INTO `admin_role_authorities` VALUES (2, 15);
INSERT INTO `admin_role_authorities` VALUES (2, 23);

-- ----------------------------
-- Table structure for admin_user
-- ----------------------------
DROP TABLE IF EXISTS `admin_user`;
CREATE TABLE `admin_user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '更改时间',
  `email` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `gander` int(11) NULL DEFAULT NULL COMMENT '性别',
  `head_pic` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
  `mobile` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `password` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `status` int(11) NULL DEFAULT NULL COMMENT '状态1-征程，0-冻结',
  `username` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `role_id` bigint(20) NULL DEFAULT NULL COMMENT '角色id',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `UK_lvod9bfm438ex1071ku1glb70`(`username`) USING BTREE,
  INDEX `FKovciu5m099lrc4e1dbhmol80l`(`role_id`) USING BTREE,
  CONSTRAINT `FKovciu5m099lrc4e1dbhmol80l` FOREIGN KEY (`role_id`) REFERENCES `admin_role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin_user
-- ----------------------------
INSERT INTO `admin_user` VALUES (1, '2021-01-11 21:17:29', '2021-05-21 22:06:14', '617646487@qq.com', 1, '20210521/1621605961571.jpg', '13014511285', '123123', 1, '织梦行云', 1);
INSERT INTO `admin_user` VALUES (2, '2021-01-11 21:22:45', '2021-05-15 00:06:26', '123', 1, '20210515/1621008361305.jpg', '123', '123123', 1, 'admin', 2);
INSERT INTO `admin_user` VALUES (5, '2021-01-12 16:45:02', '2021-01-22 22:40:24', 'bb@163afdafa.com', 0, '20210122/1611326422207.jpg', '13014511285', '123123', 1, 'test01', 8);
INSERT INTO `admin_user` VALUES (12, '2021-05-15 00:10:12', '2021-05-15 00:10:12', '123@qq.com', 0, '', '123', '123123123', 1, 'admin1', 2);

-- ----------------------------
-- Table structure for home_comment
-- ----------------------------
DROP TABLE IF EXISTS `home_comment`;
CREATE TABLE `home_comment`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '更新时间',
  `content` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '评论内容',
  `goods_id` bigint(20) NULL DEFAULT NULL COMMENT '商品id',
  `student_id` bigint(20) NULL DEFAULT NULL COMMENT '学生id',
  `reply_to` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '回复人名称',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FKdih1ahgf8qh6wasmcwr4492v`(`goods_id`) USING BTREE,
  INDEX `FKmeoixmqtcelb2q9r0p2n0ewnt`(`student_id`) USING BTREE,
  CONSTRAINT `FKdih1ahgf8qh6wasmcwr4492v` FOREIGN KEY (`goods_id`) REFERENCES `home_goods` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKmeoixmqtcelb2q9r0p2n0ewnt` FOREIGN KEY (`student_id`) REFERENCES `home_student` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of home_comment
-- ----------------------------
INSERT INTO `home_comment` VALUES (1, '2021-03-03 22:58:31', '2021-03-03 22:58:31', '111', 14, 1, NULL);
INSERT INTO `home_comment` VALUES (2, '2021-03-03 23:36:40', '2021-03-03 23:36:40', '222', 14, 8, NULL);
INSERT INTO `home_comment` VALUES (3, '2021-03-03 23:41:08', '2021-03-03 23:41:08', '回复：织梦行云11111', 14, 8, NULL);
INSERT INTO `home_comment` VALUES (4, '2021-03-04 17:26:14', '2021-03-04 17:26:14', '回复：织梦行云111', 14, 1, '织梦行云');
INSERT INTO `home_comment` VALUES (5, '2021-03-04 17:26:31', '2021-03-04 17:26:31', '回复：022300170305 111111', 14, 1, '022300170305');
INSERT INTO `home_comment` VALUES (6, '2021-03-04 17:29:54', '2021-03-04 17:29:54', '123123', 14, 1, NULL);
INSERT INTO `home_comment` VALUES (7, '2021-03-04 17:31:45', '2021-03-04 17:31:45', '123', 14, 1, '织梦行云');
INSERT INTO `home_comment` VALUES (8, '2021-03-04 19:18:37', '2021-03-04 19:18:37', '回复：“回复：织梦行云11111”\n', 14, 1, '022300170305');
INSERT INTO `home_comment` VALUES (9, '2021-03-25 20:46:28', '2021-03-25 20:46:28', '1123', 33, 1, NULL);
INSERT INTO `home_comment` VALUES (10, '2021-03-25 20:46:33', '2021-03-25 20:46:33', '回复：“1123”\n', 33, 1, '织梦行云');
INSERT INTO `home_comment` VALUES (11, '2021-03-25 21:30:12', '2021-03-25 21:30:12', '回复：“回复：“1123”\n”\n', 33, 7, '织梦行云');
INSERT INTO `home_comment` VALUES (12, '2021-03-25 21:34:25', '2021-03-25 21:34:25', '123', 33, 7, NULL);
INSERT INTO `home_comment` VALUES (13, '2021-03-25 21:36:22', '2021-03-25 21:36:22', '回复：“123”\n', 33, 7, '柳梦璃');
INSERT INTO `home_comment` VALUES (14, '2021-05-21 17:26:49', '2021-05-21 17:26:49', '123123123', 34, 1, NULL);
INSERT INTO `home_comment` VALUES (15, '2021-05-21 17:26:52', '2021-05-21 17:26:52', '123123123', 34, 1, NULL);

-- ----------------------------
-- Table structure for home_goods
-- ----------------------------
DROP TABLE IF EXISTS `home_goods`;
CREATE TABLE `home_goods`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '更新时间',
  `buy_price` float NOT NULL COMMENT '购买价格',
  `content` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品内容',
  `flag` int(11) NOT NULL COMMENT '是否擦亮 1-是；0-否',
  `name` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品名称',
  `photo` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '图片',
  `recommend` int(11) NOT NULL COMMENT '是否推荐 1-是；0-否',
  `sell_price` float NOT NULL COMMENT '卖出价格',
  `status` int(11) NOT NULL COMMENT '状态 1-上架；2-下架；3-已出售',
  `goods_category_id` bigint(20) NULL DEFAULT NULL COMMENT '商品分类',
  `student_id` bigint(20) NULL DEFAULT NULL COMMENT '学生id',
  `view_number` int(11) NOT NULL COMMENT '浏览量',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FKafr0tg7592fod3yjhh0q6i0lq`(`goods_category_id`) USING BTREE,
  INDEX `FKlqrpv80fdo8cblymja71ag712`(`student_id`) USING BTREE,
  CONSTRAINT `FKafr0tg7592fod3yjhh0q6i0lq` FOREIGN KEY (`goods_category_id`) REFERENCES `home_goods_category` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKlqrpv80fdo8cblymja71ag712` FOREIGN KEY (`student_id`) REFERENCES `home_student` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 39 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of home_goods
-- ----------------------------
INSERT INTO `home_goods` VALUES (5, '2021-02-27 17:04:26', '2021-05-21 17:26:19', 9999, '请问请问请问请问请问qweqweqwe', 1, '华为手机', '20210227/1614416637168.png', 0, 8888, 1, 2, 7, 8);
INSERT INTO `home_goods` VALUES (6, '2021-02-28 13:45:34', '2021-05-21 23:13:35', 998, '11111111111111111111111111', 1, '手表', '20210228/1614491104197.jpg', 0, 499, 1, 31, 7, 4);
INSERT INTO `home_goods` VALUES (7, '2021-02-28 13:49:43', '2021-05-22 11:21:43', 5999, '11111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111', 1, '笔记本电脑联想', '20210228/1614491348217.jpg', 0, 4999, 2, 6, 1, 10);
INSERT INTO `home_goods` VALUES (8, '2021-02-28 14:03:58', '2021-05-22 11:21:54', 99, '111111111111111111111111111', 0, '牛仔裤', '20210228/1614492219935.jpg', 0, 59, 3, 27, 1, 11);
INSERT INTO `home_goods` VALUES (9, '2021-02-28 21:01:14', '2021-03-08 20:03:43', 29, '1111111111111111111111111111', 1, 'iphone 手机壳', '20210228/1614517247413.jpg', 0, 9.9, 3, 3, 7, 2);
INSERT INTO `home_goods` VALUES (10, '2021-02-28 21:02:07', '2021-03-10 21:33:33', 19, '11111111111111111111111', 1, 'iphone 8  手机壳', '20210228/1614517304976.jpg', 0, 10, 2, 3, 7, 5);
INSERT INTO `home_goods` VALUES (11, '2021-02-28 21:05:01', '2021-03-10 21:33:37', 99, '111111111111111111', 1, '网易云耳机', '20210228/1614517477307.jpg', 0, 59, 3, 3, 7, 9);
INSERT INTO `home_goods` VALUES (14, '2021-03-03 18:07:50', '2021-03-10 20:56:44', 11, '11111111111111111111', 1, '111', '20210303/1614766062000.png', 0, 11, 1, 38, 8, 45);
INSERT INTO `home_goods` VALUES (15, '2021-03-10 20:56:07', '2021-03-10 21:02:48', 50, '1111111111111111111111', 1, '蓝月亮洗衣液', '20210310/1615380908174.jpg', 0, 29, 1, 29, 1, 4);
INSERT INTO `home_goods` VALUES (16, '2021-03-10 20:59:42', '2021-03-10 21:02:51', 20, '好东西1111111111111111111', 1, '暖壶', '20210310/1615381158648.jpg', 0, 10, 1, 42, 1, 4);
INSERT INTO `home_goods` VALUES (17, '2021-03-10 21:04:54', '2021-03-10 21:04:54', 5, '好东西111111111111', 1, '发泡棉胶', '20210310/1615381454813.jpg', 0, 1.99, 1, 42, 1, 0);
INSERT INTO `home_goods` VALUES (18, '2021-03-10 21:10:33', '2021-03-10 21:10:33', 20, '想要的来，这东西朋友送的，不想要了', 1, '玩具', '20210310/1615381792353.jpg', 0, 10, 1, 44, 1, 0);
INSERT INTO `home_goods` VALUES (19, '2021-03-10 21:12:33', '2021-03-10 21:12:33', 30, '自用的，用不完了，卖掉，想要的来哦', 1, '芦荟胶', '20210310/1615381917384.jpg', 0, 15, 1, 43, 1, 0);
INSERT INTO `home_goods` VALUES (20, '2021-03-10 21:13:41', '2021-03-15 19:30:33', 20, '绑定销售，必须俩一起买要不不卖', 1, '洗衣液', '20210310/1615381976934.jpg', 0, 12, 1, 42, 1, 1);
INSERT INTO `home_goods` VALUES (21, '2021-03-10 21:16:08', '2021-03-10 21:16:08', 50, '赶紧买吧，买了不亏，英语四级必过', 1, '恋恋有词', '20210310/1615382135244.jpg', 0, 5, 1, 35, 1, 0);
INSERT INTO `home_goods` VALUES (22, '2021-03-10 21:16:50', '2021-03-10 21:16:50', 5000, '原价好几千买的，现在要毕业了，用不到了\r\n', 1, '电车', '20210310/1615382177323.jpg', 0, 2000, 1, 38, 1, 0);
INSERT INTO `home_goods` VALUES (23, '2021-03-10 21:17:54', '2021-03-20 19:56:02', 2000, '看图，看图，看图，看图，看图，看图，看图，看图，看图，看图，', 1, 'AJ鞋子', '20210310/1615382244771.jpg', 0, 1000, 1, 30, 1, 1);
INSERT INTO `home_goods` VALUES (24, '2021-03-10 21:18:51', '2021-03-10 21:18:51', 60, '甩卖！！！！！！！！！！！！！', 1, '三个差评', '20210310/1615382305414.jpg', 0, 20, 1, 42, 1, 0);
INSERT INTO `home_goods` VALUES (25, '2021-03-10 21:24:44', '2021-03-10 21:24:44', 20, '好用实惠，不贵，可以小刀，最好自取', 1, '收纳盒', '20210310/1615382645419.JPG', 0, 20, 1, 31, 7, 0);
INSERT INTO `home_goods` VALUES (26, '2021-03-10 21:25:43', '2021-03-10 21:25:43', 10, '好事，便宜 的很，不能再便宜了，要不就白送了', 1, '床上收纳框', '20210310/1615382695662.jpg', 0, 5, 1, 42, 7, 0);
INSERT INTO `home_goods` VALUES (27, '2021-03-10 21:26:33', '2021-03-10 21:26:33', 20, '全新侧，用不了，转手了，真的是全新的', 1, '三花一草', '20210310/1615382752764.jpg', 0, 10, 1, 43, 7, 0);
INSERT INTO `home_goods` VALUES (28, '2021-03-10 21:27:30', '2021-03-10 21:33:59', 1209, '男朋友送的，现在分手了，低价专卖', 1, 'AirPoad耳机', '20210310/1615382803876.jpg', 0, 999, 1, 13, 7, 1);
INSERT INTO `home_goods` VALUES (29, '2021-03-10 21:30:54', '2021-03-25 21:30:04', 30, '111111111111111111111111', 1, '半亩花田身体乳', '20210310/1615383027653.jpg', 0, 15, 1, 43, 7, 1);
INSERT INTO `home_goods` VALUES (30, '2021-03-10 21:31:34', '2021-03-10 21:31:34', 20, '1111111111111111', 1, '蓝月亮洗衣液', '20210310/1615383074029.jpg', 0, 10, 1, 42, 7, 0);
INSERT INTO `home_goods` VALUES (31, '2021-03-10 21:32:08', '2021-03-10 21:34:11', 39.9, '111111111111111111', 1, '网络安全课本', '20210310/1615383102992.jpg', 0, 10, 1, 36, 7, 1);
INSERT INTO `home_goods` VALUES (32, '2021-03-10 21:32:40', '2021-03-10 21:32:40', 39, '11111111111111111', 1, '拉芳洗发水', '20210310/1615383138161.JPG', 0, 12, 1, 43, 7, 0);
INSERT INTO `home_goods` VALUES (33, '2021-03-10 21:33:22', '2021-04-24 12:47:25', 20, '11111111111111111', 1, '线性代数', '20210310/1615383183749.jpg', 0, 10, 1, 34, 7, 9);
INSERT INTO `home_goods` VALUES (34, '2021-03-26 20:54:35', '2021-05-22 11:13:49', 300, 'aaaaaaaaaaaaaaaaaaaa', 1, '自行车', '20210326/1616763251143.png', 0, 150, 1, 39, 1, 6);
INSERT INTO `home_goods` VALUES (35, '2021-05-11 18:39:57', '2021-05-21 23:18:23', 12, '123123123123123123', 1, '123', '20210511/1620729582482.jpg', 0, 12, 1, 43, 1, 2);
INSERT INTO `home_goods` VALUES (37, '2021-05-21 17:59:14', '2021-05-21 17:59:32', 1, '123121111111111111111111111111111111', 1, '12312312312312312', '20210521/1621591136021.jpg', 0, 1, 3, 22, 1, 1);
INSERT INTO `home_goods` VALUES (38, '2021-05-21 23:12:21', '2021-05-22 11:24:55', 123, '123123123123123123123123123123123', 1, '123123123123123123', '20210521/1621609828457.jpg', 1, 123, 1, 8, 10, 1);

-- ----------------------------
-- Table structure for home_goods_category
-- ----------------------------
DROP TABLE IF EXISTS `home_goods_category`;
CREATE TABLE `home_goods_category`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '修改时间',
  `icon` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '图标',
  `name` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '分类名称',
  `sort` int(11) NOT NULL COMMENT '排序',
  `parent_id` bigint(20) NULL DEFAULT NULL COMMENT '父id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FKn3av865ndkd6cvpa7xk424kp7`(`parent_id`) USING BTREE,
  CONSTRAINT `FKn3av865ndkd6cvpa7xk424kp7` FOREIGN KEY (`parent_id`) REFERENCES `home_goods_category` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 45 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of home_goods_category
-- ----------------------------
INSERT INTO `home_goods_category` VALUES (1, '2021-01-25 21:08:29', '2021-01-25 22:13:45', '20210125/1611584019496.png', '手机', 1, NULL);
INSERT INTO `home_goods_category` VALUES (2, '2021-01-25 21:08:43', '2021-01-25 22:14:31', '20210125/1611584054079.png', '智能机', 2, 1);
INSERT INTO `home_goods_category` VALUES (3, '2021-01-25 22:15:05', '2021-01-25 22:15:12', '20210125/1611584086787.png', '手机配件', 3, 1);
INSERT INTO `home_goods_category` VALUES (4, '2021-01-25 22:15:44', '2021-01-25 22:15:44', '20210125/1611584122319.png', '翻盖机', 4, 1);
INSERT INTO `home_goods_category` VALUES (5, '2021-01-25 22:16:14', '2021-01-25 22:16:14', '20210125/1611584163129.png', '电脑', 5, NULL);
INSERT INTO `home_goods_category` VALUES (6, '2021-01-25 22:16:46', '2021-01-25 22:16:46', '20210125/1611584184622.png', '笔记本', 6, 5);
INSERT INTO `home_goods_category` VALUES (7, '2021-01-25 22:17:11', '2021-01-25 22:17:11', '20210125/1611584218780.png', '台式机', 7, 5);
INSERT INTO `home_goods_category` VALUES (8, '2021-01-25 22:17:34', '2021-01-25 22:17:34', '20210125/1611584242754.png', '平板电脑', 8, 5);
INSERT INTO `home_goods_category` VALUES (12, '2021-01-27 15:51:35', '2021-01-27 15:51:48', '20210127/1611733818206.png', '影音娱乐', 9, NULL);
INSERT INTO `home_goods_category` VALUES (13, '2021-01-27 15:53:14', '2021-01-27 15:53:14', '20210127/1611733973497.png', '耳机', 10, 12);
INSERT INTO `home_goods_category` VALUES (14, '2021-01-27 15:53:56', '2021-01-27 15:53:56', '20210127/1611734015582.png', 'mp3/mp4', 11, 12);
INSERT INTO `home_goods_category` VALUES (15, '2021-01-27 15:54:29', '2021-01-27 15:54:29', '20210127/1611734060358.png', '鼠标', 12, 12);
INSERT INTO `home_goods_category` VALUES (16, '2021-01-27 15:54:52', '2021-01-27 15:54:52', '20210127/1611734079956.png', '键盘', 13, 12);
INSERT INTO `home_goods_category` VALUES (17, '2021-01-27 15:56:10', '2021-01-27 15:56:10', '20210127/1611734156125.png', '数码配件', 14, NULL);
INSERT INTO `home_goods_category` VALUES (18, '2021-01-27 15:57:36', '2021-01-27 15:57:36', '20210127/1611734251664.png', '移动硬盘', 15, 17);
INSERT INTO `home_goods_category` VALUES (19, '2021-01-27 15:58:30', '2021-01-27 15:58:30', '20210127/1611734294077.png', '相机', 16, 17);
INSERT INTO `home_goods_category` VALUES (20, '2021-01-27 15:58:52', '2021-01-27 15:58:52', '20210127/1611734328287.png', '显示器', 17, 17);
INSERT INTO `home_goods_category` VALUES (21, '2021-01-27 15:59:28', '2021-01-27 15:59:28', '20210127/1611734356892.png', '运动健身', 18, NULL);
INSERT INTO `home_goods_category` VALUES (22, '2021-01-27 16:01:59', '2021-01-27 16:01:59', '20210127/1611734382273.png', '篮球', 19, 21);
INSERT INTO `home_goods_category` VALUES (23, '2021-01-27 16:02:26', '2021-01-27 16:02:26', '20210127/1611734543981.png', '足球', 20, 21);
INSERT INTO `home_goods_category` VALUES (24, '2021-01-27 16:02:56', '2021-01-27 16:02:56', '20210127/1611734563931.png', '球拍', 21, 21);
INSERT INTO `home_goods_category` VALUES (25, '2021-01-27 16:03:33', '2021-01-27 16:03:33', '20210127/1611734599818.png', '衣物鞋帽', 22, NULL);
INSERT INTO `home_goods_category` VALUES (26, '2021-01-27 16:04:05', '2021-01-27 16:04:05', '20210127/1611734633741.png', '上衣', 23, 25);
INSERT INTO `home_goods_category` VALUES (27, '2021-01-27 16:04:30', '2021-01-27 16:04:30', '20210127/1611734659922.png', '裤子', 24, 25);
INSERT INTO `home_goods_category` VALUES (28, '2021-01-27 16:05:10', '2021-01-27 16:05:10', '20210127/1611734698596.png', '背包', 25, 25);
INSERT INTO `home_goods_category` VALUES (29, '2021-01-27 16:05:38', '2021-01-27 16:05:38', '20210127/1611734735476.png', '雨伞', 26, 25);
INSERT INTO `home_goods_category` VALUES (30, '2021-01-27 16:06:07', '2021-01-27 16:06:07', '20210127/1611734751635.png', '鞋子', 27, 25);
INSERT INTO `home_goods_category` VALUES (31, '2021-01-27 16:06:36', '2021-01-27 16:06:36', '20210127/1611734787112.png', '配饰', 28, 25);
INSERT INTO `home_goods_category` VALUES (32, '2021-01-27 16:08:10', '2021-01-27 16:08:10', '20210127/1611734879693.png', '图书教材', 29, NULL);
INSERT INTO `home_goods_category` VALUES (33, '2021-01-27 16:09:01', '2021-01-27 16:09:01', '20210127/1611734932694.png', '教材', 30, 32);
INSERT INTO `home_goods_category` VALUES (34, '2021-01-27 16:12:16', '2021-01-27 16:12:16', '20210127/1611735122633.png', '考研', 31, 32);
INSERT INTO `home_goods_category` VALUES (35, '2021-01-27 16:12:49', '2021-01-27 16:12:49', '20210127/1611735151607.png', '四六级', 32, 32);
INSERT INTO `home_goods_category` VALUES (36, '2021-01-27 16:13:20', '2021-01-27 16:13:20', '20210127/1611735185865.png', '计算机', 33, 32);
INSERT INTO `home_goods_category` VALUES (37, '2021-01-27 16:14:15', '2021-01-27 16:14:15', '20210127/1611735242099.png', '交通出行', 35, NULL);
INSERT INTO `home_goods_category` VALUES (38, '2021-01-27 16:14:40', '2021-01-27 16:14:40', '20210127/1611735267407.png', '电动车', 36, 37);
INSERT INTO `home_goods_category` VALUES (39, '2021-01-27 16:14:58', '2021-01-27 16:14:58', '20210127/1611735287182.png', '自行车', 37, 37);
INSERT INTO `home_goods_category` VALUES (40, '2021-01-27 16:15:57', '2021-01-27 16:15:57', '20210127/1611735344731.png', '美妆', 50, NULL);
INSERT INTO `home_goods_category` VALUES (41, '2021-01-27 16:16:19', '2021-01-27 16:16:19', '20210127/1611735370300.png', '其他', 60, NULL);
INSERT INTO `home_goods_category` VALUES (42, '2021-03-10 20:58:44', '2021-03-10 20:59:03', '20210310/1615381121523.jpg', '其他', 61, 41);
INSERT INTO `home_goods_category` VALUES (43, '2021-03-10 21:08:47', '2021-03-10 21:08:47', '20210310/1615381704880.png', '化妆品', 51, 40);
INSERT INTO `home_goods_category` VALUES (44, '2021-03-10 21:09:12', '2021-03-10 21:09:12', '20210310/1615381734980.png', '玩偶', 52, 40);

-- ----------------------------
-- Table structure for home_report_goods
-- ----------------------------
DROP TABLE IF EXISTS `home_report_goods`;
CREATE TABLE `home_report_goods`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '修改时间',
  `content` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '举报内容',
  `goods_id` bigint(20) NULL DEFAULT NULL COMMENT '商品id',
  `student_id` bigint(20) NULL DEFAULT NULL COMMENT '学生id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FKn0aub14s6vkh8swkly8crnu4j`(`goods_id`) USING BTREE,
  INDEX `FK8286f5bg0aj07qiq293u6j16o`(`student_id`) USING BTREE,
  CONSTRAINT `FK8286f5bg0aj07qiq293u6j16o` FOREIGN KEY (`student_id`) REFERENCES `home_student` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKn0aub14s6vkh8swkly8crnu4j` FOREIGN KEY (`goods_id`) REFERENCES `home_goods` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of home_report_goods
-- ----------------------------
INSERT INTO `home_report_goods` VALUES (4, '2021-03-10 20:35:44', '2021-03-10 20:35:44', '2222', 11, 1);
INSERT INTO `home_report_goods` VALUES (5, '2021-03-10 20:36:00', '2021-03-10 20:36:00', '333', 14, 7);
INSERT INTO `home_report_goods` VALUES (6, '2021-03-10 20:40:59', '2021-03-10 20:40:59', '1111', 10, 7);
INSERT INTO `home_report_goods` VALUES (7, '2021-05-21 17:26:39', '2021-05-21 17:26:39', '123123123123', 34, 1);

-- ----------------------------
-- Table structure for home_student
-- ----------------------------
DROP TABLE IF EXISTS `home_student`;
CREATE TABLE `home_student`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '修改时间',
  `academy` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '学院',
  `grade` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '年级',
  `head_pic` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
  `mobile` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `nickname` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `password` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `qq` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'qq号',
  `school` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '学校',
  `sno` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '学号',
  `status` int(11) NOT NULL COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `UK_b2wyvwnqbw1cf59we7uce75j1`(`sno`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of home_student
-- ----------------------------
INSERT INTO `home_student` VALUES (1, '2021-01-28 14:32:53', '2021-02-05 20:27:01', '信息工程学院', '2017', '20210205/1612528020837.jpg', '13014511285', '织梦行云', '123123', '617646487', '郑州财经学院', '022300170332', 1);
INSERT INTO `home_student` VALUES (7, '2021-02-27 17:01:40', '2021-02-27 17:03:05', '金融学院', '2020', NULL, '130111111111', '柳梦璃', '123123', '2779439926', '河南科技大学', '022300170315', 1);
INSERT INTO `home_student` VALUES (8, '2021-03-03 17:58:30', '2021-03-09 22:08:14', NULL, NULL, NULL, NULL, NULL, '123123', '123123', '河南科技大学', '022300170305', 1);
INSERT INTO `home_student` VALUES (9, '2021-05-21 16:20:35', '2021-05-21 16:20:35', NULL, NULL, NULL, NULL, NULL, '123123', '617646487', '河南科技大学', '022300170309', 1);
INSERT INTO `home_student` VALUES (10, '2021-05-21 23:09:42', '2021-05-21 23:10:04', '123', '123', NULL, '123', '123', '123123', '123123', '123', '022300170302', 1);

-- ----------------------------
-- Table structure for home_wanted_goods
-- ----------------------------
DROP TABLE IF EXISTS `home_wanted_goods`;
CREATE TABLE `home_wanted_goods`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '更改时间',
  `content` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '求购内容',
  `name` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '求购物品名称',
  `sell_price` float NOT NULL COMMENT '出售价格',
  `trade_place` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '交易地点',
  `view_number` int(11) NOT NULL COMMENT '浏览量',
  `student_id` bigint(20) NULL DEFAULT NULL COMMENT '学生id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK4265qm2o22u59ssd2w9ukdfq2`(`student_id`) USING BTREE,
  CONSTRAINT `FK4265qm2o22u59ssd2w9ukdfq2` FOREIGN KEY (`student_id`) REFERENCES `home_student` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of home_wanted_goods
-- ----------------------------
INSERT INTO `home_wanted_goods` VALUES (2, '2021-03-02 19:48:53', '2021-03-02 19:48:53', '111111111111111111111111111111', '小米K40 5G手机', 3000, '河南郑州', 0, 1);
INSERT INTO `home_wanted_goods` VALUES (3, '2021-03-02 19:59:07', '2021-03-02 19:59:07', '想要学长学姐不用的自行车，便宜一点的', '自行车', 400, '17号宿舍楼', 0, 7);
INSERT INTO `home_wanted_goods` VALUES (4, '2021-03-02 20:06:31', '2021-03-02 20:06:31', '手机卡，想换个新的，事逼别来！！！', '二手苹果手机', 999, '20号宿舍楼', 0, 7);
INSERT INTO `home_wanted_goods` VALUES (5, '2021-03-02 20:07:54', '2021-03-02 20:07:54', '电脑太卡了，求大家给我推荐个便宜的笔记本电脑，二手的也行。', '笔记本电脑', 2999, '食堂', 0, 7);
INSERT INTO `home_wanted_goods` VALUES (8, '2021-03-10 21:05:41', '2021-03-10 21:05:41', '看名字不要乱点，我要的就是真题', '四级真题', 20, '河南郑州', 0, 1);
INSERT INTO `home_wanted_goods` VALUES (9, '2021-03-10 21:06:24', '2021-03-10 21:06:24', '用来背英语希望大家能帮帮忙1111', '单词书', 10, '17号宿舍楼', 0, 1);
INSERT INTO `home_wanted_goods` VALUES (10, '2021-03-10 21:19:50', '2021-03-10 21:19:50', '就打打游戏，搬搬砖，合适的来!!!!!!', '笔记本电脑', 2000, '11#401', 0, 1);
INSERT INTO `home_wanted_goods` VALUES (11, '2021-03-10 21:20:27', '2021-03-10 21:20:27', '看名字，这网站做的有毛病，不想改了！！！', '行李箱', 100, '111', 0, 1);
INSERT INTO `home_wanted_goods` VALUES (12, '2021-03-10 21:21:25', '2021-03-10 21:21:25', '没啥说的，表个白吧，XXX我爱你！！！', '床上桌', 20, '食堂', 0, 1);
INSERT INTO `home_wanted_goods` VALUES (13, '2021-05-21 16:21:10', '2021-05-21 16:21:10', '12312312312311111111', '刘俊超', 10, '河南郑州', 0, 9);
INSERT INTO `home_wanted_goods` VALUES (14, '2021-05-22 11:23:32', '2021-05-22 11:23:32', '1231231231231231231231231231', '23213123123123', 111, '河南郑州', 0, 1);

SET FOREIGN_KEY_CHECKS = 1;



SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `pid` bigint(20) NOT NULL COMMENT '上级菜单ID',
  `type` int(11) NOT NULL COMMENT '菜单类型',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '菜单标题',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '组件名称',
  `component` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '组件',
  `sort` int(5) NULL DEFAULT 0 COMMENT '排序',
  `icon` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '图标',
  `path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '链接地址',
  `iframe` bit(1) NULL DEFAULT b'0' COMMENT '是否外链',
  `cache` bit(1) NULL DEFAULT b'0' COMMENT '缓存',
  `hidden` bit(1) NULL DEFAULT b'0' COMMENT '隐藏',
  `permission` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '权限',
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` tinyint(2) NOT NULL DEFAULT 0 COMMENT '是否删除（0:否，1：是）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1647541882525442051 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统菜单' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1645812231633461249, 1647248318234886145, 0, '二级菜单1', '', '', 1000, 'table', 'menu', b'0', b'0', b'0', '', 'system', 'system', '2023-04-11 23:33:08', '2023-04-11 23:33:08', 0);
INSERT INTO `sys_menu` VALUES (1645812547225477122, 1645812231633461249, 1, '三级菜单1', 'menu3-1', 'nested/menu1/menu1-1/index', 1000, 'chain', 'menu3-1', b'0', b'0', b'0', '', 'system', 'system', '2023-04-11 23:34:24', '2023-04-11 23:39:59', 0);
INSERT INTO `sys_menu` VALUES (1645812820081729537, 1647248318234886145, 1, '二级菜单2', 'menu2-2', 'nested/menu2/index.vue', 1000, 'app', 'menu2-2', b'0', b'0', b'0', '', 'system', 'system', '2023-04-11 23:35:29', '2023-04-11 23:37:20', 0);
INSERT INTO `sys_menu` VALUES (1645813802001850369, 1645812231633461249, 1, '三级菜单2', 'menu3-2', 'nested/menu1/menu1-2/index', 1000, 'email', 'menu3-2', b'0', b'0', b'0', '', 'system', 'system', '2023-04-11 23:39:23', '2023-04-11 23:39:23', 0);
INSERT INTO `sys_menu` VALUES (1646104022067286017, 1647247627433017346, 1, '树形表格', 'TreeTable', 'tree-table/index.vue', 1000, 'tree-table', 'tree-table', b'0', b'0', b'0', '', 'admin', 'admin', '2023-04-12 18:52:37', '2023-04-15 22:43:12', 0);
INSERT INTO `sys_menu` VALUES (1646917148414611458, 0, 0, '系统管理', 'System', '', 100, 'system1', '/system', b'0', b'0', b'0', '', 'admin', 'admin', '2023-04-15 00:43:41', '2023-04-17 13:38:25', 0);
INSERT INTO `sys_menu` VALUES (1646917867762913282, 1646917148414611458, 1, '用户管理', 'User', 'system/user/index.vue', 100, 'user', 'user', b'0', b'0', b'0', 'user:list', 'admin', 'admin', '2023-04-15 00:46:33', '2023-04-17 20:27:56', 0);
INSERT INTO `sys_menu` VALUES (1646924883273224193, 1646917148414611458, 1, '角色管理', 'Role', 'system/role/index.vue', 500, 'role', 'role', b'0', b'0', b'0', 'role:list', 'admin', 'admin', '2023-04-15 01:14:25', '2023-04-17 20:28:19', 0);
INSERT INTO `sys_menu` VALUES (1646925326502105089, 1646917148414611458, 1, '菜单管理', 'Menu', 'system/menu/index.vue', 1000, 'menu', 'menu', b'0', b'1', b'0', 'menu:list', 'admin', 'admin', '2023-04-15 01:16:11', '2023-04-16 00:32:34', 0);
INSERT INTO `sys_menu` VALUES (1647245235475972097, 0, 0, '系统监控', 'Monitor', '', 200, 'monitor', '/monitor', b'0', b'0', b'0', '', 'admin', 'admin', '2023-04-15 22:27:23', '2023-04-17 13:38:47', 0);
INSERT INTO `sys_menu` VALUES (1647245914567344129, 1647245235475972097, 1, 'SQL监控', 'Druid', 'monitor/druid/index.vue', 1000, 'sqlMonitor', 'druid', b'0', b'0', b'0', '', 'admin', 'admin', '2023-04-15 22:30:05', '2023-04-15 22:30:05', 0);
INSERT INTO `sys_menu` VALUES (1647246715431944194, 1647245235475972097, 1, '接口文档', 'Swagger', 'monitor/swagger/index.vue', 1000, 'doc', 'swagger', b'0', b'0', b'0', '', 'admin', 'admin', '2023-04-15 22:33:16', '2023-04-15 22:33:31', 0);
INSERT INTO `sys_menu` VALUES (1647247627433017346, 0, 0, '组件管理', 'Components', '', 300, 'zujian', '/components', b'0', b'0', b'0', '', 'admin', 'admin', '2023-04-15 22:36:53', '2023-04-17 13:39:02', 0);
INSERT INTO `sys_menu` VALUES (1647248318234886145, 0, 0, '多级菜单', 'Menus', '', 1000, 'theme', '/menus', b'0', b'0', b'0', '', 'admin', 'admin', '2023-04-15 22:39:38', '2023-04-15 22:55:08', 0);
INSERT INTO `sys_menu` VALUES (1647462845975158785, 1646917867762913282, 2, '新增用户', '', '', 1000, '', '', b'0', b'0', b'0', 'user:add', 'admin', 'admin', '2023-04-16 12:52:06', '2023-04-16 12:53:52', 0);
INSERT INTO `sys_menu` VALUES (1647462994583543809, 1646917867762913282, 2, '编辑用户', '', '', 1000, '', '', b'0', b'0', b'0', 'user:update', 'admin', 'admin', '2023-04-16 12:52:41', '2023-04-16 12:52:41', 0);
INSERT INTO `sys_menu` VALUES (1647463086354915330, 1646917867762913282, 2, '删除用户', '', '', 1000, '', '', b'0', b'0', b'0', 'user:delete', 'admin', 'admin', '2023-04-16 12:53:03', '2023-04-16 12:53:03', 0);
INSERT INTO `sys_menu` VALUES (1647463212578299905, 1646924883273224193, 2, '新增角色', '', '', 1000, '', '', b'0', b'0', b'0', 'role:add', 'admin', 'admin', '2023-04-16 12:53:33', '2023-04-16 12:53:33', 0);
INSERT INTO `sys_menu` VALUES (1647463515704844289, 1646924883273224193, 2, '编辑角色', '', '', 1000, '', '', b'0', b'0', b'0', 'role:update', 'admin', 'admin', '2023-04-16 12:54:45', '2023-04-16 12:54:45', 0);
INSERT INTO `sys_menu` VALUES (1647463605379063810, 1646924883273224193, 2, '删除角色', '', '', 1000, '', '', b'0', b'0', b'0', 'role:delete', 'admin', 'admin', '2023-04-16 12:55:07', '2023-04-16 12:55:07', 0);
INSERT INTO `sys_menu` VALUES (1647463829535252482, 1646924883273224193, 2, '保存角色菜单', '', '', 1000, '', '', b'0', b'0', b'0', 'role:saveMenu', 'admin', 'admin', '2023-04-16 12:56:00', '2023-04-16 12:56:08', 0);
INSERT INTO `sys_menu` VALUES (1647464115179937793, 1646925326502105089, 2, '新增菜单', '', '', 1000, '', '', b'0', b'0', b'0', 'menu:add', 'admin', 'admin', '2023-04-16 12:57:08', '2023-04-16 12:57:08', 0);
INSERT INTO `sys_menu` VALUES (1647464298655571970, 1646925326502105089, 2, '编辑菜单', '', '', 1000, '', '', b'0', b'0', b'0', 'menu:update', 'admin', 'admin', '2023-04-16 12:57:52', '2023-04-16 12:57:52', 0);
INSERT INTO `sys_menu` VALUES (1647464398773608449, 1646925326502105089, 2, '删除菜单', '', '', 1000, '', '', b'0', b'0', b'0', 'menu:delete', 'admin', 'admin', '2023-04-16 12:58:16', '2023-04-16 12:58:16', 0);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '名称',
  `code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '角色编码',
  `level` tinyint(2) NOT NULL DEFAULT 0 COMMENT '角色级别',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '描述',
  `data_scope` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '数据权限',
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '创建者',
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '更新者',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` tinyint(2) NOT NULL DEFAULT 0 COMMENT '是否删除（0:否，1：是）',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `idx_name`(`name`) USING BTREE COMMENT '角色名唯一索引',
  UNIQUE INDEX `idx_code`(`code`) USING BTREE COMMENT '角色编码唯一索引'
) ENGINE = InnoDB AUTO_INCREMENT = 1645992957690081283 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1641112080616607746, '超级管理员', 'ADMIN', 1, '超级管理员', '全部', 'system', 'system', '2023-03-30 00:16:25', '2023-04-09 23:07:29', 0);
INSERT INTO `sys_role` VALUES (1641465305769308162, '普通用户', 'USER', 1, '普通用户', '本级', 'system', 'system', '2023-03-30 23:40:01', '2023-04-09 23:07:05', 0);

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `menu_id` bigint(20) NOT NULL COMMENT '菜单ID',
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '创建者',
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '更新者',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` tinyint(2) NOT NULL DEFAULT 0 COMMENT '是否删除（0:否，1：是）',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `idx_roleid_menuid`(`role_id`, `menu_id`) USING BTREE COMMENT '角色菜单唯一索引'
) ENGINE = InnoDB AUTO_INCREMENT = 1647478824704688151 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户角色关联' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES (1647466185769410561, 1641112080616607746, 1646917148414611458, 'admin', 'admin', '2023-04-16 13:05:22', '2023-04-16 13:05:22', 0);
INSERT INTO `sys_role_menu` VALUES (1647466185769410562, 1641112080616607746, 1646917867762913282, 'admin', 'admin', '2023-04-16 13:05:22', '2023-04-16 13:05:22', 0);
INSERT INTO `sys_role_menu` VALUES (1647466185769410563, 1641112080616607746, 1647462845975158785, 'admin', 'admin', '2023-04-16 13:05:22', '2023-04-16 13:05:22', 0);
INSERT INTO `sys_role_menu` VALUES (1647466185769410564, 1641112080616607746, 1647462994583543809, 'admin', 'admin', '2023-04-16 13:05:22', '2023-04-16 13:05:22', 0);
INSERT INTO `sys_role_menu` VALUES (1647466185769410565, 1641112080616607746, 1647463086354915330, 'admin', 'admin', '2023-04-16 13:05:22', '2023-04-16 13:05:22', 0);
INSERT INTO `sys_role_menu` VALUES (1647466185769410566, 1641112080616607746, 1646924883273224193, 'admin', 'admin', '2023-04-16 13:05:22', '2023-04-16 13:05:22', 0);
INSERT INTO `sys_role_menu` VALUES (1647466185769410567, 1641112080616607746, 1647463212578299905, 'admin', 'admin', '2023-04-16 13:05:22', '2023-04-16 13:05:22', 0);
INSERT INTO `sys_role_menu` VALUES (1647466185769410568, 1641112080616607746, 1647463515704844289, 'admin', 'admin', '2023-04-16 13:05:22', '2023-04-16 13:05:22', 0);
INSERT INTO `sys_role_menu` VALUES (1647466185769410569, 1641112080616607746, 1647463605379063810, 'admin', 'admin', '2023-04-16 13:05:22', '2023-04-16 13:05:22', 0);
INSERT INTO `sys_role_menu` VALUES (1647466185769410570, 1641112080616607746, 1647463829535252482, 'admin', 'admin', '2023-04-16 13:05:22', '2023-04-16 13:05:22', 0);
INSERT INTO `sys_role_menu` VALUES (1647466185769410571, 1641112080616607746, 1646925326502105089, 'admin', 'admin', '2023-04-16 13:05:22', '2023-04-16 13:05:22', 0);
INSERT INTO `sys_role_menu` VALUES (1647466185769410572, 1641112080616607746, 1647464115179937793, 'admin', 'admin', '2023-04-16 13:05:22', '2023-04-16 13:05:22', 0);
INSERT INTO `sys_role_menu` VALUES (1647466185769410573, 1641112080616607746, 1647464298655571970, 'admin', 'admin', '2023-04-16 13:05:22', '2023-04-16 13:05:22', 0);
INSERT INTO `sys_role_menu` VALUES (1647466185769410574, 1641112080616607746, 1647464398773608449, 'admin', 'admin', '2023-04-16 13:05:22', '2023-04-16 13:05:22', 0);
INSERT INTO `sys_role_menu` VALUES (1647466185769410575, 1641112080616607746, 1647245235475972097, 'admin', 'admin', '2023-04-16 13:05:22', '2023-04-16 13:05:22', 0);
INSERT INTO `sys_role_menu` VALUES (1647466185769410576, 1641112080616607746, 1647245914567344129, 'admin', 'admin', '2023-04-16 13:05:22', '2023-04-16 13:05:22', 0);
INSERT INTO `sys_role_menu` VALUES (1647466185769410577, 1641112080616607746, 1647246715431944194, 'admin', 'admin', '2023-04-16 13:05:22', '2023-04-16 13:05:22', 0);
INSERT INTO `sys_role_menu` VALUES (1647466185769410578, 1641112080616607746, 1647247627433017346, 'admin', 'admin', '2023-04-16 13:05:22', '2023-04-16 13:05:22', 0);
INSERT INTO `sys_role_menu` VALUES (1647466185769410579, 1641112080616607746, 1646104022067286017, 'admin', 'admin', '2023-04-16 13:05:22', '2023-04-16 13:05:22', 0);
INSERT INTO `sys_role_menu` VALUES (1647466185769410580, 1641112080616607746, 1647248318234886145, 'admin', 'admin', '2023-04-16 13:05:22', '2023-04-16 13:05:22', 0);
INSERT INTO `sys_role_menu` VALUES (1647466185769410581, 1641112080616607746, 1645812231633461249, 'admin', 'admin', '2023-04-16 13:05:22', '2023-04-16 13:05:22', 0);
INSERT INTO `sys_role_menu` VALUES (1647466185769410582, 1641112080616607746, 1645812547225477122, 'admin', 'admin', '2023-04-16 13:05:22', '2023-04-16 13:05:22', 0);
INSERT INTO `sys_role_menu` VALUES (1647466185769410583, 1641112080616607746, 1645813802001850369, 'admin', 'admin', '2023-04-16 13:05:22', '2023-04-16 13:05:22', 0);
INSERT INTO `sys_role_menu` VALUES (1647466185769410584, 1641112080616607746, 1645812820081729537, 'admin', 'admin', '2023-04-16 13:05:22', '2023-04-16 13:05:22', 0);
INSERT INTO `sys_role_menu` VALUES (1647478824704688130, 1641465305769308162, 1646917148414611458, 'admin', 'admin', '2023-04-16 13:55:35', '2023-04-16 13:55:35', 0);
INSERT INTO `sys_role_menu` VALUES (1647478824704688131, 1641465305769308162, 1646917867762913282, 'admin', 'admin', '2023-04-16 13:55:35', '2023-04-16 13:55:35', 0);
INSERT INTO `sys_role_menu` VALUES (1647478824704688132, 1641465305769308162, 1647462845975158785, 'admin', 'admin', '2023-04-16 13:55:35', '2023-04-16 13:55:35', 0);
INSERT INTO `sys_role_menu` VALUES (1647478824704688133, 1641465305769308162, 1647462994583543809, 'admin', 'admin', '2023-04-16 13:55:35', '2023-04-16 13:55:35', 0);
INSERT INTO `sys_role_menu` VALUES (1647478824704688134, 1641465305769308162, 1647463086354915330, 'admin', 'admin', '2023-04-16 13:55:35', '2023-04-16 13:55:35', 0);
INSERT INTO `sys_role_menu` VALUES (1647478824704688135, 1641465305769308162, 1646924883273224193, 'admin', 'admin', '2023-04-16 13:55:35', '2023-04-16 13:55:35', 0);
INSERT INTO `sys_role_menu` VALUES (1647478824704688136, 1641465305769308162, 1647463212578299905, 'admin', 'admin', '2023-04-16 13:55:35', '2023-04-16 13:55:35', 0);
INSERT INTO `sys_role_menu` VALUES (1647478824704688137, 1641465305769308162, 1647463515704844289, 'admin', 'admin', '2023-04-16 13:55:35', '2023-04-16 13:55:35', 0);
INSERT INTO `sys_role_menu` VALUES (1647478824704688138, 1641465305769308162, 1647463605379063810, 'admin', 'admin', '2023-04-16 13:55:35', '2023-04-16 13:55:35', 0);
INSERT INTO `sys_role_menu` VALUES (1647478824704688139, 1641465305769308162, 1647463829535252482, 'admin', 'admin', '2023-04-16 13:55:35', '2023-04-16 13:55:35', 0);
INSERT INTO `sys_role_menu` VALUES (1647478824704688140, 1641465305769308162, 1646925326502105089, 'admin', 'admin', '2023-04-16 13:55:35', '2023-04-16 13:55:35', 0);
INSERT INTO `sys_role_menu` VALUES (1647478824704688141, 1641465305769308162, 1647464115179937793, 'admin', 'admin', '2023-04-16 13:55:35', '2023-04-16 13:55:35', 0);
INSERT INTO `sys_role_menu` VALUES (1647478824704688142, 1641465305769308162, 1647464298655571970, 'admin', 'admin', '2023-04-16 13:55:35', '2023-04-16 13:55:35', 0);
INSERT INTO `sys_role_menu` VALUES (1647478824704688143, 1641465305769308162, 1647464398773608449, 'admin', 'admin', '2023-04-16 13:55:35', '2023-04-16 13:55:35', 0);
INSERT INTO `sys_role_menu` VALUES (1647478824704688144, 1641465305769308162, 1647247627433017346, 'admin', 'admin', '2023-04-16 13:55:35', '2023-04-16 13:55:35', 0);
INSERT INTO `sys_role_menu` VALUES (1647478824704688145, 1641465305769308162, 1646104022067286017, 'admin', 'admin', '2023-04-16 13:55:35', '2023-04-16 13:55:35', 0);
INSERT INTO `sys_role_menu` VALUES (1647478824704688146, 1641465305769308162, 1647248318234886145, 'admin', 'admin', '2023-04-16 13:55:35', '2023-04-16 13:55:35', 0);
INSERT INTO `sys_role_menu` VALUES (1647478824704688147, 1641465305769308162, 1645812231633461249, 'admin', 'admin', '2023-04-16 13:55:35', '2023-04-16 13:55:35', 0);
INSERT INTO `sys_role_menu` VALUES (1647478824704688148, 1641465305769308162, 1645812547225477122, 'admin', 'admin', '2023-04-16 13:55:35', '2023-04-16 13:55:35', 0);
INSERT INTO `sys_role_menu` VALUES (1647478824704688149, 1641465305769308162, 1645813802001850369, 'admin', 'admin', '2023-04-16 13:55:35', '2023-04-16 13:55:35', 0);
INSERT INTO `sys_role_menu` VALUES (1647478824704688150, 1641465305769308162, 1645812820081729537, 'admin', 'admin', '2023-04-16 13:55:35', '2023-04-16 13:55:35', 0);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `username` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '用户名',
  `password` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '密码',
  `nick_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '昵称',
  `gender` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '性别',
  `phone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '手机号码',
  `email` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '邮箱',
  `avatar` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT 'http://java668.com/storage/avatars/000/000/001_240.jpg' COMMENT '头像',
  `enabled` tinyint(2) NOT NULL DEFAULT 0 COMMENT '状态：0启用、1禁用',
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '创建者',
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '更新者',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` tinyint(2) NOT NULL DEFAULT 0 COMMENT '是否删除（0:否，1：是）',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `idx_username`(`username`) USING BTREE COMMENT '用户名唯一索引',
  UNIQUE INDEX `idx_email`(`email`) USING BTREE COMMENT '电子邮箱唯一索引'
) ENGINE = InnoDB AUTO_INCREMENT = 1647478735722528770 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统用户' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1639891860430286854, 'admin', '{bcrypt}$2a$10$L6hYXIuAnIWNzWCF.lswhefWe/JyineHKwL3B.zQWzooQiqD.m.hS', '超级管理员', '男', '13800009999', 'admin@163.com', 'http://java668.com/storage/avatars/000/000/001_240.jpg', 0, 'system', 'system', '2023-03-26 00:15:23', '2023-04-09 23:03:57', 0);
INSERT INTO `sys_user` VALUES (1647478735722528769, 'test', '{bcrypt}$2a$10$M.CppWcVCUhfZPb7.rglM.2emd.LUToQM3KlRJFRYfeHjz0EJZIdK', '普通用户', '男', '13800138000', 'user@163.com', 'http://java668.com/storage/avatars/000/000/001_240.jpg', 0, 'admin', 'admin', '2023-04-16 13:55:14', '2023-04-16 13:55:14', 0);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '创建者',
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '更新者',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` tinyint(2) NOT NULL DEFAULT 0 COMMENT '是否删除（0:否，1：是）',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `idx_userid_roleid`(`user_id`, `role_id`) USING BTREE COMMENT '用户id角色id唯一索引'
) ENGINE = InnoDB AUTO_INCREMENT = 1647478736058073091 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户角色关联' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (1642178456714231810, 1642178456319967233, 1641112080616607746, 'system', 'system', '2023-04-01 22:53:49', '2023-04-01 22:53:49', 0);
INSERT INTO `sys_user_role` VALUES (1642178456714231811, 1642178456319967233, 1641465305769308162, 'system', 'system', '2023-04-01 22:53:49', '2023-04-01 22:53:49', 0);
INSERT INTO `sys_user_role` VALUES (1642193090204667906, 1642193089843957761, 1641112080616607746, 'system', 'system', '2023-04-01 23:51:58', '2023-04-01 23:51:58', 0);
INSERT INTO `sys_user_role` VALUES (1642193090204667907, 1642193089843957761, 1641465305769308162, 'system', 'system', '2023-04-01 23:51:58', '2023-04-01 23:51:58', 0);
INSERT INTO `sys_user_role` VALUES (1642481684953743361, 1642481684609810434, 1641112080616607746, 'system', 'system', '2023-04-02 18:58:44', '2023-04-02 18:58:44', 0);
INSERT INTO `sys_user_role` VALUES (1642481684953743362, 1642481684609810434, 1641465305769308162, 'system', 'system', '2023-04-02 18:58:44', '2023-04-02 18:58:44', 0);
INSERT INTO `sys_user_role` VALUES (1642525847590137857, 1639891860430286855, 1641112080616607746, 'system', 'system', '2023-04-02 21:54:13', '2023-04-02 21:54:13', 0);
INSERT INTO `sys_user_role` VALUES (1642568728493187073, 1642160690418417666, 1641112080616607746, 'system', 'system', '2023-04-03 00:44:37', '2023-04-03 00:44:37', 0);
INSERT INTO `sys_user_role` VALUES (1642568837079523330, 1642168051807813634, 1641465305769308162, 'system', 'system', '2023-04-03 00:45:03', '2023-04-03 00:45:03', 0);
INSERT INTO `sys_user_role` VALUES (1643569855133241346, 1643569854785114113, 1641112080616607746, 'system', 'system', '2023-04-05 19:02:44', '2023-04-05 19:02:44', 0);
INSERT INTO `sys_user_role` VALUES (1643569855133241347, 1643569854785114113, 1641465305769308162, 'system', 'system', '2023-04-05 19:02:44', '2023-04-05 19:02:44', 0);
INSERT INTO `sys_user_role` VALUES (1643570040752164866, 1643570040454369281, 1641112080616607746, 'system', 'system', '2023-04-05 19:03:28', '2023-04-05 19:03:28', 0);
INSERT INTO `sys_user_role` VALUES (1643570040752164867, 1643570040454369281, 1641465305769308162, 'system', 'system', '2023-04-05 19:03:28', '2023-04-05 19:03:28', 0);
INSERT INTO `sys_user_role` VALUES (1643570304586469377, 1643570304292868097, 1641112080616607746, 'system', 'system', '2023-04-05 19:04:31', '2023-04-05 19:04:31', 0);
INSERT INTO `sys_user_role` VALUES (1643570304586469378, 1643570304292868097, 1641465305769308162, 'system', 'system', '2023-04-05 19:04:31', '2023-04-05 19:04:31', 0);
INSERT INTO `sys_user_role` VALUES (1645080111571046402, 1639891860430286854, 1641112080616607746, 'system', 'system', '2023-04-09 23:03:57', '2023-04-09 23:03:57', 0);
INSERT INTO `sys_user_role` VALUES (1645080111571046403, 1639891860430286854, 1641465305769308162, 'system', 'system', '2023-04-09 23:03:57', '2023-04-09 23:03:57', 0);
INSERT INTO `sys_user_role` VALUES (1647478736058073090, 1647478735722528769, 1641465305769308162, 'admin', 'admin', '2023-04-16 13:55:14', '2023-04-16 13:55:14', 0);

CREATE TABLE `gene_table` (
  `table_id` bigint NOT NULL AUTO_INCREMENT COMMENT '编号',
  `table_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '表名称',
  `table_comment` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '表描述',
  `sub_table_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '关联子表的表名',
  `sub_table_fk_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '子表关联的外键名',
  `class_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '实体类名称',
  `tpl_category` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT 'crud' COMMENT '使用的模板（crud单表操作 tree树表操作）',
  `package_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '生成包路径',
  `module_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '生成模块名',
  `business_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '生成业务名',
  `function_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '生成功能名',
  `function_author` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '生成功能作者',
  `gen_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '0' COMMENT '生成代码方式（0zip压缩包 1自定义路径）',
  `gen_path` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '/' COMMENT '生成路径（不填默认项目路径）',
  `options` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '其它生成选项',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`table_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='代码生成业务表';

CREATE TABLE `gene_table_column` (
  `column_id` bigint NOT NULL AUTO_INCREMENT COMMENT '编号',
  `table_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '归属表编号',
  `column_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '列名称',
  `column_comment` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '列描述',
  `column_type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '列类型',
  `java_type` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'JAVA类型',
  `java_field` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'JAVA字段名',
  `is_pk` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '是否主键（1是）',
  `is_increment` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '是否自增（1是）',
  `is_required` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '是否必填（1是）',
  `is_insert` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '是否为插入字段（1是）',
  `is_edit` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '是否编辑字段（1是）',
  `is_list` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '是否列表字段（1是）',
  `is_query` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '是否查询字段（1是）',
  `query_type` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT 'EQ' COMMENT '查询方式（等于、不等于、大于、小于、范围）',
  `html_type` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '显示类型（文本框、文本域、下拉框、复选框、单选框、日期控件）',
  `dict_type` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '字典类型',
  `sort` int DEFAULT NULL COMMENT '排序',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`column_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='代码生成业务表字段';

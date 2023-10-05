DROP TABLE IF EXISTS `t_permission`;
CREATE TABLE `t_permission`  (
                               `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
                               `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '名称',
                               `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '接口路径',
                               `method` tinyint(3) UNSIGNED NOT NULL DEFAULT 0 COMMENT '请求方式（0-get；1-post）',
                               `service` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '服务名',
                               `parent_id` int(10) NOT NULL DEFAULT 0 COMMENT '父级权限id',
                               PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统权限表' ROW_FORMAT = Dynamic;

DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role`  (
                         `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
                         `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '名称',
                         `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
                         PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统角色表' ROW_FORMAT = Dynamic;

DROP TABLE IF EXISTS `t_role_permission`;
CREATE TABLE `t_role_permission`  (
                                    `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
                                    `role_id` int(10) UNSIGNED NOT NULL COMMENT '角色id',
                                    `permission_id` int(10) UNSIGNED NOT NULL COMMENT '权限id',
                                    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色-权限关联表' ROW_FORMAT = Dynamic;

DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role`  (
                              `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
                              `role_id` int(10) UNSIGNED NOT NULL COMMENT '角色id，数据来源于role表的主键',
                              `user_id` int(10) UNSIGNED NOT NULL COMMENT '用户id，数据来源于user表的主键',
                              PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户-角色关系表表' ROW_FORMAT = Dynamic;

DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
                         `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
                         `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '姓名',
                         `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '用户名',
                         `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '密码',
                         `phone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '手机号',
                         `gender` tinyint(4) UNSIGNED NOT NULL COMMENT '性别',
                         `enabled` tinyint(4) UNSIGNED NOT NULL COMMENT '是否启用（0-未启用；1-启用中）',
                         `last_login_time` datetime NULL DEFAULT NULL COMMENT '上一次登录时间',
                         PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;


INSERT INTO `role` VALUES (1, '超级管理员', '超管，拥有最高权限');
INSERT INTO `role` VALUES (2, '系统管理员', '管理员，拥有操作权限');

INSERT INTO `user` VALUES (1, '超级管理员', 'admin', '123456', '13500000001', 1, 1, '2023-10-01 00:00:01');
INSERT INTO `user` VALUES (2, '系统管理员', 'normal', '123456', '13800000001', 2, 1, '2023-10-01 00:00:01');

INSERT INTO `user_role` VALUES (1, 1, 1);
INSERT INTO `user_role` VALUES (2, 2, 2);

INSERT INTO `permission` VALUES (1, '用户管理', '/user/manager', 0, 'manager', 0);
INSERT INTO `permission` VALUES (2, '用户列表查询', '/user/list', 1, 'list', 1);
INSERT INTO `permission` VALUES (3, '删除用户', '/user/delete', 0, 'delete', 1);
INSERT INTO `permission` VALUES (4, '更新用户', '/user/update', 1, 'update', 1);
INSERT INTO `permission` VALUES (5, '新增用户', '/user/save', 1, 'save', 1);
INSERT INTO `permission` VALUES (6, '用户详情', '/user/getById', 0, 'getById', 1);

INSERT INTO `role_permission` VALUES (1, 1, 1);
INSERT INTO `role_permission` VALUES (2, 1, 2);
INSERT INTO `role_permission` VALUES (3, 1, 3);
INSERT INTO `role_permission` VALUES (4, 1, 4);
INSERT INTO `role_permission` VALUES (5, 1, 5);
INSERT INTO `role_permission` VALUES (6, 1, 6);

INSERT INTO `role_permission` VALUES (7, 2, 1);
INSERT INTO `role_permission` VALUES (8, 2, 2);
INSERT INTO `role_permission` VALUES (9, 2, 3);
INSERT INTO `role_permission` VALUES (10, 2, 4);
INSERT INTO `role_permission` VALUES (11, 2, 5);
INSERT INTO `role_permission` VALUES (12, 2, 6);

/*
Navicat MySQL Data Transfer

Source Server         : local_mysql
Source Server Version : 50723
Source Host           : localhost:3306
Source Database       : boohome_wechat_shiro

Target Server Type    : MYSQL
Target Server Version : 50723
File Encoding         : 65001

Date: 2018-11-08 18:03:00
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for shiro_test
-- ----------------------------
DROP TABLE IF EXISTS `shiro_test`;
CREATE TABLE `shiro_test` (
  `ID` int(11) NOT NULL,
  `REMARK` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of shiro_test
-- ----------------------------
INSERT INTO `shiro_test` VALUES ('1', '测试用例shiro');

-- ----------------------------
-- Table structure for sys_resources
-- ----------------------------
DROP TABLE IF EXISTS `sys_resources`;
CREATE TABLE `sys_resources` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) DEFAULT NULL COMMENT '资源名称',
  `res_url` varchar(255) DEFAULT NULL COMMENT '资源url',
  `user_type` int(11) DEFAULT NULL COMMENT '资源类型   1:菜单    2：按钮',
  `parent_id` int(11) DEFAULT NULL COMMENT '父资源',
  `user_sort` int(11) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_resources
-- ----------------------------
INSERT INTO `sys_resources` VALUES ('1', '系统设置', '/system', '0', '0', '1');
INSERT INTO `sys_resources` VALUES ('2', '用户管理', '/usersPage', '1', '1', '2');
INSERT INTO `sys_resources` VALUES ('3', '角色管理', '/rolesPage', '1', '1', '3');
INSERT INTO `sys_resources` VALUES ('4', '资源管理', '/resourcesPage', '1', '1', '4');
INSERT INTO `sys_resources` VALUES ('5', '添加用户', '/users/add', '2', '2', '5');
INSERT INTO `sys_resources` VALUES ('6', '删除用户', '/users/delete', '2', '2', '6');
INSERT INTO `sys_resources` VALUES ('7', '添加角色', '/roles/add', '2', '3', '7');
INSERT INTO `sys_resources` VALUES ('8', '删除角色', '/roles/delete', '2', '3', '8');
INSERT INTO `sys_resources` VALUES ('9', '添加资源', '/resources/add', '2', '4', '9');
INSERT INTO `sys_resources` VALUES ('10', '删除资源', '/resources/delete', '2', '4', '10');
INSERT INTO `sys_resources` VALUES ('11', '分配角色', '/users/saveUserRoles', '2', '2', '11');
INSERT INTO `sys_resources` VALUES ('13', '分配权限', '/roles/saveRoleResources', '2', '3', '12');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_desc` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', 'SUPERADMINISTATOR');
INSERT INTO `sys_role` VALUES ('2', 'ADMINISTATOR');
INSERT INTO `sys_role` VALUES ('3', 'USER');

-- ----------------------------
-- Table structure for sys_role_resources
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_resources`;
CREATE TABLE `sys_role_resources` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL,
  `resources_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role_resources
-- ----------------------------
INSERT INTO `sys_role_resources` VALUES ('1', '1', '2');
INSERT INTO `sys_role_resources` VALUES ('2', '1', '3');
INSERT INTO `sys_role_resources` VALUES ('3', '1', '4');
INSERT INTO `sys_role_resources` VALUES ('4', '1', '5');
INSERT INTO `sys_role_resources` VALUES ('5', '1', '6');
INSERT INTO `sys_role_resources` VALUES ('6', '1', '7');
INSERT INTO `sys_role_resources` VALUES ('7', '1', '8');
INSERT INTO `sys_role_resources` VALUES ('8', '1', '9');
INSERT INTO `sys_role_resources` VALUES ('9', '1', '10');
INSERT INTO `sys_role_resources` VALUES ('10', '1', '11');
INSERT INTO `sys_role_resources` VALUES ('11', '1', '13');
INSERT INTO `sys_role_resources` VALUES ('12', '2', '2');
INSERT INTO `sys_role_resources` VALUES ('13', '2', '3');
INSERT INTO `sys_role_resources` VALUES ('14', '2', '4');
INSERT INTO `sys_role_resources` VALUES ('15', '2', '9');
INSERT INTO `sys_role_resources` VALUES ('16', '3', '2');
INSERT INTO `sys_role_resources` VALUES ('17', '3', '3');
INSERT INTO `sys_role_resources` VALUES ('18', '3', '4');
INSERT INTO `sys_role_resources` VALUES ('19', '3', '5');
INSERT INTO `sys_role_resources` VALUES ('20', '3', '7');
INSERT INTO `sys_role_resources` VALUES ('21', '3', '8');
INSERT INTO `sys_role_resources` VALUES ('22', '3', '9');
INSERT INTO `sys_role_resources` VALUES ('23', '3', '10');
INSERT INTO `sys_role_resources` VALUES ('24', '9', '9');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(33) DEFAULT NULL,
  `pass_word` varchar(33) DEFAULT NULL,
  `user_enable` int(10) DEFAULT '1' COMMENT '是否启用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin1', 'admin1', '1');
INSERT INTO `sys_user` VALUES ('2', 'user1', 'user1', '1');
INSERT INTO `sys_user` VALUES ('3', 'user2', '121', '0');
INSERT INTO `sys_user` VALUES ('4', 'user3', 'user3', '1');
INSERT INTO `sys_user` VALUES ('5', 'user4', 'user4', '1');
INSERT INTO `sys_user` VALUES ('6', 'user5', 'user5', '1');
INSERT INTO `sys_user` VALUES ('7', 'user6', 'user6', '1');
INSERT INTO `sys_user` VALUES ('8', 'user7', 'user7', '1');
INSERT INTO `sys_user` VALUES ('9', 'user8', 'user8', '1');
INSERT INTO `sys_user` VALUES ('10', 'user9', 'user9', '1');
INSERT INTO `sys_user` VALUES ('11', 'user10', 'user10', '1');
INSERT INTO `sys_user` VALUES ('12', 'user11', 'user11', '1');
INSERT INTO `sys_user` VALUES ('13', 'user12', 'user12', '1');
INSERT INTO `sys_user` VALUES ('14', 'user13', 'user13', '1');
INSERT INTO `sys_user` VALUES ('15', 'user14', 'user14', '1');
INSERT INTO `sys_user` VALUES ('16', 'user15', 'user15', '1');
INSERT INTO `sys_user` VALUES ('17', 'user16', 'user16', '1');
INSERT INTO `sys_user` VALUES ('18', 'user17', 'user17', '1');
INSERT INTO `sys_user` VALUES ('19', 'user18', 'user18', '1');
INSERT INTO `sys_user` VALUES ('21', 'user20', 'user20', '1');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('2', '1', '1');
INSERT INTO `sys_user_role` VALUES ('3', '2', '2');
INSERT INTO `sys_user_role` VALUES ('4', '3', '1');

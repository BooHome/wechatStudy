/*
Navicat MySQL Data Transfer

Source Server         : local_mysql
Source Server Version : 50723
Source Host           : localhost:3306
Source Database       : boohome_wechat_base

Target Server Type    : MYSQL
Target Server Version : 50723
File Encoding         : 65001

Date: 2018-11-08 18:02:52
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for base_test
-- ----------------------------
DROP TABLE IF EXISTS `base_test`;
CREATE TABLE `base_test` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `REMARK` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of base_test
-- ----------------------------
INSERT INTO `base_test` VALUES ('1', '测试用例base');

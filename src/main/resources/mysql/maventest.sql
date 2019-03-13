/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : maventest

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2019-03-07 16:10:06
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for ceshi
-- ----------------------------
DROP TABLE IF EXISTS `ceshi`;
CREATE TABLE `ceshi` (
  `json` json DEFAULT NULL,
  `longtext` longtext
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ceshi
-- ----------------------------
INSERT INTO `ceshi` VALUES ('2', 'll');
INSERT INTO `ceshi` VALUES (null, null);

-- ----------------------------
-- Table structure for logs
-- ----------------------------
DROP TABLE IF EXISTS `logs`;
CREATE TABLE `logs` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `operator` int(11) DEFAULT NULL,
  `operatorType` varchar(255) DEFAULT NULL,
  `operatorObject` varchar(255) DEFAULT NULL,
  `operatorTrans` int(11) NOT NULL,
  `time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `dataCount` int(11) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of logs
-- ----------------------------
INSERT INTO `logs` VALUES ('1', '1', '测试', '测试1', '0', '2018-11-23 13:11:53', '5', '初始化日志');
INSERT INTO `logs` VALUES ('2', '1', '测试', '测试2', '1', '2018-11-23 13:12:04', '5', '初始化日志');
INSERT INTO `logs` VALUES ('3', '1', '测试', '测试3', '2', '2018-11-23 13:12:10', '5', '初始化日志');
INSERT INTO `logs` VALUES ('10', '1', '导入', '用户导入', '2', '2018-11-23 17:08:44', null, null);
INSERT INTO `logs` VALUES ('11', '1', '导入', '用户导入', '2', '2018-11-23 17:09:21', null, null);

-- ----------------------------
-- Table structure for logsdetail
-- ----------------------------
DROP TABLE IF EXISTS `logsdetail`;
CREATE TABLE `logsdetail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `logsId` int(11) NOT NULL,
  `operator` int(11) DEFAULT NULL,
  `operatorTime` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `operatorTrans` int(11) DEFAULT NULL,
  `message` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `undefined1` varchar(255) DEFAULT NULL,
  `undefined2` varchar(255) DEFAULT NULL,
  `undefined3` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of logsdetail
-- ----------------------------
INSERT INTO `logsdetail` VALUES ('1', '3', '1', '2018-11-23 14:11:35', null, '初始化日志1', null, null, null, null);
INSERT INTO `logsdetail` VALUES ('2', '3', '1', '2018-11-23 14:11:35', null, '初始化日志2', null, null, null, null);
INSERT INTO `logsdetail` VALUES ('3', '10', null, '2018-11-23 17:08:44', '2', '新增用户0位，更新用户4位！', null, null, null, null);
INSERT INTO `logsdetail` VALUES ('4', '11', null, '2018-11-23 17:09:22', '2', '新增用户0位，更新用户4位！', null, null, null, null);

-- ----------------------------
-- Table structure for module
-- ----------------------------
DROP TABLE IF EXISTS `module`;
CREATE TABLE `module` (
  `level` int(11) NOT NULL,
  `moduleNo` varchar(255) NOT NULL,
  `moduleName` varchar(255) DEFAULT NULL,
  `pardentNo` varchar(255) DEFAULT NULL,
  `pardentName` varchar(255) DEFAULT NULL,
  `isFile` int(11) NOT NULL,
  `url` varchar(255) DEFAULT NULL,
  `iconCls` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`moduleNo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of module
-- ----------------------------
INSERT INTO `module` VALUES ('2', 'bootstrap', 'bootstrap', 'demo', null, '1', 'bootstrap', null, null);
INSERT INTO `module` VALUES ('1', 'demo', '练习', null, null, '0', null, null, '');
INSERT INTO `module` VALUES ('2', 'des', '说明', 'sys', null, '1', 'des', null, null);
INSERT INTO `module` VALUES ('2', 'easyui', 'easyui', 'demo', null, '1', 'lianxiEasyui', null, null);
INSERT INTO `module` VALUES ('1', 'notice', '日志', null, null, '1', 'queryLogList', null, null);
INSERT INTO `module` VALUES ('1', 'product', '商品', null, null, '0', null, null, 'closed');
INSERT INTO `module` VALUES ('2', 'productList', '商品管理', 'product', '商品', '1', 'productList', null, null);
INSERT INTO `module` VALUES ('1', 'sys', '系统', null, null, '0', null, null, 'closed');
INSERT INTO `module` VALUES ('3', 'syspeizhi', '系统配置', 'system', '系统管理', '1', 'systemConfigMasterList', null, null);
INSERT INTO `module` VALUES ('2', 'system', '系统管理', 'sys', '系统', '0', null, null, 'closed');
INSERT INTO `module` VALUES ('3', 'transactionLogList', '作业日志', 'system', '系统管理', '1', 'transactionLogList', null, null);
INSERT INTO `module` VALUES ('3', 'userList', '用户管理', 'system', '系统管理', '1', 'userList', null, null);

-- ----------------------------
-- Table structure for module3
-- ----------------------------
DROP TABLE IF EXISTS `module3`;
CREATE TABLE `module3` (
  `level` int(11) NOT NULL,
  `moduleNo` varchar(255) NOT NULL,
  `moduleName` varchar(255) DEFAULT NULL,
  `pardentNo` varchar(255) DEFAULT NULL,
  `pardentName` varchar(255) DEFAULT NULL,
  `isFile` int(11) NOT NULL,
  `url` varchar(255) DEFAULT NULL,
  `iconCls` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`moduleNo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of module3
-- ----------------------------
INSERT INTO `module3` VALUES ('2', 'pro2', '商品1', 'product', null, '0', null, null, null);
INSERT INTO `module3` VALUES ('3', 'pro3', '商品3', 'pro2', null, '1', null, null, null);
INSERT INTO `module3` VALUES ('1', 'product', '商品', null, null, '0', null, null, 'closed');
INSERT INTO `module3` VALUES ('1', 'sys', '系统', null, null, '0', null, null, null);
INSERT INTO `module3` VALUES ('2', 'sys1', '系统1', 'sys', null, '0', null, null, null);
INSERT INTO `module3` VALUES ('3', 'sys2', '系统2', 'sys1', null, '1', 'sys2', null, null);

-- ----------------------------
-- Table structure for tes
-- ----------------------------
DROP TABLE IF EXISTS `tes`;
CREATE TABLE `tes` (
  `tes` int(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tes
-- ----------------------------
INSERT INTO `tes` VALUES ('1111111111');

-- ----------------------------
-- Table structure for userinfo
-- ----------------------------
DROP TABLE IF EXISTS `userinfo`;
CREATE TABLE `userinfo` (
  `userId` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(255) NOT NULL,
  `passWord` varchar(255) NOT NULL,
  `isUse` int(11) NOT NULL DEFAULT '1',
  `creatDate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of userinfo
-- ----------------------------
INSERT INTO `userinfo` VALUES ('2', 'chengliude', 'cld7758258', '0', '2018-12-11 14:01:45');
INSERT INTO `userinfo` VALUES ('3', 'qq1', 'qq1', '0', '2018-12-12 16:43:49');
INSERT INTO `userinfo` VALUES ('4', 'qq2', 'qq2', '0', '2018-12-12 16:43:52');
INSERT INTO `userinfo` VALUES ('5', 'qq3', '22', '0', '2018-12-12 16:43:54');
INSERT INTO `userinfo` VALUES ('6', 'qq4', '44', '0', '2018-12-12 16:43:55');
INSERT INTO `userinfo` VALUES ('7', 'qq5', '55', '0', '2018-12-12 16:44:00');
INSERT INTO `userinfo` VALUES ('8', 'qq6', '44', '0', '2018-12-12 16:44:02');
INSERT INTO `userinfo` VALUES ('9', 'qq7', '77', '0', '2019-03-01 15:12:39');
INSERT INTO `userinfo` VALUES ('10', 'qwe1', 'qwe1', '1', '2018-12-12 16:44:08');
INSERT INTO `userinfo` VALUES ('11', 'qwe2', 'qwe2', '1', '2018-12-12 16:44:11');
INSERT INTO `userinfo` VALUES ('12', 'qwe3', 'qwe3', '1', '2018-11-15 17:19:47');
INSERT INTO `userinfo` VALUES ('13', 'qwe4', 'qwe4', '1', '2018-11-15 17:19:47');
INSERT INTO `userinfo` VALUES ('14', 'rr3', '3333', '1', '2018-11-19 16:53:14');

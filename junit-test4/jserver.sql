/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 80011
Source Host           : localhost:3306
Source Database       : jserver

Target Server Type    : MYSQL
Target Server Version : 80011
File Encoding         : 65001

Date: 2020-06-29 10:36:21
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_act
-- ----------------------------
DROP TABLE IF EXISTS `t_act`;
CREATE TABLE `t_act` (
  `id` int(10) unsigned DEFAULT NULL,
  `actno` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `balance` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

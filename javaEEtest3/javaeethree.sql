/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 80011
Source Host           : localhost:3306
Source Database       : javaeethree

Target Server Type    : MYSQL
Target Server Version : 80011
File Encoding         : 65001

Date: 2020-06-29 10:36:38
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for adminlogin
-- ----------------------------
DROP TABLE IF EXISTS `adminlogin`;
CREATE TABLE `adminlogin` (
  `adminName` varchar(20) NOT NULL,
  `adminPassword` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for beforelogin
-- ----------------------------
DROP TABLE IF EXISTS `beforelogin`;
CREATE TABLE `beforelogin` (
  `uname` varchar(20) NOT NULL,
  `upassword` varchar(20) NOT NULL,
  `utelephone` varchar(20) NOT NULL,
  `uemail` varchar(30) NOT NULL,
  PRIMARY KEY (`uname`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for choosecuorse
-- ----------------------------
DROP TABLE IF EXISTS `choosecuorse`;
CREATE TABLE `choosecuorse` (
  `cuorsename` varchar(30) NOT NULL,
  `teachname` varchar(30) NOT NULL,
  PRIMARY KEY (`cuorsename`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `uemail` varchar(30) NOT NULL,
  `uword` varchar(30) NOT NULL,
  PRIMARY KEY (`uemail`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for download
-- ----------------------------
DROP TABLE IF EXISTS `download`;
CREATE TABLE `download` (
  `uname` varchar(20) NOT NULL,
  `filename` varchar(30) NOT NULL,
  `costcoin` int(11) NOT NULL,
  PRIMARY KEY (`uname`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for money
-- ----------------------------
DROP TABLE IF EXISTS `money`;
CREATE TABLE `money` (
  `uname` varchar(20) NOT NULL,
  `uemail` varchar(30) NOT NULL,
  `uprice` double NOT NULL,
  `ulevel` varchar(20) NOT NULL,
  `uuse` varchar(20) NOT NULL,
  PRIMARY KEY (`uname`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for reallyupload
-- ----------------------------
DROP TABLE IF EXISTS `reallyupload`;
CREATE TABLE `reallyupload` (
  `uploaduid` int(11) NOT NULL AUTO_INCREMENT,
  `uprovidename` varchar(20) NOT NULL,
  `filename` varchar(30) NOT NULL,
  `filepath` varchar(50) NOT NULL,
  `offer` int(11) NOT NULL,
  `adescription` varchar(30) NOT NULL,
  PRIMARY KEY (`uploaduid`)
) ENGINE=InnoDB AUTO_INCREMENT=125 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for userpay
-- ----------------------------
DROP TABLE IF EXISTS `userpay`;
CREATE TABLE `userpay` (
  `uname` varchar(20) NOT NULL,
  `uemail` varchar(30) NOT NULL,
  `uploadnumber` int(11) NOT NULL,
  `downloadnumber` int(11) NOT NULL,
  `coin` double NOT NULL,
  PRIMARY KEY (`uname`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

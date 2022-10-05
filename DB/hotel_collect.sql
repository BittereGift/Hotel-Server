/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80029
 Source Host           : localhost:3306
 Source Schema         : hotel_collect

 Target Server Type    : MySQL
 Target Server Version : 80029
 File Encoding         : 65001

 Date: 06/10/2022 02:56:35
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for collect
-- ----------------------------
DROP TABLE IF EXISTS `collect`;
CREATE TABLE `collect`  (
  `user_id` int NOT NULL,
  `room_id` int NOT NULL,
  PRIMARY KEY (`user_id`, `room_id`) USING BTREE,
  UNIQUE INDEX `user_id`(`user_id` ASC, `room_id` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of collect
-- ----------------------------
INSERT INTO `collect` VALUES (1, 1);
INSERT INTO `collect` VALUES (1, 2);
INSERT INTO `collect` VALUES (2, 1);
INSERT INTO `collect` VALUES (3, 1);

SET FOREIGN_KEY_CHECKS = 1;

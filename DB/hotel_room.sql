/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80029
 Source Host           : localhost:3306
 Source Schema         : hotel_room

 Target Server Type    : MySQL
 Target Server Version : 80029
 File Encoding         : 65001

 Date: 07/10/2022 23:35:01
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for room
-- ----------------------------
DROP TABLE IF EXISTS `room`;
CREATE TABLE `room`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `hotel_id` int NULL DEFAULT NULL,
  `type_id` int NULL DEFAULT NULL,
  `position` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of room
-- ----------------------------
INSERT INTO `room` VALUES (1, 1, 1, '101');
INSERT INTO `room` VALUES (2, 1, 1, '102');
INSERT INTO `room` VALUES (3, 2, 1, '101');
INSERT INTO `room` VALUES (4, 2, 2, '102');
INSERT INTO `room` VALUES (5, 3, 2, '101');
INSERT INTO `room` VALUES (6, 3, 1, '102');

-- ----------------------------
-- Table structure for room_book_time
-- ----------------------------
DROP TABLE IF EXISTS `room_book_time`;
CREATE TABLE `room_book_time`  (
  `order_id` int NOT NULL,
  `room_id` int NOT NULL,
  `start_time` datetime NOT NULL,
  `end_time` datetime NOT NULL,
  PRIMARY KEY (`order_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of room_book_time
-- ----------------------------
INSERT INTO `room_book_time` VALUES (1, 1, '2022-10-05 00:00:00', '2022-10-08 00:00:00');
INSERT INTO `room_book_time` VALUES (25, 2, '2022-11-01 08:00:00', '2022-11-06 08:00:00');

-- ----------------------------
-- Table structure for room_type
-- ----------------------------
DROP TABLE IF EXISTS `room_type`;
CREATE TABLE `room_type`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `price` decimal(10, 2) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of room_type
-- ----------------------------
INSERT INTO `room_type` VALUES (1, '大床房', 1000.00);
INSERT INTO `room_type` VALUES (2, '双人床', 2000.00);

SET FOREIGN_KEY_CHECKS = 1;

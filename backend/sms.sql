/*
 Navicat Premium Dump SQL

 Source Server         : common
 Source Server Type    : MySQL
 Source Server Version : 80030 (8.0.30)
 Source Host           : localhost:3306
 Source Schema         : sms

 Target Server Type    : MySQL
 Target Server Version : 80030 (8.0.30)
 File Encoding         : 65001

 Date: 15/12/2024 14:23:43
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for  t_course_and_student
-- ----------------------------
DROP TABLE IF EXISTS ` t_course_and_student`;
CREATE TABLE ` t_course_and_student`  (
  `course_id` int NOT NULL,
  `student_id` int NULL DEFAULT NULL,
  `teacher_id` int NULL DEFAULT NULL,
  `id` int NOT NULL,
  PRIMARY KEY (`id` DESC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of  t_course_and_student
-- ----------------------------

-- ----------------------------
-- Table structure for t_admin
-- ----------------------------
DROP TABLE IF EXISTS `t_admin`;
CREATE TABLE `t_admin`  (
  `id` int NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_admin
-- ----------------------------
INSERT INTO `t_admin` VALUES (1, 'root');
INSERT INTO `t_admin` VALUES (55, 'fire');

-- ----------------------------
-- Table structure for t_course
-- ----------------------------
DROP TABLE IF EXISTS `t_course`;
CREATE TABLE `t_course`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `teacher_id` int NOT NULL,
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `credit` int NOT NULL,
  `term` date NOT NULL,
  `student_limit` int UNSIGNED NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `id`(`id` ASC, `teacher_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_course
-- ----------------------------
INSERT INTO `t_course` VALUES (1, 2, '41', 3, '2024-10-06', 70);
INSERT INTO `t_course` VALUES (2, 4, 'game', 1, '2024-01-01', 16);
INSERT INTO `t_course` VALUES (3, 6, 'play', 2, '2024-10-06', 100);
INSERT INTO `t_course` VALUES (4, 2, '312', 2, '2024-11-27', 50);
INSERT INTO `t_course` VALUES (5, 4, '1', 2, '2024-12-02', 50);
INSERT INTO `t_course` VALUES (6, 4, 'test1', 2, '2024-11-27', 50);
INSERT INTO `t_course` VALUES (12, 2, '42141', 2, '2024-11-27', 50);
INSERT INTO `t_course` VALUES (13, 2, '飞洒人2人2', 2, '2024-12-06', 50);

-- ----------------------------
-- Table structure for t_score
-- ----------------------------
DROP TABLE IF EXISTS `t_score`;
CREATE TABLE `t_score`  (
  `course_id` int NOT NULL,
  `student_id` int NOT NULL,
  `teacher_id` int NOT NULL,
  `score` int NOT NULL,
  `id` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 26 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_score
-- ----------------------------
INSERT INTO `t_score` VALUES (2, 1, 1, 0, 10);
INSERT INTO `t_score` VALUES (3, 1, 1, 10, 11);
INSERT INTO `t_score` VALUES (1, 2, 1, 3, 12);
INSERT INTO `t_score` VALUES (1, 1, 1, 3, 13);
INSERT INTO `t_score` VALUES (3, 3, 6, 80, 21);
INSERT INTO `t_score` VALUES (1, 3, 2, 2, 22);
INSERT INTO `t_score` VALUES (4, 3, 2, 80, 24);
INSERT INTO `t_score` VALUES (2, 3, 4, 0, 25);

-- ----------------------------
-- Table structure for t_student
-- ----------------------------
DROP TABLE IF EXISTS `t_student`;
CREATE TABLE `t_student`  (
  `id` int NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_student
-- ----------------------------
INSERT INTO `t_student` VALUES (1, 'agiantii');
INSERT INTO `t_student` VALUES (2, 'fff');
INSERT INTO `t_student` VALUES (3, 'Agiantii');
INSERT INTO `t_student` VALUES (5151, '231');
INSERT INTO `t_student` VALUES (54125, '213');

-- ----------------------------
-- Table structure for t_teacher
-- ----------------------------
DROP TABLE IF EXISTS `t_teacher`;
CREATE TABLE `t_teacher`  (
  `id` int NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_teacher
-- ----------------------------
INSERT INTO `t_teacher` VALUES (2, '12');
INSERT INTO `t_teacher` VALUES (4, 'fire');
INSERT INTO `t_teacher` VALUES (6, 'fire');
INSERT INTO `t_teacher` VALUES (24, 'teachegdsag');
INSERT INTO `t_teacher` VALUES (26, '45154');
INSERT INTO `t_teacher` VALUES (512351, '542315');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `id` int NOT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `role` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  CONSTRAINT `role_check` CHECK (`role` in (_utf8mb4'student',_utf8mb4'admin',_utf8mb4'teacher'))
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (1, '123', 'admin');
INSERT INTO `t_user` VALUES (2, '123', 'teacher');
INSERT INTO `t_user` VALUES (3, '123', 'student');
INSERT INTO `t_user` VALUES (24, '123456', 'teacher');
INSERT INTO `t_user` VALUES (26, '1235123532', 'teacher');
INSERT INTO `t_user` VALUES (123, '124', 'student');
INSERT INTO `t_user` VALUES (213, '123', 'student');
INSERT INTO `t_user` VALUES (5151, '41244125', 'student');
INSERT INTO `t_user` VALUES (54125, '241412415', 'student');
INSERT INTO `t_user` VALUES (512351, '123521512512512', 'teacher');

SET FOREIGN_KEY_CHECKS = 1;

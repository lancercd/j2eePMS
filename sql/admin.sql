/*
 Navicat Premium Data Transfer

 Source Server         : j2ee_local
 Source Server Type    : MySQL
 Source Server Version : 50726
 Source Host           : localhost:3306
 Source Schema         : j2ee

 Target Server Type    : MySQL
 Target Server Version : 50726
 File Encoding         : 65001

 Date: 17/06/2021 00:17:18
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '管理员表',
  `username` varchar(42) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名',
  `pwd` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码',
  `is_del` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否删除(逻辑删除 0启用 1删除)',
  `add_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '管理员表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for adviser_info
-- ----------------------------
DROP TABLE IF EXISTS `adviser_info`;
CREATE TABLE `adviser_info`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '指导老师信息表id',
  `teacher_id` int(11) NOT NULL COMMENT '老师id',
  `semester_id` int(11) NOT NULL COMMENT '学期id(外键)',
  `doc_type_id` int(11) NULL DEFAULT NULL COMMENT '资料类型(确认后填写上传资料类型 外键)',
  `req_info` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '要求信息(确认后填写信息)',
  `is_accept` tinyint(2) NOT NULL DEFAULT 0 COMMENT '是否同意(0等待回复  1同意  -1不同意)',
  `is_del` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否删除(0启用 1删除 逻辑删除)',
  `add_time` datetime(0) NOT NULL COMMENT '添加时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `adviserInfo_teacher`(`teacher_id`) USING BTREE,
  INDEX `adviserInfo_semester`(`semester_id`) USING BTREE,
  INDEX `adviserInfo_docType`(`doc_type_id`) USING BTREE,
  CONSTRAINT `adviserInfo_semester` FOREIGN KEY (`semester_id`) REFERENCES `semester` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `adviserInfo_teacher` FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `adviserInfo_docType` FOREIGN KEY (`doc_type_id`) REFERENCES `document_type` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '指导老师信息表(教学顾问发送是否愿意成为指导老师的请求   由该表记录)， 记录该老师该学期是否愿意成为指导老师 以及 要求是什么' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for appraise_teacher
-- ----------------------------
DROP TABLE IF EXISTS `appraise_teacher`;
CREATE TABLE `appraise_teacher`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '评阅老师id',
  `teacher_id` int(11) NOT NULL COMMENT '教师id (外键)',
  `stu_tea_ch` int(11) NOT NULL COMMENT '导师学生选择表id',
  `suggestion` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '意见   对该指导老师学生的资料评阅后的意见',
  `score` int(11) NULL DEFAULT NULL COMMENT '分数  评阅后的分数',
  `is_accept` tinyint(2) NOT NULL DEFAULT 0 COMMENT '是否接受 成为该学生的指导老师 (0等待回复  1同意  -1不同意)',
  `is_del` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否删除(0启用 1删除 逻辑删除)',
  `add_time` datetime(0) NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `appraiseTeacher_teacher`(`teacher_id`) USING BTREE,
  INDEX `appraiseTeacher_stuTeaCh`(`stu_tea_ch`) USING BTREE,
  CONSTRAINT `appraiseTeacher_teacher` FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `appraiseTeacher_stuTeaCh` FOREIGN KEY (`stu_tea_ch`) REFERENCES `stu_tea_ch` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '评阅老师表(评阅老师就是普通的老师)' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for document
-- ----------------------------
DROP TABLE IF EXISTS `document`;
CREATE TABLE `document`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '资料表',
  `type_id` int(11) NOT NULL COMMENT '资料类型id (外键)',
  `student_id` int(11) NOT NULL COMMENT '学生id (外键)  哪个学生上传的该资料',
  `status` tinyint(2) NOT NULL COMMENT '状态 (0,1,2) 未审核  审核不通过  审核通过',
  `path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '该资料存放的路径',
  `is_del` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否删除(0启用 1删除 逻辑删除)',
  `add_time` datetime(0) NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `document_docType`(`type_id`) USING BTREE,
  INDEX `document_student`(`student_id`) USING BTREE,
  CONSTRAINT `document_docType` FOREIGN KEY (`type_id`) REFERENCES `document_type` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `document_student` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '资料表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for document_type
-- ----------------------------
DROP TABLE IF EXISTS `document_type`;
CREATE TABLE `document_type`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '资料类型表id',
  `name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '类型名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '资料类型表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for news
-- ----------------------------
DROP TABLE IF EXISTS `news`;
CREATE TABLE `news`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '新闻表id',
  `title` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '标题',
  `content` varchar(2048) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '内容',
  `is_active` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否启用',
  `is_del` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否删除(0启用 1删除 逻辑删除)',
  `add_time` datetime(0) NOT NULL COMMENT '添加时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '新闻表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for semester
-- ----------------------------
DROP TABLE IF EXISTS `semester`;
CREATE TABLE `semester`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '学期表id',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '学期名',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '学期表(记录学期与学期名)' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of semester
-- ----------------------------
INSERT INTO `semester` VALUES (1, '2019第一学期');
INSERT INTO `semester` VALUES (2, '2019第二学期');
INSERT INTO `semester` VALUES (3, '2020第一学期');
INSERT INTO `semester` VALUES (4, '2020第二学期');
INSERT INTO `semester` VALUES (5, '2021第一学期');
INSERT INTO `semester` VALUES (6, '2021第二学期');

-- ----------------------------
-- Table structure for stu_tea_ch
-- ----------------------------
DROP TABLE IF EXISTS `stu_tea_ch`;
CREATE TABLE `stu_tea_ch`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '学生选择导师表id',
  `teacher_id` int(11) NOT NULL COMMENT '教师id (外键)',
  `student_id` int(11) NOT NULL COMMENT '学生id (外键)',
  `adviser_info` int(11) NOT NULL COMMENT '指导老师信息id (外键)',
  `semester_id` int(11) NOT NULL COMMENT '学期id (外键)',
  `appraise_id` int(11) NULL DEFAULT NULL COMMENT '评阅老师id (外键) 指导老师选择评阅老师 且不能是自己',
  `intro` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '学生自我介绍',
  `suggestion` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '指导老师评阅资料的意见',
  `score` int(11) NULL DEFAULT NULL COMMENT '评阅后的分数',
  `document_id` int(11) NULL DEFAULT NULL COMMENT '学生上传的资料( 资料表id 外键)',
  `is_accept` tinyint(2) NOT NULL DEFAULT 0 COMMENT '是否接受 成为该学生的指导老师 (0等待回复  1同意  -1不同意)',
  `is_del` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否删除(0启用 1删除 逻辑删除)',
  `add_time` datetime(0) NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `stuTeaCh_teacher`(`teacher_id`) USING BTREE,
  INDEX `stuTeaCh_student`(`student_id`) USING BTREE,
  INDEX `stuTeaCh_semester`(`semester_id`) USING BTREE,
  INDEX `stuTeaCh_appraiseTea`(`appraise_id`) USING BTREE,
  INDEX `stuTeaCh_document`(`document_id`) USING BTREE,
  CONSTRAINT `stuTeaCh_teacher` FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `stuTeaCh_student` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `stuTeaCh_semester` FOREIGN KEY (`semester_id`) REFERENCES `semester` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `stuTeaCh_appraiseTea` FOREIGN KEY (`appraise_id`) REFERENCES `appraise_teacher` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `stuTeaCh_document` FOREIGN KEY (`document_id`) REFERENCES `document` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '学生选择导师表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '学生表id',
  `number` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '学号',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '姓名',
  `password` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码',
  `is_del` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否删除(0启用 1删除)',
  `add_time` datetime(0) NULL DEFAULT NULL COMMENT '添加时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '学生表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES (1, '2018051603074', 'lancercd', '123456', 0, NULL);

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '教师表id',
  `number` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '教师编号',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '姓名',
  `pwd` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `is_del` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否删除(0启用 1删除)',
  `add_time` datetime(0) NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '教师表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for teaching_secretary
-- ----------------------------
DROP TABLE IF EXISTS `teaching_secretary`;
CREATE TABLE `teaching_secretary`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '教学秘书表id',
  `username` varchar(48) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名',
  `pwd` varchar(68) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码',
  `is_del` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否删除(逻辑删除 0启用 1删除)',
  `add_time` datetime(0) NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '教学秘书表' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;

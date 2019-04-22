/*
 Navicat Premium Data Transfer

 Source Server         : 127.0.0.1
 Source Server Type    : MariaDB
 Source Server Version : 100311
 Source Host           : 127.0.0.1:3306
 Source Schema         : book_manage

 Target Server Type    : MariaDB
 Target Server Version : 100311
 File Encoding         : 65001

 Date: 22/04/2019 15:36:56
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_order_book
-- ----------------------------
DROP TABLE IF EXISTS `tb_order_book`;
CREATE TABLE `tb_order_book`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '主键',
  `order_book_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '书籍名称',
  `order_book_number` int(5) NOT NULL COMMENT '书籍数量',
  `order_book_author` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '作者名称',
  `order_book_type` enum('MLZY_MZDSX','ZX','SHKX','ZRKX','ZHXTS') CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '书籍类型(马列主义毛泽东思想、哲学、社会科学、自然科学、综合性图书)',
  `gmt_create` datetime(6) NULL DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime(6) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_order_book
-- ----------------------------
INSERT INTO `tb_order_book` VALUES ('0d55da732c74a80af8962c81dd6ded42', 'JAVA开发', 5, 'java之父', 'SHKX', '2019-04-12 00:00:00.000000', NULL);
INSERT INTO `tb_order_book` VALUES ('43131c8faf706872cf01159d117ce382', '孤独患者的四句话', 5, '重度患者', 'ZHXTS', '2019-04-12 00:00:00.000000', NULL);
INSERT INTO `tb_order_book` VALUES ('4bf04915f9138d54a03bea0be3330483', '看不见的城市', 15, '伊塔洛•卡尔维诺', 'ZX', '2019-04-10 00:00:00.000000', NULL);
INSERT INTO `tb_order_book` VALUES ('87b750983bec9feab39eb6414737dfee', '如何与人相处', 5, '姚树礼', 'ZHXTS', '2019-04-10 00:00:00.000000', NULL);
INSERT INTO `tb_order_book` VALUES ('9da3a96f710f0922f1d0ebebe85ef824', '毛泽东思想', 50, '毛泽东', 'MLZY_MZDSX', '2019-04-10 00:00:00.000000', NULL);
INSERT INTO `tb_order_book` VALUES ('adeae56b18055409c6289edb72d40319', '这个世界怎么救', 5, '马坡', 'SHKX', '2019-04-12 00:00:00.000000', NULL);
INSERT INTO `tb_order_book` VALUES ('dd441b25d59e1854c3ae44406f52ebcf', '傻逼是怎么练成的', 2, '傻逼', 'ZHXTS', '2019-04-12 00:00:00.000000', NULL);
INSERT INTO `tb_order_book` VALUES ('e84a40f93fa64897c86b94a35dcd9503', '钢铁是怎么练成的', 50, '德国人名字叫啥忘了', 'ZHXTS', '2019-04-10 00:00:00.000000', NULL);

-- ----------------------------
-- Table structure for tb_permission
-- ----------------------------
DROP TABLE IF EXISTS `tb_permission`;
CREATE TABLE `tb_permission`  (
  `id` bigint(255) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `permission_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '权限地址',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '备注',
  `gmt_create` datetime(6) NULL DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime(6) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_permission
-- ----------------------------
INSERT INTO `tb_permission` VALUES (1, '/swagger-ui.html', '权限api', '2019-03-29 00:00:00.000000', NULL);

-- ----------------------------
-- Table structure for tb_purchase
-- ----------------------------
DROP TABLE IF EXISTS `tb_purchase`;
CREATE TABLE `tb_purchase`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT ' 主键',
  `purchase_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '采购计划名称',
  `purchase_description` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '采购描述',
  `purchase_number` int(11) NOT NULL COMMENT '采购数量',
  `purchase_money` decimal(15, 0) NULL DEFAULT NULL COMMENT '采购金额',
  `gmt_create` datetime(6) NULL DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime(6) NULL DEFAULT NULL COMMENT '修改时间',
  `purchase_state` enum('EXECUTING','UNCHECKED','CHECK') CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '计划状态(采购中、待核对、已核对)',
  `principal_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '采购负责人',
  `principal_phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '负责人联系电话',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_purchase
-- ----------------------------
INSERT INTO `tb_purchase` VALUES ('15548990885145596915281475159811', '20190410采购计划', 'xxx图书馆第三十五次采购计划，采购书本120本！', 120, NULL, '2019-04-10 00:00:00.000000', NULL, 'EXECUTING', '姚树礼', '17600562303');
INSERT INTO `tb_purchase` VALUES ('15550646987442051394420913684478', '20190412采购计划', '哈哈哈哈哈哈哈哈哈哈', 17, NULL, '2019-04-12 00:00:00.000000', NULL, 'EXECUTING', '侯兆烨', '17600562303');

-- ----------------------------
-- Table structure for tb_purchase_order_book
-- ----------------------------
DROP TABLE IF EXISTS `tb_purchase_order_book`;
CREATE TABLE `tb_purchase_order_book`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `order_book_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '新订购图书id',
  `purchase_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '采购计划id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_purchase_order_book
-- ----------------------------
INSERT INTO `tb_purchase_order_book` VALUES (4, '87b750983bec9feab39eb6414737dfee', '15548990885145596915281475159811');
INSERT INTO `tb_purchase_order_book` VALUES (5, '9da3a96f710f0922f1d0ebebe85ef824', '15548990885145596915281475159811');
INSERT INTO `tb_purchase_order_book` VALUES (6, 'e84a40f93fa64897c86b94a35dcd9503', '15548990885145596915281475159811');
INSERT INTO `tb_purchase_order_book` VALUES (7, '4bf04915f9138d54a03bea0be3330483', '15548990885145596915281475159811');
INSERT INTO `tb_purchase_order_book` VALUES (8, 'dd441b25d59e1854c3ae44406f52ebcf', '15550646987442051394420913684478');
INSERT INTO `tb_purchase_order_book` VALUES (9, 'adeae56b18055409c6289edb72d40319', '15550646987442051394420913684478');
INSERT INTO `tb_purchase_order_book` VALUES (10, '43131c8faf706872cf01159d117ce382', '15550646987442051394420913684478');
INSERT INTO `tb_purchase_order_book` VALUES (11, '0d55da732c74a80af8962c81dd6ded42', '15550646987442051394420913684478');

-- ----------------------------
-- Table structure for tb_readers
-- ----------------------------
DROP TABLE IF EXISTS `tb_readers`;
CREATE TABLE `tb_readers`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `readers_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '读者姓名',
  `password` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '借阅密码',
  `readers_phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '读者手机号',
  `id_card` varchar(18) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '读者身份证号',
  `native_place` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '籍贯',
  `readers_sex` enum('MALE','FEMALE','UNKNOWN') CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '性别',
  `readers_state` enum('AVAILABLE','BLACKLIST') CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '读者卡状态',
  `gmt_create` datetime(6) NULL DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime(6) NULL DEFAULT NULL COMMENT '修改时间',
  `student_number` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '读者学号',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '读者现住址',
  `blacklist_description` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '拉黑原因',
  `is_contact` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '是否知情',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_readers
-- ----------------------------
INSERT INTO `tb_readers` VALUES (13, '张龙', '123456789', '17600562303', '372928199211210514', '山东省', 'MALE', 'AVAILABLE', '2019-04-12 00:00:00.000000', NULL, '', '', NULL, NULL);

-- ----------------------------
-- Table structure for tb_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_role`;
CREATE TABLE `tb_role`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '角色名称',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '备注',
  `gmt_create` datetime(6) NULL DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime(6) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_role
-- ----------------------------
INSERT INTO `tb_role` VALUES (1, 'admin', '超级管理员', '2019-03-27 16:42:02.000000', '2019-03-13 16:42:05.000000');

-- ----------------------------
-- Table structure for tb_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `tb_role_permission`;
CREATE TABLE `tb_role_permission`  (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `role_id` bigint(255) NOT NULL COMMENT '用户id',
  `permission_id` bigint(20) NOT NULL COMMENT '角色id',
  `gmt_create` datetime(6) NULL DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime(6) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_role_permission
-- ----------------------------
INSERT INTO `tb_role_permission` VALUES (1, 1, 1, '2019-03-14 16:42:42.000000', NULL);

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '用户名',
  `password` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '密码',
  `phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '手机号',
  `email` varchar(30) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '邮箱',
  `user_state` enum('AVAILABLE','LOCKING','LIMIT') CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '状态(可用、锁定、限制)',
  `gmt_create` datetime(6) NULL DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime(6) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_username`(`user_name`) USING BTREE COMMENT '用户名唯一索引防止脏数据'
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES (1, 'admin', 'admin', '17600562303', '1397940314@qq.com', 'AVAILABLE', '2019-03-27 16:41:33.000000', '2019-03-13 16:41:38.000000');

-- ----------------------------
-- Table structure for tb_user_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_role`;
CREATE TABLE `tb_user_role`  (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `user_id` bigint(255) NOT NULL COMMENT '用户id',
  `role_id` bigint(20) NOT NULL COMMENT '角色id',
  `gmt_create` datetime(6) NULL DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime(6) NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_user_role
-- ----------------------------
INSERT INTO `tb_user_role` VALUES (1, 1, 1, '2019-03-29 16:42:23.000000', '2019-03-28 16:42:27.000000');

SET FOREIGN_KEY_CHECKS = 1;

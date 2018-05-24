/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50160
Source Host           : localhost:3306
Source Database       : attendence

Target Server Type    : MYSQL
Target Server Version : 50160
File Encoding         : 65001

Date: 2018-05-21 16:33:35
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_classroom`
-- ----------------------------
DROP TABLE IF EXISTS `t_classroom`;
CREATE TABLE `t_classroom` (
  `id` int(20) NOT NULL COMMENT '教室编号',
  `name` varchar(20) NOT NULL COMMENT '教室名称',
  `type` int(10) NOT NULL COMMENT '教室类型（普通教室，阶梯大教室，机房，实验室等）',
  `seatCount` int(10) NOT NULL COMMENT '可容纳人数（座位数，不包括教师）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_classroom
-- ----------------------------

-- ----------------------------
-- Table structure for `t_college`
-- ----------------------------
DROP TABLE IF EXISTS `t_college`;
CREATE TABLE `t_college` (
  `id` int(20) NOT NULL COMMENT '学院（系）编号',
  `name` varchar(50) NOT NULL COMMENT '院系名称',
  `studentCount` int(10) NOT NULL COMMENT '学生人数',
  `teacherCount` int(10) NOT NULL COMMENT '教职工人数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_college
-- ----------------------------

-- ----------------------------
-- Table structure for `t_course`
-- ----------------------------
DROP TABLE IF EXISTS `t_course`;
CREATE TABLE `t_course` (
  `id` int(20) NOT NULL COMMENT '课程编号',
  `studentCount` int(20) NOT NULL COMMENT '选课人数',
  `teacherId` int(20) NOT NULL COMMENT '任课教师ID',
  `roomId` int(20) NOT NULL COMMENT '占用教室编号',
  `week` int(10) NOT NULL COMMENT '上课日期（星期）',
  `partStart` int(10) NOT NULL COMMENT '节数（开始）',
  `partEnd` int(10) NOT NULL COMMENT '节数（结束）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_course
-- ----------------------------

-- ----------------------------
-- Table structure for `t_elecource`
-- ----------------------------
DROP TABLE IF EXISTS `t_elecource`;
CREATE TABLE `t_elecource` (
  `id` int(20) NOT NULL COMMENT '选课记录编号',
  `studentId` int(20) NOT NULL COMMENT '选课学生ID',
  `courseId` int(20) NOT NULL COMMENT '课程ID',
  `createDate` date NOT NULL COMMENT '创建选课时间（按日）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_elecource
-- ----------------------------

-- ----------------------------
-- Table structure for `t_major`
-- ----------------------------
DROP TABLE IF EXISTS `t_major`;
CREATE TABLE `t_major` (
  `id` int(20) NOT NULL COMMENT '专业ID',
  `name` varchar(50) NOT NULL COMMENT '专业名称',
  `collegeId` int(20) NOT NULL COMMENT '开设院系ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_major
-- ----------------------------

-- ----------------------------
-- Table structure for `t_permission`
-- ----------------------------
DROP TABLE IF EXISTS `t_permission`;
CREATE TABLE `t_permission` (
  `id` int(10) NOT NULL COMMENT '权限ID',
  `name` varchar(50) NOT NULL COMMENT '权限名称',
  `uri` char(200) NOT NULL COMMENT '请求地址',
  `isMenu` bit(10) NOT NULL COMMENT '是否菜单',
  `isPage` bit(10) NOT NULL COMMENT '是否页面',
  `isAction` bit(10) NOT NULL COMMENT '是否动作',
  `createDate` date NOT NULL COMMENT '创建时间（按日）',
  `createBy` int(20) NOT NULL COMMENT '创建者用户ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_permission
-- ----------------------------

-- ----------------------------
-- Table structure for `t_role`
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `id` int(10) NOT NULL COMMENT '角色ID',
  `name` varchar(50) NOT NULL COMMENT ' 角色名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES ('1', '管理员');
INSERT INTO `t_role` VALUES ('2', '学生');
INSERT INTO `t_role` VALUES ('3', '教职工');

-- ----------------------------
-- Table structure for `t_rolepermission`
-- ----------------------------
DROP TABLE IF EXISTS `t_rolepermission`;
CREATE TABLE `t_rolepermission` (
  `id` int(20) NOT NULL COMMENT '权限记录编号',
  `roleId` int(20) NOT NULL COMMENT '角色Id',
  `perId` int(20) NOT NULL COMMENT '被授予权限Id',
  `authorizedBy` int(20) NOT NULL COMMENT ' 授权者ID(一般是管理员)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_rolepermission
-- ----------------------------

-- ----------------------------
-- Table structure for `t_student`
-- ----------------------------
DROP TABLE IF EXISTS `t_student`;
CREATE TABLE `t_student` (
  `id` int(20) NOT NULL COMMENT '学生编号（学号）',
  `entranceYear` int(10) NOT NULL COMMENT '入学年份',
  `entranceTime` date NOT NULL COMMENT '入学时间（按日）',
  `collegeId` int(20) NOT NULL COMMENT '所属院系ID',
  `majorId` int(20) NOT NULL COMMENT '主修专业ID',
  `inClassId` int(20) NOT NULL COMMENT '所在班级id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_student
-- ----------------------------

-- ----------------------------
-- Table structure for `t_teacher`
-- ----------------------------
DROP TABLE IF EXISTS `t_teacher`;
CREATE TABLE `t_teacher` (
  `id` int(20) NOT NULL COMMENT '教师ID(教职工编号)',
  `collegeId` int(20) NOT NULL COMMENT '所属院系ID',
  `proTitle` varchar(50) NOT NULL COMMENT '职称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_teacher
-- ----------------------------

-- ----------------------------
-- Table structure for `t_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` int(50) NOT NULL COMMENT '用户编号',
  `name` varchar(200) NOT NULL COMMENT '用户名（一律是人名）',
  `password` varchar(70) NOT NULL COMMENT '密码（哈希加密后的）',
  `sex` varchar(50) NOT NULL COMMENT '性别',
  `role` int(10) NOT NULL COMMENT '用户角色设定',
  `phone` varchar(30) NOT NULL COMMENT '用户电话号码',
  `email` varchar(100) NOT NULL COMMENT '注册邮箱',
  `createDate` date NOT NULL COMMENT '创建日期（按日）',
  `createBy` int(11) DEFAULT NULL COMMENT '用户创建者ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('10000', '李震宇', 'root123456', '男', '1', '13235905890', '810772273@qq.com', '2018-05-20', null);

/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50160
Source Host           : localhost:3306
Source Database       : attendence

Target Server Type    : MYSQL
Target Server Version : 50160
File Encoding         : 65001

Date: 2018-06-16 19:36:35
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `course_30002_student_list`
-- ----------------------------
DROP TABLE IF EXISTS `course_30002_student_list`;
CREATE TABLE `course_30002_student_list` (
  `studentId` int(20) NOT NULL COMMENT '学生ID',
  `courseId` int(20) NOT NULL COMMENT '课程ID',
  `teacherId` int(20) NOT NULL COMMENT '任课教师ID',
  `studentName` varchar(50) NOT NULL COMMENT '学生姓名',
  `AbsentRecordCount` int(10) NOT NULL COMMENT '缺席次数',
  `LeaveRecordCount` int(10) NOT NULL COMMENT '请假次数',
  `performScore` int(10) NOT NULL COMMENT '平时分（满分100）',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`studentId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of course_30002_student_list
-- ----------------------------
INSERT INTO `course_30002_student_list` VALUES ('170327020', '30002', '386000001', '李明', '1', '0', '0', '');
INSERT INTO `course_30002_student_list` VALUES ('170327021', '30002', '386000001', '摩雯思', '0', '1', '0', '');
INSERT INTO `course_30002_student_list` VALUES ('170327022', '30002', '386000001', '路建宁', '0', '0', '0', '');
INSERT INTO `course_30002_student_list` VALUES ('170327023', '30002', '386000001', '莫文蔚', '1', '0', '0', '');

-- ----------------------------
-- Table structure for `course_30003_student_list`
-- ----------------------------
DROP TABLE IF EXISTS `course_30003_student_list`;
CREATE TABLE `course_30003_student_list` (
  `studentId` int(20) NOT NULL COMMENT '学生ID',
  `courseId` int(20) NOT NULL COMMENT '课程ID',
  `teacherId` int(20) NOT NULL COMMENT '任课教师ID',
  `studentName` varchar(50) NOT NULL COMMENT '学生姓名',
  `AbsentRecordCount` int(10) NOT NULL COMMENT '缺席次数',
  `LeaveRecordCount` int(10) NOT NULL COMMENT '请假次数',
  `performScore` int(10) NOT NULL COMMENT '平时分（满分100）',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`studentId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of course_30003_student_list
-- ----------------------------
INSERT INTO `course_30003_student_list` VALUES ('170327024', '30003', '386000003', '江萱', '0', '0', '0', '');
INSERT INTO `course_30003_student_list` VALUES ('170327025', '30003', '386000003', '方杰', '0', '0', '0', '');
INSERT INTO `course_30003_student_list` VALUES ('170327026', '30003', '386000003', '龙心', '0', '0', '0', '');
INSERT INTO `course_30003_student_list` VALUES ('170327027', '30003', '386000003', '陈莉', '0', '0', '0', '');

-- ----------------------------
-- Table structure for `sign_in_30002_20180609`
-- ----------------------------
DROP TABLE IF EXISTS `sign_in_30002_20180609`;
CREATE TABLE `sign_in_30002_20180609` (
  `studentId` int(20) NOT NULL COMMENT '学生ID',
  `row` int(10) NOT NULL COMMENT '签到座位（行）',
  `col` int(10) NOT NULL COMMENT '签到座位（列）',
  `flag` int(10) NOT NULL COMMENT '签到标志',
  `isAbsent` tinyint(2) NOT NULL COMMENT '是否缺席',
  `isLeave` tinyint(2) NOT NULL COMMENT '是否请假',
  `signTime` datetime NOT NULL COMMENT '签到时间 （精确到秒）',
  PRIMARY KEY (`studentId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sign_in_30002_20180609
-- ----------------------------
INSERT INTO `sign_in_30002_20180609` VALUES ('170327020', '0', '0', '0', '1', '0', '1970-01-01 08:00:00');
INSERT INTO `sign_in_30002_20180609` VALUES ('170327021', '0', '0', '0', '0', '1', '1970-01-01 08:00:00');
INSERT INTO `sign_in_30002_20180609` VALUES ('170327022', '4', '1', '1', '0', '0', '2018-06-09 21:02:26');
INSERT INTO `sign_in_30002_20180609` VALUES ('170327023', '0', '0', '0', '1', '0', '1970-01-01 08:00:00');

-- ----------------------------
-- Table structure for `sign_in_30003_20180612`
-- ----------------------------
DROP TABLE IF EXISTS `sign_in_30003_20180612`;
CREATE TABLE `sign_in_30003_20180612` (
  `studentId` int(20) NOT NULL COMMENT '学生ID',
  `row` int(10) NOT NULL COMMENT '签到座位（行）',
  `col` int(10) NOT NULL COMMENT '签到座位（列）',
  `flag` int(10) NOT NULL COMMENT '签到标志',
  `isAbsent` tinyint(2) NOT NULL COMMENT '是否缺席',
  `isLeave` tinyint(2) NOT NULL COMMENT '是否请假',
  `signTime` datetime NOT NULL COMMENT '签到时间 （精确到秒）',
  PRIMARY KEY (`studentId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sign_in_30003_20180612
-- ----------------------------
INSERT INTO `sign_in_30003_20180612` VALUES ('170327024', '0', '0', '0', '1', '0', '1970-01-01 08:00:00');
INSERT INTO `sign_in_30003_20180612` VALUES ('170327025', '3', '3', '1', '0', '0', '2018-06-12 19:53:26');
INSERT INTO `sign_in_30003_20180612` VALUES ('170327026', '0', '0', '0', '1', '0', '1970-01-01 08:00:00');
INSERT INTO `sign_in_30003_20180612` VALUES ('170327027', '0', '0', '0', '1', '0', '1970-01-01 08:00:00');

-- ----------------------------
-- Table structure for `sign_in_30003_20180613`
-- ----------------------------
DROP TABLE IF EXISTS `sign_in_30003_20180613`;
CREATE TABLE `sign_in_30003_20180613` (
  `studentId` int(20) NOT NULL COMMENT '学生ID',
  `row` int(10) NOT NULL COMMENT '签到座位（行）',
  `col` int(10) NOT NULL COMMENT '签到座位（列）',
  `flag` int(10) NOT NULL COMMENT '签到标志',
  `isAbsent` tinyint(2) NOT NULL COMMENT '是否缺席',
  `isLeave` tinyint(2) NOT NULL COMMENT '是否请假',
  `signTime` datetime NOT NULL COMMENT '签到时间 （精确到秒）',
  PRIMARY KEY (`studentId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sign_in_30003_20180613
-- ----------------------------
INSERT INTO `sign_in_30003_20180613` VALUES ('170327024', '0', '0', '0', '1', '0', '1970-01-01 08:00:00');
INSERT INTO `sign_in_30003_20180613` VALUES ('170327025', '3', '3', '1', '0', '0', '2018-06-13 19:52:39');
INSERT INTO `sign_in_30003_20180613` VALUES ('170327026', '0', '0', '0', '1', '0', '1970-01-01 08:00:00');
INSERT INTO `sign_in_30003_20180613` VALUES ('170327027', '0', '0', '0', '1', '0', '1970-01-01 08:00:00');

-- ----------------------------
-- Table structure for `sign_in_30003_20180616`
-- ----------------------------
DROP TABLE IF EXISTS `sign_in_30003_20180616`;
CREATE TABLE `sign_in_30003_20180616` (
  `studentId` int(20) NOT NULL COMMENT '学生ID',
  `row` int(10) NOT NULL COMMENT '签到座位（行）',
  `col` int(10) NOT NULL COMMENT '签到座位（列）',
  `flag` int(10) NOT NULL COMMENT '签到标志',
  `isAbsent` tinyint(2) NOT NULL COMMENT '是否缺席',
  `isLeave` tinyint(2) NOT NULL COMMENT '是否请假',
  `signTime` datetime NOT NULL COMMENT '签到时间 （精确到秒）',
  PRIMARY KEY (`studentId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sign_in_30003_20180616
-- ----------------------------
INSERT INTO `sign_in_30003_20180616` VALUES ('170327024', '1', '2', '1', '0', '0', '2018-06-16 19:16:21');
INSERT INTO `sign_in_30003_20180616` VALUES ('170327025', '6', '5', '1', '0', '0', '2018-06-16 19:16:34');
INSERT INTO `sign_in_30003_20180616` VALUES ('170327026', '3', '8', '1', '0', '0', '2018-06-16 19:16:42');
INSERT INTO `sign_in_30003_20180616` VALUES ('170327027', '8', '7', '1', '0', '0', '2018-06-16 19:17:00');

-- ----------------------------
-- Table structure for `t_auto_sign_in_table_list`
-- ----------------------------
DROP TABLE IF EXISTS `t_auto_sign_in_table_list`;
CREATE TABLE `t_auto_sign_in_table_list` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '记录ID',
  `tableName` char(100) NOT NULL COMMENT '自动建表的表名',
  `courseId` int(20) NOT NULL COMMENT '签到的课程ID',
  `createDate` date NOT NULL COMMENT ' 建表日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_auto_sign_in_table_list
-- ----------------------------
INSERT INTO `t_auto_sign_in_table_list` VALUES ('11', 'sign_in_30002_20180609', '30002', '2018-06-09');
INSERT INTO `t_auto_sign_in_table_list` VALUES ('13', 'sign_in_30003_20180612', '30003', '2018-06-12');
INSERT INTO `t_auto_sign_in_table_list` VALUES ('14', 'sign_in_30003_20180613', '30003', '2018-06-13');
INSERT INTO `t_auto_sign_in_table_list` VALUES ('15', 'sign_in_30003_20180616', '30003', '2018-06-16');

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
INSERT INTO `t_classroom` VALUES ('930001', '东1-101', '1', '50');
INSERT INTO `t_classroom` VALUES ('930002', '东1-102', '2', '240');
INSERT INTO `t_classroom` VALUES ('930003', '东1-103', '1', '50');
INSERT INTO `t_classroom` VALUES ('930004', '东1-104', '1', '50');
INSERT INTO `t_classroom` VALUES ('930005', '东1-105', '3', '70');
INSERT INTO `t_classroom` VALUES ('930006', '东1-106', '2', '120');
INSERT INTO `t_classroom` VALUES ('930007', '东1-107', '1', '90');

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
INSERT INTO `t_college` VALUES ('1', '经管学院', '1326', '35');
INSERT INTO `t_college` VALUES ('2', '土木工程学院', '2403', '56');
INSERT INTO `t_college` VALUES ('3', '数计学院', '1700', '24');
INSERT INTO `t_college` VALUES ('4', '电气学院', '2385', '30');
INSERT INTO `t_college` VALUES ('5', '人文学院', '2200', '18');

-- ----------------------------
-- Table structure for `t_course`
-- ----------------------------
DROP TABLE IF EXISTS `t_course`;
CREATE TABLE `t_course` (
  `id` int(20) NOT NULL COMMENT '课程编号',
  `name` varchar(50) NOT NULL COMMENT '课程名称',
  `studentCount` int(20) NOT NULL COMMENT '选课人数',
  `teacherId` int(20) NOT NULL COMMENT '任课教师ID',
  `roomId` int(20) NOT NULL COMMENT '占用教室编号',
  `week` int(10) NOT NULL COMMENT '上课日期（星期）',
  `partStart` int(10) NOT NULL COMMENT '节数（开始）',
  `partEnd` int(10) NOT NULL COMMENT '节数（结束）',
  `enable` tinyint(2) NOT NULL COMMENT '是否可选课(1可以，0不能)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_course
-- ----------------------------
INSERT INTO `t_course` VALUES ('30001', '计算机图形学', '40', '386000002', '930001', '3', '5', '7', '1');
INSERT INTO `t_course` VALUES ('30002', '机器学习', '90', '386000001', '930007', '5', '5', '7', '0');
INSERT INTO `t_course` VALUES ('30003', '工程训练', '60', '386000003', '930005', '6', '1', '4', '0');

-- ----------------------------
-- Table structure for `t_elecourse`
-- ----------------------------
DROP TABLE IF EXISTS `t_elecourse`;
CREATE TABLE `t_elecourse` (
  `id` char(100) NOT NULL COMMENT '选课记录编号（自动生成）',
  `studentId` int(20) NOT NULL COMMENT '选课学生ID',
  `courseId` int(20) NOT NULL COMMENT '课程ID',
  `createDate` date NOT NULL COMMENT '创建选课时间（按日）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_elecourse
-- ----------------------------
INSERT INTO `t_elecourse` VALUES ('0650589c14734c1b8e4199ae5bfe21b3', '170327021', '30002', '2018-05-31');
INSERT INTO `t_elecourse` VALUES ('10e7ae2a9e8944bea75bef2328a444d0', '170327020', '30002', '2018-05-31');
INSERT INTO `t_elecourse` VALUES ('263e0662be4240879a29bfdf3acbabac', '170327022', '30002', '2018-05-31');
INSERT INTO `t_elecourse` VALUES ('5010b645d33241d4b7328983f3457d0a', '170327025', '30003', '2018-05-31');
INSERT INTO `t_elecourse` VALUES ('93b90dc04ee24e6c96a0c72322be0339', '170327024', '30003', '2018-05-31');
INSERT INTO `t_elecourse` VALUES ('f26af133311e44ac879c4d38c84a86bb', '170327023', '30002', '2018-05-31');
INSERT INTO `t_elecourse` VALUES ('f949df47d0854d74a3233e404653f6e2', '170327026', '30003', '2018-05-31');
INSERT INTO `t_elecourse` VALUES ('feaa06c206434c4cb6d4134bec9817e2', '170327027', '30003', '2018-05-31');

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
INSERT INTO `t_major` VALUES ('301', '计算机技术', '3');
INSERT INTO `t_major` VALUES ('302', '软件工程', '3');

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
  `id` char(100) NOT NULL COMMENT '权限记录编号（自动生成）',
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
INSERT INTO `t_student` VALUES ('170327019', '2017', '2017-09-15', '3', '301', '1');
INSERT INTO `t_student` VALUES ('170327020', '2017', '2017-09-15', '3', '301', '1');
INSERT INTO `t_student` VALUES ('170327021', '2017', '2017-09-15', '3', '301', '1');
INSERT INTO `t_student` VALUES ('170327022', '2017', '2017-09-15', '3', '301', '1');
INSERT INTO `t_student` VALUES ('170327023', '2017', '2017-09-15', '3', '301', '1');
INSERT INTO `t_student` VALUES ('170327024', '2017', '2017-09-15', '3', '301', '1');
INSERT INTO `t_student` VALUES ('170327025', '2017', '2017-09-15', '3', '301', '1');
INSERT INTO `t_student` VALUES ('170327026', '2017', '2017-09-15', '3', '301', '1');
INSERT INTO `t_student` VALUES ('170327027', '2017', '2017-09-15', '3', '301', '1');

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
INSERT INTO `t_teacher` VALUES ('386000001', '3', '教授');
INSERT INTO `t_teacher` VALUES ('386000002', '3', '副教授');
INSERT INTO `t_teacher` VALUES ('386000003', '3', '讲师');

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
  `createBy` int(20) DEFAULT NULL COMMENT '用户创建者ID',
  `status` int(10) NOT NULL COMMENT '账户状态：0:正常使用；1:帐户已禁用；2:帐户待激活；3:帐户待审核；4:帐户审核未能过；5:帐户已过期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('10000', '李震宇', '123456', '男', '1', '13235905890', 'admin', '2018-05-20', null, '0');
INSERT INTO `t_user` VALUES ('170327019', '邓文路', '123456', '男', '2', '13233333333', '123@qq.com', '2018-05-20', '10000', '0');
INSERT INTO `t_user` VALUES ('170327020', '李明', '123456', '男', '2', '13233333334', '124@qq.com', '2018-05-20', '10000', '0');
INSERT INTO `t_user` VALUES ('170327021', '摩雯思', '123456', '女', '2', '13233333335', '126@qq.com', '2018-05-20', '10000', '0');
INSERT INTO `t_user` VALUES ('170327022', '路建宁', '123456', '男', '2', '13233333336', '127@qq.com', '2018-05-20', '10000', '0');
INSERT INTO `t_user` VALUES ('170327023', '莫文蔚', '123456', '女', '2', '13233333337', '129@qq.com', '2018-05-20', '10000', '0');
INSERT INTO `t_user` VALUES ('170327024', '江萱', '123456', '女', '2', '13233333338', 'student', '2018-05-20', '10000', '0');
INSERT INTO `t_user` VALUES ('170327025', '方杰', '123456', '男', '2', '13233333339', '134@qq.com', '2018-05-20', '10000', '0');
INSERT INTO `t_user` VALUES ('170327026', '龙心', '123456', '女', '2', '13233333340', '136@qq,com', '2018-05-20', '10000', '0');
INSERT INTO `t_user` VALUES ('170327027', '陈莉', '123456', '女', '2', '13233333341', '137@qq.com', '2018-05-20', '10000', '0');
INSERT INTO `t_user` VALUES ('386000001', '叶东毅', '12345678', '男', '3', '13400000001', '60001@qq.com', '2018-05-20', '10000', '0');
INSERT INTO `t_user` VALUES ('386000002', '于春燕', '12345678', '女', '3', '13400000002', '60002@qq.com', '2018-05-20', '10000', '0');
INSERT INTO `t_user` VALUES ('386000003', '标哥', '123456', '男', '3', '13400000003', 'teacher', '2018-05-20', '10000', '0');

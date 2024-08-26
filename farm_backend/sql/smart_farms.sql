/*
 Navicat MySQL Data Transfer

 Source Server         : 本地
 Source Server Type    : MySQL
 Source Server Version : 50731 (5.7.31-log)
 Source Host           : 127.0.0.1:3306
 Source Schema         : smart_farms

 Target Server Type    : MySQL
 Target Server Version : 50731 (5.7.31-log)
 File Encoding         : 65001

 Date: 26/08/2024 23:05:40
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept` (
  `dept_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '部门id',
  `parent_id` bigint(20) DEFAULT '0' COMMENT '父部门id',
  `ancestors` varchar(50) DEFAULT '' COMMENT '祖级列表',
  `dept_name` varchar(30) DEFAULT '' COMMENT '部门名称',
  `order_num` int(4) DEFAULT '0' COMMENT '显示顺序',
  `leader` varchar(20) DEFAULT NULL COMMENT '负责人',
  `phone` varchar(11) DEFAULT NULL COMMENT '联系电话',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `status` char(1) DEFAULT '0' COMMENT '部门状态（0正常 1停用）',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`dept_id`)
) ENGINE=InnoDB AUTO_INCREMENT=110 DEFAULT CHARSET=utf8 COMMENT='部门表';

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `menu_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
  `menu_name` varchar(50) NOT NULL COMMENT '菜单名称',
  `parent_id` bigint(20) DEFAULT '0' COMMENT '父菜单ID',
  `order_num` int(4) DEFAULT '0' COMMENT '显示顺序',
  `path` varchar(200) DEFAULT '' COMMENT '路由地址',
  `component` varchar(255) DEFAULT NULL COMMENT '组件路径',
  `query` varchar(255) DEFAULT NULL COMMENT '路由参数',
  `is_frame` int(1) DEFAULT '1' COMMENT '是否为外链（0是 1否）',
  `is_cache` int(1) DEFAULT '0' COMMENT '是否缓存（0缓存 1不缓存）',
  `menu_type` char(1) DEFAULT '' COMMENT '菜单类型（M目录 C菜单 F按钮）',
  `visible` char(1) DEFAULT '0' COMMENT '菜单状态（0显示 1隐藏）',
  `status` char(1) DEFAULT '0' COMMENT '菜单状态（0正常 1停用）',
  `perms` varchar(100) DEFAULT NULL COMMENT '权限标识',
  `icon` varchar(100) DEFAULT '#' COMMENT '菜单图标',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2074 DEFAULT CHARSET=utf8 COMMENT='菜单权限表';

-- ----------------------------
-- Table structure for sys_post
-- ----------------------------
DROP TABLE IF EXISTS `sys_post`;
CREATE TABLE `sys_post` (
  `post_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '岗位ID',
  `post_code` varchar(64) NOT NULL COMMENT '岗位编码',
  `post_name` varchar(50) NOT NULL COMMENT '岗位名称',
  `post_sort` int(4) NOT NULL COMMENT '显示顺序',
  `status` char(1) NOT NULL COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`post_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='岗位信息表';

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `role_name` varchar(30) NOT NULL COMMENT '角色名称',
  `role_key` varchar(100) NOT NULL COMMENT '角色权限字符串',
  `role_sort` int(4) NOT NULL COMMENT '显示顺序',
  `data_scope` char(1) DEFAULT '1' COMMENT '数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）',
  `menu_check_strictly` tinyint(1) DEFAULT '1' COMMENT '菜单树选择项是否关联显示',
  `dept_check_strictly` tinyint(1) DEFAULT '1' COMMENT '部门树选择项是否关联显示',
  `status` char(1) NOT NULL COMMENT '角色状态（0正常 1停用）',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=utf8 COMMENT='角色信息表';

-- ----------------------------
-- Table structure for sys_role_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_dept`;
CREATE TABLE `sys_role_dept` (
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `dept_id` bigint(20) NOT NULL COMMENT '部门ID',
  PRIMARY KEY (`role_id`,`dept_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色和部门关联表';

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `menu_id` bigint(20) NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`role_id`,`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色和菜单关联表';

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `dept_id` bigint(20) DEFAULT NULL COMMENT '部门ID',
  `user_name` varchar(30) NOT NULL COMMENT '用户账号',
  `nick_name` varchar(30) NOT NULL COMMENT '用户昵称',
  `user_type` varchar(2) DEFAULT '00' COMMENT '用户类型（00系统用户）',
  `email` varchar(50) DEFAULT '' COMMENT '用户邮箱',
  `phonenumber` varchar(11) DEFAULT '' COMMENT '手机号码',
  `sex` char(1) DEFAULT '0' COMMENT '用户性别（0男 1女 2未知）',
  `avatar` varchar(100) DEFAULT '' COMMENT '头像地址',
  `password` varchar(100) DEFAULT '' COMMENT '密码',
  `status` char(1) DEFAULT '0' COMMENT '帐号状态（0正常 1停用）',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `login_ip` varchar(128) DEFAULT '' COMMENT '最后登录IP',
  `login_date` datetime DEFAULT NULL COMMENT '最后登录时间',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='用户信息表';

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户和角色关联表';

-- ----------------------------
-- Table structure for gen_table
-- ----------------------------
DROP TABLE IF EXISTS `gen_table`;
CREATE TABLE `gen_table` (
  `table_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `table_name` varchar(200) DEFAULT '' COMMENT '表名称',
  `table_comment` varchar(500) DEFAULT '' COMMENT '表描述',
  `sub_table_name` varchar(64) DEFAULT NULL COMMENT '关联子表的表名',
  `sub_table_fk_name` varchar(64) DEFAULT NULL COMMENT '子表关联的外键名',
  `class_name` varchar(100) DEFAULT '' COMMENT '实体类名称',
  `tpl_category` varchar(200) DEFAULT 'crud' COMMENT '使用的模板（crud单表操作 tree树表操作）',
  `tpl_web_type` varchar(30) DEFAULT '' COMMENT '前端模板类型（element-ui模版 element-plus模版）',
  `package_name` varchar(100) DEFAULT NULL COMMENT '生成包路径',
  `module_name` varchar(30) DEFAULT NULL COMMENT '生成模块名',
  `business_name` varchar(30) DEFAULT NULL COMMENT '生成业务名',
  `function_name` varchar(50) DEFAULT NULL COMMENT '生成功能名',
  `function_author` varchar(50) DEFAULT NULL COMMENT '生成功能作者',
  `gen_type` char(1) DEFAULT '0' COMMENT '生成代码方式（0zip压缩包 1自定义路径）',
  `gen_path` varchar(200) DEFAULT '/' COMMENT '生成路径（不填默认项目路径）',
  `options` varchar(1000) DEFAULT NULL COMMENT '其它生成选项',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`table_id`)
) ENGINE=InnoDB AUTO_INCREMENT=123 DEFAULT CHARSET=utf8 COMMENT='代码生成业务表';

-- ----------------------------
-- Table structure for gen_table_column
-- ----------------------------
DROP TABLE IF EXISTS `gen_table_column`;
CREATE TABLE `gen_table_column` (
  `column_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `table_id` bigint(20) DEFAULT NULL COMMENT '归属表编号',
  `column_name` varchar(200) DEFAULT NULL COMMENT '列名称',
  `column_comment` varchar(500) DEFAULT NULL COMMENT '列描述',
  `column_type` varchar(100) DEFAULT NULL COMMENT '列类型',
  `java_type` varchar(500) DEFAULT NULL COMMENT 'JAVA类型',
  `java_field` varchar(200) DEFAULT NULL COMMENT 'JAVA字段名',
  `is_pk` char(1) DEFAULT NULL COMMENT '是否主键（1是）',
  `is_increment` char(1) DEFAULT NULL COMMENT '是否自增（1是）',
  `is_required` char(1) DEFAULT NULL COMMENT '是否必填（1是）',
  `is_insert` char(1) DEFAULT NULL COMMENT '是否为插入字段（1是）',
  `is_edit` char(1) DEFAULT NULL COMMENT '是否编辑字段（1是）',
  `is_list` char(1) DEFAULT NULL COMMENT '是否列表字段（1是）',
  `is_query` char(1) DEFAULT NULL COMMENT '是否查询字段（1是）',
  `query_type` varchar(200) DEFAULT 'EQ' COMMENT '查询方式（等于、不等于、大于、小于、范围）',
  `html_type` varchar(200) DEFAULT NULL COMMENT '显示类型（文本框、文本域、下拉框、复选框、单选框、日期控件）',
  `dict_type` varchar(200) DEFAULT '' COMMENT '字典类型',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`column_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1607 DEFAULT CHARSET=utf8 COMMENT='代码生成业务表字段';

-- ----------------------------
-- Table structure for sys_dict_data
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_data`;
CREATE TABLE `sys_dict_data` (
  `dict_code` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '字典编码',
  `dict_sort` int(4) DEFAULT '0' COMMENT '字典排序',
  `dict_label` varchar(100) DEFAULT '' COMMENT '字典标签',
  `dict_value` varchar(100) DEFAULT '' COMMENT '字典键值',
  `dict_type` varchar(100) DEFAULT '' COMMENT '字典类型',
  `css_class` varchar(100) DEFAULT NULL COMMENT '样式属性（其他样式扩展）',
  `list_class` varchar(100) DEFAULT NULL COMMENT '表格回显样式',
  `is_default` char(1) DEFAULT 'N' COMMENT '是否默认（Y是 N否）',
  `status` char(1) DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`dict_code`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8 COMMENT='字典数据表';

-- ----------------------------
-- Table structure for sys_dict_type
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_type`;
CREATE TABLE `sys_dict_type` (
  `dict_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '字典主键',
  `dict_name` varchar(100) DEFAULT '' COMMENT '字典名称',
  `dict_type` varchar(100) DEFAULT '' COMMENT '字典类型',
  `status` char(1) DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`dict_id`),
  UNIQUE KEY `dict_type` (`dict_type`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COMMENT='字典类型表';

-- ----------------------------
-- Table structure for sys_job
-- ----------------------------
DROP TABLE IF EXISTS `sys_job`;
CREATE TABLE `sys_job` (
  `job_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '任务ID',
  `job_name` varchar(64) NOT NULL DEFAULT '' COMMENT '任务名称',
  `job_group` varchar(64) NOT NULL DEFAULT 'DEFAULT' COMMENT '任务组名',
  `invoke_target` varchar(500) NOT NULL COMMENT '调用目标字符串',
  `cron_expression` varchar(255) DEFAULT '' COMMENT 'cron执行表达式',
  `misfire_policy` varchar(20) DEFAULT '3' COMMENT '计划执行错误策略（1立即执行 2执行一次 3放弃执行）',
  `concurrent` char(1) DEFAULT '1' COMMENT '是否并发执行（0允许 1禁止）',
  `status` char(1) DEFAULT '0' COMMENT '状态（0正常 1暂停）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT '' COMMENT '备注信息',
  PRIMARY KEY (`job_id`,`job_name`,`job_group`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='定时任务调度表';

-- ----------------------------
-- Table structure for sys_job_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_job_log`;
CREATE TABLE `sys_job_log` (
  `job_log_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '任务日志ID',
  `job_name` varchar(64) NOT NULL COMMENT '任务名称',
  `job_group` varchar(64) NOT NULL COMMENT '任务组名',
  `invoke_target` varchar(500) NOT NULL COMMENT '调用目标字符串',
  `job_message` varchar(500) DEFAULT NULL COMMENT '日志信息',
  `status` char(1) DEFAULT '0' COMMENT '执行状态（0正常 1失败）',
  `exception_info` varchar(2000) DEFAULT '' COMMENT '异常信息',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`job_log_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='定时任务调度日志表';

-- ----------------------------
-- Table structure for sys_logininfor
-- ----------------------------
DROP TABLE IF EXISTS `sys_logininfor`;
CREATE TABLE `sys_logininfor` (
  `info_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '访问ID',
  `user_name` varchar(50) DEFAULT '' COMMENT '用户账号',
  `ipaddr` varchar(128) DEFAULT '' COMMENT '登录IP地址',
  `login_location` varchar(255) DEFAULT '' COMMENT '登录地点',
  `browser` varchar(50) DEFAULT '' COMMENT '浏览器类型',
  `os` varchar(50) DEFAULT '' COMMENT '操作系统',
  `status` char(1) DEFAULT '0' COMMENT '登录状态（0成功 1失败）',
  `msg` varchar(255) DEFAULT '' COMMENT '提示消息',
  `login_time` datetime DEFAULT NULL COMMENT '访问时间',
  PRIMARY KEY (`info_id`),
  KEY `idx_sys_logininfor_s` (`status`),
  KEY `idx_sys_logininfor_lt` (`login_time`)
) ENGINE=InnoDB AUTO_INCREMENT=204 DEFAULT CHARSET=utf8 COMMENT='系统访问记录';

-- ----------------------------
-- Table structure for sys_notice
-- ----------------------------
DROP TABLE IF EXISTS `sys_notice`;
CREATE TABLE `sys_notice` (
  `notice_id` int(4) NOT NULL AUTO_INCREMENT COMMENT '公告ID',
  `notice_title` varchar(50) NOT NULL COMMENT '公告标题',
  `notice_type` char(1) NOT NULL COMMENT '公告类型（1通知 2公告）',
  `notice_content` longblob COMMENT '公告内容',
  `status` char(1) DEFAULT '0' COMMENT '公告状态（0正常 1关闭）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`notice_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='通知公告表';

-- ----------------------------
-- Table structure for sys_oper_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_oper_log`;
CREATE TABLE `sys_oper_log` (
  `oper_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '日志主键',
  `title` varchar(50) DEFAULT '' COMMENT '模块标题',
  `business_type` int(2) DEFAULT '0' COMMENT '业务类型（0其它 1新增 2修改 3删除）',
  `method` varchar(100) DEFAULT '' COMMENT '方法名称',
  `request_method` varchar(10) DEFAULT '' COMMENT '请求方式',
  `operator_type` int(1) DEFAULT '0' COMMENT '操作类别（0其它 1后台用户 2手机端用户）',
  `oper_name` varchar(50) DEFAULT '' COMMENT '操作人员',
  `dept_name` varchar(50) DEFAULT '' COMMENT '部门名称',
  `oper_url` varchar(255) DEFAULT '' COMMENT '请求URL',
  `oper_ip` varchar(128) DEFAULT '' COMMENT '主机地址',
  `oper_location` varchar(255) DEFAULT '' COMMENT '操作地点',
  `oper_param` varchar(2000) DEFAULT '' COMMENT '请求参数',
  `json_result` varchar(2000) DEFAULT '' COMMENT '返回参数',
  `status` int(1) DEFAULT '0' COMMENT '操作状态（0正常 1异常）',
  `error_msg` varchar(2000) DEFAULT '' COMMENT '错误消息',
  `oper_time` datetime DEFAULT NULL COMMENT '操作时间',
  `cost_time` bigint(20) DEFAULT '0' COMMENT '消耗时间',
  PRIMARY KEY (`oper_id`),
  KEY `idx_sys_oper_log_bt` (`business_type`),
  KEY `idx_sys_oper_log_s` (`status`),
  KEY `idx_sys_oper_log_ot` (`oper_time`)
) ENGINE=InnoDB AUTO_INCREMENT=353 DEFAULT CHARSET=utf8 COMMENT='操作日志记录';

-- ----------------------------
-- Table structure for sys_user_post
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_post`;
CREATE TABLE `sys_user_post` (
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `post_id` bigint(20) NOT NULL COMMENT '岗位ID',
  PRIMARY KEY (`user_id`,`post_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户与岗位关联表';

-- ----------------------------
-- Table structure for QRTZ_BLOB_TRIGGERS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_BLOB_TRIGGERS`;
CREATE TABLE `QRTZ_BLOB_TRIGGERS` (
  `sched_name` varchar(120) NOT NULL COMMENT '调度名称',
  `trigger_name` varchar(200) NOT NULL COMMENT 'qrtz_triggers表trigger_name的外键',
  `trigger_group` varchar(200) NOT NULL COMMENT 'qrtz_triggers表trigger_group的外键',
  `blob_data` blob COMMENT '存放持久化Trigger对象',
  PRIMARY KEY (`sched_name`,`trigger_name`,`trigger_group`),
  CONSTRAINT `qrtz_blob_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `trigger_name`, `trigger_group`) REFERENCES `QRTZ_TRIGGERS` (`sched_name`, `trigger_name`, `trigger_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Blob类型的触发器表';

-- ----------------------------
-- Table structure for QRTZ_CALENDARS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_CALENDARS`;
CREATE TABLE `QRTZ_CALENDARS` (
  `sched_name` varchar(120) NOT NULL COMMENT '调度名称',
  `calendar_name` varchar(200) NOT NULL COMMENT '日历名称',
  `calendar` blob NOT NULL COMMENT '存放持久化calendar对象',
  PRIMARY KEY (`sched_name`,`calendar_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='日历信息表';

-- ----------------------------
-- Table structure for QRTZ_CRON_TRIGGERS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_CRON_TRIGGERS`;
CREATE TABLE `QRTZ_CRON_TRIGGERS` (
  `sched_name` varchar(120) NOT NULL COMMENT '调度名称',
  `trigger_name` varchar(200) NOT NULL COMMENT 'qrtz_triggers表trigger_name的外键',
  `trigger_group` varchar(200) NOT NULL COMMENT 'qrtz_triggers表trigger_group的外键',
  `cron_expression` varchar(200) NOT NULL COMMENT 'cron表达式',
  `time_zone_id` varchar(80) DEFAULT NULL COMMENT '时区',
  PRIMARY KEY (`sched_name`,`trigger_name`,`trigger_group`),
  CONSTRAINT `qrtz_cron_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `trigger_name`, `trigger_group`) REFERENCES `QRTZ_TRIGGERS` (`sched_name`, `trigger_name`, `trigger_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Cron类型的触发器表';

-- ----------------------------
-- Table structure for QRTZ_FIRED_TRIGGERS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_FIRED_TRIGGERS`;
CREATE TABLE `QRTZ_FIRED_TRIGGERS` (
  `sched_name` varchar(120) NOT NULL COMMENT '调度名称',
  `entry_id` varchar(95) NOT NULL COMMENT '调度器实例id',
  `trigger_name` varchar(200) NOT NULL COMMENT 'qrtz_triggers表trigger_name的外键',
  `trigger_group` varchar(200) NOT NULL COMMENT 'qrtz_triggers表trigger_group的外键',
  `instance_name` varchar(200) NOT NULL COMMENT '调度器实例名',
  `fired_time` bigint(13) NOT NULL COMMENT '触发的时间',
  `sched_time` bigint(13) NOT NULL COMMENT '定时器制定的时间',
  `priority` int(11) NOT NULL COMMENT '优先级',
  `state` varchar(16) NOT NULL COMMENT '状态',
  `job_name` varchar(200) DEFAULT NULL COMMENT '任务名称',
  `job_group` varchar(200) DEFAULT NULL COMMENT '任务组名',
  `is_nonconcurrent` varchar(1) DEFAULT NULL COMMENT '是否并发',
  `requests_recovery` varchar(1) DEFAULT NULL COMMENT '是否接受恢复执行',
  PRIMARY KEY (`sched_name`,`entry_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='已触发的触发器表';

-- ----------------------------
-- Table structure for QRTZ_JOB_DETAILS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_JOB_DETAILS`;
CREATE TABLE `QRTZ_JOB_DETAILS` (
  `sched_name` varchar(120) NOT NULL COMMENT '调度名称',
  `job_name` varchar(200) NOT NULL COMMENT '任务名称',
  `job_group` varchar(200) NOT NULL COMMENT '任务组名',
  `description` varchar(250) DEFAULT NULL COMMENT '相关介绍',
  `job_class_name` varchar(250) NOT NULL COMMENT '执行任务类名称',
  `is_durable` varchar(1) NOT NULL COMMENT '是否持久化',
  `is_nonconcurrent` varchar(1) NOT NULL COMMENT '是否并发',
  `is_update_data` varchar(1) NOT NULL COMMENT '是否更新数据',
  `requests_recovery` varchar(1) NOT NULL COMMENT '是否接受恢复执行',
  `job_data` blob COMMENT '存放持久化job对象',
  PRIMARY KEY (`sched_name`,`job_name`,`job_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='任务详细信息表';

-- ----------------------------
-- Table structure for QRTZ_LOCKS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_LOCKS`;
CREATE TABLE `QRTZ_LOCKS` (
  `sched_name` varchar(120) NOT NULL COMMENT '调度名称',
  `lock_name` varchar(40) NOT NULL COMMENT '悲观锁名称',
  PRIMARY KEY (`sched_name`,`lock_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='存储的悲观锁信息表';

-- ----------------------------
-- Table structure for QRTZ_PAUSED_TRIGGER_GRPS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_PAUSED_TRIGGER_GRPS`;
CREATE TABLE `QRTZ_PAUSED_TRIGGER_GRPS` (
  `sched_name` varchar(120) NOT NULL COMMENT '调度名称',
  `trigger_group` varchar(200) NOT NULL COMMENT 'qrtz_triggers表trigger_group的外键',
  PRIMARY KEY (`sched_name`,`trigger_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='暂停的触发器表';

-- ----------------------------
-- Table structure for QRTZ_SCHEDULER_STATE
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_SCHEDULER_STATE`;
CREATE TABLE `QRTZ_SCHEDULER_STATE` (
  `sched_name` varchar(120) NOT NULL COMMENT '调度名称',
  `instance_name` varchar(200) NOT NULL COMMENT '实例名称',
  `last_checkin_time` bigint(13) NOT NULL COMMENT '上次检查时间',
  `checkin_interval` bigint(13) NOT NULL COMMENT '检查间隔时间',
  PRIMARY KEY (`sched_name`,`instance_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='调度器状态表';

-- ----------------------------
-- Table structure for QRTZ_SIMPLE_TRIGGERS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_SIMPLE_TRIGGERS`;
CREATE TABLE `QRTZ_SIMPLE_TRIGGERS` (
  `sched_name` varchar(120) NOT NULL COMMENT '调度名称',
  `trigger_name` varchar(200) NOT NULL COMMENT 'qrtz_triggers表trigger_name的外键',
  `trigger_group` varchar(200) NOT NULL COMMENT 'qrtz_triggers表trigger_group的外键',
  `repeat_count` bigint(7) NOT NULL COMMENT '重复的次数统计',
  `repeat_interval` bigint(12) NOT NULL COMMENT '重复的间隔时间',
  `times_triggered` bigint(10) NOT NULL COMMENT '已经触发的次数',
  PRIMARY KEY (`sched_name`,`trigger_name`,`trigger_group`),
  CONSTRAINT `qrtz_simple_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `trigger_name`, `trigger_group`) REFERENCES `QRTZ_TRIGGERS` (`sched_name`, `trigger_name`, `trigger_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='简单触发器的信息表';

-- ----------------------------
-- Table structure for QRTZ_SIMPROP_TRIGGERS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_SIMPROP_TRIGGERS`;
CREATE TABLE `QRTZ_SIMPROP_TRIGGERS` (
  `sched_name` varchar(120) NOT NULL COMMENT '调度名称',
  `trigger_name` varchar(200) NOT NULL COMMENT 'qrtz_triggers表trigger_name的外键',
  `trigger_group` varchar(200) NOT NULL COMMENT 'qrtz_triggers表trigger_group的外键',
  `str_prop_1` varchar(512) DEFAULT NULL COMMENT 'String类型的trigger的第一个参数',
  `str_prop_2` varchar(512) DEFAULT NULL COMMENT 'String类型的trigger的第二个参数',
  `str_prop_3` varchar(512) DEFAULT NULL COMMENT 'String类型的trigger的第三个参数',
  `int_prop_1` int(11) DEFAULT NULL COMMENT 'int类型的trigger的第一个参数',
  `int_prop_2` int(11) DEFAULT NULL COMMENT 'int类型的trigger的第二个参数',
  `long_prop_1` bigint(20) DEFAULT NULL COMMENT 'long类型的trigger的第一个参数',
  `long_prop_2` bigint(20) DEFAULT NULL COMMENT 'long类型的trigger的第二个参数',
  `dec_prop_1` decimal(13,4) DEFAULT NULL COMMENT 'decimal类型的trigger的第一个参数',
  `dec_prop_2` decimal(13,4) DEFAULT NULL COMMENT 'decimal类型的trigger的第二个参数',
  `bool_prop_1` varchar(1) DEFAULT NULL COMMENT 'Boolean类型的trigger的第一个参数',
  `bool_prop_2` varchar(1) DEFAULT NULL COMMENT 'Boolean类型的trigger的第二个参数',
  PRIMARY KEY (`sched_name`,`trigger_name`,`trigger_group`),
  CONSTRAINT `qrtz_simprop_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `trigger_name`, `trigger_group`) REFERENCES `QRTZ_TRIGGERS` (`sched_name`, `trigger_name`, `trigger_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='同步机制的行锁表';

-- ----------------------------
-- Table structure for QRTZ_TRIGGERS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_TRIGGERS`;
CREATE TABLE `QRTZ_TRIGGERS` (
  `sched_name` varchar(120) NOT NULL COMMENT '调度名称',
  `trigger_name` varchar(200) NOT NULL COMMENT '触发器的名字',
  `trigger_group` varchar(200) NOT NULL COMMENT '触发器所属组的名字',
  `job_name` varchar(200) NOT NULL COMMENT 'qrtz_job_details表job_name的外键',
  `job_group` varchar(200) NOT NULL COMMENT 'qrtz_job_details表job_group的外键',
  `description` varchar(250) DEFAULT NULL COMMENT '相关介绍',
  `next_fire_time` bigint(13) DEFAULT NULL COMMENT '上一次触发时间（毫秒）',
  `prev_fire_time` bigint(13) DEFAULT NULL COMMENT '下一次触发时间（默认为-1表示不触发）',
  `priority` int(11) DEFAULT NULL COMMENT '优先级',
  `trigger_state` varchar(16) NOT NULL COMMENT '触发器状态',
  `trigger_type` varchar(8) NOT NULL COMMENT '触发器的类型',
  `start_time` bigint(13) NOT NULL COMMENT '开始时间',
  `end_time` bigint(13) DEFAULT NULL COMMENT '结束时间',
  `calendar_name` varchar(200) DEFAULT NULL COMMENT '日程表名称',
  `misfire_instr` smallint(2) DEFAULT NULL COMMENT '补偿执行的策略',
  `job_data` blob COMMENT '存放持久化job对象',
  PRIMARY KEY (`sched_name`,`trigger_name`,`trigger_group`),
  KEY `sched_name` (`sched_name`,`job_name`,`job_group`),
  CONSTRAINT `qrtz_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `job_name`, `job_group`) REFERENCES `QRTZ_JOB_DETAILS` (`sched_name`, `job_name`, `job_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='触发器详细信息表';

-- ----------------------------
-- Table structure for t_storage_files
-- ----------------------------
DROP TABLE IF EXISTS `t_storage_files`;
CREATE TABLE `t_storage_files` (
  `id` varchar(255) NOT NULL COMMENT 'id',
  `file_id` varchar(255) DEFAULT NULL COMMENT '文件ID',
  `file_name` varchar(255) DEFAULT NULL COMMENT '文件名',
  `original_name` varchar(255) DEFAULT NULL COMMENT '原始文件名',
  `file_suffix` varchar(255) DEFAULT NULL COMMENT '文件后缀',
  `url` varchar(255) DEFAULT NULL COMMENT 'url 地址',
  `create_by_name` varchar(255) DEFAULT NULL COMMENT '创建人姓名',
  `create_by` varchar(255) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `is_deleted` int(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='存储资源文件';

-- ----------------------------
-- Table structure for t_land_service_extra
-- ----------------------------
DROP TABLE IF EXISTS `t_land_service_extra`;
CREATE TABLE `t_land_service_extra` (
  `id` varchar(255) NOT NULL,
  `service_id` varchar(255) DEFAULT NULL COMMENT '服务编号',
  `extra_id` varchar(255) DEFAULT NULL COMMENT '服务扩展编号',
  `extra_name` varchar(255) DEFAULT NULL COMMENT '特色服务名称',
  `extra_files` varchar(255) DEFAULT NULL COMMENT '附件;10，包种植、包配送；20，包种植；30，只出租土地，不包含其他服务',
  `summary` varchar(255) DEFAULT NULL COMMENT '说明',
  `create_by_name` varchar(255) DEFAULT NULL COMMENT '创建人姓名',
  `create_by` varchar(255) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `is_deleted` varchar(1) NOT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='服务信息扩展表';

-- ----------------------------
-- Table structure for merchant_details
-- ----------------------------
DROP TABLE IF EXISTS `merchant_details`;
CREATE TABLE `merchant_details` (
  `details_id` char(32) NOT NULL COMMENT '列表id',
  `pay_type` varchar(100) NOT NULL COMMENT '支付类型(支付渠道) 详情查看com.egzosn.pay.spring.boot.core.merchant.PaymentPlatform对应子类，aliPay 支付宝， wxPay微信..等等',
  `appid` varchar(32) DEFAULT NULL COMMENT '应用id',
  `mch_id` varchar(32) DEFAULT NULL COMMENT '商户id，商户号，合作伙伴id等等',
  `cert_store_type` varchar(16) DEFAULT NULL COMMENT '当前面私钥公钥为证书类型的时候，这里必填，可选值:PATH,STR,INPUT_STREAM,CLASS_PATH,URL',
  `key_private` mediumtext COMMENT '私钥或私钥证书',
  `key_public` mediumtext COMMENT '公钥或公钥证书',
  `key_cert` varchar(20480) DEFAULT NULL COMMENT 'key证书,附加证书使用，如SSL证书，或者银联根级证书方面',
  `key_cert_pwd` varchar(32) DEFAULT NULL COMMENT '私钥证书或key证书的密码',
  `notify_url` varchar(256) DEFAULT NULL COMMENT '异步回调',
  `return_url` varchar(256) DEFAULT NULL COMMENT '同步回调地址，大部分用于付款成功后页面转跳',
  `sign_type` varchar(16) NOT NULL COMMENT '签名方式,目前已实现多种签名方式详情查看com.egzosn.pay.common.util.sign.encrypt。MD5,RSA等等',
  `seller` varchar(32) DEFAULT NULL COMMENT '收款账号，暂时只有支付宝部分使用，可根据开发者自行使用',
  `sub_app_id` varchar(32) DEFAULT NULL COMMENT '子appid',
  `sub_mch_id` varchar(32) DEFAULT NULL COMMENT '子商户id',
  `input_charset` varchar(16) NOT NULL COMMENT '编码类型，大部分为utf-8',
  `is_test` tinyint(1) NOT NULL COMMENT '是否为测试环境: 0 否，1 测试环境',
  PRIMARY KEY (`details_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='支付渠道配置信息表';

-- ----------------------------
-- Table structure for ums_feedback
-- ----------------------------
DROP TABLE IF EXISTS `ums_feedback`;
CREATE TABLE `ums_feedback` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `feedback_id` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL COMMENT '类型',
  `content` varchar(500) DEFAULT NULL COMMENT '具体说明',
  `images` text COMMENT '图片',
  `phone` varchar(255) DEFAULT NULL COMMENT '联系电话',
  `handle_status` int(11) DEFAULT NULL COMMENT '处理状态 0：未处理  1：已处理',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `handle_time` datetime(3) DEFAULT NULL COMMENT '处理时间',
  `create_by_name` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '创建人姓名',
  `create_by` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint(20) DEFAULT NULL COMMENT '修改人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `is_deleted` varchar(1) CHARACTER SET utf8 NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='意见反馈';

-- ----------------------------
-- Table structure for sys_address
-- ----------------------------
DROP TABLE IF EXISTS `sys_address`;
CREATE TABLE `sys_address` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `code` bigint(20) NOT NULL COMMENT '地区邮编',
  `parent_code` bigint(20) NOT NULL COMMENT '父地区邮编',
  `name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '地区名',
  `level` tinyint(4) NOT NULL COMMENT '地区层级',
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `address_code_parent_code_unique` (`code`,`parent_code`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=56925 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='地区编码表';

-- ----------------------------
-- Table structure for ums_member
-- ----------------------------
DROP TABLE IF EXISTS `ums_member`;
CREATE TABLE `ums_member` (
  `id` varchar(30) NOT NULL,
  `member_id` bigint(30) DEFAULT NULL,
  `nickname` varchar(64) DEFAULT NULL COMMENT '昵称',
  `password` varchar(64) DEFAULT NULL COMMENT '密码',
  `phone_encrypted` varchar(255) DEFAULT NULL COMMENT '加密手机号',
  `phone_hidden` varchar(64) DEFAULT NULL COMMENT '隐藏前三位后四位的手机号',
  `mark` varchar(255) DEFAULT '' COMMENT '用户备注',
  `status` int(1) DEFAULT NULL COMMENT '帐号启用状态:0->禁用；1->启用',
  `avatar` varchar(256) DEFAULT NULL COMMENT '头像',
  `gender` int(1) DEFAULT NULL COMMENT '性别：0->未知；1->男；2->女',
  `city` varchar(64) DEFAULT NULL COMMENT '用户所在城市',
  `province` varchar(64) DEFAULT NULL COMMENT '用户所在省份',
  `country` varchar(64) DEFAULT NULL COMMENT '用户所在国家',
  `remark` varchar(256) DEFAULT NULL COMMENT '公众号运营者对粉丝的备注，公众号运营者可在微信公众平台用户管理界面对粉丝添加备注',
  `birthday` date DEFAULT NULL COMMENT '生日',
  `spread_uid` bigint(20) unsigned DEFAULT '0' COMMENT '推广员id',
  `spread_time` datetime DEFAULT NULL COMMENT '推广员关联时间',
  `level` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '等级',
  `integral` decimal(8,2) unsigned NOT NULL DEFAULT '0.00' COMMENT '用户剩余积分',
  `create_by_name` varchar(255) DEFAULT NULL COMMENT '创建人姓名',
  `create_by` varchar(255) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint(20) DEFAULT NULL COMMENT '修改人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `is_deleted` varchar(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='会员信息';

-- ----------------------------
-- Table structure for ums_member_wechat
-- ----------------------------
DROP TABLE IF EXISTS `ums_member_wechat`;
CREATE TABLE `ums_member_wechat` (
  `id` varchar(255) NOT NULL,
  `member_id` bigint(30) DEFAULT NULL,
  `unionid` varchar(30) DEFAULT NULL COMMENT '只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段',
  `openid` varchar(30) DEFAULT NULL COMMENT '用户的标识，对当前公众号唯一',
  `routine_openid` varchar(32) DEFAULT NULL COMMENT '小程序唯一身份ID',
  `groupid` smallint(5) DEFAULT '0' COMMENT '用户所在的分组ID（兼容旧的用户分组接口）',
  `tagid_list` varchar(256) DEFAULT NULL COMMENT '用户被打上的标签ID列表',
  `subscribe` tinyint(3) DEFAULT '1' COMMENT '用户是否订阅该公众号标识',
  `subscribe_time` int(10) DEFAULT NULL COMMENT '关注公众号时间',
  `session_key` varchar(32) DEFAULT NULL COMMENT '小程序用户会话密匙',
  `access_token` varchar(500) DEFAULT NULL COMMENT 'token',
  `expires_in` int(11) DEFAULT NULL COMMENT '过期时间',
  `refresh_token` varchar(500) DEFAULT NULL COMMENT '刷新token',
  `expire_time` datetime(3) DEFAULT NULL COMMENT '过期时间',
  `remark` varchar(255) DEFAULT NULL,
  `create_by_name` varchar(255) DEFAULT NULL COMMENT '创建人姓名',
  `create_by` varchar(255) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint(20) DEFAULT NULL COMMENT '修改人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `is_deleted` varchar(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='用户微信信息';

-- ----------------------------
-- Table structure for ums_member_account
-- ----------------------------
DROP TABLE IF EXISTS `ums_member_account`;
CREATE TABLE `ums_member_account` (
  `id` varchar(50) NOT NULL,
  `member_id` bigint(30) NOT NULL,
  `integral_balance` decimal(10,2) DEFAULT '0.00' COMMENT '积分余额',
  `total_integral_balance` decimal(10,2) DEFAULT NULL COMMENT '历史总共积分',
  `remark` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `create_by_name` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '创建人姓名',
  `create_by` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint(20) DEFAULT NULL COMMENT '修改人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `is_deleted` varchar(1) CHARACTER SET utf8 NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='会员账户表';

-- ----------------------------
-- Table structure for ums_member_address
-- ----------------------------
DROP TABLE IF EXISTS `ums_member_address`;
CREATE TABLE `ums_member_address` (
  `id` varchar(255) NOT NULL,
  `member_id` bigint(30) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL COMMENT '收货人名称',
  `phone_hidden` varchar(64) DEFAULT NULL COMMENT '隐藏前三位后四位的手机号',
  `phone_encrypted` varchar(255) DEFAULT NULL COMMENT '手机号密文',
  `default_status` int(1) DEFAULT NULL COMMENT '是否为默认',
  `post_code` varchar(100) DEFAULT NULL COMMENT '邮政编码',
  `province` varchar(100) DEFAULT NULL COMMENT '省份/直辖市',
  `city` varchar(100) DEFAULT NULL COMMENT '城市',
  `district` varchar(100) DEFAULT NULL COMMENT '区',
  `detail_address` varchar(128) DEFAULT NULL COMMENT '详细地址(街道)',
  `is_default` tinyint(3) unsigned zerofill NOT NULL DEFAULT '000' COMMENT '是否默认',
  `province_id` bigint(20) DEFAULT NULL COMMENT '省份/直辖市id',
  `city_id` bigint(20) DEFAULT NULL COMMENT '城市id',
  `district_id` bigint(20) DEFAULT NULL COMMENT '区id',
  `remark` varchar(255) DEFAULT NULL,
  `create_by` varchar(255) DEFAULT NULL COMMENT '创建人',
  `create_by_name` varchar(255) DEFAULT NULL COMMENT '创建人姓名',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint(20) DEFAULT NULL COMMENT '修改人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `is_deleted` varchar(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='会员收货地址';

-- ----------------------------
-- Table structure for ums_member_logininfor
-- ----------------------------
DROP TABLE IF EXISTS `ums_member_logininfor`;
CREATE TABLE `ums_member_logininfor` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `member_id` bigint(20) DEFAULT NULL COMMENT '会员id',
  `phone` varchar(255) DEFAULT NULL COMMENT '会员手机号',
  `ipaddr` varchar(128) DEFAULT '' COMMENT '登录IP地址',
  `login_location` varchar(255) DEFAULT '' COMMENT '登录地点',
  `browser` varchar(50) DEFAULT '' COMMENT '浏览器类型',
  `os` varchar(50) DEFAULT '' COMMENT '操作系统',
  `login_time` datetime DEFAULT NULL COMMENT '登陆时间',
  `create_by` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '创建人',
  `create_by_name` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '创建人姓名',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `is_deleted` varchar(1) CHARACTER SET utf8 NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1818316949742989315 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='会员登录记录';

-- ----------------------------
-- Table structure for t_order_pay
-- ----------------------------
DROP TABLE IF EXISTS `t_order_pay`;
CREATE TABLE `t_order_pay` (
  `id` varchar(255) NOT NULL COMMENT 'id',
  `order_id` varchar(255) DEFAULT NULL COMMENT '账单ID',
  `order_type` tinyint(4) DEFAULT '1' COMMENT '账单类型;1.租赁合同；2购买种子',
  `pay_id` varchar(255) DEFAULT NULL COMMENT '支付单ID',
  `create_by_name` varchar(255) DEFAULT NULL COMMENT '创建人姓名',
  `create_by` varchar(255) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `is_deleted` int(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='账单支付单关联表';

-- ----------------------------
-- Table structure for t_pay
-- ----------------------------
DROP TABLE IF EXISTS `t_pay`;
CREATE TABLE `t_pay` (
  `id` varchar(255) NOT NULL COMMENT 'id',
  `pay_id` varchar(255) DEFAULT NULL COMMENT '支付记录ID',
  `third_trade_no` varchar(255) DEFAULT NULL COMMENT '第三方订单号',
  `subject` varchar(255) DEFAULT NULL COMMENT '支付记录标题',
  `body` varchar(255) DEFAULT NULL COMMENT '支付商品描述信息',
  `pay_source` int(11) DEFAULT NULL COMMENT '支付来源;10.小程序，20，APP，30，H5',
  `pay_user_id` varchar(255) DEFAULT NULL COMMENT '支付用户ID',
  `pay_channel` int(11) DEFAULT NULL COMMENT '支付渠道;10.微信；20支付宝；30现金',
  `pay_amount` decimal(24,6) DEFAULT NULL COMMENT '支付金额',
  `update_amount` decimal(24,6) DEFAULT NULL COMMENT '后台修改的金额',
  `pay_status` int(11) DEFAULT NULL COMMENT '支付状态;00,待支付；10，支付中；20，支付成功；30-支付失败, 40-已撤销, 50-已退款, 60-订单关闭',
  `pay_time` datetime DEFAULT NULL COMMENT '支付时间',
  `expired_time` datetime DEFAULT NULL COMMENT '支付单过期时间',
  `create_by_name` varchar(255) DEFAULT NULL COMMENT '创建人姓名',
  `create_by` varchar(255) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `is_deleted` int(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='支付记录表';

-- ----------------------------
-- Table structure for t_field_management_record_files
-- ----------------------------
DROP TABLE IF EXISTS `t_field_management_record_files`;
CREATE TABLE `t_field_management_record_files` (
  `id` varchar(255) NOT NULL COMMENT 'id',
  `field_management_record_id` varchar(255) DEFAULT NULL COMMENT '记录ID',
  `file_id` varchar(255) DEFAULT NULL COMMENT '附件id',
  `create_by_name` varchar(255) DEFAULT NULL COMMENT '创建人姓名',
  `create_by` varchar(255) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `is_deleted` int(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='田间管理记录附件表';

-- ----------------------------
-- Table structure for t_field_management_record
-- ----------------------------
DROP TABLE IF EXISTS `t_field_management_record`;
CREATE TABLE `t_field_management_record` (
  `id` varchar(255) NOT NULL COMMENT 'id',
  `field_management_record_id` varchar(255) DEFAULT NULL COMMENT '记录ID',
  `growth_id` varchar(255) DEFAULT NULL COMMENT '生长周期ID',
  `operation_type` int(11) DEFAULT NULL COMMENT '操作类型;00 播种前准备，10，播种 20浇水；30，施肥，40除草，50 除虫，60防病，70，收获',
  `operation_time` datetime DEFAULT NULL COMMENT '操作时间',
  `operation_status` int(11) DEFAULT NULL COMMENT '操作状态',
  `operation_result` int(11) DEFAULT NULL COMMENT '操作结果',
  `operation_mode` int(11) DEFAULT NULL COMMENT '操作方式;10，线上代理；20线下预约',
  `create_by_name` varchar(255) DEFAULT NULL COMMENT '创建人姓名',
  `create_by` varchar(255) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `is_deleted` int(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='田间管理记录表';

-- ----------------------------
-- Table structure for t_farmland_lessee_seed
-- ----------------------------
DROP TABLE IF EXISTS `t_farmland_lessee_seed`;
CREATE TABLE `t_farmland_lessee_seed` (
  `id` varchar(255) NOT NULL COMMENT 'id',
  `farmland_lessee_id` varchar(255) DEFAULT NULL COMMENT '农田租赁信息ID',
  `seed_id` varchar(255) DEFAULT NULL COMMENT '种子ID',
  `create_by_name` varchar(255) DEFAULT NULL COMMENT '创建人姓名',
  `create_by` varchar(255) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `is_deleted` varchar(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='租赁信息和种子关联表';

-- ----------------------------
-- Table structure for t_farmland_lessee_order
-- ----------------------------
DROP TABLE IF EXISTS `t_farmland_lessee_order`;
CREATE TABLE `t_farmland_lessee_order` (
  `id` varchar(255) NOT NULL COMMENT 'id',
  `farmland_lessee_id` varchar(255) DEFAULT NULL COMMENT '农田租赁信息ID',
  `order_id` varchar(255) DEFAULT NULL COMMENT '账单ID',
  `create_by_name` varchar(255) DEFAULT NULL COMMENT '创建人姓名',
  `create_by` varchar(255) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `is_deleted` varchar(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='租赁信息和订单关联表';

-- ----------------------------
-- Table structure for t_express
-- ----------------------------
DROP TABLE IF EXISTS `t_express`;
CREATE TABLE `t_express` (
  `id` varchar(255) NOT NULL COMMENT 'id',
  `express_id` varchar(255) DEFAULT NULL COMMENT '快递信息ID',
  `order_id` varchar(255) DEFAULT NULL COMMENT '账单ID',
  `express_name` varchar(255) DEFAULT NULL COMMENT '物流公司名称',
  `express_code` varchar(255) DEFAULT NULL COMMENT '物流公司编码',
  `express_no` varchar(255) DEFAULT NULL COMMENT '快递编号',
  `create_by_name` varchar(255) DEFAULT NULL COMMENT '创建人姓名',
  `create_by` varchar(255) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `is_deleted` int(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='快递信息表';

-- ----------------------------
-- Table structure for t_comment
-- ----------------------------
DROP TABLE IF EXISTS `t_comment`;
CREATE TABLE `t_comment` (
  `id` varchar(255) NOT NULL COMMENT 'id',
  `field_management_record_id` varchar(255) DEFAULT NULL COMMENT '田间管理记录表ID',
  `comment_id` varchar(255) DEFAULT NULL COMMENT '评价记录ID',
  `score` int(11) DEFAULT NULL COMMENT '评分;10,1星；20,2星；30，3星；40,4星；50,5星',
  `content` text COMMENT '评论内容',
  `is_picture` int(11) DEFAULT NULL COMMENT '是否图片;1，是，0，否',
  `picture_ids` varchar(255) DEFAULT NULL COMMENT '图片ID集合',
  `sort` int(11) DEFAULT NULL COMMENT '排序序号',
  `is_show` int(11) DEFAULT NULL COMMENT '是否显示;1，显示，0隐藏',
  `create_by_name` varchar(255) DEFAULT NULL COMMENT '创建人姓名',
  `create_by` varchar(255) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `is_deleted` int(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='田间管理评价表';

-- ----------------------------
-- Table structure for t_booking_record
-- ----------------------------
DROP TABLE IF EXISTS `t_booking_record`;
CREATE TABLE `t_booking_record` (
  `id` varchar(255) NOT NULL COMMENT 'id',
  `booking_record_id` varchar(255) DEFAULT NULL COMMENT '预约记录ID',
  `field_management_record_id` varchar(255) DEFAULT NULL COMMENT '田间管理记录ID',
  `booking_time` datetime DEFAULT NULL COMMENT '预约时间',
  `booking_status` int(11) DEFAULT NULL COMMENT '预约状态;00，生成；10-预约成功；20-预约失败',
  `booking_remarks` varchar(255) DEFAULT NULL COMMENT '描述',
  `traffic_mode` int(11) DEFAULT NULL COMMENT '交通方式;10-自驾；20-公共交通',
  `create_by_name` varchar(255) DEFAULT NULL COMMENT '创建人姓名',
  `create_by` varchar(255) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `is_deleted` int(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='线下活动预约记录';

-- ----------------------------
-- Table structure for t_plot
-- ----------------------------
DROP TABLE IF EXISTS `t_plot`;
CREATE TABLE `t_plot` (
  `id` varchar(32) NOT NULL,
  `plot_id` varchar(255) DEFAULT NULL COMMENT '农场编号',
  `plot_name` varchar(255) DEFAULT NULL COMMENT '农场地块名称',
  `area` varchar(255) DEFAULT NULL COMMENT '面积单位;平方米',
  `lat` varchar(255) DEFAULT NULL COMMENT '维度',
  `lon` varchar(255) DEFAULT NULL COMMENT '经度',
  `owner_name` varchar(255) DEFAULT NULL COMMENT '负责人姓名',
  `owner_phone` int(11) DEFAULT NULL COMMENT '负责人手机号',
  `location` varchar(255) DEFAULT NULL COMMENT '位置',
  `status` varchar(255) DEFAULT NULL COMMENT '农场地块状态;0.未上架；1，待租赁，2，已租赁',
  `description` text COMMENT '描述',
  `file_ids` varchar(255) DEFAULT NULL COMMENT '附件ID集合;逗号分割',
  `create_by_name` varchar(255) DEFAULT NULL COMMENT '创建人姓名',
  `create_by` varchar(255) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `is_deleted` varchar(1) NOT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='农场地块';

-- ----------------------------
-- Table structure for t_plot_farmland
-- ----------------------------
DROP TABLE IF EXISTS `t_plot_farmland`;
CREATE TABLE `t_plot_farmland` (
  `id` varchar(32) NOT NULL,
  `plot_id` varchar(255) DEFAULT NULL COMMENT '农场编号',
  `farmland_id` varchar(255) DEFAULT NULL COMMENT '农田编号',
  `create_by_name` varchar(255) DEFAULT NULL COMMENT '创建人姓名',
  `create_by` varchar(255) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `is_deleted` int(1) NOT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='农场农田关联表';

-- ----------------------------
-- Table structure for t_refund
-- ----------------------------
DROP TABLE IF EXISTS `t_refund`;
CREATE TABLE `t_refund` (
  `id` varchar(255) NOT NULL COMMENT 'id',
  `refund_id` varchar(255) DEFAULT NULL COMMENT '退款记录ID',
  `pay_id` varchar(255) DEFAULT NULL COMMENT '支付记录ID',
  `third_refund_no` varchar(255) DEFAULT NULL COMMENT '第三方退款交易号',
  `third_trade_no` varchar(255) DEFAULT NULL COMMENT '第三方支付交易号',
  `pay_amount` decimal(24,6) DEFAULT NULL COMMENT '支付金额',
  `update_amount` decimal(24,6) DEFAULT NULL COMMENT '后台修改金额',
  `refund_amount` decimal(24,6) DEFAULT NULL COMMENT '退款金额',
  `refund_reason` varchar(900) DEFAULT NULL COMMENT '退款原因',
  `refund_status` int(11) DEFAULT NULL COMMENT '退款状态;00-退款记录生成；10-退款中；20退款成功；30-退款失败；40-退款关闭',
  `refund_time` datetime DEFAULT NULL COMMENT '退款时间',
  `expire_time` datetime DEFAULT NULL COMMENT '退款过期时间',
  `create_by_name` varchar(255) DEFAULT NULL COMMENT '创建人姓名',
  `create_by` varchar(255) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `is_deleted` int(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='退款记录表';

-- ----------------------------
-- Table structure for t_seed
-- ----------------------------
DROP TABLE IF EXISTS `t_seed`;
CREATE TABLE `t_seed` (
  `id` varchar(50) NOT NULL COMMENT 'id',
  `seed_id` varchar(100) DEFAULT NULL COMMENT '种子ID',
  `seed_name` varchar(255) DEFAULT NULL COMMENT '种子名称',
  `seed_type` int(11) DEFAULT NULL COMMENT '种子类型',
  `origin` varchar(255) DEFAULT NULL COMMENT '种子产地',
  `growth_cycle` int(11) DEFAULT NULL COMMENT '生长周期;单位天',
  `yield` decimal(24,6) DEFAULT NULL COMMENT '产量;单位吨/亩',
  `seedtime` varchar(255) DEFAULT NULL COMMENT '播种季节',
  `climate` text COMMENT '适宜气候描述',
  `planting_technique` text COMMENT '种植技术描述',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注',
  `unit_price` decimal(24,6) DEFAULT NULL COMMENT '单价;单位元/斤',
  `stock_total` varchar(255) DEFAULT NULL COMMENT '库存数量',
  `status` varchar(255) DEFAULT NULL COMMENT '商品状态;0待上架，10在售，20已下架',
  `file_ids` varchar(255) DEFAULT NULL COMMENT '附件ID集合;逗号分割',
  `create_by_name` varchar(255) DEFAULT NULL COMMENT '创建人姓名',
  `create_by` varchar(255) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `is_deleted` int(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='种子信息表';

-- ----------------------------
-- Table structure for t_seed_order
-- ----------------------------
DROP TABLE IF EXISTS `t_seed_order`;
CREATE TABLE `t_seed_order` (
  `id` varchar(255) NOT NULL COMMENT 'id',
  `seed_id` varchar(255) DEFAULT NULL COMMENT '种子ID',
  `order_id` varchar(255) DEFAULT NULL COMMENT '账单ID',
  `order_amount` decimal(24,6) DEFAULT NULL COMMENT '订单金额',
  `pay_amount` decimal(24,6) DEFAULT NULL COMMENT '实付金额',
  `buy_user_id` varchar(255) DEFAULT NULL COMMENT '购买的用户id',
  `order_status` int(11) DEFAULT NULL COMMENT '订单状态',
  `buy_count` int(11) DEFAULT NULL COMMENT '购买数量',
  `unit_price` decimal(24,6) DEFAULT NULL COMMENT '购买单价',
  `create_by_name` varchar(255) DEFAULT NULL COMMENT '创建人姓名',
  `create_by` varchar(255) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `is_deleted` int(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='种子购买订单关联表';

-- ----------------------------
-- Table structure for t_lessee
-- ----------------------------
DROP TABLE IF EXISTS `t_lessee`;
CREATE TABLE `t_lessee` (
  `id` varchar(40) NOT NULL COMMENT 'id',
  `user_id` varchar(255) DEFAULT NULL COMMENT '系统用户ID',
  `lessee_id` varchar(255) DEFAULT NULL COMMENT '租户ID',
  `lessee_name` varchar(255) DEFAULT NULL COMMENT '租户姓名',
  `lessee_phone` varchar(255) DEFAULT NULL COMMENT '租户手机号',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_by_name` varchar(255) DEFAULT NULL COMMENT '创建人姓名',
  `create_by` varchar(255) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_by` bigint(20) DEFAULT NULL COMMENT '修改人',
  `is_deleted` varchar(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='租户信息表';

-- ----------------------------
-- Table structure for t_farmland_lessee
-- ----------------------------
DROP TABLE IF EXISTS `t_farmland_lessee`;
CREATE TABLE `t_farmland_lessee` (
  `id` varchar(90) NOT NULL COMMENT 'id',
  `farmland_lessee_id` varchar(255) DEFAULT NULL COMMENT '农田租赁合同ID',
  `farmland_id` varchar(255) DEFAULT NULL COMMENT '农田id',
  `lessee_id` varchar(255) DEFAULT NULL COMMENT '租户id',
  `member_id` bigint(30) DEFAULT NULL,
  `land_area_id` varchar(255) DEFAULT NULL COMMENT '租赁面积ID',
  `service_id` varchar(255) DEFAULT NULL COMMENT '服务ID',
  `lessee_price` decimal(24,6) DEFAULT NULL COMMENT '租赁单价;单位：元/天',
  `lessee_amount` decimal(24,6) DEFAULT NULL COMMENT '租赁总金额;单位：元',
  `lessee_day` int(11) DEFAULT NULL COMMENT '租赁天数;单位:天',
  `lessee_start_date` datetime DEFAULT NULL COMMENT '租赁开始时间',
  `lessee_end_date` datetime DEFAULT NULL COMMENT '租赁结束时间',
  `status` int(11) DEFAULT NULL COMMENT '租赁状态;0，待支付，1，已生效，2已结束，3违约',
  `lessee_files` varchar(255) DEFAULT NULL COMMENT '合同附件id集合',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_by_name` varchar(255) DEFAULT NULL COMMENT '创建人姓名',
  `create_by` varchar(255) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` bigint(20) DEFAULT NULL COMMENT '修改人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `is_deleted` int(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='农田租赁信息表';

-- ----------------------------
-- Table structure for t_growth
-- ----------------------------
DROP TABLE IF EXISTS `t_growth`;
CREATE TABLE `t_growth` (
  `id` varchar(50) NOT NULL COMMENT 'id',
  `growth_id` varchar(100) DEFAULT NULL COMMENT '生长周期ID',
  `farmland_id` varchar(255) DEFAULT NULL COMMENT '农田ID',
  `farmland_lessee_id` varchar(255) DEFAULT NULL COMMENT '农田租赁合同ID',
  `seed_id` varchar(255) DEFAULT NULL COMMENT '种子ID',
  `land_area_id` varchar(255) DEFAULT NULL COMMENT '租赁面积ID',
  `farmland_snap` text COMMENT '农田信息快照',
  `seed_snap` text COMMENT '种子信息快照',
  `farmland_name` varchar(255) DEFAULT NULL COMMENT '农田昵称',
  `crop_name` varchar(255) DEFAULT NULL COMMENT '农作物名称',
  `scientific_name` varchar(255) DEFAULT NULL COMMENT '学名',
  `growth_stage` tinyint(4) DEFAULT '10' COMMENT '生长阶段;（如：10播种期、20生长期、30成熟期、40结束期等）',
  `growth_time` varchar(255) DEFAULT NULL COMMENT '生长时间;（以天为单位）',
  `start_date` varchar(255) DEFAULT NULL COMMENT '生长阶段的起始日期',
  `end_date` varchar(255) DEFAULT NULL COMMENT '生长阶段的结束日期',
  `growth_conditions` text COMMENT '生长条件;（如土壤湿度、光照需求、温度范围等）',
  `fertilizer_requirements` text COMMENT '施肥要求;（肥料种类、施肥时间、施肥量等）',
  `irrigation_needs` text COMMENT '灌溉需求;（灌溉方式、灌溉量、灌溉频率等）',
  `pest_control_measures` text COMMENT '病虫害防治措施;（如农药使用、生物防治等）',
  `climate_suitability` text COMMENT '气候适应性;（描述作物对气候的敏感性和适应性）',
  `soil_type` varchar(255) DEFAULT NULL COMMENT '土壤类型;（如砂土、壤土、粘土等）',
  `growth_phase_details` text COMMENT '生长阶段详细信息;（每个阶段的具体特点、注意事项等）',
  `yield_expectation` decimal(24,6) DEFAULT NULL COMMENT '预期产量;（单位面积或单棵作物的预期产量）',
  `variety_description` text COMMENT '品种描述;（包括特性、优势等）',
  `remarks` varchar(255) DEFAULT NULL,
  `create_by_name` varchar(255) DEFAULT NULL COMMENT '创建人姓名',
  `create_by` varchar(255) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `is_deleted` int(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='农作物生长周期信息表';

-- ----------------------------
-- Table structure for t_news
-- ----------------------------
DROP TABLE IF EXISTS `t_news`;
CREATE TABLE `t_news` (
  `id` varchar(255) NOT NULL COMMENT 'id',
  `news_id` varchar(255) DEFAULT NULL COMMENT '资讯ID',
  `news_type` int(11) DEFAULT NULL COMMENT '咨讯类型,字典中的news_type',
  `new_title` varchar(255) DEFAULT NULL,
  `summary` varchar(255) DEFAULT NULL COMMENT '摘要',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `thumbnail_file` varchar(255) DEFAULT NULL COMMENT '封面缩略图ID;1，是，0，否',
  `picture_ids` varchar(255) DEFAULT NULL COMMENT '图片ID集合',
  `content` longtext COMMENT '内容',
  `release_by` varchar(255) DEFAULT NULL COMMENT '发布人',
  `release_time` datetime DEFAULT NULL COMMENT '发布时间',
  `start_time` datetime DEFAULT NULL COMMENT '开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '结束时间',
  `status` int(11) DEFAULT NULL COMMENT '资讯状态;1，显示，0隐藏',
  `create_by_name` varchar(255) DEFAULT NULL COMMENT '创建人姓名',
  `create_by` varchar(255) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `is_deleted` int(11) NOT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='资讯表';

-- ----------------------------
-- Table structure for t_farmland
-- ----------------------------
DROP TABLE IF EXISTS `t_farmland`;
CREATE TABLE `t_farmland` (
  `id` varchar(90) NOT NULL COMMENT 'id',
  `farmland_id` varchar(255) DEFAULT NULL COMMENT 'farmland_id',
  `farmland_name` varchar(255) DEFAULT NULL COMMENT '农田名称',
  `area` varchar(255) DEFAULT NULL COMMENT '面积单位;平方米',
  `lat` varchar(255) DEFAULT NULL COMMENT '维度',
  `lon` varchar(255) DEFAULT NULL COMMENT '经度',
  `location` varchar(255) DEFAULT NULL COMMENT '位置',
  `status` tinyint(1) DEFAULT '0' COMMENT '农田状态;0.未上架；10，待租赁，20，已租赁',
  `lease_price` decimal(24,4) DEFAULT NULL COMMENT '租赁价格;单位：元/天',
  `summary` varchar(1000) DEFAULT NULL COMMENT '摘要',
  `description` text COMMENT '描述',
  `contact_phone` varchar(255) DEFAULT NULL COMMENT '联系电话',
  `file_ids` text COMMENT '附件ID集合;逗号分割',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_by_name` varchar(255) DEFAULT NULL COMMENT '创建人姓名',
  `create_by` varchar(255) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` bigint(20) DEFAULT NULL COMMENT '修改人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `is_deleted` varchar(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='农田信息表';

-- ----------------------------
-- Table structure for t_land_area
-- ----------------------------
DROP TABLE IF EXISTS `t_land_area`;
CREATE TABLE `t_land_area` (
  `id` varchar(32) NOT NULL,
  `land_area_id` varchar(255) DEFAULT NULL COMMENT '农田租赁面积编号Id',
  `land_area_name` varchar(255) DEFAULT NULL COMMENT '面积别名',
  `area` decimal(24,6) DEFAULT NULL COMMENT '面积;单位：平方米',
  `unit_price` decimal(24,6) DEFAULT NULL COMMENT '单价;单位：元/天',
  `lessee_status` int(2) DEFAULT '0' COMMENT '0，未出租；1，已出租',
  `sort` int(11) DEFAULT NULL COMMENT '排序序号',
  `remark` varchar(255) DEFAULT NULL COMMENT '描述',
  `create_by_name` varchar(255) DEFAULT NULL COMMENT '创建人姓名',
  `create_by` varchar(255) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_by` bigint(20) DEFAULT NULL COMMENT '修改人',
  `is_deleted` varchar(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='农田租赁最小面积表';

-- ----------------------------
-- Table structure for t_farmland_area
-- ----------------------------
DROP TABLE IF EXISTS `t_farmland_area`;
CREATE TABLE `t_farmland_area` (
  `id` varchar(32) NOT NULL,
  `farmland_id` varchar(255) DEFAULT NULL COMMENT '农田编号ID',
  `land_area_id` varchar(255) DEFAULT NULL COMMENT '农田租赁面积id',
  `create_by_name` varchar(255) DEFAULT NULL COMMENT '创建人姓名',
  `create_by` varchar(255) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `is_deleted` varchar(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='农田、租赁面积关联表';

-- ----------------------------
-- Table structure for t_farmland_service
-- ----------------------------
DROP TABLE IF EXISTS `t_farmland_service`;
CREATE TABLE `t_farmland_service` (
  `id` varchar(32) NOT NULL,
  `farmland_id` varchar(255) DEFAULT NULL COMMENT '农田编号ID',
  `service_id` varchar(255) DEFAULT NULL COMMENT '服务id',
  `create_by_name` varchar(255) DEFAULT NULL COMMENT '创建人姓名',
  `create_by` varchar(255) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `is_deleted` varchar(1) DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='农田、服务关联表';

-- ----------------------------
-- Table structure for t_land_service
-- ----------------------------
DROP TABLE IF EXISTS `t_land_service`;
CREATE TABLE `t_land_service` (
  `id` varchar(255) NOT NULL,
  `service_id` varchar(255) DEFAULT NULL COMMENT '服务编号',
  `service_name` varchar(255) DEFAULT NULL COMMENT '服务名称',
  `service_price` decimal(24,6) DEFAULT NULL COMMENT '价格',
  `service_type` int(11) DEFAULT NULL COMMENT '类型;10，包种植、包配送；20，包种植；30，只出租土地，不包含其他服务',
  `summary` varchar(255) DEFAULT NULL COMMENT '说明',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_by_name` varchar(255) DEFAULT NULL COMMENT '创建人姓名',
  `create_by` varchar(255) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_by` bigint(20) DEFAULT NULL COMMENT '修改人',
  `is_deleted` varchar(1) DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='服务信息表';

-- ----------------------------
-- Table structure for t_farmland_seed
-- ----------------------------
DROP TABLE IF EXISTS `t_farmland_seed`;
CREATE TABLE `t_farmland_seed` (
  `id` varchar(32) NOT NULL,
  `farmland_id` varchar(255) DEFAULT NULL COMMENT '农田编号ID',
  `seed_id` varchar(255) DEFAULT NULL COMMENT '种子id',
  `create_by_name` varchar(255) DEFAULT NULL COMMENT '创建人姓名',
  `create_by` varchar(255) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='农田、种子关联表';

-- ----------------------------
-- Table structure for t_merchant_type
-- ----------------------------
DROP TABLE IF EXISTS `t_merchant_type`;
CREATE TABLE `t_merchant_type` (
  `id` bigint(32) NOT NULL,
  `pid` bigint(32) unsigned NOT NULL DEFAULT '0' COMMENT '父级ID',
  `name` varchar(100) DEFAULT NULL COMMENT '名称',
  `icon` varchar(255) DEFAULT NULL COMMENT 'icon',
  `sort` int(11) NOT NULL DEFAULT '999' COMMENT '排序',
  `is_show` bit(1) NOT NULL DEFAULT b'1' COMMENT '显示状态',
  `is_del` tinyint(3) unsigned DEFAULT '0' COMMENT '是否删除',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='自定义商户店铺类型表';

-- ----------------------------
-- Table structure for t_merchant_type_attr
-- ----------------------------
DROP TABLE IF EXISTS `t_merchant_type_attr`;
CREATE TABLE `t_merchant_type_attr` (
  `id` bigint(32) NOT NULL COMMENT '主键',
  `mer_type_id` bigint(32) unsigned NOT NULL DEFAULT '0' COMMENT '商户店铺类型ID',
  `attr_name` varchar(200) NOT NULL COMMENT '属性名',
  `attr_values` varchar(1000) DEFAULT NULL COMMENT '默认属性值',
  `attr_type` int(11) DEFAULT '2' COMMENT '属性值的类型：1，数字；2，字符。3，时间',
  `sort` int(11) DEFAULT '999' COMMENT '排序',
  `is_del` tinyint(4) DEFAULT '0' COMMENT '是否删除,0-否，1-是',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='自定义商户店铺类型属性表';

-- ----------------------------
-- Table structure for t_merchant_type_attr_value
-- ----------------------------
DROP TABLE IF EXISTS `t_merchant_type_attr_value`;
CREATE TABLE `t_merchant_type_attr_value` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `mer_id` bigint(32) unsigned NOT NULL DEFAULT '0' COMMENT '商户ID',
  `mer_type_attr_id` bigint(32) NOT NULL COMMENT '商户店铺类型属性id',
  `attr_values` varchar(1000) DEFAULT NULL COMMENT '属性值',
  `is_del` tinyint(1) DEFAULT '0' COMMENT '是否删除,0-否，1-是',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='每个商户店铺属性值表';

-- ----------------------------
-- Table structure for sys_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config` (
  `config_id` int(5) NOT NULL AUTO_INCREMENT COMMENT '参数主键',
  `config_name` varchar(100) DEFAULT '' COMMENT '参数名称',
  `config_key` varchar(100) DEFAULT '' COMMENT '参数键名',
  `config_value` text COMMENT '参数键值',
  `config_type` char(1) DEFAULT 'N' COMMENT '系统内置（Y是 N否）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`config_id`)
) ENGINE=InnoDB AUTO_INCREMENT=102 DEFAULT CHARSET=utf8 COMMENT='参数配置表';

SET FOREIGN_KEY_CHECKS = 1;

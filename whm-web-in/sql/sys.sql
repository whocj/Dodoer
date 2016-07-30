/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50018
Source Host           : localhost:3306
Source Database       : whm

Target Server Type    : MYSQL
Target Server Version : 50018
File Encoding         : 65001

Date: 2015-09-14 16:41:07
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for  bbs_category 
-- ----------------------------
DROP TABLE IF EXISTS  bbs_category ;
CREATE TABLE  bbs_category  (
   ID  int(11) NOT NULL auto_increment,
   SITE_ID  int(11) NOT NULL,
   PATH  varchar(20) NOT NULL COMMENT '访问路径',
   TITLE  varchar(100) NOT NULL COMMENT '标题',
   PRIORITY  int(11) NOT NULL default '10' COMMENT '排列顺序',
   FORUM_COLS  int(11) NOT NULL default '1' COMMENT '板块列数',
   moderators  varchar(256) default NULL,
   creator varchar(64) NOT NULL,
  createTime datetime NOT NULL,
  lastUpdate datetime NOT NULL,
  PRIMARY KEY  ( ID )
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='论坛分区';

-- ----------------------------
-- Records of bbs_category
-- ----------------------------

-- ----------------------------
-- Table structure for  bbs_category_user 
-- ----------------------------
DROP TABLE IF EXISTS  bbs_category_user ;
CREATE TABLE  bbs_category_user  (
   ID int(11)  NOT NULL AUTO_INCREMENT PRIMARY KEY,
   CATEGORY_ID  int(11) NOT NULL,
   user_id  int(11) NOT NULL,
      creator varchar(64) NOT NULL,
  createTime datetime NOT NULL,
  lastUpdate datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='分区版主';

-- ----------------------------
-- Records of bbs_category_user
-- ----------------------------

-- ----------------------------
-- Table structure for  bbs_forum 
-- ----------------------------
DROP TABLE IF EXISTS  bbs_forum ;
CREATE TABLE  bbs_forum  (
   ID int(11)  NOT NULL AUTO_INCREMENT PRIMARY KEY,
   CATEGORY_ID  int(11) NOT NULL COMMENT '分区ID',
   SITE_ID  int(11) NOT NULL COMMENT '站点ID',
   POST_ID  int(11) default NULL COMMENT '最后回帖',
   replyer_id  int(11) default NULL COMMENT '最后回帖会员',
   PATH  varchar(20) NOT NULL COMMENT '访问路径',
   TITLE  varchar(150) NOT NULL COMMENT '标题',
   DESCRIPTION  varchar(255) default NULL COMMENT '描述',
   KEYWORDS  varchar(255) default NULL COMMENT 'meta-keywords',
   FORUM_RULE  varchar(255) default NULL COMMENT '版规',
   PRIORITY  int(11) NOT NULL default '10' COMMENT '排列顺序',
   TOPIC_TOTAL  int(11) NOT NULL default '0' COMMENT '主题总数',
   POST_TOTAL  int(11) NOT NULL default '0' COMMENT '帖子总数',
   POST_TODAY  int(11) NOT NULL default '0' COMMENT '今日新帖',
   OUTER_URL  varchar(255) default NULL COMMENT '外部链接',
   POINT_TOPIC  int(11) NOT NULL default '0' COMMENT '发贴加分',
   POINT_REPLY  int(11) NOT NULL default '0' COMMENT '回帖加分',
   POINT_PRIME  int(11) NOT NULL default '0' COMMENT '精华加分',
   LAST_TIME  datetime default NULL COMMENT '最后发贴时间',
   TOPIC_LOCK_LIMIT  int(11) NOT NULL default '0' COMMENT '锁定主题（天）',
   moderators  varchar(100) default NULL COMMENT '版主',
   group_views  varchar(100) default NULL COMMENT '访问会员组',
   group_topics  varchar(100) default NULL COMMENT '发帖会员组',
   group_replies  varchar(100) default NULL COMMENT '回复会员组',
   POINT_AVAILABLE  tinyint(1) default NULL COMMENT '积分是否启用',
   PRESTIGE_AVAILABLE  tinyint(1) default NULL COMMENT '威望是否启用',
   PRESTIGE_TOPIC  int(11) default '0' COMMENT '发帖加威望',
   PRESTIGE_REPLY  int(11) default '0' COMMENT '回帖加威望',
   PRESTIGE_PRIME1  int(11) default '0' COMMENT '精华1加威望',
   PRESTIGE_PRIME2  int(11) default '0' COMMENT '精华2加威望',
   PRESTIGE_PRIME3  int(11) default '0' COMMENT '精华3加威望',
   PRESTIGE_PRIME0  int(11) default '0' COMMENT '解除精华扣除威望'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='论坛板块';

-- ----------------------------
-- Records of bbs_forum
-- ----------------------------

-- ----------------------------
-- Table structure for  bbs_forum_group_reply 
-- ----------------------------
DROP TABLE IF EXISTS  bbs_forum_group_reply ;
CREATE TABLE  bbs_forum_group_reply  (
	ID int(11)  NOT NULL AUTO_INCREMENT PRIMARY KEY,
   FORUM_ID  int(11) NOT NULL,
   GROUP_ID  int(11) NOT NULL,
      creator varchar(64) NOT NULL,
  createTime datetime NOT NULL,
  lastUpdate datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='回复权限';

-- ----------------------------
-- Records of bbs_forum_group_reply
-- ----------------------------

-- ----------------------------
-- Table structure for  bbs_forum_group_topic 
-- ----------------------------
DROP TABLE IF EXISTS  bbs_forum_group_topic ;
CREATE TABLE  bbs_forum_group_topic  (
ID int(11)  NOT NULL AUTO_INCREMENT PRIMARY KEY,
   FORUM_ID  int(11) NOT NULL,
   GROUP_ID  int(11) NOT NULL,
   creator varchar(64) NOT NULL,
   createTime datetime NOT NULL,
   lastUpdate datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='发贴权限';

-- ----------------------------
-- Records of bbs_forum_group_topic
-- ----------------------------

-- ----------------------------
-- Table structure for  bbs_forum_group_view 
-- ----------------------------
DROP TABLE IF EXISTS  bbs_forum_group_view ;
CREATE TABLE  bbs_forum_group_view  (
	ID int(11)  NOT NULL AUTO_INCREMENT PRIMARY KEY,
   GROUP_ID  int(11) NOT NULL,
   FORUM_ID  int(11) NOT NULL,
      creator varchar(64) NOT NULL,
  createTime datetime NOT NULL,
  lastUpdate datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='浏览权限';

-- ----------------------------
-- Records of bbs_forum_group_view
-- ----------------------------

-- ----------------------------
-- Table structure for  bbs_forum_user 
-- ----------------------------
DROP TABLE IF EXISTS  bbs_forum_user ;
CREATE TABLE  bbs_forum_user  (
   ID int(11)  NOT NULL AUTO_INCREMENT PRIMARY KEY,
   FORUM_ID  int(11) NOT NULL,
   user_id  int(11) NOT NULL,
      creator varchar(64) NOT NULL,
  createTime datetime NOT NULL,
  lastUpdate datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='版块版主';

-- ----------------------------
-- Records of bbs_forum_user
-- ----------------------------

-- ----------------------------
-- Table structure for  bbs_friendship 
-- ----------------------------
DROP TABLE IF EXISTS  bbs_friendship ;
CREATE TABLE  bbs_friendship  (
	ID int(11)  NOT NULL AUTO_INCREMENT PRIMARY KEY,
   user_id  int(11) NOT NULL default '0',
   friend_id  int(11) NOT NULL default '0',
   status  tinyint(1) NOT NULL default '0' COMMENT '好友状态(0:申请中;1:接受;2:拒绝)',
      creator varchar(64) NOT NULL,
  createTime datetime NOT NULL,
  lastUpdate datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bbs_friendship
-- ----------------------------

-- ----------------------------
-- Table structure for  bbs_post 
-- ----------------------------
DROP TABLE IF EXISTS  bbs_post ;
CREATE TABLE  bbs_post  (
   ID int(11)  NOT NULL AUTO_INCREMENT PRIMARY KEY,
   TOPIC_ID  int(11) NOT NULL COMMENT '主题',
   SITE_ID  int(11) NOT NULL COMMENT '站点',
   CONFIG_ID  int(11) NOT NULL,
   EDITER_ID  int(11) default NULL COMMENT '编辑器会员',
   CREATER_ID  int(11) NOT NULL COMMENT '发贴会员',
   CREATE_TIME  datetime NOT NULL COMMENT '发贴时间',
   POSTER_IP  varchar(20) NOT NULL default '' COMMENT '发贴IP',
   EDIT_TIME  datetime default NULL COMMENT '编辑时间',
   EDITER_IP  varchar(20) default '' COMMENT '编辑者IP',
   EDIT_COUNT  int(11) NOT NULL default '0' COMMENT '编辑次数',
   INDEX_COUNT  int(11) NOT NULL default '1' COMMENT '第几楼',
   STATUS  smallint(6) NOT NULL default '0' COMMENT '状态',
   IS_AFFIX  tinyint(1) NOT NULL default '0' COMMENT '是否上传附件',
   IS_HIDDEN  tinyint(1) default '0' COMMENT '是否有隐藏内容',
   TYPE_ID  int(11) NOT NULL COMMENT '帖子分类id',
   ANONYMOUS  tinyint(1) default NULL COMMENT '是否匿名'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='论坛帖子';

-- ----------------------------
-- Records of bbs_post
-- ----------------------------

-- ----------------------------
-- Table structure for  bbs_post_text 
-- ----------------------------
DROP TABLE IF EXISTS  bbs_post_text ;
CREATE TABLE  bbs_post_text  (
   ID int(11)  NOT NULL AUTO_INCREMENT PRIMARY KEY,
   POST_ID  bigint(20) NOT NULL,
   POST_TITLE  varchar(100) default NULL COMMENT '帖子标题',
   POST_CONTENT  longtext COMMENT '帖子内容'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='论坛帖子内容';

-- ----------------------------
-- Records of bbs_post_text
-- ----------------------------

-- ----------------------------
-- Table structure for  bbs_topic 
-- ----------------------------
DROP TABLE IF EXISTS  bbs_topic ;
CREATE TABLE  bbs_topic  (
   ID int(11)  NOT NULL AUTO_INCREMENT PRIMARY KEY,
   FORUM_ID  int(11) NOT NULL COMMENT '板块',
   LAST_POST_ID  int(11) default NULL COMMENT '最后帖',
   FIRST_POST_ID  int(11) default NULL COMMENT '主题帖',
   SITE_ID  int(11) NOT NULL COMMENT '站点',
   CREATER_ID  int(11) NOT NULL COMMENT '发帖会员',
   REPLYER_ID  int(11) NOT NULL COMMENT '最后回帖会员',
   TITLE  varchar(100) NOT NULL COMMENT '标题',
   CREATE_TIME  datetime NOT NULL COMMENT '创建时间',
   LAST_TIME  datetime NOT NULL COMMENT '最后回帖时间',
   SORT_TIME  datetime NOT NULL COMMENT '用于排序',
   VIEW_COUNT  bigint(20) NOT NULL default '0' COMMENT '浏览次数',
   REPLY_COUNT  int(11) NOT NULL default '0' COMMENT '恢复次数',
   TOP_LEVEL  smallint(6) NOT NULL default '0' COMMENT '固定级别',
   PRIME_LEVEL  smallint(6) NOT NULL default '0' COMMENT '精华级别',
   STATUS  smallint(6) NOT NULL default '0' COMMENT '状态',
   OUTER_URL  varchar(255) default NULL COMMENT '外部链接',
   STYLE_BOLD  tinyint(1) NOT NULL default '0' COMMENT '粗体',
   STYLE_ITALIC  tinyint(1) NOT NULL default '0' COMMENT '斜体',
   STYLE_COLOR  char(6) default NULL COMMENT '颜色',
   STYLE_TIME  datetime default NULL COMMENT '样式有效时间',
   IS_AFFIX  tinyint(1) NOT NULL default '0' COMMENT '是否上传附件',
   HAVA_REPLY  longtext COMMENT '回复列表',
   moderator_reply  tinyint(1) default '0' COMMENT '版主是否回复',
   TYPE_ID  int(11) NOT NULL COMMENT '主题分类id',
   ALLAY_REPLY  tinyint(1) default NULL COMMENT '主题是否允许回复',
   HAS_SOFAED  tinyint(1) default NULL COMMENT '主题是否已经被抢走沙发',
   CATEGORY  tinyint(1) default NULL COMMENT '帖子类型(0:普通帖;1:投票贴)',
   TOTAL_COUNT  int(11) default NULL COMMENT '总票数',
   views_day  int(11) NOT NULL default '0' COMMENT '日访问量',
   views_week  int(11) NOT NULL default '0' COMMENT '周访问量',
   views_month  int(11) NOT NULL default '0' COMMENT '月访问量',
   replycount_day  int(11) NOT NULL default '0' COMMENT '日回复量' 
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='论坛主题';

-- ----------------------------
-- Records of bbs_topic
-- ----------------------------

-- ----------------------------
-- Table structure for  bbs_topic_text 
-- ----------------------------
DROP TABLE IF EXISTS  bbs_topic_text ;
CREATE TABLE  bbs_topic_text  (
   ID int(11)  NOT NULL AUTO_INCREMENT PRIMARY KEY,
   topic_id  int(11) NOT NULL,
   title  varchar(100) default NULL COMMENT '主题标题' 
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='论坛主题内容';

-- ----------------------------
-- Records of bbs_topic_text
-- ----------------------------

-- ----------------------------
-- Table structure for  sys_authentication 
-- ----------------------------
DROP TABLE IF EXISTS  sys_authentication ;
CREATE TABLE  sys_authentication  (
   ID int(11)  NOT NULL AUTO_INCREMENT PRIMARY KEY,
   userid  int(11) NOT NULL COMMENT '用户ID',
   username  varchar(100) NOT NULL COMMENT '用户名',
   email  varchar(100) default NULL COMMENT '邮箱',
   login_time  datetime NOT NULL COMMENT '登录时间',
   login_ip  varchar(50) NOT NULL COMMENT '登录ip',
   update_time  datetime NOT NULL COMMENT '更新时间' 
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='认证信息表';

-- ----------------------------
-- Records of sys_authentication
-- ----------------------------

-- ----------------------------
-- Table structure for  sys_limit 
-- ----------------------------
DROP TABLE IF EXISTS  sys_limit ;
CREATE TABLE  sys_limit  (
 ID int(11)  NOT NULL AUTO_INCREMENT PRIMARY KEY,
   ip  varchar(50) default '' COMMENT '限制ip',
   user_id  int(11) default NULL COMMENT '限制用户ID' ,
      creator varchar(64) NOT NULL,
  createTime datetime NOT NULL,
  lastUpdate datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='限制发帖回帖';

-- ----------------------------
-- Records of sys_limit
-- ----------------------------

-- ----------------------------
-- Table structure for  sys_login_log 
-- ----------------------------
DROP TABLE IF EXISTS  sys_login_log ;
CREATE TABLE  sys_login_log  (
   ID int(11)  NOT NULL AUTO_INCREMENT PRIMARY KEY,
   user_id  int(11) default NULL COMMENT '登录用户',
   login_time  datetime default NULL COMMENT '登录时间',
   logout_time  datetime default NULL COMMENT '退出时间',
   ip  varchar(255) character set gbk default NULL COMMENT '登录ip'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='登录日志';

-- ----------------------------
-- Records of sys_login_log
-- ----------------------------

-- ----------------------------
-- Table structure for  sys_sensitivity 
-- ----------------------------
DROP TABLE IF EXISTS  sys_sensitivity ;
CREATE TABLE  sys_sensitivity  (
ID int(11)  NOT NULL AUTO_INCREMENT PRIMARY KEY,
   site_id  int(11) default NULL,
   search  varchar(255) NOT NULL COMMENT '敏感词',
   replacement  varchar(255) NOT NULL COMMENT '替换词',
      creator varchar(64) NOT NULL,
  createTime datetime NOT NULL,
  lastUpdate datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='CMS敏感词表';

-- ----------------------------
-- Records of sys_sensitivity
-- ----------------------------

-- ----------------------------
-- Table structure for  sys_user 
-- ----------------------------
DROP TABLE IF EXISTS  sys_user ;
CREATE TABLE  sys_user  (
   ID int(11)  NOT NULL AUTO_INCREMENT PRIMARY KEY,
   username  varchar(25) NOT NULL,
   nickname  varchar(25) default NULL,
   realname  varchar(25) default NULL,
   password  varchar(64) NOT NULL,
   email  varchar(30) default NULL,
   status  varchar(10) default NULL,
   role  enum('admin','editor','contributor') default NULL,
   description  varchar(100) default NULL,
   createTime  datetime default NULL,
   creator  varchar(15) default NULL,
   lastUpdate  datetime default NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO  sys_user  VALUES ('1', 'admin', 'admin', 'admin', '5f4dcc3b5aa765d61d8327deb882cf99', 'admin@whm.com', null, null, null, null, null, null);

-- ----------------------------
-- Table structure for  sys_user_online 
-- ----------------------------
DROP TABLE IF EXISTS  sys_user_online ;
CREATE TABLE  sys_user_online  (
   ID int(11)  NOT NULL AUTO_INCREMENT PRIMARY KEY,
   online_latest  double(10,2) default NULL COMMENT '最后登录时长',
   online_day  double(10,2) default NULL COMMENT '今日在线时长',
   online_week  double(10,2) default NULL COMMENT '本周在线',
   online_month  double(10,2) default NULL COMMENT '本月在线',
   online_year  double(10,2) default NULL COMMENT '本年在线',
   online_total  double(10,2) default NULL COMMENT '总在线时长' ,
      creator varchar(64) NOT NULL,
  createTime datetime NOT NULL,
  lastUpdate datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=gbk COMMENT='用户在线时长统计';

-- ----------------------------
-- Records of sys_user_online
-- ----------------------------

-- ----------------------------
-- Table structure for  user_friendlink 
-- ----------------------------
DROP TABLE IF EXISTS  sys_friendlink ;
CREATE TABLE  sys_friendlink  (
   ID int(11)  NOT NULL AUTO_INCREMENT PRIMARY KEY,
   site_id  int(11) NOT NULL,
   friendlinkctg_id  int(11) NOT NULL,
   site_name  varchar(150) NOT NULL COMMENT '网站名称',
   domain  varchar(255) NOT NULL COMMENT '网站地址',
   logo  varchar(150) default NULL COMMENT '图标',
   email  varchar(100) default NULL COMMENT '站长邮箱',
   description  varchar(255) default NULL COMMENT '描述',
   views  int(11) NOT NULL default '0' COMMENT '点击次数',
   is_enabled  char(1) NOT NULL default '1' COMMENT '是否显示',
   priority  int(11) NOT NULL default '10' COMMENT '排列顺序' ,
  creator varchar(64) NOT NULL,
  createTime datetime NOT NULL,
  lastUpdate datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='CMS友情链接';

-- ----------------------------
-- Records of user_friendlink
-- ----------------------------
-- ----------------------------
-- Table structure for `bbs_user_group`
-- ----------------------------
DROP TABLE IF EXISTS `bbs_user_group`;
CREATE TABLE `bbs_user_group` (
  `ID` int(11) NOT NULL auto_increment,
  `site_id` int(11) NOT NULL,
  `NAME` varchar(20) NOT NULL COMMENT '头衔',
  `GROUP_TYPE` smallint(6) NOT NULL default '0' COMMENT '组类别',
  `GROUP_IMG` varchar(100) default NULL COMMENT '图片',
  `GROUP_POINT` int(11) NOT NULL default '0' COMMENT '升级积分',
  `IS_DEFAULT` tinyint(1) NOT NULL default '0' COMMENT '是否默认组',
  `GRADE_NUM` int(11) default '0' COMMENT '评分',
  `magic_packet_size` int(11) default NULL COMMENT '用户组道具包容量',
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='论坛会员组';

-- ----------------------------
-- Records of bbs_user_group
-- ----------------------------
INSERT INTO `bbs_user_group` VALUES ('1', '1', '白丁', '1', '1', '0', '1', '0', '0');
INSERT INTO `bbs_user_group` VALUES ('2', '1', '童生', '1', '2', '100', '0', '0', '100');
INSERT INTO `bbs_user_group` VALUES ('3', '1', '秀才', '1', '3', '500', '0', '0', '0');
INSERT INTO `bbs_user_group` VALUES ('4', '1', '举人', '1', '4', '1000', '0', '0', '0');
INSERT INTO `bbs_user_group` VALUES ('5', '1', '解元', '1', '5', '2000', '0', '0', '0');
INSERT INTO `bbs_user_group` VALUES ('6', '1', '贡士', '1', '6', '4000', '0', '0', '0');
INSERT INTO `bbs_user_group` VALUES ('7', '1', '会元', '1', '7', '8000', '0', '0', '0');
INSERT INTO `bbs_user_group` VALUES ('8', '1', '进士', '1', '8', '16000', '0', '0', '0');
INSERT INTO `bbs_user_group` VALUES ('9', '1', '探花', '1', '9', '32000', '0', '0', '0');
INSERT INTO `bbs_user_group` VALUES ('10', '1', '榜眼', '1', '10', '64000', '0', '0', '0');
INSERT INTO `bbs_user_group` VALUES ('11', '1', '状元', '1', '11', '128000', '0', '0', '0');
INSERT INTO `bbs_user_group` VALUES ('12', '1', '版主', '2', '21', '0', '0', '0', '0');
INSERT INTO `bbs_user_group` VALUES ('13', '1', 'VIP会员', '3', '10', '0', '0', '0', '0');
INSERT INTO `bbs_user_group` VALUES ('14', '1', '游客', '0', '1', '0', '0', '0', '0');



-- Table structure for  sys_user_online 
-- ----------------------------
DROP TABLE IF EXISTS  sys_user_online ;
CREATE TABLE  sys_user_online  (
   ID int(11)  NOT NULL AUTO_INCREMENT PRIMARY KEY,
	user_id int(11),
	username varchar(128),
	loginTime datetime,
	loginIP varchar(128),
      creator varchar(64),
  createTime datetime,
  lastUpdate datetime
) ENGINE=InnoDB DEFAULT CHARSET=gbk COMMENT='在线用户';

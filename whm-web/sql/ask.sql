DROP TABLE IF EXISTS  sys_site ;
CREATE TABLE  sys_site  (
  ID int(11)  NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name varchar(64),
  domain varchar(64),
  contextPath varchar(64),
  creator varchar(64),
  createTime datetime,
  lastUpdate datetime
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='网站';

DROP TABLE IF EXISTS  ask_question ;
CREATE TABLE  ask_question  (
  ID int(11)  NOT NULL AUTO_INCREMENT PRIMARY KEY,
  categoryId  int(11),
  userId  int(11),
  username varchar(64),
  tagName varchar(512),
  viewCount  int(20) NOT NULL default '0' COMMENT '浏览次数',
  replyCount  int(11) NOT NULL default '0' COMMENT '回复次数',
  outerUrl  varchar(255) default NULL COMMENT '外部链接',
  isAffix  tinyint(1) NOT NULL default '0' COMMENT '是否上传附件',
  editTime  datetime default NULL COMMENT '编辑时间',
  editerIp  varchar(20) default '' COMMENT '编辑者IP',
  editCount  int(11) NOT NULL default '0' COMMENT '编辑次数',
  status  smallint(6) NOT NULL default '0' COMMENT '状态',
  anonymous  tinyint(1) default NULL COMMENT '是否匿名',
  creator varchar(64),
  createTime datetime,
  lastUpdate datetime
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='问题';

DROP TABLE IF EXISTS  ask_question_text ;
CREATE TABLE  ask_question_text  (
   ID int(11)  NOT NULL AUTO_INCREMENT PRIMARY KEY,
   questionId  int(11) NOT NULL,
   questionTitle  varchar(256) default NULL COMMENT '标题',
   questionContent  longtext COMMENT '内容'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='问题内容';

DROP TABLE IF EXISTS  ask_answer ;
CREATE TABLE  ask_answer  (
  ID int(11)  NOT NULL AUTO_INCREMENT PRIMARY KEY,
  questionId  int(11) NOT NULL,
  categoryId  int(11),
  userId  int(11),
  username varchar(64),
  askName varchar(64),
  askEmail varchar(64),
  askWebSite varchar(128),
  outerUrl  varchar(255) default NULL COMMENT '外部链接',
  editTime  datetime default NULL COMMENT '编辑时间',
  editerIp  varchar(20) default '' COMMENT '编辑者IP',
  anonymous  tinyint(1) default NULL COMMENT '是否匿名',
  isGood varchar(8) default '0' COMMENT '是否是最好的',
  creator varchar(64),
  createTime datetime,
  lastUpdate datetime
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='回答';

DROP TABLE IF EXISTS  ask_answer_text ;
CREATE TABLE  ask_answer_text  (
   ID int(11)  NOT NULL AUTO_INCREMENT PRIMARY KEY,
   answerId  int(11) NOT NULL,
   answerTitle  varchar(256) default NULL COMMENT '标题',
   answerContent  longtext COMMENT '内容'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='回答内容';

DROP TABLE IF EXISTS  sys_tag ;
CREATE TABLE  sys_tag  (
  ID int(11)  NOT NULL AUTO_INCREMENT PRIMARY KEY,
  siteId int(11),
  name varchar(64),
  styleBold  varchar(8) NOT NULL default '0' COMMENT '粗体',
  styleItalic  varchar(8) NOT NULL default '0' COMMENT '斜体',
  styleColor  varchar(8) default NULL COMMENT '颜色',
  creator varchar(64),
  createTime datetime,
  lastUpdate datetime
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='标签';

DROP TABLE IF EXISTS  bbs_search_post ;
CREATE TABLE  bbs_search_post  (
   ID int(11)  NOT NULL AUTO_INCREMENT PRIMARY KEY,
   docId varchar(32),
   type varchar(16),
   title varchar(1024) COMMENT '标题',
   content longtext  COMMENT '内容',
   author varchar(256),
	 createTime varchar(32),
   tags varchar(128),
   site varchar(128),
   url varchar(1024),
   isIndex int(11)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='搜索';
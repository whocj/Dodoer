
-- ----------------------------
-- Table structure for jforum_craw_info
-- ----------------------------
DROP TABLE IF EXISTS SPIDER_CRAW_INFO;
CREATE TABLE SPIDER_CRAW_INFO (
  ID int(11) NOT NULL auto_increment,
  URL varchar(1024) default NULL,
  CREATETIME timestamp NULL default NULL on update CURRENT_TIMESTAMP,
  PRIMARY KEY  (ID),
  KEY url_index USING HASH (url(255))
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS SPIDER_LIST_TEMPLATE;
CREATE TABLE SPIDER_LIST_TEMPLATE (
  ID int(11) NOT NULL auto_increment PRIMARY KEY,
     CRAW_TEMPLATE_ID INT(11),
	URL varchar(1024) default NULL,
	username varchar(128) default NULL,
    userId int(11),
    topicType  int(11),
	listXPath varchar(128) default NULL,
	totalPageXPath varchar(128) default NULL,
	titleXPath varchar(128) default NULL,
	nextXPath varchar(128) default NULL,
	prevXPath varchar(128) default NULL,
	detailXPath varchar(128) default NULL,
	creator varchar(64),
  	createTime datetime,
  	lastUpdate datetime
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS SPIDER_DETAIL_TEMPLATE;
CREATE TABLE SPIDER_DETAIL_TEMPLATE (
  ID int(11) NOT NULL auto_increment PRIMARY KEY,
     CRAW_TEMPLATE_ID INT(11),
  	URL varchar(1024) default NULL,
	username varchar(128) default NULL,
    userId int(11),
    topicType  int(11),
	titleXPath varchar(128) default NULL,
	prevXPath varchar(128) default NULL,
	nextXPath varchar(128) default NULL,
	contentXPath varchar(128) default NULL,
	contentNextXPath varchar(128) default NULL,
	contentPrevXPath varchar(128) default NULL,
	commentsXPath varchar(128) default NULL,
	creator varchar(64),
  	createTime datetime,
  	lastUpdate datetime

) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS SPIDER_CRAW_TEMPLATE;
CREATE TABLE SPIDER_CRAW_TEMPLATE (
  ID int(11) NOT NULL auto_increment PRIMARY KEY,
  NAME VARCHAR(128),
  DESCRIPTION  varchar(255) default NULL COMMENT '描述',
  TYPE VARCHAR(64),
  	creator varchar(64),
  	createTime datetime,
  	lastUpdate datetime
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS SPIDER_CRAW_LOG;
CREATE TABLE SPIDER_CRAW_LOG (
  ID int(11) NOT NULL auto_increment PRIMARY KEY,
   CRAW_TEMPLATE_ID INT(11),
   beginTime datetime,
   endTime datetime,
   status varchar(16),
   log varchar(2048),
  	creator varchar(64),
  	createTime datetime,
  	lastUpdate datetime
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



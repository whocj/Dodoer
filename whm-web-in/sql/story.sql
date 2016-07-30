SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for  bbs_category 
-- ----------------------------
DROP TABLE IF EXISTS  story_info ;
CREATE TABLE  story_info  (
   id  int(11) NOT NULL auto_increment PRIMARY KEY ,
   categoryId int(11),
   title  varchar(256) NOT NULL COMMENT '标题',
   author varchar(64),
   status varchar(8),
   picPath varchar(256),
   outline varchar(1024),
   lastDetailId int(11),
   lastDetailTitle varchar(256),
   tagName varchar(128),
   likeCount  int(11),
   readCount  int(11),
   replyCount int(11),
   sortIndex  int(11),
   remark     varchar(256),
   crawlUrl   varchar(256),
   creator varchar(64),
   createTime datetime,
   lastUpdate datetime,
   keywords  varchar(1024),
   description varchar(1024)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='小说信息';

DROP TABLE IF EXISTS  story_part ;
CREATE TABLE  story_part  (
   id  int(11) NOT NULL auto_increment PRIMARY KEY ,
   storyId int(11),
   title  varchar(256) NOT NULL COMMENT '标题',
   outline varchar(1024),
   creator varchar(64),
   createTime datetime,
   lastUpdate datetime
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='小说段落';


DROP TABLE IF EXISTS  story_detail;
CREATE TABLE  story_detail  (
   id  int(11) NOT NULL auto_increment PRIMARY KEY ,
   storyId int(11),
   title  varchar(256) NOT NULL COMMENT '标题',
   content longtext,
   partId int(11),
   partTitle varchar(256),
   status varchar(8),
   picPath varchar(256),
   readCount  int(11),
   replyCount int(11),
	crawlUrl   varchar(256),
   creator varchar(64),
   createTime datetime,
   lastUpdate datetime
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='小说明细';

DROP TABLE IF EXISTS  story_user_bookshelf;
CREATE TABLE  story_user_bookshelf  (
   id  int(11) NOT NULL auto_increment PRIMARY KEY ,
   storyId int(11),
   title  varchar(256) NOT NULL COMMENT '标题',
   userId int(11),
   username varchar(64) NOT NULL,
   readDetailId int(11),
   readDetailTitle varchar(256),
   status varchar(8),
   creator varchar(64),
   createTime datetime,
   lastUpdate datetime
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='我的书架';

DROP TABLE IF EXISTS  story_user_read;
CREATE TABLE  story_user_read  (
   id  int(11) NOT NULL auto_increment PRIMARY KEY ,
   storyId int(11),
   title  varchar(256) NOT NULL COMMENT '标题',
   userId int(11),
   username varchar(64) NOT NULL,
   readDetailId int(11),
   readDetailTitle varchar(256),
   creator varchar(64),
   createTime datetime,
   lastUpdate datetime
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='我的阅读记录';

DROP TABLE IF EXISTS  sys_notice;
CREATE TABLE  sys_notice  (
   id  int(11) NOT NULL auto_increment PRIMARY KEY ,
   title  varchar(256) NOT NULL COMMENT '标题',
   content longtext,
   siteId  int(11),
   userId int(11),
   username varchar(64),
   creator varchar(64),
   createTime datetime,
   lastUpdate datetime
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统公告';


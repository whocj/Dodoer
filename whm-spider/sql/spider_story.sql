DROP TABLE IF EXISTS SPIDER_STORY_TEMPLATE;
CREATE TABLE SPIDER_STORY_TEMPLATE (
  	id int(11) NOT NULL auto_increment PRIMARY KEY,
	name varchar(128),
  	userId int(11),
	username varchar(128),
	titleXPath varchar(128),
	authorXPath varchar(128),
	outlineXPath varchar(128),
	picPathXPath varchar(128),
	detailXPath varchar(128),
	detailTitleXPath varchar(128),
	detailContentXPath varchar(128),
	nextXPath varchar(128),
	prevXPath varchar(128),
	execModel varchar(128),
	filterWord varchar(512),
	filterItem varchar(512),
	creator varchar(64),
  	createTime datetime,
  	lastUpdate datetime
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS SPIDER_STORY_JOB;
CREATE TABLE SPIDER_STORY_JOB (
  	id int(11) NOT NULL auto_increment PRIMARY KEY,
  	templateId int(11),
  	storyId int(11),
	userId  int(11),
	username varchar(128),
	name varchar(128),
	url varchar(128),
	title varchar(128),
	status varchar(8),
    qtRule varchar(32),
	creator varchar(64),
  	createTime datetime,
  	lastUpdate datetime
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
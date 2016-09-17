DROP TABLE IF EXISTS STORY_REGISTER;
CREATE TABLE STORY_REGISTER (
  	ID int(11) NOT NULL auto_increment PRIMARY KEY  ,

  	categoryId int(11),
  	author varchar(128),
	title varchar(128),
	status varchar(128),
	remark varchar(128),

  	creator varchar(64),
  	createTime datetime,
  	lastUpdate datetime 
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
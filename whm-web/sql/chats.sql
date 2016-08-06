DROP TABLE IF EXISTS  chats_friend;
CREATE TABLE  chats_friend  (
  id int(11)  NOT NULL AUTO_INCREMENT PRIMARY KEY,
  username varchar(64),
  friendname varchar(64),
  creator varchar(64),
  createTime datetime,
  lastUpdate datetime
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='好友表';

DROP TABLE IF EXISTS  chats_group;
CREATE TABLE  chats_group  (
  id int(11)  NOT NULL AUTO_INCREMENT PRIMARY KEY,
  title varchar(128),
  content varchar(2048),
  username varchar(64),
  creator varchar(64),
  createTime datetime,
  lastUpdate datetime
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='好友群组';

DROP TABLE IF EXISTS  chats_topic;
CREATE TABLE  chats_topic  (
  id int(11)  NOT NULL AUTO_INCREMENT PRIMARY KEY,
  parentId int(11),
  title varchar(128),
  content varchar(2048),
  creator varchar(64),
  createTime datetime,
  lastUpdate datetime
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='主题表';

DROP TABLE IF EXISTS  chats_user_reta;
CREATE TABLE  chats_user_rete  (
  id int(11)  NOT NULL AUTO_INCREMENT PRIMARY KEY,
  retaId int(11),
  username varchar(64),
  messageStauts varchar(32),
  type varchar(32),
  creator varchar(64),
  createTime datetime,
  lastUpdate datetime
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户关系表';

DROP TABLE IF EXISTS  chats_friend_message;
CREATE TABLE  chats_friend_message  (
  id int(11)  NOT NULL AUTO_INCREMENT PRIMARY KEY,
  username varchar(64),
  acceptname varchar(64),
  content varchar(2048),
  status varchar(8),
  creator varchar(64),
  createTime datetime,
  lastUpdate datetime
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='消息表';

DROP TABLE IF EXISTS  chats_topic_message;
CREATE TABLE  chats_topic_message  (
  id int(11)  NOT NULL AUTO_INCREMENT PRIMARY KEY,
  topicId int(11),
  username varchar(64),
  content varchar(2048),
  creator varchar(64),
  createTime datetime,
  lastUpdate datetime
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='主题消息表';

DROP TABLE IF EXISTS  chats_group_message;
CREATE TABLE  chats_group_message  (
  id int(11)  NOT NULL AUTO_INCREMENT PRIMARY KEY,
  groupId int(11),
  username varchar(64),
  content varchar(2048),
  creator varchar(64),
  createTime datetime,
  lastUpdate datetime
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='群组消息表';

DROP TABLE IF EXISTS  chats_kyes;
CREATE TABLE  chats_kyes  (
  id int(11)  NOT NULL AUTO_INCREMENT PRIMARY KEY,
  chatkey varchar(64),
  type varchar(64),
  objId varchar(64),
  creator varchar(64),
  createTime datetime,
  lastUpdate datetime
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='聊天唯一标识';
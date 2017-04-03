#CREATE SEQUENCE S_APP_STATISTIC INCREMENT BY 1 START WITH 957877 MINVALUE 957877;
#CREATE SEQUENCE S_APP_USER INCREMENT BY 1 START WITH 8 MINVALUE 8
#CREATE SEQUENCE S_SCENARIO INCREMENT BY 1 START WITH 9 MINVALUE 9
#CREATE SEQUENCE S_TASK INCREMENT BY 1 START WITH 192 MINVALUE 192
#CREATE SEQUENCE S_TASKGROUP INCREMENT BY 1 START WITH 18 MINVALUE 18
#CREATE SEQUENCE S_TASKGROUP_RANK INCREMENT BY 1 START WITH 65 MINVALUE 65
#CREATE SEQUENCE S_TASK_RANK INCREMENT BY 1 START WITH 205 MINVALUE 205
   
CREATE TABLE S_APP_STATISTIC (
  ID	 BIGINT AUTO_INCREMENT NOT NULL,
  PRIMARY KEY (ID)
)AUTO_INCREMENT = 957877;

#insert into s_app_statistic (id) values (null);
#select last_insert_id() from s_app_statistic;

# sequence for table task column rank
CREATE TABLE S_APP_USER (
  ID	 BIGINT AUTO_INCREMENT NOT NULL,
  PRIMARY KEY (ID)
)AUTO_INCREMENT = 8;

CREATE TABLE S_SCENARIO (
  ID	 BIGINT AUTO_INCREMENT NOT NULL,
  PRIMARY KEY (ID)
)AUTO_INCREMENT = 9;

CREATE TABLE S_TASK (
  ID	 BIGINT AUTO_INCREMENT NOT NULL,
  PRIMARY KEY (ID)
)AUTO_INCREMENT = 192;

CREATE TABLE S_TASKGROUP (
  ID	 BIGINT AUTO_INCREMENT NOT NULL,
  PRIMARY KEY (ID)
)AUTO_INCREMENT = 18;

CREATE TABLE S_TASKGROUP_RANK (
  ID	 BIGINT AUTO_INCREMENT NOT NULL,
  PRIMARY KEY (ID)
)AUTO_INCREMENT = 65;

CREATE TABLE S_TASK_RANK (
  ID	 BIGINT AUTO_INCREMENT NOT NULL,
  PRIMARY KEY (ID)
)AUTO_INCREMENT = 205;
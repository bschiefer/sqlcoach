# not DEC(10,0) for autoincrement, else serial is an alias for for BIGINT UNSIGNED NOT NULL AUTO_INCREMENT UNIQUE.
#drop table app_statistic;

CREATE TABLE APP_STATISTIC (
  ID	 DEC(10,0) NOT NULL,
  TASK_ID	 DEC(10,0) NOT NULL,
  success    char(1) NOT NULL CHECK (success IN ('0','1')),
  QUERY	 VARCHAR(2048) NOT NULL,
  SESSION_ID	 VARCHAR(255) NOT NULL,
  DATECREATE	 TIMESTAMP(0) DEFAULT CURRENT_TIMESTAMP NOT NULL,
  DATELASTMOD	 TIMESTAMP(0) DEFAULT CURRENT_TIMESTAMP NOT NULL,
 PRIMARY KEY (ID)
);

CREATE INDEX app_static_index ON APP_STATISTIC (DATECREATE ASC, SUCCESS ASC);

CREATE TABLE APP_STATISTIC_MONTH (
  JAHR	 CHAR(4),
  MONAT	 CHAR(2),
  ANZAHL	 DEC(10,0),
  INSERTED	 TIMESTAMP(0) DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE APP_STATISTIC_YEAR (
  JAHR	 CHAR(4),
  ANZAHL	 DEC(10,0),
  INSERTED	 TIMESTAMP(0) DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE APP_USER (
  ID	 DEC(10,0) NOT NULL,
  NICKNAME	 VARCHAR(24) NOT NULL,
  PASSWORD	 VARCHAR(64) NOT NULL,
  TITLE	 VARCHAR(24),
  FIRSTNAME	 VARCHAR(64),
  LASTNAME	 VARCHAR(64),
  EMAIL	 VARCHAR(64),
  ROLE	 VARCHAR(64),
  DATECREATE	 TIMESTAMP(0) DEFAULT CURRENT_TIMESTAMP NOT NULL,
  DATELASTMOD	 TIMESTAMP(0) DEFAULT CURRENT_TIMESTAMP NOT NULL,
 PRIMARY KEY (ID)
);

ALTER TABLE APP_USER ADD UNIQUE (NICKNAME);

CREATE TABLE SCENARIO (
  ID	 DEC(10,0) NOT NULL,
  APP_USER_ID	 DEC(10,0) NOT NULL,
  DESCRIPTION	 VARCHAR(255) NOT NULL,
  DATASOURCE	 VARCHAR(64) NOT NULL,
  DATECREATE	 TIMESTAMP(0) DEFAULT CURRENT_TIMESTAMP NOT NULL,
  DATELASTMOD	 TIMESTAMP(0) DEFAULT CURRENT_TIMESTAMP NOT NULL,
  DATABASEPRODUCTNAME varchar(64),
  SAMPLESOLUTIONHINTCOUNT integer,
 PRIMARY KEY (ID)
);

ALTER TABLE SCENARIO ADD UNIQUE (DESCRIPTION);

CREATE TABLE SCENARIO_TABLE (
  SCENARIO_ID	 DEC(10,0) NOT NULL,
  SCENARIO_TABLE	 VARCHAR(255) NOT NULL,
  DATECREATE	 TIMESTAMP(0) DEFAULT CURRENT_TIMESTAMP NOT NULL,
  DATELASTMOD	 TIMESTAMP(0) DEFAULT CURRENT_TIMESTAMP NOT NULL,
 PRIMARY KEY (SCENARIO_ID, SCENARIO_TABLE)
);

CREATE TABLE TASK (
  ID	 DEC(10,0) NOT NULL,
  TASKGROUP_ID	 DEC(10,0) NOT NULL,
  RANK	 DEC(10,0) NOT NULL,
  DESCRIPTION	 VARCHAR(2048) NOT NULL,
  QUERY	 VARCHAR(2048) NOT NULL,
  DATECREATE	 TIMESTAMP(0) DEFAULT CURRENT_TIMESTAMP NOT NULL,
  DATELASTMOD	 TIMESTAMP(0) DEFAULT CURRENT_TIMESTAMP NOT NULL,
  HINT	 VARCHAR(2048),
  HINT_TRIALS	 INTEGER NOT NULL DEFAULT           2,
  SOLUTION_TRIALS	 INTEGER NOT NULL DEFAULT           3,
 PRIMARY KEY (ID)
);

CREATE TABLE TASKGROUP (
  ID	 DEC(10,0) NOT NULL,
  SCENARIO_ID	 DEC(10,0) NOT NULL,
  RANK	 DEC(10,0) NOT NULL,
  DESCRIPTION	 VARCHAR(255) NOT NULL,
  DATECREATE	 TIMESTAMP(0) DEFAULT CURRENT_TIMESTAMP NOT NULL,
  DATELASTMOD	 TIMESTAMP(0) DEFAULT CURRENT_TIMESTAMP NOT NULL,
 PRIMARY KEY (ID)
);
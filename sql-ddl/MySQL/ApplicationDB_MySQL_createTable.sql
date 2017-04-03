-- drop table scenario_table;
drop table scenario_table;
drop table app_statistic;
drop table task;
drop table taskgroup;
drop table scenario;
drop table app_user;

CREATE TABLE app_user (
  id            decimal(10)  NOT NULL,
  nickname      varchar(24)  NOT NULL,
  password      varchar(64)  NOT NULL,
  title         varchar(24)  NULL,
  firstname     varchar(64)  NULL,
  lastname      varchar(64)  NULL,
  email         varchar(64)  NULL,
  role          varchar(64)  NULL,
  datecreate    TIMESTAMP(0) Not null,
  datelastmod   TIMESTAMP(0) Not null,
  PRIMARY KEY (id),
  UNIQUE (nickname)
);
-- Each user may have several scenarios
-- A scenario comprises a set of tables and a set of tasks
CREATE TABLE scenario (
  id            decimal(10)   NOT NULL,
  app_user_id   decimal(10)   NOT NULL,
  description   varchar(255)  NOT NULL,
  datasource    varchar(64)   NOT NULL,
  datecreate    TIMESTAMP(0) Not null,
  datelastmod   TIMESTAMP(0) Not null,
  DATABASEPRODUCTNAME varchar(64),
  SAMPLESOLUTIONHINTCOUNT integer,
  FOREIGN KEY (app_user_id) REFERENCES app_user(id) ON DELETE CASCADE,
  PRIMARY KEY (id),
  UNIQUE (description)
);
-- Tasks must be grouped to taskgroups - groups of somehow related tasks
CREATE TABLE taskgroup (
  id           decimal(10)    NOT NULL,
  scenario_id  decimal(10)    NOT NULL,
  rank         decimal(10,0)  NOT NULL,
  description  varchar(255)   NOT NULL,
  datecreate    TIMESTAMP(0) Not null,
  datelastmod   TIMESTAMP(0) Not null,
  FOREIGN KEY (scenario_id) REFERENCES scenario(id) ON DELETE CASCADE,
  PRIMARY KEY (id)
);
-- A task consists of a description that will be displayed to the student.
-- query is the solution that must be found by the student.
-- a hint is not obligatory - it may be given to help the user
-- hint_trials determines when the hint will be displayed
CREATE TABLE task (
  id            decimal(10)    NOT NULL,
  taskgroup_id  decimal(10)    NOT NULL,
  rank          decimal(10,0)  NOT NULL,
  description   varchar(2048)  NOT NULL,
  query         varchar(2048)  NOT NULL,
  hint          varchar(2048),
  hint_trials   int          DEFAULT 0 NOT NULL,
  datecreate    TIMESTAMP(0) Not null,
  datelastmod   TIMESTAMP(0) Not null,
  FOREIGN KEY (taskgroup_id) REFERENCES taskgroup(id) ON DELETE CASCADE,
  PRIMARY KEY (id)
);
-- Simply the name of the tables that belong to one scenario
CREATE TABLE scenario_table (
  scenario_id  decimal(10)    NOT NULL,
  scenario_table varchar(255) NOT NULL,
  datecreate    TIMESTAMP(0) Not null,
  datelastmod   TIMESTAMP(0) Not null,
  FOREIGN KEY (scenario_id) REFERENCES scenario(id) ON DELETE CASCADE,
  PRIMARY KEY (scenario_id, scenario_table)
);
-- Statistics that may be used by the teacher
-- Every try is registered - together with the information
-- whether it was successful
CREATE TABLE app_statistic (
  id           decimal(10)   NOT NULL,
  task_id      decimal(10)   NOT NULL,
  success      char(1)       NOT NULL CHECK (success IN ('0','1')),
  query        varchar(2048) NOT NULL,
  session_id   varchar(255)  NOT NULL,
  datecreate    TIMESTAMP(0) Not null,
  FOREIGN KEY (task_id) REFERENCES task(id) ON DELETE CASCADE,
  PRIMARY KEY (id)
);
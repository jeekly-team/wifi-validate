DROP TABLE wifiuser IF EXISTS;
DROP TABLE validatelog IF EXISTS;
DROP TABLE validatecodelog IF EXISTS;
CREATE TABLE wifiuser (id varchar(32) not null,name varchar(64) not null unique, primary key (id));
CREATE TABLE validatelog (id varchar(32) not null,dt timestamp,sid varchar(32) not null,type varchar(16) not null,wifiuser_id varchar(32) not null,primary key (id));
CREATE TABLE validatecodelog ( id varchar(32) not null, vcode varchar(32) not null, log_id varchar(32) not null, primary key (id));
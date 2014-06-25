DROP TABLE city IF EXISTS;

DROP TABLE wifiuser IF EXISTS;
/*
DROP TABLE ValidateLog IF EXISTS;
*/
CREATE TABLE city (
    id varchar(32) not null,
    country varchar(50) not null,
    name varchar(50),
    state varchar(50),
    map varchar(256),
    primary key (id)
);

CREATE TABLE wifiuser (
    id varchar(32) not null,
    name varchar(50) not null,
    category varchar(50) not null,
    primary key (id)
);

CREATE TABLE validatelog (
    id varchar(32) not null,
    dt timestamp,
    sid varchar(32) not null,
    type varchar(50) not null,
    wifiuser_id varchar(32) not null,
    primary key (id)
);

DROP TABLE city IF EXISTS;
CREATE TABLE city (
    id varchar(32) not null,
    country varchar(50) not null,
    name varchar(50),
    state varchar(50),
    map varchar(256),
    primary key (id)
);
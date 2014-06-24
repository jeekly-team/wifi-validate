
DROP TABLE city IF EXISTS;

CREATE TABLE city (
    id varchar(32) not null,
    country varchar(50) not null,
    name varchar(50),
    state varchar(50),
    map varchar(256),
    primary key (id)
);
insert into city values('402881e437d467d80137d46fc0e50001','Australia', 'Brisbane', 'Queensland', '-27.470933, 153.023502');
insert into city values('402881e437d467d80137d46fc0e50002','Canada', 'Montreal', 'Quebec', '45.508889, -73.554167');
insert into city values('402881e437d467d80137d46fc0e50003','Israel', 'Tel Aviv', '', '32.066157, 34.777821');
insert into city values('402881e437d467d80137d46fc0e50004','USA', 'Atlanta', 'GA', '33.748995, -84.387982');
insert into city values('402881e437d467d80137d46fc0e50005','UK', 'Bath', 'Somerset', '51.381428, -2.357454');
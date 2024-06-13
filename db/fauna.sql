create table fauna(
id serial primary key,
name text,
avg_age int,
discovery_date date without time zone
);

insert into fauna(name, avg_age, discovery_date)
values('elephant', 29000, null);
insert into fauna(name, avg_age, discovery_date)
values('fish', 11725, '02.02.1793');
insert into fauna(name, avg_age, discovery_date)
values('horse', 15450, '03.03.1901');
insert into fauna(name, avg_age, discovery_date)
values('bird', 30720, '04.04.1975');
insert into fauna(name, avg_age, discovery_date)
values('hare', 9129, '05.05.1896');

select * from fauna where name like '%fish%';
select * from fauna where avg_age > 10000 and avg_age < 21000;
select * from fauna where discovery_date is null;
select * from fauna where discovery_date < '01.01.1950';


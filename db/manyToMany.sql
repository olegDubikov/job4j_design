create table people(
id serial primary key,
name varchar(255)
 );
 
create table age(
id serial primary key,
number int
 );
 
create table people_age(
id serial primary key,
people_id int references people(id),
age_id int references age(id)
 );

insert into people(name) values ('Ivan');
insert into people(name) values ('Stepan');
insert into people(name) values ('Alex');

insert into age(number) values (21);
insert into age(number) values (48);
insert into age(number) values (77);

insert into people_age(people_id, age_id) values (1, 3);
insert into people_age(people_id, age_id) values (2, 2);
insert into people_age(people_id, age_id) values (3, 1);
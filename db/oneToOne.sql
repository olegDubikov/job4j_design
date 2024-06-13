create table item(
id serial primary key,
seria varchar(3),
number int
);

create table weapon(
id serial primary key,
name varchar(255),
item_id int references item(id) unique
);
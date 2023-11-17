create table roles(
id serial primary key,
genre text
);

create table rules(
id serial primary key,
emotion text
);

create table states(
id serial primary key,
name text
);

create table categores(
id serial primary key,
number int
);

create table users(
id serial primary key,
first_name varchar(30),
last_name varchar(30),
roles_id int references roles(id)
);

create table roles_rules(
id serial primary key,
roles_id int references roles(id),
rules_id int references rules(id)
);

create table items(
id serial primary key,
number int,
users_id int references users(id),
categores_id int references categores(id),
states_id int references states(id)
);

create table comments(
id serial primary key,
description text,
items_id int references items(id)
);

create table attachs(
id serial primary key,
name text,
items_id int references items(id)
);
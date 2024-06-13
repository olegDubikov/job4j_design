create table number(
id serial primary key,
size int
);

create table clothes(
id serial primary key,
name varchar(255),
number_id int references number(id)
);

insert into number(size) values(50);
insert into clothes(name, number_id) values('shirt', 1);

select * from clothes;
select * from number where id in (select number_id from clothes);
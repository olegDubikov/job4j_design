Create table car
(
id serial primary key,
name varchar(15),
model varchar(10),
amount integer
);

insert into car(name, model, amount)
values('name_1', 'model_1', 10), ('name_2', 'model_2', 15), ('name_3', 'model_3', 20);

select * from car;

1 transaction:

begin transaction isolation level serializable;

select sum(amount) from car;

update car set amount = 100 where name = 'name_1';


2 transaction:

begin transaction isolation level serializable;

select sum(amount) from car;

update car set amount = 100 where name = 'name_2';

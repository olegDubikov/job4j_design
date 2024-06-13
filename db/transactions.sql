create table car 
(
id serial primary key,
name varchar(15),
model varchar(10),
amount integer
)

insert into car (name, model, amount)
values ('name_3', 'model_3', 20);
insert into car (name, model, amount)
values ('name_2', 'model_2', 777);

begin transaction;
set transaction isolation level serializable;

insert into car (name, model, amount)
values ('name_1', 'model_1', 10);

savepoint f_p;

delete from car where amount = 777;

savepoint s_p;

update car  set amount = amount + 90 where amount = 10;

savepoint t_p;
select * from car;

rollback to s_p;
select * from car;

rollback to f_p;
select * from car;
commit;
select  * from car;





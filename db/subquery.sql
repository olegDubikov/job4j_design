create table customers
(
id serial primary key,
first_name text,
last_name text,
age int,
country text
);

insert into customers(first_name, last_name, age, country)
values('Ivan', 'Ivanov', 18, 'Russia');
insert into customers(first_name, last_name, age, country)
values('Ray', 'Charles', 68, 'USA');
insert into customers(first_name, last_name, age, country)
values('Alan', 'Wilder', 54, 'GB');

select first_name, last_name, age, country
from customers
where age = (select min(age) from customers);

create table orders
(
id serial primary key,
amount int,
customer_id int references customers(id)
);

 insert into orders(amount, customer_id)
 values(1, 1);
 insert into orders(amount, customer_id)
 values(3, 3);

select first_name, last_name
from customers
where id not in (select customer_id from orders);
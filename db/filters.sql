create table type(
id serial primary key,
name text
);

create table product(
id serial primary key,
name text,
type_id int references type(id),
expired_date date,
price float
);

insert into type(name) values('СЫР'), ('МОЛОКО'), ('КРУПА'), ('МОРОЖЕНОЕ');

insert into product(name, type_id, expired_date, price)
values('Сыр Российский', 1, '2023-10-26', 150.59), ('Сыр Голландский', 1, '2023-12-12', 170.21), ('Сыр плавленный', 1, '2024-01-01', 45.00);
insert into product(name, type_id, expired_date, price)
values('Молоко топленое', 2, '2023-11-26', 119.00), ('Молоко детское', 2, '2023-11-12', 121.01), ('Молоко козье', 2, '2023-11-27', 223.55);
insert into product(name, type_id, expired_date, price)
values('Крупа рисовая', 3, '2024-06-15', 60.70), ('Крупа перловая', 3, '2024-02-20', 57.65), ('Крупа гречневая', 3, '2023-09-11', 70.00);
insert into product(name, type_id, expired_date, price)
values('Сливочное мороженое', 4, '2023-11-15', 20.10), ('Мороженое пломбир', 4, '2023-12-23', 37.25), ('Фруктовое мороженое', 4, '2023-11-25', 15.70);

select * from product pp
join type p
on pp.type_id = p.id and p.name like 'СЫР'; 

select * from product where name like '%мороженое%';

select * from product where current_date > expired_date;

select name, price
from product
group by name, price
having price = (select max(price) from product);

select p.name as "имя_типа", count(pp.name) "количество"
from product pp
join type p
on pp.type_id = p.id
group by p.name;

select * from product pp
join type p
on pp.type_id = p.id
where p.name = 'СЫР' or p.name = 'МОЛОКО';

select p.name, count(pp.name)
from product pp
join type p
on pp.type_id = p.id
group by p.name
having count(pp.name) < 10;

select pp.name, p.name
from product pp
join type p
on pp.type_id = p.id
group by pp.name, p.name
order by p.name asc;


create table book(
id serial primary key,
title text,
amount int
);

create table writer(
id serial primary key,
name text,
book_id int references book(id)
);

insert into book(title, amount)
values('Thunderstorm', 5);
insert into book(title, amount)
values('The Little Prince', 2);
insert into book(title, amount)
values('Fall', 1);

insert into writer(name, book_id)
values('J. Gordon', 1);
insert into writer(name, book_id)
values('A. Saint-Exupery' 3);
insert into writer(name, book_id)
values('A.S. Pushkin', 2);

select pp.name, p.title, p.amount
from writer as pp join book as p on pp.book_id = p.id;

select pp.name as Фамилия, p.title as Название, p.amount as Количество
from writer as pp join book as p on pp.book_id = p.id;

select pp.name Автор, p.title Название, p.amount as "Количество книг"
from writer pp join book p on pp.book_id = p.id;




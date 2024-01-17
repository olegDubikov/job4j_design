create table movie
(
id serial primary key,
name text,
director text
);

create table book
(
id serial primary key,
title text,
author text
);

insert into movie(name, director)
values('Марсианин', 'Ридли Скотт'),
       ('Матрица', 'Братья Вачовски'),
       ('Властелин колец', 'Питер Джексон'),
       ('Гарри Поттер и узник Азкабана', 'Альфонсо Куарон'),
       ('Железный человек', 'Джон Фавро');
       
insert into book(title, author)
values('Гарри Поттер и узник Азкабана', 'Джоан Роулинг'),
       ('Властелин колец', 'Джон Толкин'),
       ('1984', 'Джордж Оруэлл'),
       ('Марсианин', 'Энди Уир'),
       ('Божественная комедия', 'Данте Алигьери');
      
select name as "Фильмы снятые по книге"
from movie
intersect
select title
from book;

select title as "Книги без экранизации"
from book
except
select name
from movie;

select name as "Фильмы снятые не по книге"
from movie
except
select title
from book;

select name "Уникальные названия"
from movie
where name not in (select title from book) 
union
select title
from book
where title not in (select name from movie);

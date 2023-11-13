create table car(
id serial primary key,
model varchar(255),
color text,
power integer
);
insert into car(model, color, power) values('JMC', 'silver', 350);
update car set color = 'yellow';
delete from car
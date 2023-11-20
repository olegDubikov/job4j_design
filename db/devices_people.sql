create table devices(
    id serial primary key,
    name varchar(255),
    price float
);

create table people(
    id serial primary key,
    name varchar(255)
);

create table devices_people(
    id serial primary key,
    device_id int references devices(id),
    people_id int references people(id)
);

insert into devices(name, price)
values('phone', 17.300);
insert into devices(name, price)
values('laptop', 103.325);  
insert into devices(name, price)
values('earphones', 2.100);

insert into people(name)
values('Ivan');
insert into people(name)
values('Oleg');
insert into people(name)
values('Petr');

insert into devices_people(device_id, people_id)
values(3, 1), (2, 3), (1, 2);
insert into devices_people(device_id, people_id)
values(3, 2), (2, 1), (1, 3);

select avg(price) from devices;

select p.name, avg(d.price)
from devices_people pp
join devices d on pp.device_id = d.id
join people p on pp.people_id = p.id 
group by p.name
having avg(d.price) > 5.000;
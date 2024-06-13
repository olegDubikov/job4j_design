insert into users(first_name, last_name, roles_id)
values('Ivan', 'Petrov', 1);
insert into users(first_name, last_name, roles_id)
values('Petr', 'Ivanov', 2);
insert into users(first_name, last_name, roles_id)
values('Oleg', 'Sidorov', 3);

insert into roles(genre)
values('comedy');
insert into roles(genre)
values('drama');
insert into roles(genre)
values('thriller');

insert into rules(emotion)
values('laughter');
insert into rules(emotion)
values('cry');
insert into rules(emotion)
values('fear');

insert into roles_rules(roles_id, rules_id)
values(1, 1);
insert into roles_rules(roles_id, rules_id)
values(2, 2);
insert into roles_rules(roles_id, rules_id)
values(3, 3);

insert into items(number, users_id, categores_id, states_id)
values(1, 2, 3, 1);
insert into items(number, users_id, categores_id, states_id)
values(2, 1, 2, 3);
insert into items(number, users_id, categores_id, states_id)
values(3, 3, 1, 2);

insert into comments(description, items_id)
values('good', 3);
insert into comments(description, items_id)
values('neutral', 2);
insert into comments(description, items_id)
values('bad', 1);

insert into attachs(name, items_id)
values('abc', 2);
insert into attachs(name, items_id)
values('bac', 3);
insert into attachs(name, items_id)
values('cab', 1);

insert into states(name)
values('end');
insert into states(name)
values('continue');
insert into states(name)
values('start');

insert into categores(number)
values(3);
insert into categores(number)
values(2);
insert into categores(number)
values(1);
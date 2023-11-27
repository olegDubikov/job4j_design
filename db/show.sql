CREATE TABLE factory(
id serial primary key,
name text
);

CREATE TABLE owners(
id serial primary key,
name text
);

CREATE TABLE weapons(
id serial primary key,
name text,
number int,
factory_id int REFERENCES factory(id)
);

CREATE TABLE store(
id serial primary key,
owner_id int REFERENCES owners(id),
weapon_id int REFERENCES weapons(id)
);

INSERT INTO factory(name)
VALUES('AAA'), ('BBB'), ('CCC');

INSERT INTO owners(name)
VALUES('Ivan Ivanov'), ('Petr Petrov'), ('J.Pesci');

INSERT INTO weapons(name, number, factory_id)
VALUES('gun', 1111, 3), ('rifle', 2222, 1), ('shotgun', 3333, 3), ('carbine', 4444, 2);

INSERT INTO store(owner_id, weapon_id)
VALUES(1, 1), (2, 4), (3, 2), (3, 3), (3, 3), (3, 3);

CREATE VIEW show_armed_to_the_teeth
AS SELECT o.name AS "Имя владельца", count(w.name) "Количество", 
w.name "Оружие", w.number AS "Серийный номер оружия", 
f.name AS "Завод изготовитель"  
FROM owners o
JOIN store s ON o.id = s.owner_id
JOIN weapons w ON s.weapon_id = w.id
JOIN factory f ON w.factory_id = f.id
GROUP BY o.name, w.name, w.number, f.name
HAVING count(w.name) > 2
ORDER BY o.name;

SELECT * FROM show_armed_to_the_teeth;
ALTER VIEW show__armed_to_the_teeth RENAME TO show_goodguy;


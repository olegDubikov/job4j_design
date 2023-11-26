CREATE TABLE car_bodies(
id serial primary key,
name text
);

CREATE TABLE car_engines(
id serial primary key,
name text
);

CREATE TABLE car_transmissions(
id serial primary key,
name text
);

CREATE TABLE cars(
id serial primary key,
name text,
body_id int REFERENCES car_bodies(id),
engine_id int REFERENCES car_engines(id),
transmission_id int REFERENCES car_transmissions(id)
);

INSERT INTO car_bodies(name)
VALUES ('седан'), ('хетчбек'), ('пикап');

INSERT INTO car_engines(name)
VALUES('BOXER 2.5'), ('V6'), ('V12'), ('паровой двигатель');

INSERT INTO car_transmissions(name)
VALUES('МКПП'), ('АКПП'), ('СКПП'), ('variator');

INSERT INTO cars(name, body_id, engine_id, transmission_id)
VALUES('SUBARU', 1, 1, 3), ('FORD', 3, 2, 2), 
('Lada', 1, null, 1), ('KAMAZ', null, 3, 2), ('TESLA', 1, null, null);

SELECT id, name "car_name", body_id "body_name", 
engine_id "engine_name", transmission_id "transmission_name"
FROM cars;

SELECT b.name AS "Тип кузова"
FROM cars c
RIGHT JOIN car_bodies b
ON b.id = c.body_id
WHERE c.body_id is null;

SELECT e.name AS "Тип двигателя"
FROM cars c
RIGHT JOIN car_engines e
ON e.id = c.engine_id
WHERE c.engine_id is null;

SELECT t.name AS "Коробка передач"
FROM car_transmissions t
LEFT JOIN cars c
ON c.transmission_id = t.id
WHERE c.transmission_id is null;
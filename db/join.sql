CREATE TABLE employees(
id serial primary key,
name text
);

CREATE TABLE departaments(
id serial primary key,
name text,
employee_id int REFERENCES employees(id)
);

INSERT INTO employees(name)
VALUES('Ivan Ivanov'), ('Petr Petrov'), ('Gleb Glebov');

INSERT INTO departaments(name, employee_id)
VALUES('IT', 1), ('AUTO', 2), ('CREDIT', 3), ('SALES', null);

SELECT * FROM departaments d 
LEFT JOIN employees e
ON d.employee_id = e.id;

SELECT * FROM employees e 
RIGHT JOIN departaments d
ON e.id = d.employee_id;

SELECT * FROM departaments d 
RIGHT JOIN employees e
ON d.employee_id = e.id; 

SELECT * FROM departaments d 
FULL JOIN employees e
ON d.employee_id = e.id;

SELECT * FROM departaments d 
CROSS JOIN employees e;

SELECT * FROM departaments d 
LEFT JOIN employees e
ON d.employee_id = e.id
WHERE d.employee_id is null;

CREATE TABLE teens(
id serial primary key,
name text,
gender varchar(1)
);

INSERT INTO teens(name, gender)
VALUES('Joe', 'M'), ('Gala', 'F'), ('Eduardo', 'M'), ('Gulia', 'F'), ('Alex', 'M'), 
('Jessica', 'F'), ('Ivan', 'M'), ('Irina', 'F'), ('Vasya', 'M'), ('Tanya', 'F');

SELECT t1.name, t1.gender, t2.name, t2.gender, (t1.name <> t2.name) "Pare"
FROM teens t1
CROSS JOIN teens t2
WHERE (t1.gender LIKE 'M' AND t2.gender LIKE 'F');




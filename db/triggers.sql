CREATE TABLE products(
id serial primary key,
name varchar(50),
producer varchar(50),
count integer default 0,
price integer
);


CREATE OR REPLACE function tax()
returns trigger AS
$$
BEGIN
update products
set price = price + price * 0.13
where id = (SELECT id FROM inserted);
return new;
END;
$$
LANGUAGE 'plpgsql';

CREATE TRIGGER tax_on_products
AFTER INSERT ON products
REFERENCING NEW TABLE AS inserted
FOR EACH STATEMENT
execute procedure tax();


CREATE OR REPLACE function tax2()
returns trigger AS
$$
BEGIN
new.price = new.price + new.price * 0.13;
return new;
END;
$$
LANGUAGE 'plpgsql';

CREATE TRIGGER tax2_on_products
BEFORE INSERT ON products
FOR EACH ROW
execute procedure tax2();



create table history_of_price (
    id serial primary key,
    name varchar(50),
    price integer,
    date timestamp
);


CREATE OR REPLACE function record_to_history()
returns trigger AS
$$
BEGIN
INSERT INTO history_of_price(name, price, date)
VALUES(new.name, new.price, current_date);
return new;
END;
$$
LANGUAGE 'plpgsql';

CREATE TRIGGER record_to_table
AFTER INSERT ON products
FOR EACH ROW
execute procedure record_to_history();
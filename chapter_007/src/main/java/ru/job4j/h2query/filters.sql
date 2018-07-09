CREATE TABLE types (
	id SERIAL PRIMARY KEY,
	name VARCHAR(255)
);

CREATE TABLE products (
	id SERIAL PRIMARY KEY,
	name VARCHAR(255),
	type_id INT REFERENCES types(id),
	expire_date DATE,
	price DEC(4,2),
	amount INT
);

SELECT * FROM products;
SELECT * FROM types;

INSERT INTO types (name) VALUES ('СЫР');
INSERT INTO types (name) VALUES ('КОЛБАСА');
INSERT INTO types (name) VALUES ('ХЛЕБ');
INSERT INTO types (name) VALUES ('ВОДА');
INSERT INTO types (name) VALUES ('МОЛОКО');
INSERT INTO types (name) VALUES ('КРУПА');
INSERT INTO types (name) VALUES ('ДЕСЕРТ');

INSERT INTO products (name, type_id, expire_date, price, amount) VALUES ('Квас JOB4J', 4, '2018-08-05', 50.05, 25);
INSERT INTO products (name, type_id, expire_date, price, amount) VALUES ('Российский сыр', 1, '2018-07-15', 99.45, 4);
INSERT INTO products (name, type_id, expire_date, price, amount) VALUES ('Батон нарезной', 3, '2018-07-10', 25.53, 11);
INSERT INTO products (name, type_id, expire_date, price, amount) VALUES ('Колбаса докторская', 2, '2018-07-20', 83.13, 32);
INSERT INTO products (name, type_id, expire_date, price, amount) VALUES ('Молоко 3,2%', 5, '2018-07-11', 65.11, 8);
INSERT INTO products (name, type_id, expire_date, price, amount) VALUES ('Моцарелла', 1, '2018-07-14', 59.13, 17);
INSERT INTO products (name, type_id, expire_date, price, amount) VALUES ('Мороженое Пломбир', 7, '2018-07-09', 40.08, 19);
INSERT INTO products (name, type_id, expire_date, price, amount) VALUES ('Гауда', 1, '2018-07-16', 85.78, 6);
INSERT INTO products (name, type_id, expire_date, price, amount) VALUES ('Сок Orange', 4, '2018-08-29', 80.88, 45);
INSERT INTO products (name, type_id, expire_date, price, amount) VALUES ('Вафельный рожок (мороженое)', 7, '2018-07-09', 40.08, 100);
INSERT INTO products (name, type_id, expire_date, price, amount) VALUES ('Сложноквашинское молоко', 5, '2018-07-21', 75.10, 5);

-- 1.
SELECT * FROM products AS p
INNER JOIN types AS t ON p.type_id = t.id WHERE p.type_id = 1;
-- 2.
SELECT * FROM products AS p WHERE p.name like '%ороженое%';
-- 3.
SELECT * FROM products AS p WHERE p.expire_date > '2018-07-31';
-- 4.
SELECT * FROM products WHERE price = (SELECT MAX(price) FROM products);
-- 5.
SELECT count(p.id) FROM products AS p WHERE p.type_id = 1;
-- 6.
SELECT * FROM products AS p
INNER JOIN types AS t ON p.type_id = t.id WHERE p.type_id IN (1, 5);
-- 7.
SELECT t.name, p.amount FROM products AS p 
INNER JOIN types AS t ON p.type_id = t.id WHERE p.amount < 10;
-- 8.
SELECT * FROM products AS p
INNER JOIN types AS t ON p.type_id = t.id;
CREATE TABLE types (
	id SERIAL PRIMARY KEY,
	name VARCHAR(255)
);

DROP TABLE IF EXISTS products;
CREATE TABLE products (
	id SERIAL PRIMARY KEY,
	name VARCHAR(255),
	type_id INT REFERENCES types(id),
	expire_date DATE,
	price DEC(4,2)
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

INSERT INTO products (name, type_id, expire_date, price) VALUES ('Сыр пармезан', 1, '2018-08-01', 97.10);
INSERT INTO products (name, type_id, expire_date, price) VALUES ('Квас JOB4J', 4, '2018-08-05', 50.05);
INSERT INTO products (name, type_id, expire_date, price) VALUES ('Российский сыр', 1, '2018-07-15', 99.45);
INSERT INTO products (name, type_id, expire_date, price) VALUES ('Батон нарезной', 3, '2018-07-10', 25.53);
INSERT INTO products (name, type_id, expire_date, price) VALUES ('Сыр Камамбер', 1, '2018-08-02', 67.16);
INSERT INTO products (name, type_id, expire_date, price) VALUES ('Колбаса докторская', 2, '2018-07-20', 83.13);
INSERT INTO products (name, type_id, expire_date, price) VALUES ('Сыр фета', 1, '2018-08-03', 78.18);
INSERT INTO products (name, type_id, expire_date, price) VALUES ('Молоко 3,2%', 5, '2018-07-11', 65.11);
INSERT INTO products (name, type_id, expire_date, price) VALUES ('Моцарелла', 1, '2018-07-04', 59.13);
INSERT INTO products (name, type_id, expire_date, price) VALUES ('Мороженое Пломбир', 7, '2018-07-09', 40.08);
INSERT INTO products (name, type_id, expire_date, price) VALUES ('Гауда', 1, '2018-07-16', 85.78);
INSERT INTO products (name, type_id, expire_date, price) VALUES ('Сок Orange', 4, '2018-08-29', 80.88);
INSERT INTO products (name, type_id, expire_date, price) VALUES ('Сыр адыгейский', 1, '2018-08-05', 87.80);
INSERT INTO products (name, type_id, expire_date, price) VALUES ('Вафельный рожок (мороженое)', 7, '2018-07-09', 40.08);
INSERT INTO products (name, type_id, expire_date, price) VALUES ('Сыр с плесенью', 1, '2018-09-11', 99.99);
INSERT INTO products (name, type_id, expire_date, price) VALUES ('Сложноквашинское молоко', 5, '2018-07-21', 75.10);
INSERT INTO products (name, type_id, expire_date, price) VALUES ('Сыр тофу', 1, '2018-08-06', 57.15);
INSERT INTO products (name, type_id, expire_date, price) VALUES ('Рис', 6, '2018-11-09', 57.13);
INSERT INTO products (name, type_id, expire_date, price) VALUES ('Сыр Чеддер', 1, '2018-08-07', 78.88);
INSERT INTO products (name, type_id, expire_date, price) VALUES ('Сок томатный', 4, '2018-10-29', 85.88);
INSERT INTO products (name, type_id, expire_date, price) VALUES ('Сыр пармезан', 1, '2018-08-08', 93.13);
INSERT INTO products (name, type_id, expire_date, price) VALUES ('Крупа гречневая', 6, '2018-10-10', 72.27);

-- 1.
SELECT * FROM products AS p
INNER JOIN types AS t ON p.type_id = t.id WHERE p.type_id = 1;
-- 2.
SELECT * FROM products AS p WHERE p.name like '%ороженое%';
-- 3.
-- SELECT * FROM products AS p WHERE p.expire_date > '2018-07-31';
SELECT * FROM products p WHERE EXTRACT(MONTH FROM p.expire_date) = EXTRACT(MONTH FROM now()) + 1;
-- 4.
SELECT * FROM products WHERE price = (SELECT MAX(price) FROM products);
-- 5.
SELECT count(p.id) FROM products AS p WHERE p.type_id = 1;
-- 6.
SELECT * FROM products AS p
INNER JOIN types AS t ON p.type_id = t.id WHERE p.type_id IN (1, 5);
-- 7.
SELECT p.type_id, t.name, count(*) FROM products AS p 
INNER JOIN types AS t ON p.type_id = t.id
GROUP BY p.type_id, t.name 
HAVING count(*) < 10 
ORDER BY p.type_id;
-- 8.
SELECT * FROM products AS p
INNER JOIN types AS t ON p.type_id = t.id;
CREATE DATABASE car_catalog;

CREATE TABLE carbodies (
	id SERIAL PRIMARY KEY,
	name VARCHAR(255)
);

CREATE TABLE engines (
	id SERIAL PRIMARY KEY,
	name VARCHAR(255)
);

CREATE TABLE transmissions (
	id SERIAL PRIMARY KEY,
	name VARCHAR(255)
);

CREATE TABLE cars (
	id SERIAL PRIMARY KEY,
	name VARCHAR(255),
	carbody_id INT REFERENCES carbodies(id),
	engine_id INT REFERENCES engines(id),
	trans_id INT REFERENCES transmissions(id)
);

INSERT INTO carbodies (name) VALUES ('Седан');
INSERT INTO carbodies (name) VALUES ('Универсал');
INSERT INTO carbodies (name) VALUES ('Хэтчбек');
INSERT INTO carbodies (name) VALUES ('Купе');
INSERT INTO carbodies (name) VALUES ('Лимузин');
INSERT INTO carbodies (name) VALUES ('Внедорожник');

INSERT INTO engines (name) VALUES ('Бензиновый');
INSERT INTO engines (name) VALUES ('Дизельный');
INSERT INTO engines (name) VALUES ('Газовый');
INSERT INTO engines (name) VALUES ('Электро');

INSERT INTO transmissions (name) VALUES ('Механическая коробка (МКПП)');
INSERT INTO transmissions (name) VALUES ('Автоматическая коробка (АКПП)');
INSERT INTO transmissions (name) VALUES ('Роботизированная коробка (РКПП)');
INSERT INTO transmissions (name) VALUES ('Вариативная коробка (Вариатор)');

INSERT INTO cars (name, carbody_id, engine_id, trans_id) VALUES ('Ford Focus', 1, 1, 2);
INSERT INTO cars (name, carbody_id, engine_id, trans_id) VALUES ('Volvo', 2, 2, 1);
INSERT INTO cars (name, carbody_id, engine_id, trans_id) VALUES ('Mercedes-Benz', 5, 1, 2);
INSERT INTO cars (name, carbody_id, engine_id, trans_id) VALUES ('Opel Astra', 3, 1, 3);
INSERT INTO cars (name, carbody_id, engine_id, trans_id) VALUES ('LADA (ВАЗ) 2105', 1, 1, 1);
INSERT INTO cars (name, carbody_id, engine_id, trans_id) VALUES ('Kia Ceed GT', 3, 1, 2);
INSERT INTO cars (name, carbody_id, engine_id, trans_id) VALUES ('Peugeot 406', 2, 1, 1);
INSERT INTO cars (name, carbody_id, engine_id, trans_id) VALUES ('Mitsubishi Pajero', 6, 2, 2);
INSERT INTO cars (name, carbody_id, engine_id, trans_id) VALUES ('Tesla Model X', 6, 4, 2);

--1.
SELECT c.name, ca.name, e.name, t.name FROM cars c
INNER JOIN carbodies ca
ON c.carbody_id = ca.id
INNER JOIN engines e
ON c.engine_id = e.id
INNER JOIN transmissions t
ON c.trans_id = t.id;

--2.
SELECT ca.name, c.name FROM carbodies ca
LEFT OUTER JOIN cars c
ON ca.id = c.carbody_id;

--3.
SELECT e.name, c.name FROM cars c
RIGHT OUTER JOIN engines e
ON e.id = c.engine_id;

--4.
SELECT t.name, c.name FROM transmissions t
LEFT OUTER JOIN cars c
ON t.id = c.trans_id;
DROP TABLE seats;
CREATE TABLE seats (
  id SERIAL PRIMARY KEY,
  number INT NOT NULL,
  price INT NOT NULL CHECK (price > 0),
  status INT DEFAULT 0
);

ALTER TABLE seats 
ADD CONSTRAINT number_val UNIQUE (number);

CREATE TABLE accounts (
  id SERIAL PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  surname VARCHAR(255) NOT NULL,
  patron VARCHAR(255) NOT NULL,
  mobile VARCHAR(255) NOT NULL,
  seat INT REFERENCES seats(number)
);

INSERT INTO seats (number, price) VALUES (11, 300);
INSERT INTO seats (number, price) VALUES (12, 300);
INSERT INTO seats (number, price) VALUES (13, 300);
INSERT INTO seats (number, price) VALUES (21, 400);
INSERT INTO seats (number, price) VALUES (22, 400);
INSERT INTO seats (number, price) VALUES (23, 400);
INSERT INTO seats (number, price) VALUES (31, 500);
INSERT INTO seats (number, price) VALUES (32, 500);
INSERT INTO seats (number, price) VALUES (33, 500);

SELECT price, status FROM seats
ORDER BY id;

UPDATE seats
SET status = 1
WHERE id IN (3, 5, 7);

UPDATE seats
SET status = 1
WHERE number = 33;
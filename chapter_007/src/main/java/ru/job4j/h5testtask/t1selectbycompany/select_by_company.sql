CREATE TABLE company (
	id INT NOT NULL,
	name CHARACTER VARYING,
	CONSTRAINT company_pkey 
	PRIMARY KEY (id)
);

CREATE TABLE person (
	id INT NOT NULL,
	name VARCHAR,
	company_id INT,
	CONSTRAINT person_pkey 
	PRIMARY KEY(id)
);

INSERT INTO person (id, name, company_id) VALUES (1, 'john', 1);
INSERT INTO person (id, name, company_id) VALUES (2, 'kate', 2);
INSERT INTO person (id, name, company_id) VALUES (3, 'sam', 1);
INSERT INTO person (id, name, company_id) VALUES (4, 'chris', 4);
INSERT INTO person (id, name, company_id) VALUES (5, 'jack', 5);
INSERT INTO person (id, name, company_id) VALUES (6, 'bill', 1);
INSERT INTO person (id, name, company_id) VALUES (7, 'sol', 6);
INSERT INTO person (id, name, company_id) VALUES (8, 'al', 2);
INSERT INTO person (id, name, company_id) VALUES (9, 'tony', 5);
INSERT INTO person (id, name, company_id) VALUES (10, 'don', 1);
INSERT INTO person (id, name, company_id) VALUES (11, 'leo', 4);
INSERT INTO person (id, name, company_id) VALUES (12, 'mike', 1);
INSERT INTO person (id, name, company_id) VALUES (13, 'raph', 3);
INSERT INTO person (id, name, company_id) VALUES (14, 'bruce', 2);
INSERT INTO person (id, name, company_id) VALUES (15, 'jocker', 2);
INSERT INTO person (id, name, company_id) VALUES (16, 'bane', 4);
INSERT INTO person (id, name, company_id) VALUES (17, 'rob', 1);
-- DELETE FROM person;

INSERT INTO company (id, name) VALUES (1, 'oracle');
INSERT INTO company (id, name) VALUES (2, 'microsoft');
INSERT INTO company (id, name) VALUES (3, 'google');
INSERT INTO company (id, name) VALUES (4, 'apple');
INSERT INTO company (id, name) VALUES (5, 'ibm');
INSERT INTO company (id, name) VALUES (6, 'samsung');
INSERT INTO company (id, name) VALUES (7, 'sony');

-- 1.
SELECT p.name, c.name FROM person p
INNER JOIN company c
ON c.id = p.company_id WHERE NOT p.company_id = 5
ORDER BY p.company_id;

-- 2.
SELECT MAX(count) FROM (
	SELECT c.name AS company_name, count(p.company_id) AS count
	FROM person p
	INNER JOIN company c
	ON c.id = p.company_id
	GROUP BY p.company_id, c.name
	ORDER BY count DESC
) x;
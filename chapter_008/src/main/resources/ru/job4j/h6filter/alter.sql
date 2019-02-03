-- ALTER TABLE users ADD COLUMN password VARCHAR(255) NOT NULL DEFAULT 'password';
-- ALTER TABLE users ADD COLUMN role INT REFERENCES roles DEFAULT 3;

CREATE TABLE roles (
	id SERIAL PRIMARY KEY,
	role_name VARCHAR(255)
);

INSERT INTO roles (role_name) VALUES ('Administrator');
INSERT INTO roles (role_name) VALUES ('Anonymous');
INSERT INTO roles (role_name) VALUES ('Moderator');
INSERT INTO roles (role_name) VALUES ('User');
-- ALTER TABLE roles RENAME COLUMN name TO role_name;

ALTER TABLE users ADD CONSTRAINT users_role_fkey FOREIGN KEY (role) REFERENCES roles;
ALTER TABLE users DROP CONSTRAINT users_role_fkey;

UPDATE users SET password = 1 WHERE id = 5;
UPDATE users SET password = 2 WHERE id = 8;
UPDATE users SET password = 1 WHERE id = 18;
UPDATE users SET password = 3 WHERE id = 19;

SELECT * FROM roles ORDER BY role_name;

INSERT INTO users (name, login, email, comments, password, role) VALUES ('Vit', 'rav', 'rav.energ@r.ru', 'foobar', 'pass', 1);

SELECT u.id,u.name,u.login,u.email,u.createdate,u.comments,u.password,r.role_name FROM users u
LEFT OUTER JOIN roles r ON u.role = r.id
WHERE u.id = 28
ORDER BY u.id;

create table users_new (
	id SERIAL PRIMARY KEY,
	name VARCHAR(255) NOT NULL,
	login VARCHAR(255) NOT NULL,
	email VARCHAR(255) NOT NULL,
	country VARCHAR(255) NOT NULL,
	city VARCHAR(255) NOT NULL,
	createDate TIMESTAMP WITHOUT TIME ZONE DEFAULT now(),
	comments VARCHAR(1000) NOT NULL,
	password VARCHAR(255) NOT NULL,
	role INT NOT NULL REFERENCES roles
);

INSERT INTO users_new (name, login, email, country, city, comments, password, role)
VALUES ('Vit', 'ad', 'a@b.ru', 'Russian Federation', 'Moscow', 'comments', 'ad', 1);

ALTER TABLE users_new RENAME TO users; 

SELECT u.id,u.name,u.login,u.email,u.country,u.city,u.createdate,u.comments,u.password,r.role_name FROM users u
LEFT OUTER JOIN roles r ON u.role = r.id








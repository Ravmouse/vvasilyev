DROP DATABASE IF EXISTS sql_test;
CREATE DATABASE sql_test;

CREATE TABLE roles (
	role_id SERIAL PRIMARY KEY,
	role_name VARCHAR(255)
);

CREATE TABLE users (
	user_id SERIAL PRIMARY KEY,
	user_name VARCHAR(255),
	role_id INT REFERENCES roles(role_id)
); 

CREATE TABLE rules (
	rule_id SERIAL PRIMARY KEY,
	rule_name VARCHAR(255)
);

CREATE TABLE roles_rules (
	role_rule_id SERIAL PRIMARY KEY,
	role_id INT REFERENCES roles(role_id),
	rule_id INT REFERENCES rules(rule_id)
);

CREATE TABLE categories (
	category_id SERIAL PRIMARY KEY,
	category_name VARCHAR(255)
);

CREATE TABLE states (
	state_id SERIAL PRIMARY KEY,
	state_name VARCHAR(255)
);

CREATE TABLE items (
	item_id INT REFERENCES users(user_id) PRIMARY KEY,
	item_name VARCHAR(255),
	category_id INT REFERENCES categories(category_id),
	state_id INT REFERENCES states(state_id)
);

CREATE TABLE comments (
	comment_id SERIAL PRIMARY KEY,
	comment_name VARCHAR(255),
	item_id INT REFERENCES items(item_id)
);

CREATE TABLE attaches (
	attach_id SERIAL PRIMARY KEY,
	attach_name VARCHAR(255),
	item_id INT REFERENCES items(item_id)
);

INSERT INTO roles (role_name) VALUES ('Administrator');
INSERT INTO users (user_name, role_id) VALUES ('Bill', 1);
INSERT INTO rules (rule_name) VALUES ('Full Access');
INSERT INTO roles_rules (role_id, rule_id) VALUES (1, 1);
INSERT INTO categories (category_name) VALUES ('Service Item');
INSERT INTO states (state_name) VALUES ('In progress');
INSERT INTO items (item_id, item_name, category_id, state_id) VALUES (1, 'System Error!', 1, 1);
INSERT INTO comments (comment_name, item_id) VALUES ('Important', 1);
INSERT INTO attaches (attach_name, item_id) VALUES ('List.txt', 1);
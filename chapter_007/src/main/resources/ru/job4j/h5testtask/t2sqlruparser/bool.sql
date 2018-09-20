CREATE TABLE info (
	id SERIAL PRIMARY KEY,
	is_launched BOOLEAN
);

INSERT INTO info (is_launched) VALUES (FALSE);
SELECT * FROM info;

UPDATE info
SET is_launched = FALSE;
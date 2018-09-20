DROP TABLE IF EXISTS job_offers;

CREATE TABLE job_offers (
	id SERIAL PRIMARY KEY,
	job_title VARCHAR(255),
	pub_date VARCHAR(255)
);

SELECT j.job_title, j.pub_date FROM job_offers AS j
ORDER BY j.pub_date DESC;

DELETE FROM job_offers;
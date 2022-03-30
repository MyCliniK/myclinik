DROP TABLE IF EXISTS clients;
CREATE TABLE clients (
	id SERIAL PRIMARY KEY,
	first_name VARCHAR(30) NOT NULL,
	last_name VARCHAR(50),
	email VARCHAR(50),
	phone_number VARCHAR(12) ,
	medical_observations VARCHAR(500),
	observations VARCHAR(500)
);
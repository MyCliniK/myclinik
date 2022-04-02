DROP TABLE IF EXISTS clients CASCADE;
DROP TABLE IF EXISTS appointments CASCADE;
DROP SEQUENCE IF EXISTS id_seq CASCADE;

CREATE SEQUENCE id_seq START WITH 6 INCREMENT BY 1;

CREATE TABLE clients (
	client_id SERIAL PRIMARY KEY,
	first_name VARCHAR(30) NOT NULL,
	last_name VARCHAR(50),
	dni VARCHAR(9) NOT NULL,
	birthdate DATE,
	sex VARCHAR(1),
	phone VARCHAR(12) ,
	email VARCHAR(50),
	promos BOOLEAN,
	medical_observations VARCHAR(500),
	observations VARCHAR(500)
);

CREATE TABLE appointments (
	appointment_id SERIAL PRIMARY KEY,
	appointment_date TIMESTAMP,
	done BOOLEAN,
	paid BOOLEAN,
	client_id int,
	FOREIGN KEY (client_id) REFERENCES clients(client_id)

);

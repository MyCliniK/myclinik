DROP TABLE IF EXISTS clients CASCADE;
DROP TABLE IF EXISTS appointments CASCADE;
DROP TABLE IF EXISTS treatments CASCADE;

CREATE TABLE clients (
	id SERIAL PRIMARY KEY,
	first_name VARCHAR(30) NOT NULL,
	last_name VARCHAR(50) NOT NULL,
	dni VARCHAR(9) NOT NULL,
	birthdate DATE,
	sex VARCHAR(1),
	phone VARCHAR(12),
	email VARCHAR(50) NOT NULL,
	promos BOOLEAN,
	medical_observations VARCHAR(500),
	observations VARCHAR(500)
);

CREATE TABLE treatments (
	id SERIAL PRIMARY KEY,
	name VARCHAR(255) NOT NULL,
	subservice VARCHAR(255),
	price FLOAT NOT NULL,
	duration INT NOT NULL,
	consents VARCHAR(255)

);

CREATE TABLE appointments (
	id SERIAL PRIMARY KEY,
	appointment_date TIMESTAMP NOT NULL,
	done BOOLEAN,
	paid BOOLEAN,
	client_id INT NOT NULL,
	treatment_id INT NOT NULL,
	FOREIGN KEY (client_id) REFERENCES clients(id),
	FOREIGN KEY (treatment_id) REFERENCES treatments(id)
);

DROP TABLE IF EXISTS clients CASCADE;
DROP TABLE IF EXISTS appointments CASCADE;
DROP TABLE IF EXISTS treatments CASCADE;
DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS users_roles CASCADE;
DROP TABLE IF EXISTS roles CASCADE;

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
	duration INT NOT NULL
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

CREATE TABLE users (
	id SERIAL PRIMARY KEY,
	username VARCHAR(50) NOT NULL,
	password VARCHAR(255) NOT NULL,
	enabled BOOLEAN NOT NULL
);

CREATE TABLE users_roles (
	userid SERIAL NOT NULL,
	roleid SERIAL NOT NULL,
	FOREIGN KEY (userid) REFERENCES users(id)
);

CREATE TABLE roles (
	id SERIAL PRIMARY KEY,
	name VARCHAR(50) NOT NULL
);

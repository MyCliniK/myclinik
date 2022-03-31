DROP TABLE IF EXISTS appointments;
DROP TABLE IF EXISTS clients;

CREATE TABLE clients (
	client_id SERIAL PRIMARY KEY,
	first_name VARCHAR(30) NOT NULL,
	last_name VARCHAR(50),
	email VARCHAR(50),
	phone VARCHAR(12) ,
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

DROP TABLE IF EXISTS clients;
CREATE TABLE clients (
	client_id SERIAL PRIMARY KEY,
	first_name VARCHAR(30) NOT NULL,
	last_name VARCHAR(50),
	email VARCHAR(50),
	phone VARCHAR(12) ,
	medical_observations VARCHAR(500),
	observations VARCHAR(500),
);

DROP TABLE IF EXISTS appointments;
CREATE TABLE appointments (
	appointment_id SERIAL PRIMARY KEY,
	date VARCHAR(10),
	hour VARCHAR(10),
	done VARCHAR(50),
	paid VARCHAR(12) ,
	FOREIGN KEY (client_id) REFERENCES clients(id)

);

DROP TABLE IF EXISTS client_appointments;
CREATE TABLE client_appointments (
	appointment_id SERIAL NOT NULL,
	client_id SERIAL NOT NULL ,
	FOREIGN KEY (client_id) REFERENCES client(client_id), 
    FOREIGN KEY (appointment_id REFERENCES appointment(appointment_id),
    UNIQUE (StudentID, ClassID)
);

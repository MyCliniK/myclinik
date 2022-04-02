INSERT INTO clients(first_name, last_name, email, dni) VALUES ('Andres', 'Cienfuegos', 'andres.cienfuegos@gmail.com', '12345678A');
INSERT INTO clients(first_name, last_name, phone, dni) VALUES ('Samu', 'García', '+56987654321', '12345678A');
INSERT INTO clients(first_name, last_name, observations, dni) VALUES ('Claudia', 'Fernández', 'la mejor', '12345678A');
INSERT INTO clients(first_name, last_name, medical_observations, dni) VALUES ('Gonzalo', 'Azcarate', 'no sabe poner comas xd', '12345678A');
INSERT INTO clients(first_name, last_name, dni) VALUES ('Ana', 'Méndez', '12345678A');

INSERT INTO treatments(name, price, duration) VALUES ('Tratamiento 1', '100', '30');
INSERT INTO treatments(name, price, duration) VALUES ('Tratamiento 2', '200', '60');
INSERT INTO treatments(name, price, duration) VALUES ('Tratamiento 3', '300', '90');

INSERT INTO appointments(appointment_date, client_id, treatment_id, done, paid) VALUES ('2020-01-01', 1, 1, false, false);
INSERT INTO appointments(appointment_date, client_id, treatment_id, done, paid) VALUES ('2020-01-02', 1, 2, true, false);
INSERT INTO appointments(appointment_date, client_id, treatment_id, done, paid) VALUES ('2020-01-03', 2, 1, true, true);

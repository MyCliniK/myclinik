INSERT INTO clients(first_name, last_name, email, dni) VALUES ('Andres', 'Cienfuegos', 'andres.cienfuegos@gmail.com', '12345678A');
INSERT INTO clients(first_name, last_name, phone, dni, email) VALUES ('Samu', 'García', '+56987654321', '12345678A', 'samu@mail.com');
INSERT INTO clients(first_name, last_name, observations, dni, email) VALUES ('Claudia', 'Fernández', 'la mejor', '12345678A', 'claudia@mail.com');
INSERT INTO clients(first_name, last_name, medical_observations, dni, email) VALUES ('Gonzalo', 'Azcarate', 'No le gusta la asignatura de ISST', '12345678A', 'gonzalo@mail.com');
INSERT INTO clients(first_name, last_name, dni, email) VALUES ('Ana', 'Méndez', '12345678A', 'ana@mail.com');

INSERT INTO treatments(name, price, duration) VALUES ('Depilación Espalda', '80', '30');
INSERT INTO treatments(name, price, duration) VALUES ('Depilación Axilas', '50', '60');
INSERT INTO treatments(name, price, duration) VALUES ('Depilación Piernas', '100', '90');

INSERT INTO appointments(appointment_date, client_id, treatment_id, done, paid) VALUES ('2020-01-01 19:10', 1, 1, false, false);
INSERT INTO appointments(appointment_date, client_id, treatment_id, done, paid) VALUES ('2020-01-02 13:30', 1, 2, true, false);
INSERT INTO appointments(appointment_date, client_id, treatment_id, done, paid) VALUES ('2020-01-03 14:15', 2, 1, true, true);

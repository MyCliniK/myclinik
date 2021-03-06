INSERT INTO clients(first_name, last_name, email, dni) VALUES ('Andres', 'Cienfuegos', 'andres.cienfuegos@gmail.com', '12345678A');
INSERT INTO clients(first_name, last_name, phone, dni, email) VALUES ('Samu', 'García', '+56987654321', '12345678A', 'samu@mail.com');
INSERT INTO clients(first_name, last_name, observations, dni, email) VALUES ('Claudia', 'Fernández', 'la mejor', '12345678A', 'claudia@mail.com');
INSERT INTO clients(first_name, last_name, medical_observations, dni, email) VALUES ('Gonzalo', 'Azcarate', 'No le gusta la asignatura de ISST', '12345678A', 'gonzalo@mail.com');
INSERT INTO clients(first_name, last_name, dni, email) VALUES ('Ana', 'Méndez', '12345678A', 'ana@mail.com');

INSERT INTO treatments(name, price, duration) VALUES ('Depilación Espalda', '80', '30');
INSERT INTO treatments(name, price, duration) VALUES ('Depilación Axilas', '50', '60');
INSERT INTO treatments(name, price, duration) VALUES ('Depilación Piernas', '100', '90');

INSERT INTO appointments(appointment_date, client_id, treatment_id, done, paid) VALUES ('2022-01-01 19:10', 1, 1, false, false);
INSERT INTO appointments(appointment_date, client_id, treatment_id, done, paid) VALUES ('2022-01-02 13:30', 1, 2, true, false);
INSERT INTO appointments(appointment_date, client_id, treatment_id, done, paid) VALUES ('2022-01-03 14:15', 2, 1, true, true);
INSERT INTO appointments(appointment_date, client_id, treatment_id, done, paid) VALUES ('2022-02-03 14:15', 3, 3, false, true);
INSERT INTO appointments(appointment_date, client_id, treatment_id, done, paid) VALUES ('2022-02-03 14:15', 2, 3, false, true);
INSERT INTO appointments(appointment_date, client_id, treatment_id, done, paid) VALUES ('2022-04-03 14:15', 1, 3, true, true);
INSERT INTO appointments(appointment_date, client_id, treatment_id, done, paid) VALUES ('2022-08-03 14:15', 2, 2, true, true);
INSERT INTO appointments(appointment_date, client_id, treatment_id, done, paid) VALUES ('2022-08-03 14:15', 2, 1, true, true);
INSERT INTO appointments(appointment_date, client_id, treatment_id, done, paid) VALUES ('2022-05-02 14:15', 2, 3, true, true);
INSERT INTO appointments(appointment_date, client_id, treatment_id, done, paid) VALUES ('2022-05-03 14:15', 2, 1, true, true);

INSERT INTO users (username, password, enabled) values ('admin', '$2a$10$T7ftSUbI/bOXZXmjcRYI9uZlZdH0QdC5blKqvX0qpgM.On.fyN7K2', true);
INSERT INTO users (username, password, enabled) values ('ops', '$2a$10$3ZFVnMjGhqX9hPN.6sRahe7rk01PHuGziMvly2aYYDe6irp8CZAym', true);
INSERT INTO users (username, password, enabled) values ('cont', '$2a$10$EQ4iwYaq72wYzMJroxVfEefN3UQMWX0.GrTtOlRFeqIV8behJDb5K', true);

INSERT INTO roles (name) VALUES ('ADMIN');
INSERT INTO roles (name) VALUES ('OPS');
INSERT INTO roles (name) VALUES ('CONT');

INSERT INTO users_roles (userid, roleid) VALUES (1, 1);
INSERT INTO users_roles (userid, roleid) VALUES (2, 2);
INSERT INTO users_roles (userid, roleid) VALUES (3, 3);

DROP TABLE IF EXISTS cars;
DROP TABLE IF EXISTS users;

CREATE TABLE users
(
    id   BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL
);

CREATE TABLE cars
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    make        VARCHAR(50) NOT NULL,
    model       VARCHAR(50) NOT NULL,
    numberplate VARCHAR(6) UNIQUE NOT NULL,
    fk_user BIGINT NOT NULL,
    CONSTRAINT FK_cars FOREIGN KEY (fk_user) REFERENCES users (id)
);

INSERT INTO users (id, name) VALUES (1, 'Teet Järveküla');
INSERT INTO users (id, name) VALUES (2, 'Pille Purk');
INSERT INTO users (id, name) VALUES (3, 'Mati Kaal');
INSERT INTO users (id, name) VALUES (4, 'Külli Kukk');
INSERT INTO users (id, name) VALUES (5, 'Teet Kruus');

INSERT INTO cars (make, model, numberplate, fk_user) VALUES ('Lada', '2101', '123ASD', 1);
INSERT INTO cars (make, model, numberplate, fk_user) VALUES ('Kia', 'Sorento', '534TTT', 1);
INSERT INTO cars (make, model, numberplate, fk_user) VALUES ('Skoda', 'Octavia', '999GLF', 2);
INSERT INTO cars (make, model, numberplate, fk_user) VALUES ('Kia', 'Sorento', '555TFF', 2);
INSERT INTO cars (make, model, numberplate, fk_user) VALUES ('Lada', '2101', '445KKK', 3);
INSERT INTO cars (make, model, numberplate, fk_user) VALUES ('Audi', 'A6', '997HHH', 3);
INSERT INTO cars (make, model, numberplate, fk_user) VALUES ('BMW', '760', '444RRR', 4);
INSERT INTO cars (make, model, numberplate, fk_user) VALUES ('Audi', 'A6', '876OUI', 4);
INSERT INTO cars (make, model, numberplate, fk_user) VALUES ('BMW', '740', '112YUI', 5);
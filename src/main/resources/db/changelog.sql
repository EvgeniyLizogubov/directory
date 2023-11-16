--liquibase formatted sql

--changeset Lizogubov Eugeny:init_schema
CREATE TABLE building
(
    id        INTEGER AUTO_INCREMENT PRIMARY KEY,
    address   CHARACTER VARYING(255) NOT NULL UNIQUE,
    latitude  INTEGER                NOT NULL,
    longitude INTEGER                NOT NULL,
    CONSTRAINT uk_building UNIQUE (address, latitude, longitude)
);

CREATE TABLE company
(
    building_id INTEGER                NOT NULL,
    id          INTEGER auto_increment PRIMARY KEY,
    name        CHARACTER VARYING(200) NOT NULL UNIQUE,
    CONSTRAINT uk_company UNIQUE (building_id, name),
    CONSTRAINT fk_company_building FOREIGN KEY (building_id) REFERENCES building
);

CREATE TABLE company_phone_number
(
    company_id   INTEGER NOT NULL,
    phone_number CHARACTER VARYING(255),
    CONSTRAINT fk_company_phone_number FOREIGN KEY (company_id) REFERENCES company ON DELETE CASCADE
);

CREATE TABLE heading
(
    id   INTEGER AUTO_INCREMENT PRIMARY KEY,
    name CHARACTER VARYING(255) NOT NULL UNIQUE
);

CREATE TABLE tree_path
(
    ancestor   INTEGER NOT NULL,
    descendant INTEGER NOT NULL,
    CONSTRAINT tree_path_pkey PRIMARY KEY (ancestor, descendant),
    CONSTRAINT fk_ancestor FOREIGN KEY (ancestor) REFERENCES heading,
    CONSTRAINT fk_descendant FOREIGN KEY (descendant) REFERENCES heading
);

CREATE TABLE heading_company
(
    heading_id INTEGER NOT NULL,
    company_id INTEGER NOT NULL,
    CONSTRAINT fk_heading_company FOREIGN KEY (company_id) REFERENCES company,
    CONSTRAINT fk_company_heading FOREIGN KEY (heading_id) REFERENCES heading
);

--changeset Lizogubov Eugeny:populate_data
INSERT INTO building (address, latitude, longitude)
VALUES ('ул. Пушкина, д. Колотушкина', 111, 111),
       ('ул. Ленина, д. 666', 222, 222),
       ('ул. Школьная, д. 13', 333, 333);

INSERT INTO company (building_id, name)
VALUES (1, 'Coca-cola'),
       (2, 'Pepsi'),
       (3, 'Nestle'),
       (2, 'Ochakovo');

INSERT INTO company_phone_number
VALUES (1, '123-123-123'),
       (1, '456-456-456'),
       (2, '321-321-321'),
       (2, '654-654-654'),
       (3, '789-789-789'),
       (4, '777-777-777');

INSERT INTO heading (name)
VALUES ('Напитки'), /*1*/
       ('Газированные'), /*2*/
       ('Квасы'), /*3*/
       ('Живые'), /*4*/
       ('Неживые'), /*5*/
       ('Еда'), /*6*/
       ('Полуфабрикаты оптом'), /*7*/
       ('Мясная продукция'), /*8*/
       ('Автомобили'), /*9*/
       ('Грузовые'), /*10*/
       ('Легковые'), /*11*/
       ('Запчасти для подвески'), /*12*/
       ('Шины/Диски'); /*13*/

INSERT INTO tree_path
VALUES (1, 1),
       (1, 2),
       (1, 3),
       (1, 4),
       (1, 5),
       (2, 2),
       (2, 3),
       (2, 4),
       (2, 5),
       (3, 3),
       (3, 4),
       (3, 5),
       (4, 4),
       (5, 5),
       (6, 6),
       (6, 7),
       (6, 8),
       (7, 7),
       (8, 8),
       (9, 9),
       (9, 10),
       (9, 11),
       (9, 12),
       (9, 13),
       (10, 10),
       (11, 11),
       (11, 12),
       (11, 13),
       (12, 12),
       (13, 13);

INSERT INTO heading_company
VALUES (2, 1),
       (2, 2),
       (6, 3),
       (3, 4),
       (5, 4),
       (1, 1);


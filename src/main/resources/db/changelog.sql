--liquibase formatted sql

--changeset Lizogubov Eugeny:init_schema
create table BUILDING
(
    ID      INTEGER auto_increment primary key,
    ADDRESS CHARACTER VARYING(255) not null unique
);

create table COMPANY
(
    BUILDING_ID INTEGER                not null,
    ID          INTEGER auto_increment primary key,
    NAME        CHARACTER VARYING(200) not null unique,
    constraint UK_COMPANY unique (BUILDING_ID, NAME),
    constraint FK_COMPANY_BUILDING foreign key (BUILDING_ID) references BUILDING
);

create table COMPANY_PHONE_NUMBER
(
    COMPANY_ID   INTEGER not null,
    PHONE_NUMBER CHARACTER VARYING(255),
    constraint FK_COMPANY_PHONE_NUMBER foreign key (COMPANY_ID) references COMPANY on delete cascade
);

create table COORDINATES
(
    BUILDING_ID INTEGER not null primary key,
    X           INTEGER not null,
    Y           INTEGER not null,
    constraint FK_COORDINATES_BUILDING foreign key (BUILDING_ID) references BUILDING on delete cascade
);

create table HEADING
(
    HEADING_ID INTEGER auto_increment primary key,
    PARENT_ID  INTEGER,
    NAME       CHARACTER VARYING(255) not null unique,
    constraint FK_HEADING foreign key (PARENT_ID) references HEADING
);

create table HEADING_COMPANY
(
    COMPANY_ID INTEGER not null,
    HEADING_ID INTEGER not null,
    constraint FK_HEADING_COMPANY foreign key (COMPANY_ID) references COMPANY,
    constraint FK_COMPANY_HEADING foreign key (HEADING_ID) references HEADING
);

--changeset Lizogubov Eugeny:populate_data
INSERT INTO BUILDING (ADDRESS)
VALUES ('ул. Пушкина, д. Колотушкина'),
       ('ул. Ленина, д. 666'),
       ('ул. Школьная, д. 13');

INSERT INTO COORDINATES
VALUES (1, 111, 111),
       (2, 222, 222),
       (3, 333, 333);

INSERT INTO COMPANY (BUILDING_ID, NAME)
VALUES (1, 'Coca-cola'),
       (2, 'Pepsi'),
       (3, 'Nestle'),
       (2, 'Ochakovo');

INSERT INTO COMPANY_PHONE_NUMBER
VALUES (1, '123-123-123'),
       (1, '456-456-456'),
       (2, '321-321-321'),
       (2, '654-654-654'),
       (3, '789-789-789'),
       (4, '777-777-777');

INSERT INTO HEADING (PARENT_ID, NAME)
VALUES (null, 'Напитки'), /*1*/
       (1, 'Газированные'), /*2*/
       (2, 'Квасы'), /*3*/
       (3, 'Живые'), /*4*/
       (3, 'Неживые'), /*5*/
       (null, 'Еда'), /*6*/
       (1, 'Полуфабрикаты оптом'), /*7*/
       (1, 'Мясная продукция'), /*8*/
       (null, 'Автомобили'), /*9*/
       (4, 'Грузовые'), /*10*/
       (4, 'Легковые'), /*11*/
       (6, 'Запчасти для подвески'), /*12*/
       (6, 'Шины/Диски'); /*13*/

INSERT INTO HEADING_COMPANY
VALUES (1, 2),
       (2, 2),
       (3, 6),
       (4, 3),
       (4, 5),
       (1, 1);


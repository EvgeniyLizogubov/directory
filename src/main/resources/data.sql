INSERT INTO BUILDING (BUILDING_ADDRESS)
VALUES ('ул. Пушкина, д. Колотушкина'),
       ('ул. Ленина, д. 666');

INSERT INTO COORDINATES
VALUES (1, 111, 111),
       (2, 222, 222);

INSERT INTO COMPANY (BUILDING_ID, NAME)
VALUES (1, 'Coca-cola'),
       (2, 'Pepsi'),
       (1, 'Nestle'),
       (2, 'Ochakovo');

INSERT INTO COMPANY_PHONE_NUMBER
VALUES (1, '123-123-123'),
       (1, '456-456-456'),
       (2, '321-321-321'),
       (2, '654-654-654'),
       (3, '789-789-789'),
       (4, '777-777-777');

INSERT INTO HEADING (PARENT_ID, NAME)
VALUES (null, 'Еда'), /*1*/
       (1, 'Полуфабрикаты оптом'), /*2*/
       (1, 'Мясная продукция'), /*3*/
       (null, 'Автомобили'), /*4*/
       (4, 'Грузовые'), /*5*/
       (4, 'Легковые'), /*6*/
       (6, 'Запчасти для подвески'), /*7*/
       (6, 'Шины/Диски'), /*8*/
       (null, 'Напитки'), /*9*/
       (9, 'Газированные'), /*10*/
       (10, 'Квасы'), /*11*/
       (11, 'Живые'), /*12*/
       (11, 'Неживые'); /*13*/

INSERT INTO HEADING_COMPANY
VALUES (1, 10),
       (2, 10),
       (3, 1),
       (4, 11),
       (4, 13),
       (1, 9);
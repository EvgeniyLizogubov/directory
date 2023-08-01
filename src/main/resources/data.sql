INSERT INTO BUILDING (BUILDING_ADDRESS)
VALUES ('ул. Пушкина, д. Колотушкина'),
       ('ул. Ленина, д. 666');

INSERT INTO COORDINATES
VALUES (1, 111, 111),
       (2, 222, 222);

INSERT INTO COMPANY (BUILDING_ID, NAME)
VALUES (1, 'Coca-cola'),
       (2, 'Pepsi');

INSERT INTO COMPANY_PHONE_NUMBER
VALUES (1, '123-123-123'),
       (1, '456-456-456'),
       (2, '321-321-321'),
       (2, '654-654-654');

INSERT INTO HEADING (PARENT_HEADING_ID, ROOT_HEADING_ID, NAME)
VALUES (null, null, 'Еда'),
       (1, 1, 'Полуфабрикаты оптом'),
       (1, 1, 'Мясная продукция'),
       (null, null, 'Автомобили'),
       (4, 4, 'Грузовые'),
       (4, 4, 'Легковые'),
       (6, 4, 'Запчасти для подвески'),
       (6, 4, 'Шины/Диски'),
       (null, null, 'Напитки'),
       (9, 9, 'Газированные');

INSERT INTO HEADING_COMPANY
VALUES (1, 10),
       (2, 10);
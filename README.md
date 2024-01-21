## Реализация REST API приложения для справочника компаний, зданий, рубрик.

### Описание

> **Компания** - Представляет собой карточку организации в справочнике и должна содержать в себе следующую информацию:
> * Название (Например, ООО “Рога и Копыта”)
> * Несколько номеров телефонов (2-222-222, 3-333-333, 8-923-666-13-13)
> * Находится в одном конкретном здании (Например, Блюхера, 32/1)
> * Может относиться к нескольким рубрикам (Например, “Полуфабрикаты оптом”, “Мясная продукция”)

> **Здание** - Содержит в себе как минимум информацию о конкретном здании, а именно:
> * адрес здания
> * географические координаты его местоположения (в виде долготы/широты)

> **Рубрика** - Рубрики позволяют классифицировать род деятельности фирм в каталоге. Имеют название и могут в древовидном виде вкладываться друг в друга. Пример возможных рубрик и их иерархии:
> * Еда
>   * Полуфабрикаты оптом
>   * Мясная продукция
> * Автомобили
>   * Грузовые
>   * Легковые
>     * Запчасти для подвески
>     * Шины/Диски
> * Спорт

### Функции приложения

Взаимодействие с пользователем происходит посредством HTTP запросов к API серверу. Все ответы представляют собой JSON объекты. Сервер реализует следующие методы:

* выдача всех организаций находящихся в конкретном здании
* список всех организаций, которые относятся к указанной рубрике
* список организаций, которые находятся в заданном радиусе/прямоугольной области относительно указанной точки на карте
* список зданий
* выдача информации об организациях по их идентификаторам
* искать организации по рубрике. Например, поиск по рубрике «Еда», которая находится на первом уровне дерева рубрик, и чтобы нашлись все фирмы, которые относятся к рубрикам лежащим внутри рубрики Еда — Овощи, Полуфабрикаты оптом и т.д.
* поиск организации по названию
* рубрикатор каталога сделать с произвольным уровнем вложенности рубрик друг в друга

##### Стэк:
Spring Boot, Spring MVC, Spring Data JPA, Liquibase, H2, Lombok, Jackson.

### [Документация](http://185.244.210.11:8080)
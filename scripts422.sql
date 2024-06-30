-- Курс 4 - урок 2 - задание 2
-- Проект school hogwarts
-- База данных garage
-- Таблица 1
CREATE TABLE driver (
    id int4 NOT NULL PRIMARY KEY,
    name varchar(50) NOT NULL,
    age int4 CHECK (age>0),
    license boolean DEFAULT false
    )
-- Таблица 2
CREATE TABLE car_type (
    brand varchar(50) UNIQUE NOT NULL,
    model varchar(50) UNIQUE NOT NULL,
    price float4 CHECK (price>0),
    PRIMARY KEY (brand,model)
    )
-- Таблица 3
CREATE TABLE car (
    number varchar(15) UNIQUE NOT NULL,
    brand varchar(50) REFERENCES car_type (brand) ON DELETE CASCADE,
    model varchar(50) REFERENCES car_type (model) ON DELETE CASCADE
    )
-- Таблица 4
CREATE TABLE car_owner (
    car_number varchar(15) NOT NULL REFERENCES car (number) ON DELETE CASCADE,
    driver_id int4 NOT NULL REFERENCES driver (id) ON DELETE CASCADE
    )

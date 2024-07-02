-- Курс 4 - урок 2 - задание 2
-- Проект school hogwarts
-- База данных garage
-- Таблица 1 (водитель)
CREATE TABLE driver (
    id SERIAL PRIMARY KEY,  -- автоинкрементируемый идентификатор
    name varchar(50) NOT NULL,
    age int4 CHECK (age > 0),
    license boolean DEFAULT false
    );
-- Таблица 2 (тип машины, - бренд и модель вместе образуют уникальную комбинацию)
CREATE TABLE car_type (
    brand varchar(50) NOT NULL,
    model varchar(50) NOT NULL,
    price NUMERIC(10, 2) CHECK (price > 0),
    PRIMARY KEY (brand, model)
    );
-- Таблица 3 (машина, - с указанием уникального номера, бренда и модели)
CREATE TABLE car (
    number varchar(15) UNIQUE NOT NULL,
    brand varchar(50) NOT NULL,
    model varchar(50) NOT NULL,
    FOREIGN KEY (brand, model) REFERENCES car_type(brand, model) ON DELETE CASCADE
    );
-- Таблица 4 (владелец машины, - много водителей могут иметь доступ к одной машине)
CREATE TABLE car_owner (
    car_number varchar(15) NOT NULL,
    driver_id INT NOT NULL,
    PRIMARY KEY (car_number, driver_id),
    FOREIGN KEY (car_number) REFERENCES car(number) ON DELETE CASCADE,
    FOREIGN KEY (driver_id) REFERENCES driver(id) ON DELETE CASCADE
    );

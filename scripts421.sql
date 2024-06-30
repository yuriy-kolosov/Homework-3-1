-- Курс 4 - урок 2 - задание 1
-- Проект school hogwarts
-- База данных hogwarts
-- Ограничение 1
ALTER TABLE student ADD CONSTRAINT age_constraint CHECK (age>16)
-- Ограничение 2
ALTER TABLE student ADD CONSTRAINT name_unique UNIQUE (name)
ALTER TABLE student ALTER COLUMN name SET NOT NULL
-- Ограничение 3
ALTER TABLE faculty ADD CONSTRAINT name_color_unique UNIQUE (name,color)
-- Ограничение 4
ALTER TABLE student ALTER COLUMN name SET DEFAULT 20

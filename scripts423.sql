-- Курс 4 - урок 2 - задание 3
-- Проект school hogwarts
-- База данных hogwarts
-- Запрос 1
SELECT s.name AS student_name, s.age AS student_age, f.name AS faculty_name
    FROM student AS s
    LEFT JOIN faculty AS f ON s.faculty_id = f.faculty_id
-- Запрос 2
SELECT s.name AS student_name, s.age AS student_age
    FROM student AS s
    INNER JOIN avatar AS a ON s.id = a.student_id

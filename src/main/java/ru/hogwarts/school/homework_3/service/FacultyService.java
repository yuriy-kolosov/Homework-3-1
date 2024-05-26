package ru.hogwarts.school.homework_3.service;

import ru.hogwarts.school.homework_3.model.Faculty;

import java.util.List;

public interface FacultyService {

    Faculty create(Faculty faculty);

    List<Faculty> readAll();

    List<Faculty> readByColor(String color);

    Faculty read(Long id);

    Faculty update(Long id, Faculty faculty);

    Faculty delete(Long id);

}

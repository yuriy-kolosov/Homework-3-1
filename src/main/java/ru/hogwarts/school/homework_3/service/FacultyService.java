package ru.hogwarts.school.homework_3.service;

import ru.hogwarts.school.homework_3.model.Faculty;
import ru.hogwarts.school.homework_3.model.Student;

import javax.management.InstanceNotFoundException;
import java.util.List;

public interface FacultyService {

    Faculty create(Faculty faculty);

    List<Faculty> readAll();

    List<Faculty> readByName(String name);

    List<Faculty> readByColor(String color);

    Faculty read(Long id);

    String readLongName();

    List<Student> readStudents(Long id);

    Faculty update(Faculty faculty);

    void delete(Long id);

}

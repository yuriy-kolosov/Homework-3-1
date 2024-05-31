package ru.hogwarts.school.homework_3.service;

import ru.hogwarts.school.homework_3.model.Faculty;
import ru.hogwarts.school.homework_3.model.Student;

import java.util.List;

public interface StudentService {

    Student create(Student student);

    List<Student> readAll();

    List<Student> readByAge(int age);

    List<Student> readByAgeBetween(int minAge, int maxAge);

    Student read(Long id);

    Faculty readFaculty(Long id);

    Student update(Student student);

    void delete(Long id);

}

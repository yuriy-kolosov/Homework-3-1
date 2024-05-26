package ru.hogwarts.school.homework_3.service;

import ru.hogwarts.school.homework_3.model.Student;

import java.util.List;

public interface StudentService {

    Student create(Student student);

    List<Student> readAll();

    List<Student> readByAge(int age);

    Student read(Long id);

    Student update(Long id, Student student);

    Student delete(Long id);

}

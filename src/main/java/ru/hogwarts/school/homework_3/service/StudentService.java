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

    int getAllQuantify();

    int countAverageAgeByMethod1();

    int countAverageAgeByMethod2();

    List<Student> getLastFive();

    List<String> getNamesStartingWithA();

    Student update(Student student);

    void delete(Long id);

    void print6Parallel();

    void print6Synchronized();

}

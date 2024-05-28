package ru.hogwarts.school.homework_3.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.hogwarts.school.homework_3.model.Student;
import ru.hogwarts.school.homework_3.repository.StudentRepository;
import ru.hogwarts.school.homework_3.service.impl.StudentServiceImpl;

import java.util.*;

import static ru.hogwarts.school.homework_3.constant.StudentServiceTestConstants.*;

public class StudentServiceImplTest {

    @Autowired
    private final StudentRepository studentRepository;

    public StudentServiceImplTest(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Test
    public void shouldCreateCorrect() {
//        Подготовка
        final StudentServiceImpl studentServiceImplTest = new StudentServiceImpl(studentRepository);
        Student student1 = new Student(1L, STUDENT1_NAME, STUDENT1_AGE);
//        Выполнение
        Assertions.assertEquals(student1, studentServiceImplTest.create(student1));
    }

    @Test
    public void shouldReadCorrect() {
//        Подготовка
        final StudentServiceImpl studentServiceImplTest = new StudentServiceImpl(studentRepository);
        Student student1 = new Student(1L, STUDENT1_NAME, STUDENT1_AGE);
        studentServiceImplTest.create(student1);
//        Выполнение
        Assertions.assertEquals(student1, studentServiceImplTest.read(1L));
    }

    @Test
    public void shouldReadAllCorrect() {
//        Подготовка
        final StudentServiceImpl studentServiceImplTest = new StudentServiceImpl(studentRepository);
        Student student1 = new Student(1L, STUDENT1_NAME, STUDENT1_AGE);
        Student student2 = new Student(1L, STUDENT2_NAME, STUDENT2_AGE);
        Student student3 = new Student(1L, STUDENT3_NAME, STUDENT3_AGE);
        Student student4 = new Student(1L, STUDENT4_NAME, STUDENT4_AGE);
        Student student5 = new Student(1L, STUDENT5_NAME, STUDENT5_AGE);
        studentServiceImplTest.create(student1);
        studentServiceImplTest.create(student2);
        studentServiceImplTest.create(student3);
        studentServiceImplTest.create(student4);
        studentServiceImplTest.create(student5);
        List<Student> students = new ArrayList<>();
        students.add(student1);
        students.add(student2);
        students.add(student3);
        students.add(student4);
        students.add(student5);
//        Выполнение
        Assertions.assertEquals(students, studentServiceImplTest.readAll());
    }

    @Test
    public void shouldReadByAgeCorrect() {
//        Подготовка
        final StudentServiceImpl studentServiceImplTest = new StudentServiceImpl(studentRepository);
        Student student1 = new Student(1L, STUDENT1_NAME, STUDENT1_AGE);
        Student student2 = new Student(1L, STUDENT2_NAME, STUDENT2_AGE);
        Student student3 = new Student(1L, STUDENT3_NAME, STUDENT3_AGE);
        Student student4 = new Student(1L, STUDENT4_NAME, STUDENT4_AGE);
        Student student5 = new Student(1L, STUDENT5_NAME, STUDENT5_AGE);
        studentServiceImplTest.create(student1);
        studentServiceImplTest.create(student2);
        studentServiceImplTest.create(student3);
        studentServiceImplTest.create(student4);
        studentServiceImplTest.create(student5);
        List<Student> students = new ArrayList<>();
        students.add(student2);
        students.add(student4);
//        Выполнение
        Assertions.assertEquals(students, studentServiceImplTest.readByAge(20));
    }

    @Test
    public void shouldUpdateCorrect() {
//        Подготовка
        final StudentServiceImpl studentServiceImplTest = new StudentServiceImpl(studentRepository);
        Student student1 = new Student(1L, STUDENT1_NAME, STUDENT1_AGE);
        Student student2 = new Student(1L, STUDENT2_NAME, STUDENT2_AGE);
        Student student3 = new Student(1L, STUDENT3_NAME, STUDENT3_AGE);
        studentServiceImplTest.create(student1);
        studentServiceImplTest.create(student2);
        studentServiceImplTest.create(student3);
//        Выполнение
        Assertions.assertEquals(student2, studentServiceImplTest.update(student2));
    }

    @Test
    public void shouldDeleteCorrect() {
//        Подготовка
        final StudentServiceImpl studentServiceImplTest = new StudentServiceImpl(studentRepository);
        Student student1 = new Student(1L, STUDENT1_NAME, STUDENT1_AGE);
        Student student2 = new Student(1L, STUDENT2_NAME, STUDENT2_AGE);
        Student student3 = new Student(1L, STUDENT3_NAME, STUDENT3_AGE);
        studentServiceImplTest.create(student1);
        studentServiceImplTest.create(student2);
        studentServiceImplTest.create(student3);
//        Выполнение
        studentServiceImplTest.delete(2L);
        Assertions.assertEquals(student1, studentServiceImplTest.read(2L));
    }

}

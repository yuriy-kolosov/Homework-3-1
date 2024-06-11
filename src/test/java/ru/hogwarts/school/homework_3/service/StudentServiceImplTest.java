package ru.hogwarts.school.homework_3.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.hogwarts.school.homework_3.model.Student;
import ru.hogwarts.school.homework_3.repository.StudentRepository;
import ru.hogwarts.school.homework_3.service.impl.StudentServiceImpl;

import java.util.*;

import static ru.hogwarts.school.homework_3.constant.StudentServiceTestConstants.*;

public class StudentServiceImplTest {

    StudentRepository studentRepository = Mockito.mock(StudentRepository.class);
    final StudentServiceImpl studentServiceImplTest = new StudentServiceImpl(studentRepository);

    @Test
    public void shouldCreateCorrect() {
//        Подготовка
        Student student1 = new Student(1L, STUDENT1_NAME, STUDENT1_AGE);
        Mockito.when(studentRepository.save(student1)).thenReturn(student1);
//        Выполнение
        Assertions.assertEquals(student1, studentServiceImplTest.create(student1));
    }

    @Test
    public void shouldReadCorrect() {
//        Подготовка
        Student student1 = new Student(1L, STUDENT1_NAME, STUDENT1_AGE);
        studentServiceImplTest.create(student1);
        Mockito.when(studentRepository.findById(1L)).thenReturn(Optional.of(student1));
//        Выполнение
        Assertions.assertEquals(student1, studentServiceImplTest.read(1L));
    }

    @Test
    public void shouldReadAllCorrect() {
//        Подготовка
        Student student1 = new Student(1L, STUDENT1_NAME, STUDENT1_AGE);
        Student student2 = new Student(2L, STUDENT2_NAME, STUDENT2_AGE);
        Student student3 = new Student(3L, STUDENT3_NAME, STUDENT3_AGE);
        Student student4 = new Student(4L, STUDENT4_NAME, STUDENT4_AGE);
        Student student5 = new Student(5L, STUDENT5_NAME, STUDENT5_AGE);
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
        Mockito.when(studentRepository.findAll()
                .stream().
                toList()).thenReturn(students);
//        Выполнение
        Assertions.assertEquals(students, studentServiceImplTest.readAll());
    }

    @Test
    public void shouldReadByAgeCorrect() {
//        Подготовка
        Student student1 = new Student(1L, STUDENT1_NAME, STUDENT1_AGE);
        Student student2 = new Student(2L, STUDENT2_NAME, STUDENT2_AGE);
        Student student3 = new Student(3L, STUDENT3_NAME, STUDENT3_AGE);
        Student student4 = new Student(4L, STUDENT4_NAME, STUDENT4_AGE);
        Student student5 = new Student(5L, STUDENT5_NAME, STUDENT5_AGE);
        studentServiceImplTest.create(student1);
        studentServiceImplTest.create(student2);
        studentServiceImplTest.create(student3);
        studentServiceImplTest.create(student4);
        studentServiceImplTest.create(student5);
        List<Student> students = new ArrayList<>();
        students.add(student2);
        students.add(student4);
        Mockito.when(studentRepository.findAll()
                .stream()
                .filter(student -> student.getAge() == 20)
                .toList()).thenReturn(students);
//        Выполнение
        Assertions.assertEquals(students, studentServiceImplTest.readByAge(20));
    }

    @Test
    public void shouldUpdateCorrect() {
//        Подготовка
        Student student1 = new Student(1L, STUDENT1_NAME, STUDENT1_AGE);
        Student student2 = new Student(2L, STUDENT2_NAME, STUDENT2_AGE);
        Student student3 = new Student(3L, STUDENT3_NAME, STUDENT3_AGE);
        studentServiceImplTest.create(student1);
        studentServiceImplTest.create(student2);
        studentServiceImplTest.create(student3);
        Mockito.when(studentRepository.save(student2)).thenReturn(student2);
//        Выполнение
        Assertions.assertEquals(student2, studentServiceImplTest.update(student2));
    }

}

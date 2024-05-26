package ru.hogwarts.school.homework_3.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.hogwarts.school.homework_3.model.Faculty;
import ru.hogwarts.school.homework_3.service.impl.FacultyServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static ru.hogwarts.school.homework_3.constant.FacultyServiceTestConstants.*;

public class FacultyServiceImplTest {

    private final FacultyServiceImpl facultyServiceImplTest = new FacultyServiceImpl();

    @Test
    public void shouldCreateCorrect() {
//        Подготовка
        Faculty faculty1 = new Faculty(1L, FACULTY1_NAME, FACULTY1_COLOR);
//        Выполнение
        Assertions.assertEquals(faculty1, facultyServiceImplTest.create(faculty1));
    }

    @Test
    public void shouldReadCorrect() {
//        Подготовка
        Faculty faculty1 = new Faculty(1L, FACULTY1_NAME, FACULTY1_COLOR);
        facultyServiceImplTest.create(faculty1);
//        Выполнение
        Assertions.assertEquals(faculty1, facultyServiceImplTest.read(1L));
    }

    @Test
    public void shouldReadAllCorrect() {
//        Подготовка
        Faculty faculty1 = new Faculty(1L, FACULTY1_NAME, FACULTY1_COLOR);
        Faculty faculty2 = new Faculty(1L, FACULTY2_NAME, FACULTY1_COLOR);
        Faculty faculty3 = new Faculty(1L, FACULTY3_NAME, FACULTY1_COLOR);
        Faculty faculty4 = new Faculty(1L, FACULTY4_NAME, FACULTY1_COLOR);
        Faculty faculty5 = new Faculty(1L, FACULTY5_NAME, FACULTY5_COLOR);
        facultyServiceImplTest.create(faculty1);
        facultyServiceImplTest.create(faculty2);
        facultyServiceImplTest.create(faculty3);
        facultyServiceImplTest.create(faculty4);
        facultyServiceImplTest.create(faculty5);
        List<Faculty> faculties = new ArrayList<>();
        faculties.add(faculty1);
        faculties.add(faculty2);
        faculties.add(faculty3);
        faculties.add(faculty4);
        faculties.add(faculty5);
//        Выполнение
        Assertions.assertEquals(faculties, facultyServiceImplTest.readAll());
    }

    @Test
    public void shouldReadByColorCorrect() {
//        Подготовка
        Faculty faculty1 = new Faculty(1L, FACULTY1_NAME, FACULTY1_COLOR);
        Faculty faculty2 = new Faculty(1L, FACULTY2_NAME, FACULTY2_COLOR);
        Faculty faculty3 = new Faculty(1L, FACULTY3_NAME, FACULTY3_COLOR);
        Faculty faculty4 = new Faculty(1L, FACULTY4_NAME, FACULTY4_COLOR);
        Faculty faculty5 = new Faculty(1L, FACULTY5_NAME, FACULTY5_COLOR);
        facultyServiceImplTest.create(faculty1);
        facultyServiceImplTest.create(faculty2);
        facultyServiceImplTest.create(faculty3);
        facultyServiceImplTest.create(faculty4);
        facultyServiceImplTest.create(faculty5);
        List<Faculty> faculties = new ArrayList<>();
        faculties.add(faculty1);
        faculties.add(faculty3);
//        Выполнение
        Assertions.assertEquals(faculties, facultyServiceImplTest.readByColor("Белый"));
    }

    @Test
    public void shouldUpdateCorrect() {
//        Подготовка
        Faculty faculty1 = new Faculty(1L, FACULTY1_NAME, FACULTY1_COLOR);
        Faculty faculty2 = new Faculty(1L, FACULTY2_NAME, FACULTY2_COLOR);
        Faculty faculty3 = new Faculty(1L, FACULTY3_NAME, FACULTY3_COLOR);
        facultyServiceImplTest.create(faculty1);
        facultyServiceImplTest.create(faculty2);
        facultyServiceImplTest.create(faculty3);
//        Выполнение
        Assertions.assertEquals(faculty2, facultyServiceImplTest.update(2L, faculty2));
    }

    @Test
    public void shouldDeleteCorrect() {
//        Подготовка
        Faculty faculty1 = new Faculty(1L, FACULTY1_NAME, FACULTY1_COLOR);
        Faculty faculty2 = new Faculty(1L, FACULTY2_NAME, FACULTY2_COLOR);
        Faculty faculty3 = new Faculty(1L, FACULTY3_NAME, FACULTY3_COLOR);
        facultyServiceImplTest.create(faculty1);
        facultyServiceImplTest.create(faculty2);
        facultyServiceImplTest.create(faculty3);
//        Выполнение
        Assertions.assertEquals(faculty2, facultyServiceImplTest.delete(2L));
    }

}

package ru.hogwarts.school.homework_3;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import ru.hogwarts.school.homework_3.controller.FacultyController;
import ru.hogwarts.school.homework_3.model.Faculty;

import static ru.hogwarts.school.homework_3.constant.FacultyControllerTestConstants.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FacultyControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private FacultyController facultyController;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void contextLoadsFacultyControllerTest() throws Exception {
        Assertions.assertThat(facultyController).isNotNull();
    }

    private FacultyControllerTest() {
    }

    @Test
    void postFacultyTest() throws Exception {
        Faculty faculty = new Faculty();
        faculty.setName(NAME1F);
        faculty.setColor(COLOR1);
        Assertions
                .assertThat(this.restTemplate.postForObject("http://localhost:" + port + "/faculty"
                        , faculty
                        , String.class))
                .isNotNull();
    }

    @Test
    void getFacultiesTest() throws Exception {
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/faculty"
                        , String.class))
                .isNotNull();
    }

    @Test
    void getFacultyByIdTest() throws Exception {
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/faculty/"
                        , String.class
                        , ID1F))
                .isNotNull();
    }

    @Test
    void getStudentsByFacultyIdTest() throws Exception {
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/faculty/" + "/students"
                        , String.class
                        , ID1F))
                .isNotNull();
    }

    @Test
    void putFacultyTest() throws Exception {
        Faculty faculty = new Faculty();
        faculty.setId(ID4F);
        faculty.setName(NAME4F);
        faculty.setColor(COLOR4);
        this.restTemplate.put("http://localhost:" + port + "/faculty"
                , faculty);
    }

    @Test
    void deleteFacultyByIdTest() throws Exception {
        this.restTemplate.delete("http://localhost:" + port + "/faculty/" + ID1F);
    }

}

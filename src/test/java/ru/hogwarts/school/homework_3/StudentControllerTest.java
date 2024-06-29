package ru.hogwarts.school.homework_3;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import ru.hogwarts.school.homework_3.controller.StudentController;
import ru.hogwarts.school.homework_3.model.Student;

import static ru.hogwarts.school.homework_3.constant.StudentControllerTestConstants.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StudentControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private StudentController studentController;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void contextLoadsStudentControllerTest() throws Exception {
        Assertions.assertThat(studentController).isNotNull();
    }

    private StudentControllerTest() {
    }

    @Test
    void postStudentTest() throws Exception {
        Student student = new Student();
        student.setName(NAME1S);
        student.setAge(AGE1);
        Assertions
                .assertThat(this.restTemplate.postForObject("http://localhost:" + port + "/student"
                        , student
                        , String.class))
                .isNotNull();
    }

    @Test
    void getStudentsTest() throws Exception {
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/student"
                        , String.class))
                .isNotNull();
    }

    @Test
    void getStudentsAgeTest() throws Exception {
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/student/age"
                        , String.class
                        , AGE1))
                .isNotNull();
    }

    @Test
    void getStudentsAgeBetweenTest() throws Exception {
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/student/age"
                        , String.class
                        , AGE2, AGE3))
                .isNotNull();
    }

    @Test
    void getStudentByIdTest() throws Exception {
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/student/"
                        , String.class
                        , ID1S))
                .isNotNull();
    }

    @Test
    void getFacultyByStudentIdTest() throws Exception {
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/student/" + "/faculty"
                        , String.class
                        , ID1S))
                .isNotNull();
    }

    @Test
    void putStudentTest() throws Exception {
        Student student = new Student();
        student.setId(ID4S);
        student.setName(NAME4S);
        student.setAge(AGE4);
        this.restTemplate.put("http://localhost:" + port + "/student"
                , student);
    }

    @Test
    void deleteStudentByIdTest() throws Exception {
        long studentId = ID1S;
        this.restTemplate.delete("http://localhost:" + port + "/student/" + studentId);
    }

}

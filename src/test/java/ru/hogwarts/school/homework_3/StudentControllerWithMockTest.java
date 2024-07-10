package ru.hogwarts.school.homework_3;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.hogwarts.school.homework_3.configuration.ServerPortProperties;
import ru.hogwarts.school.homework_3.controller.AvatarController;
import ru.hogwarts.school.homework_3.controller.FacultyController;
import ru.hogwarts.school.homework_3.controller.InfoController;
import ru.hogwarts.school.homework_3.controller.StudentController;
import ru.hogwarts.school.homework_3.model.Faculty;
import ru.hogwarts.school.homework_3.model.Student;
import ru.hogwarts.school.homework_3.repository.AvatarRepository;
import ru.hogwarts.school.homework_3.repository.FacultyRepository;
import ru.hogwarts.school.homework_3.repository.StudentRepository;
import ru.hogwarts.school.homework_3.service.impl.*;

import static ru.hogwarts.school.homework_3.constant.StudentControllerWithMockTestConstants.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
public class StudentControllerWithMockTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AvatarRepository avatarRepository;
    @SpyBean
    private AvatarServiceImpl avatarServiceImpl;
    @InjectMocks
    private AvatarController avatarController;

    @MockBean
    private FacultyRepository facultyRepository;
    @SpyBean
    private FacultyServiceImpl facultyServiceImpl;
    @InjectMocks
    private FacultyController facultyController;

    @SpyBean
    private InfoServiceImpl infoServiceImpl;
    @MockBean
    private ServerPortProperties serverPortProperties;
    @SpyBean
    private ServerServiceImpl serverServiceImpl;
    @InjectMocks
    private InfoController infoController;

    @MockBean
    private StudentRepository studentRepository;
    @SpyBean
    private StudentServiceImpl studentServiceImpl;
    @InjectMocks
    private StudentController studentController;

    private StudentControllerWithMockTest() {
    }

    @Test
    void postStudentWithMockTest() throws Exception {

        JSONObject studentObject = new JSONObject();
        studentObject.put("id", ID1S);
        studentObject.put("name", NAME1S);
        studentObject.put("age", AGE1);

        Student student1 = new Student(ID1S, NAME1S, AGE1);

        when(studentRepository.save(any(Student.class))).thenReturn(student1);
        when(studentRepository.findById(any(Long.class))).thenReturn(Optional.of(student1));

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/student")
                        .content(studentObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(ID1S))
                .andExpect(jsonPath("$.name").value(NAME1S))
                .andExpect(jsonPath("$.age").value(AGE1));

    }

    @Test
    void getStudentsWithMockTest() throws Exception {

        Student student1 = new Student(ID1S, NAME1S, AGE1);
        Student student2 = new Student(ID2S, NAME2S, AGE2);
        Student student3 = new Student(ID3S, NAME3S, AGE3);

        List<Student> students = new ArrayList<>();
        students.add(student1);
        students.add(student2);
        students.add(student3);

        JSONObject studentsObject1 = new JSONObject();
        studentsObject1.put("id", ID1S);
        studentsObject1.put("name", NAME1S);
        studentsObject1.put("age", AGE1);

        JSONObject studentsObject2 = new JSONObject();
        studentsObject2.put("id", ID2S);
        studentsObject2.put("name", NAME2S);
        studentsObject2.put("age", AGE2);

        JSONObject studentsObject3 = new JSONObject();
        studentsObject3.put("id", ID3S);
        studentsObject3.put("name", NAME3S);
        studentsObject3.put("age", AGE3);

        JSONArray studentsJsonArray = new JSONArray();
        studentsJsonArray.put(studentsObject1);
        studentsJsonArray.put(studentsObject2);
        studentsJsonArray.put(studentsObject3);

        when(studentRepository.findAll()).thenReturn(students);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(studentsJsonArray.toString()));

    }

    @Test
    void getStudentsByAgeWithMockTest() throws Exception {

        Student student2 = new Student(ID2S, NAME2S, AGE2);
        Student student4 = new Student(ID4S, NAME4S, AGE4);

        List<Student> students = new ArrayList<>();
        students.add(student2);
        students.add(student4);

        JSONObject studentsObject2 = new JSONObject();
        studentsObject2.put("id", ID2S);
        studentsObject2.put("name", NAME2S);
        studentsObject2.put("age", AGE2);

        JSONObject studentsObject4 = new JSONObject();
        studentsObject4.put("id", ID4S);
        studentsObject4.put("name", NAME4S);
        studentsObject4.put("age", AGE4);

        JSONArray studentsJsonArray = new JSONArray();
        studentsJsonArray.put(studentsObject2);
        studentsJsonArray.put(studentsObject4);

        when(studentRepository.findAll()).thenReturn(students);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student/age?age=" + AGE2)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(studentsJsonArray.toString()));

    }

    @Test
    void getStudentsByAgeBetweenWithMockTest() throws Exception {

        Student student1 = new Student(ID1S, NAME1S, AGE1);
        Student student3 = new Student(ID3S, NAME3S, AGE3);

        List<Student> students = new ArrayList<>();
        students.add(student1);
        students.add(student3);

        JSONObject studentsObject1 = new JSONObject();
        studentsObject1.put("id", ID1S);
        studentsObject1.put("name", NAME1S);
        studentsObject1.put("age", AGE1);

        JSONObject studentsObject3 = new JSONObject();
        studentsObject3.put("id", ID3S);
        studentsObject3.put("name", NAME3S);
        studentsObject3.put("age", AGE3);

        JSONArray studentsJsonArray = new JSONArray();
        studentsJsonArray.put(studentsObject1);
        studentsJsonArray.put(studentsObject3);

        when(studentRepository.findByAgeBetween(AGE1, AGE3)).thenReturn(students);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student/age/between?minAge=" + AGE1 + "&maxAge=" + AGE3)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(studentsJsonArray.toString()));

    }

    @Test
    void getStudentByIdWithMockTest() throws Exception {

        Student student1 = new Student(ID1S, NAME1S, AGE1);

        when(studentRepository.findById(any(Long.class))).thenReturn(Optional.of(student1));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student/" + ID1S)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(ID1S))
                .andExpect(jsonPath("$.name").value(NAME1S))
                .andExpect(jsonPath("$.age").value(AGE1));

    }

    @Test
    void getStudentFacultyWithMockTest() throws Exception {

        Student student1 = new Student(ID1S, NAME1S, AGE1);
        Faculty faculty1 = new Faculty(ID1F, NAME1F, COLOR1);

        when(studentRepository.findById(ID1S)).thenReturn(Optional.of(student1));
        when(studentServiceImpl.readFaculty(ID1S)).thenReturn(faculty1);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student/" + ID1S + "/faculty")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(ID1F))
                .andExpect(jsonPath("$.name").value(NAME1F))
                .andExpect(jsonPath("$.color").value(COLOR1));

    }

    @Test
    void getStudentsAllQuantifyWithMockTest() throws Exception {

        when(studentRepository.countAll()).thenReturn(ALL_QUANTITY);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student/count"))
                .andExpect(status().isOk())
                .andExpect(content().string(String.valueOf(ALL_QUANTITY)));

    }

    @Test
    void countStudentAverageAgeWithMockTest1() throws Exception {

        when(studentRepository.countAvgAge()).thenReturn(AVERAGE_AGE);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student/count/age/average/method1"))
                .andExpect(status().isOk())
                .andExpect(content().string(String.valueOf(AVERAGE_AGE)));

    }

    @Test
    void countStudentAverageAgeWithMockTest2() throws Exception {

        Student student1 = new Student(ID1S, NAME1S, AGE1);
        Student student2 = new Student(ID2S, NAME2S, AGE2);
        Student student3 = new Student(ID3S, NAME3S, AGE3);
        Student student4 = new Student(ID4S, NAME4S, AGE4);
        Student student5 = new Student(ID5S, NAME5S, AGE5);

        List<Student> students = new ArrayList<>();
        students.add(student1);
        students.add(student2);
        students.add(student3);
        students.add(student4);
        students.add(student5);

        when(studentRepository.findAll()).thenReturn(students);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student/count/age/average/method2"))
                .andExpect(status().isOk())
                .andExpect(content().string(String.valueOf(AVERAGE_AGE)));

    }

    @Test
    void getStudentsLastFiveWithMockTest() throws Exception {

        Student student1 = new Student(ID1S, NAME1S, AGE1);
        Student student2 = new Student(ID2S, NAME2S, AGE2);
        Student student3 = new Student(ID3S, NAME3S, AGE3);
        Student student4 = new Student(ID4S, NAME4S, AGE4);
        Student student5 = new Student(ID5S, NAME5S, AGE5);

        List<Student> students = new ArrayList<>();
        students.add(student5);
        students.add(student4);
        students.add(student3);
        students.add(student2);
        students.add(student1);

        JSONObject studentsObject1 = new JSONObject();
        studentsObject1.put("id", ID1S);
        studentsObject1.put("name", NAME1S);
        studentsObject1.put("age", AGE1);

        JSONObject studentsObject2 = new JSONObject();
        studentsObject2.put("id", ID2S);
        studentsObject2.put("name", NAME2S);
        studentsObject2.put("age", AGE2);

        JSONObject studentsObject3 = new JSONObject();
        studentsObject3.put("id", ID3S);
        studentsObject3.put("name", NAME3S);
        studentsObject3.put("age", AGE3);

        JSONObject studentsObject4 = new JSONObject();
        studentsObject4.put("id", ID4S);
        studentsObject4.put("name", NAME4S);
        studentsObject4.put("age", AGE4);

        JSONObject studentsObject5 = new JSONObject();
        studentsObject5.put("id", ID5S);
        studentsObject5.put("name", NAME5S);
        studentsObject5.put("age", AGE5);

        JSONArray studentsJsonArray = new JSONArray();
        studentsJsonArray.put(studentsObject5);
        studentsJsonArray.put(studentsObject4);
        studentsJsonArray.put(studentsObject3);
        studentsJsonArray.put(studentsObject2);
        studentsJsonArray.put(studentsObject1);

        when(studentRepository.findLastFive()).thenReturn(students);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student/last/5")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(studentsJsonArray.toString()));

    }

    @Test
    void getStudentsNamesStartingWithAWithMockTest() throws Exception {

        Student student1 = new Student(ID1S, NAME1S, AGE1);
        Student student2 = new Student(ID2S, NAME2S, AGE2);
        Student student3 = new Student(ID3S, NAME3S, AGE3);
        Student student4 = new Student(ID4S, NAME4S, AGE4);
        Student student5 = new Student(ID5S, NAME5S, AGE5);

        List<Student> students = new ArrayList<>();
        students.add(student5);
        students.add(student4);
        students.add(student3);
        students.add(student2);
        students.add(student1);

        List<String> name = List.of(NAME1S_UPCASE);

        when(studentRepository.findAll()).thenReturn(students);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student/names/A")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(name.toString()));

    }

    @Test
    void putStudentWithMockTest() throws Exception {

        JSONObject studentObject = new JSONObject();
        studentObject.put("id", ID1S);
        studentObject.put("name", NAME1S);
        studentObject.put("age", AGE1);

        Student student = new Student(ID1S, NAME1S, AGE1);

        when(studentRepository.save(any(Student.class))).thenReturn(student);
        when(studentRepository.findById(any(Long.class))).thenReturn(Optional.of(student));

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/student")
                        .content(studentObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(ID1S))
                .andExpect(jsonPath("$.name").value(NAME1S))
                .andExpect(jsonPath("$.age").value(AGE1));

    }

    @Test
    void deleteStudentWithMockTest() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/student/" + ID1S)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

}

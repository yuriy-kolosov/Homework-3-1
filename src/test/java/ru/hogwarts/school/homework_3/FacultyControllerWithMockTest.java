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
import ru.hogwarts.school.homework_3.controller.AvatarController;
import ru.hogwarts.school.homework_3.controller.FacultyController;
import ru.hogwarts.school.homework_3.controller.StudentController;
import ru.hogwarts.school.homework_3.model.Faculty;
import ru.hogwarts.school.homework_3.model.Student;
import ru.hogwarts.school.homework_3.repository.AvatarRepository;
import ru.hogwarts.school.homework_3.repository.FacultyRepository;
import ru.hogwarts.school.homework_3.repository.StudentRepository;
import ru.hogwarts.school.homework_3.service.impl.AvatarServiceImpl;
import ru.hogwarts.school.homework_3.service.impl.FacultyServiceImpl;
import ru.hogwarts.school.homework_3.service.impl.StudentServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static ru.hogwarts.school.homework_3.constant.FacultyControllerWithMockTestConstants.*;

@WebMvcTest
public class FacultyControllerWithMockTest {

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

    @MockBean
    private StudentRepository studentRepository;
    @SpyBean
    private StudentServiceImpl studentServiceImpl;
    @InjectMocks
    private StudentController studentController;

    @Test
    void postFacultyWithMockTest() throws Exception {

        JSONObject facultyObject = new JSONObject();
        facultyObject.put("id", ID1F);
        facultyObject.put("name", NAME1F);
        facultyObject.put("color", COLOR1);

        Faculty faculty1 = new Faculty(ID1F, NAME1F, COLOR1);

        when(facultyRepository.save(any(Faculty.class))).thenReturn(faculty1);
        when(facultyRepository.findById(any(Long.class))).thenReturn(Optional.of(faculty1));

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/faculty")
                        .content(facultyObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(ID1F))
                .andExpect(jsonPath("$.name").value(NAME1F))
                .andExpect(jsonPath("$.color").value(COLOR1));

    }

    @Test
    void getFacultiesWithMockTest() throws Exception {

        Faculty faculty1 = new Faculty(ID1F, NAME1F, COLOR1);
        Faculty faculty2 = new Faculty(ID2F, NAME2F, COLOR2);

        List<Faculty> facultiesByName = new ArrayList<>();
        facultiesByName.add(faculty1);

        List<Faculty> facultiesByColor = new ArrayList<>();
        facultiesByColor.add(faculty2);

        JSONObject facultiesObjectByName = new JSONObject();
        facultiesObjectByName.put("id", ID1F);
        facultiesObjectByName.put("name", NAME1F);
        facultiesObjectByName.put("color", COLOR1);

        JSONObject facultiesObjectByColor = new JSONObject();
        facultiesObjectByColor.put("id", ID2F);
        facultiesObjectByColor.put("name", NAME2F);
        facultiesObjectByColor.put("color", COLOR2);

        JSONArray facultiesJsonArrayByName = new JSONArray();
        facultiesJsonArrayByName.put(facultiesObjectByName);

        JSONArray facultiesJsonArrayByColor = new JSONArray();
        facultiesJsonArrayByColor.put(facultiesObjectByColor);

        when(facultyRepository.findByNameIgnoreCase(NAME1F)).thenReturn(facultiesByName);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty?name=" + NAME1F)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(facultiesJsonArrayByName.toString()));

        when(facultyRepository.findByColorIgnoreCase(COLOR2)).thenReturn(facultiesByColor);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty?color=" + COLOR2)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(facultiesJsonArrayByColor.toString()));

    }

    @Test
    void getFacultyByIdWithMockTest() throws Exception {

        Faculty faculty1 = new Faculty(ID1F, NAME1F, COLOR1);

        when(facultyRepository.findById(ID1F)).thenReturn(Optional.of(faculty1));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty/" + ID1F)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(ID1F))
                .andExpect(jsonPath("$.name").value(NAME1F))
                .andExpect(jsonPath("$.color").value(COLOR1));

    }

    @Test
    void getStudentsByFacultyWithMockTest() throws Exception {

        Faculty faculty1 = new Faculty(ID1F, NAME1F, COLOR1);
        Student student1 = new Student(ID1S, NAME1S, AGE1);

        List<Student> students = new ArrayList<>();
        students.add(student1);

        JSONObject studentsObject = new JSONObject();
        studentsObject.put("id", ID1S);
        studentsObject.put("name", NAME1S);
        studentsObject.put("age", AGE1);

        JSONArray studentsJsonArray = new JSONArray();
        studentsJsonArray.put(studentsObject);

        when(facultyRepository.findById(ID1F)).thenReturn(Optional.of(faculty1));
        when(facultyServiceImpl.readStudents(ID1F)).thenReturn(students);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty/" + ID1F + "/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(studentsJsonArray.toString()));

    }

    @Test
    void putFacultyWithMockTest() throws Exception {

        JSONObject facultyObject = new JSONObject();
        facultyObject.put("id", ID1F);
        facultyObject.put("name", NAME1F);
        facultyObject.put("color", COLOR1);

        Faculty faculty1 = new Faculty(ID1F, NAME1F, COLOR1);

        when(facultyRepository.save(any(Faculty.class))).thenReturn(faculty1);
        when(facultyRepository.findById(any(Long.class))).thenReturn(Optional.of(faculty1));

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/faculty")
                        .content(facultyObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(ID1F))
                .andExpect(jsonPath("$.name").value(NAME1F))
                .andExpect(jsonPath("$.color").value(COLOR1));

    }

    @Test
    void deleteFacultyWithMockTest() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/faculty/" + ID1F)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

}

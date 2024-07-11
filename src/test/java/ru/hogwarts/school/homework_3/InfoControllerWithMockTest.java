package ru.hogwarts.school.homework_3;

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
import ru.hogwarts.school.homework_3.repository.AvatarRepository;
import ru.hogwarts.school.homework_3.repository.FacultyRepository;
import ru.hogwarts.school.homework_3.repository.StudentRepository;
import ru.hogwarts.school.homework_3.service.impl.*;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.hogwarts.school.homework_3.constant.InfoControllerWithMockTestConstants.JSON_PORT;
import static ru.hogwarts.school.homework_3.constant.ServerServiceImplTestConstants.PORT;

@WebMvcTest
public class InfoControllerWithMockTest {

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

    public InfoControllerWithMockTest() {
    }

    @Test
    void getServerPortWithMockTest() throws Exception {

        when(serverPortProperties.getPort()).thenReturn(PORT);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/port")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(JSON_PORT));

    }

}

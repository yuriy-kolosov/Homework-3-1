package ru.hogwarts.school.homework_3;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import ru.hogwarts.school.homework_3.controller.InfoController;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class InfoControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private InfoController infoController;

    @Autowired
    private TestRestTemplate restTemplate;

    public InfoControllerTest() {
    }

    @Test
    void getServerPortTest() throws Exception {
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/port"
                        , String.class))
                .isNotNull();
    }

}

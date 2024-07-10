package ru.hogwarts.school.homework_3.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.hogwarts.school.homework_3.configuration.ServerPortProperties;
import ru.hogwarts.school.homework_3.service.impl.ServerServiceImpl;

import static ru.hogwarts.school.homework_3.constant.ServerServiceImplTestConstants.*;

public class ServerServiceImplTest {

    final ServerPortProperties serverPortPropertiesTest = Mockito.mock(ServerPortProperties.class);

    public ServerServiceImplTest() {
    }

    @Test
    public void shouldGetServerPortCorrect() {
//        Подготовка
        final ServerServiceImpl serverServiceImplTest = new ServerServiceImpl(serverPortPropertiesTest);
        Mockito.when(serverPortPropertiesTest.getPort()).thenReturn(PORT);
//        Выполнение
        Assertions.assertEquals(PORT, serverServiceImplTest.serverPort());
    }

}

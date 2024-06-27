package ru.hogwarts.school.homework_3.service;

import org.mockito.Mockito;
import org.springframework.data.domain.PageRequest;
import ru.hogwarts.school.homework_3.model.Avatar;
import ru.hogwarts.school.homework_3.model.Student;
import ru.hogwarts.school.homework_3.repository.AvatarRepository;
import ru.hogwarts.school.homework_3.repository.StudentRepository;
import ru.hogwarts.school.homework_3.service.impl.AvatarServiceImpl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static ru.hogwarts.school.homework_3.constant.StudentServiceTestConstants.*;

public class AvatarServiceImplTest {

    final AvatarRepository avatarRepository = Mockito.mock(AvatarRepository.class);
    final StudentRepository studentRepository = Mockito.mock(StudentRepository.class);

    private AvatarServiceImplTest() {
    }

    @Test
    public void shouldFindAvatarCorrect() {
//        Подготовка

        final AvatarServiceImpl avatarServiceImplTest = new AvatarServiceImpl(avatarRepository, studentRepository);

        Student student1 = new Student(1L, STUDENT1_NAME, STUDENT1_AGE);
        Avatar avatar1 = new Avatar(1L, "F:/Course3", 4096L, "jpg", student1);
        Mockito.when(avatarRepository.findByStudentId(1L)).thenReturn(Optional.of(avatar1));
//        Выполнение
        Assertions.assertEquals(avatar1, avatarServiceImplTest.findAvatar(1L));
    }

    @Test
    public void shouldDownloadAvatarDataByPages() {
//        Подготовка

        final AvatarServiceImpl avatarServiceImplTest = new AvatarServiceImpl(avatarRepository, studentRepository);

        Student student1 = new Student(1L, STUDENT1_NAME, STUDENT1_AGE);
        byte[] data1 = new byte[10];
        Avatar avatar1 = new Avatar(1L, "F:/Course3", 4096L, "jpg", data1, student1);
        List<Avatar> avatars1 = new ArrayList<>();
        avatars1.add(avatar1);
        PageRequest avatarPages = PageRequest.of(1, 1);
        Mockito.when(avatarRepository.findPages(avatarPages)).thenReturn(avatars1);
//        Выполнение
        org.assertj.core.api.Assertions.assertThat(avatarServiceImplTest
                        .findAvatarsByPages(1, 1))
                .isNotNull();
    }

}

package ru.hogwarts.school.homework_3.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.hogwarts.school.homework_3.model.Avatar;
import ru.hogwarts.school.homework_3.model.Student;
import ru.hogwarts.school.homework_3.repository.AvatarRepository;
import ru.hogwarts.school.homework_3.repository.StudentRepository;
import ru.hogwarts.school.homework_3.service.impl.AvatarServiceImpl;

import java.util.Optional;

import static ru.hogwarts.school.homework_3.constant.StudentServiceTestConstants.STUDENT1_AGE;
import static ru.hogwarts.school.homework_3.constant.StudentServiceTestConstants.STUDENT1_NAME;

public class AvatarServiceImplTest {

    AvatarRepository avatarRepository = Mockito.mock(AvatarRepository.class);
    StudentRepository studentRepository = Mockito.mock(StudentRepository.class);
    final AvatarServiceImpl avatarServiceImplTest = new AvatarServiceImpl(avatarRepository, studentRepository);

    @Test
    public void shouldFindAvatarCorrect() {
//        Подготовка
        Student student1 = new Student(1L, STUDENT1_NAME, STUDENT1_AGE);
        Avatar avatar1 = new Avatar(1L, "F:/Course3", 4096L, "jpg", student1);
        Mockito.when(avatarRepository.findByStudentId(1L)).thenReturn(Optional.of(avatar1));
//        Выполнение
        Assertions.assertEquals(avatar1, avatarServiceImplTest.findAvatar(1L));
    }

}

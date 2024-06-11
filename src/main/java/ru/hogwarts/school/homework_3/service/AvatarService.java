package ru.hogwarts.school.homework_3.service;

import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.homework_3.model.Avatar;

import java.io.IOException;

public interface AvatarService {

    void upload(Long studentId, MultipartFile avatarFile) throws IOException;

    Avatar findAvatar(Long studentId);

}

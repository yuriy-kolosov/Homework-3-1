package ru.hogwarts.school.homework_3.service.impl;

import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.homework_3.model.Avatar;
import ru.hogwarts.school.homework_3.model.Student;
import ru.hogwarts.school.homework_3.repository.AvatarRepository;
import ru.hogwarts.school.homework_3.repository.StudentRepository;
import ru.hogwarts.school.homework_3.service.AvatarService;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

@Service
@Transactional
public class AvatarServiceImpl implements AvatarService {

    @Value("${path.to.avatars.folder}")
    private String avatarDir;

    private final AvatarRepository avatarRepository;
    private final StudentRepository studentRepository;

    public AvatarServiceImpl(AvatarRepository avatarRepository, StudentRepository studentRepository) {
        this.avatarRepository = avatarRepository;
        this.studentRepository = studentRepository;
    }

    Logger logger = LoggerFactory.getLogger(AvatarServiceImpl.class);

    public void upload(Long studentId, MultipartFile avatarFile) throws IOException {
        Student student = studentRepository.findById(studentId).orElseThrow();
        Path filePath = Path.of(avatarDir, studentId + "." + getExtensions(avatarFile.getOriginalFilename()));
        Files.createDirectories(filePath.getParent());
        Files.deleteIfExists(filePath);
        try (
                InputStream is = avatarFile.getInputStream();
                OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
                BufferedInputStream bis = new BufferedInputStream(is, 1024);
                BufferedOutputStream bos = new BufferedOutputStream(os, 1024);
        ) {
            logger.info("\"Upload\" avatar method was invoke... to file...");
            bis.transferTo(bos);
        }
        Avatar avatar = findAvatar(studentId);
        avatar.setStudent(student);
        avatar.setFilePath(filePath.toString());
        avatar.setFileSize(avatarFile.getSize());
        avatar.setMediaType(avatarFile.getContentType());
        avatar.setData(avatarFile.getBytes());
        logger.info("\"Upload\" avatar method was invoke... to database...");
        avatarRepository.save(avatar);
    }

    private String getExtensions(String fileName) {
        logger.debug("\"Get extension\" avatar method was invoke...");
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    public Avatar findAvatar(Long studentId) {
        logger.info("\"Find\" avatar method was invoke...");
        return avatarRepository.findByStudentId(studentId).orElse(new Avatar());
    }

    public List<Avatar> findAvatarsByPages(int pageNumber, int pageAmount) {
        logger.info("\"Find by pages\" avatar method was invoke...");
        PageRequest avatarPages = PageRequest.of(pageNumber - 1, pageAmount);
        return avatarRepository.findPages(avatarPages);
    }

}

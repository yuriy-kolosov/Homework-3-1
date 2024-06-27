package ru.hogwarts.school.homework_3.repository;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.school.homework_3.model.Avatar;

import java.util.List;
import java.util.Optional;

public interface AvatarRepository extends JpaRepository<Avatar, Long> {

    Optional<Avatar> findByStudentId(Long studentId);

    default List<Avatar> findPages(PageRequest avatarPages) {
        return findAll(avatarPages).getContent();
    }

}

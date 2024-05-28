package ru.hogwarts.school.homework_3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.school.homework_3.model.Faculty;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {
}

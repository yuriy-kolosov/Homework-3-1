package ru.hogwarts.school.homework_3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.school.homework_3.model.Faculty;

import java.util.List;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {

    List<Faculty> findByNameIgnoreCase(String name);

    List<Faculty> findByColorIgnoreCase(String color);

}

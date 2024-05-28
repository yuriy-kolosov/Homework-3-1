package ru.hogwarts.school.homework_3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.school.homework_3.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
}

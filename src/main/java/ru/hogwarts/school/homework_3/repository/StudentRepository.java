package ru.hogwarts.school.homework_3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.school.homework_3.model.Student;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findByAgeBetween(int minAge, int maxAge);

}

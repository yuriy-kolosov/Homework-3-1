package ru.hogwarts.school.homework_3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.hogwarts.school.homework_3.model.Student;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findByAgeBetween(int minAge, int maxAge);

    @Query(value = "SELECT COUNT(*) AS Count_students FROM student"
            , nativeQuery = true)
    int countAll();

    @Query(value = "SELECT AVG(age) AS Avg_age_students FROM student"
            , nativeQuery = true)
    int countAvgAge();

    @Query(value = "SELECT * FROM student ORDER BY id DESC LIMIT 5"
            , nativeQuery = true)
    List<Student> findLastFive();

}

package ru.hogwarts.school.homework_3.service.impl;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.homework_3.model.Student;
import ru.hogwarts.school.homework_3.service.StudentService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StudentServiceImpl implements StudentService {

    private Map<Long, Student> studentMap = new HashMap<>();
    private Long studentMapId = 0L;

    @PostConstruct
    public void init() {
        create(new Student("Ivan", 20));
        create(new Student("Petr", 22));
        create(new Student("Sidor", 20));
        create(new Student("Egor", 23));
        create(new Student("Kirill", 22));
    }

    @Override
    public Student create(Student student) {
        student.setId(++studentMapId);
        studentMap.put(studentMapId, student);
        return student;
    }

    @Override
    public List<Student> readAll() {
        return studentMap.values()
                .stream().toList();
    }

    @Override
    public List<Student> readByAge(int age) {
        return studentMap.values()
                .stream()
                .filter(student -> student.getAge() == age)
                .toList();
    }

    @Override
    public Student read(Long id) {
        return studentMap.get(id);
    }

    @Override
    public Student update(Long id, Student student) {
        if (!studentMap.containsKey(id)) {
            return null;
        }
        studentMap.put(id, student);
        return student;
    }

    @Override
    public Student delete(Long id) {
        return studentMap.remove(id);
    }

}

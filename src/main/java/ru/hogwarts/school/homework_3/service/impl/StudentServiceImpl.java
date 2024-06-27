package ru.hogwarts.school.homework_3.service.impl;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.homework_3.model.Faculty;
import ru.hogwarts.school.homework_3.model.Student;
import ru.hogwarts.school.homework_3.repository.StudentRepository;
import ru.hogwarts.school.homework_3.service.StudentService;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @PostConstruct
    public void init() {
        create(new Student("Ivan", 17));
        create(new Student("Petr", 22));
        create(new Student("Sidor", 20));
        create(new Student("Egor", 23));
        create(new Student("Fedor", 21));
        create(new Student("Pavel", 19));
        create(new Student("Victor", 18));
        create(new Student("Kirill", 21));
        create(new Student("Semen", 23));
        create(new Student("Denis", 22));
    }

    @Override
    public Student create(Student student) {
        student.setId(null);
        return studentRepository.save(student);
    }

    @Override
    public List<Student> readAll() {
        return studentRepository.findAll()
                .stream().toList();
    }

    @Override
    public List<Student> readByAge(int age) {
        return studentRepository.findAll()
                .stream()
                .filter(student -> student.getAge() == age)
                .toList();
    }

    @Override
    public List<Student> readByAgeBetween(int minAge, int maxAge) {
        return studentRepository.findByAgeBetween(minAge, maxAge);
    }

    @Override
    public Student read(Long id) {
        return studentRepository.findById(id).get();
    }

    @Override
    public Faculty readFaculty(Long id) {
        Student student = studentRepository.findById(id).get();
        return student.getFaculty();
    }

    @Override
    public int getAllQuantify() {
        return studentRepository.countAll();
    }

    @Override
    public int countAverageAge() {
        return studentRepository.countAvgAge();
    }

    @Override
    public List<Student> getLastFive() {
        return studentRepository.findLastFive();
    }

    @Override
    public Student update(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public void delete(Long id) {
        studentRepository.deleteById(id);
    }

}

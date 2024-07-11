package ru.hogwarts.school.homework_3.service.impl;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);

    @PostConstruct
    public void init() {
        logger.debug("Next ten \"Create\" student methods for application testing was invoke...");
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
        create(new Student("Anna", 20));
        create(new Student("Alexandra", 22));

        System.out.println();
        System.out.println(System.getProperty("java.runtime.version"));
        System.out.println();

    }

    @Override
    public Student create(Student student) {
        logger.info("\"Create\" student method was invoke...");
        student.setId(null);
        return studentRepository.save(student);
    }

    @Override
    public List<Student> readAll() {
        logger.info("\"Read all\" student method was invoke...");
        return studentRepository.findAll()
                .stream().toList();
    }

    @Override
    public List<Student> readByAge(int age) {
        logger.info("\"Read by age\" student method was invoke...");
        return studentRepository.findAll()
                .stream()
                .filter(student -> student.getAge() == age)
                .toList();
    }

    @Override
    public List<Student> readByAgeBetween(int minAge, int maxAge) {
        logger.info("\"Read by age between\" student method was invoke...");
        return studentRepository.findByAgeBetween(minAge, maxAge);
    }

    @Override
    public Student read(Long id) {
        logger.info("\"Read by ID\" student method was invoke...");
        return studentRepository.findById(id).get();
    }

    @Override
    public Faculty readFaculty(Long id) {
        logger.info("\"Read faculty by ID\" student method was invoke...");
        Student student = studentRepository.findById(id).get();
        return student.getFaculty();
    }

    @Override
    public int getAllQuantify() {
        logger.info("\"Get all quantity\" student method was invoke...");
        return studentRepository.countAll();
    }

    @Override
    public int countAverageAgeByMethod1() {
        logger.info("\"Get average age\" student method 1 was invoke...");
        return studentRepository.countAvgAge();
    }

    @Override
    public int countAverageAgeByMethod2() {
        logger.info("\"Get average age\" student method 2 was invoke...");
        int averageAge = 0;
        int countStudents = (int) studentRepository.findAll().size();
        int sumStudentsAge = studentRepository.findAll().stream()
                .map(Student::getAge)
                .reduce(Integer::sum)
                .orElse(0);
        if (countStudents != 0) {
            averageAge = (int) sumStudentsAge / countStudents;
        }
        return averageAge;
    }

    @Override
    public List<Student> getLastFive() {
        logger.info("\"Get last five\" student method was invoke...");
        return studentRepository.findLastFive();
    }

    @Override
    public List<String> getNamesStartingWithA() {
        logger.info("\"Get names starting with A\" student method was invoke...");
        return studentRepository.findAll().stream()
                .map(Student::getName)
                .map(String::toUpperCase)
                .filter(name -> name.startsWith("A"))
                .toList();
    }

    @Override
    public Student update(Student student) {
        logger.info("\"Update\" student method was invoke...");
        return studentRepository.save(student);
    }

    @Override
    public void delete(Long id) {
        logger.info("\"Delete\" student method was invoke...");
        studentRepository.deleteById(id);
    }

}

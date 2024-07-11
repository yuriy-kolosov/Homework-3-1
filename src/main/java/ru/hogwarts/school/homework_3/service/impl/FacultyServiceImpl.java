package ru.hogwarts.school.homework_3.service.impl;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.homework_3.model.Faculty;
import ru.hogwarts.school.homework_3.model.Student;
import ru.hogwarts.school.homework_3.repository.FacultyRepository;
import ru.hogwarts.school.homework_3.service.FacultyService;

import java.util.Comparator;
import java.util.List;

@Service
public class FacultyServiceImpl implements FacultyService {

    private final FacultyRepository facultyRepository;

    public FacultyServiceImpl(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    Logger logger = LoggerFactory.getLogger(FacultyServiceImpl.class);

    @PostConstruct
    public void init() {
        logger.debug("Next five \"Create\" faculty methods for application testing was invoke...");
        create(new Faculty("Cybernetics", "white"));
        create(new Faculty("Automation", "yellow"));
        create(new Faculty("Physics", "green"));
        create(new Faculty("Technique", "blue"));
        create(new Faculty("Chemistry", "red"));
    }

    @Override
    public Faculty create(Faculty faculty) {
        logger.info("\"Create\" faculty method for application testing was invoke...");
        faculty.setId(null);
        return facultyRepository.save(faculty);
    }

    @Override
    public List<Faculty> readAll() {
        logger.info("\"Read all\" faculty method for application testing was invoke...");
        return facultyRepository.findAll()
                .stream().toList();
    }

    @Override
    public List<Faculty> readByName(String name) {
        logger.info("\"Read by name\" faculty method for application testing was invoke...");
        return facultyRepository.findByNameIgnoreCase(name);
    }

    @Override
    public List<Faculty> readByColor(String color) {
        logger.info("\"Read by color\" faculty method for application testing was invoke...");
        return facultyRepository.findByColorIgnoreCase(color);
    }

    @Override
    public Faculty read(Long id) {
        logger.info("\"Read by ID\" faculty method for application testing was invoke...");
        return facultyRepository.findById(id).get();
    }

    @Override
    public String readLongName() {
        logger.info("\"Read long name\" faculty method for application testing was invoke...");
        return facultyRepository.findAll().stream()
                .map(Faculty::getName)
                .max(Comparator.comparingInt(String::length))
                .orElse("");
    }

    @Override
    public List<Student> readStudents(Long id) {
        logger.info("\"Read students by ID\" faculty method for application testing was invoke...");
        Faculty faculty = facultyRepository.findById(id).get();
        return faculty.getStudents();
    }

    @Override
    public Faculty update(Faculty faculty) {
        logger.info("\"Update\" faculty method for application testing was invoke...");
        return facultyRepository.save(faculty);
    }

    @Override
    public void delete(Long id) {
        logger.info("\"Delete\" faculty method for application testing was invoke...");
        facultyRepository.deleteById(id);
    }

}

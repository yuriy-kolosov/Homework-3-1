package ru.hogwarts.school.homework_3.service.impl;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.homework_3.model.Faculty;
import ru.hogwarts.school.homework_3.model.Student;
import ru.hogwarts.school.homework_3.repository.FacultyRepository;
import ru.hogwarts.school.homework_3.service.FacultyService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class FacultyServiceImpl implements FacultyService {

    private final FacultyRepository facultyRepository;

    public FacultyServiceImpl(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    @PostConstruct
    public void init() {
        create(new Faculty("Cybernetics", "white"));
        create(new Faculty("Automation", "yellow"));
        create(new Faculty("Physics", "green"));
        create(new Faculty("Technique", "blue"));
        create(new Faculty("Chemistry", "red"));
    }

    @Override
    public Faculty create(Faculty faculty) {
        faculty.setId(null);
        return facultyRepository.save(faculty);
    }

    @Override
    public List<Faculty> readAll() {
        return facultyRepository.findAll()
                .stream().toList();
    }

    @Override
    public List<Faculty> readByName(String name) {
        return facultyRepository.findByNameIgnoreCase(name);
    }

    @Override
    public List<Faculty> readByColor(String color) {
        return facultyRepository.findByColorIgnoreCase(color);
    }

    @Override
    public Faculty read(Long id) {
        return facultyRepository.findById(id).get();
    }

    @Override
    public List<Student> readStudents(Long id) {
        Faculty faculty = facultyRepository.findById(id).get();
        return faculty.getStudents();
    }

    @Override
    public Faculty update(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    @Override
    public void delete(Long id) {
        facultyRepository.deleteById(id);
    }

}

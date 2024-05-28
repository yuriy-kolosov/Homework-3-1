package ru.hogwarts.school.homework_3.service.impl;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.homework_3.model.Faculty;
import ru.hogwarts.school.homework_3.repository.FacultyRepository;
import ru.hogwarts.school.homework_3.service.FacultyService;

import java.util.List;

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
        create(new Faculty("Physics", "white"));
        create(new Faculty("Technique", "green"));
        create(new Faculty("Chemistry", "white"));
    }

    @Override
    public Faculty create(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    @Override
    public List<Faculty> readAll() {
        return facultyRepository.findAll()
                .stream().toList();
    }

    @Override
    public List<Faculty> readByColor(String color) {
        return facultyRepository.findAll()
                .stream()
                .filter(faculty -> faculty.getColor().equals(color))
                .toList();
    }

    @Override
    public Faculty read(Long id) {
        return facultyRepository.findById(id).get();
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

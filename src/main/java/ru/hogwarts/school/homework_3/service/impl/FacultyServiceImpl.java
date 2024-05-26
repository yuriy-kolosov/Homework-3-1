package ru.hogwarts.school.homework_3.service.impl;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.homework_3.model.Faculty;
import ru.hogwarts.school.homework_3.service.FacultyService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FacultyServiceImpl implements FacultyService {

    private Map<Long, Faculty> facultyMap = new HashMap<>();
    private Long facultyMapId = 0L;

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
        faculty.setId(++facultyMapId);
        facultyMap.put(facultyMapId, faculty);
        return faculty;
    }

    @Override
    public List<Faculty> readAll() {
        return facultyMap.values()
                .stream().toList();
    }

    @Override
    public List<Faculty> readByColor(String color) {
        return facultyMap.values()
                .stream()
                .filter(faculty -> faculty.getColor().equals(color))
                .toList();
    }

    @Override
    public Faculty read(Long id) {
        return facultyMap.get(id);
    }

    @Override
    public Faculty update(Long id, Faculty faculty) {
        if (!facultyMap.containsKey(id)) {
            return null;
        }
        facultyMap.put(id, faculty);
        return faculty;
    }

    @Override
    public Faculty delete(Long id) {
        return facultyMap.remove(id);
    }

}

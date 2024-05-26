package ru.hogwarts.school.homework_3.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.homework_3.model.Faculty;
import ru.hogwarts.school.homework_3.service.FacultyService;

import java.util.List;

@RestController
@RequestMapping("/faculty")
public class FacultyController {

    public final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @PostMapping()
    public ResponseEntity<Faculty> createFaculty(@RequestBody Faculty faculty) {
        Faculty createdFaculty = facultyService.create(faculty);
        return ResponseEntity.ok(createdFaculty);
    }

    @GetMapping()
    public ResponseEntity<List<Faculty>> readAllFaculties() {
        List<Faculty> allFaculties = facultyService.readAll();
        if (allFaculties == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(allFaculties);
    }

    @GetMapping("/color")
    public ResponseEntity<List<Faculty>> readFacultiesByColor(@RequestParam("color") String color) {
        List<Faculty> facultiesByColor = facultyService.readByColor(color);
        if (facultiesByColor == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(facultiesByColor);
    }

    @GetMapping("/{facultyId}")
    public ResponseEntity<Faculty> readFaculty(@PathVariable Long facultyId) {
        Faculty faculty = facultyService.read(facultyId);
        if (faculty == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(faculty);
    }

    @PutMapping()
    public ResponseEntity<Faculty> updateFaculty(@RequestBody Faculty faculty) {
        Faculty updatedFaculty = facultyService.update(faculty.getId(), faculty);
        if (updatedFaculty == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(updatedFaculty);
    }

    @DeleteMapping("/{facultyId}")
    public ResponseEntity<Faculty> deleteFaculty(@PathVariable Long facultyId) {
        Faculty faculty = facultyService.delete(facultyId);
        if (faculty == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(faculty);
    }

}

package ru.hogwarts.school.homework_3.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.homework_3.model.Faculty;
import ru.hogwarts.school.homework_3.model.Student;
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
    public ResponseEntity<List<Faculty>> readFacultiesByNameOrByColorEitherAll(@RequestParam(required = false) String name
            , @RequestParam(required = false) String color) {
        if (name != null && !name.isBlank()) {
            return ResponseEntity.ok(facultyService.readByName(name));
        }
        if (color != null && !color.isBlank()) {
            return ResponseEntity.ok(facultyService.readByColor(color));
        }
        return ResponseEntity.ok(facultyService.readAll());
    }

    @GetMapping("/{facultyId}")
    public ResponseEntity<Faculty> readFaculty(@PathVariable Long facultyId) {
        Faculty faculty = facultyService.read(facultyId);
        if (faculty == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(faculty);
    }

    @GetMapping("/{facultyId}/students")
    public ResponseEntity<List<Student>> readStudentsByFaculty(@PathVariable Long facultyId) {
        List<Student> students = facultyService.readStudents(facultyId);
        if (students == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(students);
    }

    @PutMapping()
    public ResponseEntity<Faculty> updateFaculty(@RequestBody Faculty faculty) {
        Faculty updatedFaculty = facultyService.update(faculty);
        if (updatedFaculty == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(updatedFaculty);
    }

    @DeleteMapping("/{facultyId}")
    public ResponseEntity<Void> deleteFaculty(@PathVariable Long facultyId) {
        facultyService.delete(facultyId);
        return ResponseEntity.ok().build();
    }

}

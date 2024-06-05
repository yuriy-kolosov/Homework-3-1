package ru.hogwarts.school.homework_3.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.homework_3.model.Faculty;
import ru.hogwarts.school.homework_3.model.Student;
import ru.hogwarts.school.homework_3.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    public final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping()
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        Student createdStudent = studentService.create(student);
        return ResponseEntity.ok(createdStudent);
    }

    @GetMapping()
    public ResponseEntity<List<Student>> readAllStudents() {
        List<Student> allStudents = studentService.readAll();
        if (allStudents == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(allStudents);
    }

    @GetMapping("/age")
    public ResponseEntity<List<Student>> readStudentsByAge(@RequestParam("age") int age) {
        List<Student> studentsByAge = studentService.readByAge(age);
        if (studentsByAge == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(studentsByAge);
    }

    @GetMapping("/age/between")
    public ResponseEntity<List<Student>> readStudentsByAgeBetween(@RequestParam("minAge") int minAge
            , @RequestParam("maxAge") int maxAge) {
        List<Student> studentsByAgeBetween = studentService.readByAgeBetween(minAge, maxAge);
        if (studentsByAgeBetween.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(studentsByAgeBetween);
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<Student> readStudent(@PathVariable Long studentId) {
        Student student = studentService.read(studentId);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }

    @GetMapping("/{studentId}/faculty")
    public ResponseEntity<Faculty> readStudentFaculty(@PathVariable Long studentId) {
        Faculty faculty = studentService.readFaculty(studentId);
        if (faculty == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(faculty);
    }

    @PutMapping()
    public ResponseEntity<Student> updateStudent(@RequestBody Student student) {
        Student updatedStudent = studentService.update(student);
        if (updatedStudent == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(updatedStudent);
    }

    @DeleteMapping("/{studentId}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long studentId) {
        studentService.delete(studentId);
        return ResponseEntity.ok().build();
    }

}

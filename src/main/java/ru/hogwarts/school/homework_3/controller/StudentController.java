package ru.hogwarts.school.homework_3.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping("/{studentId}")
    public ResponseEntity<Student> readStudent(@PathVariable Long studentId) {
        Student student = studentService.read(studentId);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }

    @PutMapping()
    public ResponseEntity<Student> updateStudent(@RequestBody Student student) {
        Student updatedStudent = studentService.update(student.getId(), student);
        if (updatedStudent == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(updatedStudent);
    }

    @DeleteMapping("/{studentId}")
    public ResponseEntity<Student> deleteStudent(@PathVariable Long studentId) {
        Student student = studentService.delete(studentId);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }

}

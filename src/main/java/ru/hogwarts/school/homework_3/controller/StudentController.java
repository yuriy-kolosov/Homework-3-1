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

    @GetMapping("/count")
    public ResponseEntity<Integer> quantifyAllStudents() {
        int countAllStudents = studentService.getAllQuantify();
        return ResponseEntity.ok(countAllStudents);
    }

    @GetMapping("/count/age/average/method1")
    public ResponseEntity<Integer> countStudentAverageAgeByMethod1() {
        int countStudentAvgAge = studentService.countAverageAgeByMethod1();
        return ResponseEntity.ok(countStudentAvgAge);
    }

    @GetMapping("/count/age/average/method2")
    public ResponseEntity<Integer> countStudentAverageAgeByMethod2() {
        int countStudentAvgAge = studentService.countAverageAgeByMethod2();
        return ResponseEntity.ok(countStudentAvgAge);
    }

    @GetMapping("/last/5")
    public ResponseEntity<List<Student>> getLastFiveStudents() {
        List<Student> lastFiveStudents = studentService.getLastFive();
        if (lastFiveStudents == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(lastFiveStudents);
    }

    @GetMapping("/names/A")
    public ResponseEntity<List<String>> getStudentsNamesStartingWithA() {
        List<String> namesStartingWithAStudents = studentService.getNamesStartingWithA();
        if (namesStartingWithAStudents == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(namesStartingWithAStudents);
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

    @GetMapping("/print-parallel")
    public ResponseEntity<Void> print6StudentsParallel() {
        studentService.print6Parallel();
        return ResponseEntity.ok().build();
    }

    @GetMapping("/print-synchronized")
    public ResponseEntity<Void> print6ParallelSynchronized() {
        studentService.print6Synchronized();
        return ResponseEntity.ok().build();
    }

}

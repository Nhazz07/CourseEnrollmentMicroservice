package org.example.studentservice.Controller;

import org.example.studentservice.Dto.ApiResponse;
import org.example.studentservice.Entity.Student;
import org.example.studentservice.Service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Student>>> getAllStudents() {
        List<Student> students = studentService.getAllStudents();

        ApiResponse<List<Student>> response = new ApiResponse<>(
                true,
                "Student Retrieved Successfully",
                students
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Student>> getStudentById(@PathVariable Long id) {

        Student student = studentService.getStudentById(id);

        ApiResponse<Student> response = new ApiResponse<>(
                true,
                "Student Retrieved Successfully",
                student
        );
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Student>> createStudent(@RequestBody Student student) {
        Student createdStudent = studentService.createStudent(student);

        ApiResponse<Student> response = new ApiResponse<>(
                true,
                "Student Created Successfully",
                createdStudent
        );
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Student>> updateStudent(
            @PathVariable Long id,
            @RequestBody Student student
    ) {
        Student updateStudent = studentService.updateStudent(id,student);

        ApiResponse<Student> response = new ApiResponse<>(
                true,
                "Student Updated Successfully ",
                updateStudent
        );
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);

        ApiResponse<Void> response = new ApiResponse<>(
                true,
                "Student Deleted Successfully",
                null
        );
        return ResponseEntity.ok(response);
    }
}

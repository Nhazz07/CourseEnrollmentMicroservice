package org.example.studentservice.Controller;

import org.example.studentservice.Dto.ApiResponse;
import org.example.studentservice.Dto.Request.StudentRequestDto;
import org.example.studentservice.Dto.Response.StudentResponseDto;
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
    public ResponseEntity<ApiResponse<List<StudentResponseDto>>> getAllStudents() {
        List<StudentResponseDto> students = studentService.getAllStudents();

        ApiResponse<List<StudentResponseDto>> response = new ApiResponse<>(
                true,
                "Student Retrieved Successfully",
                students
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<StudentResponseDto>> getStudentById(@PathVariable Long id) {

        StudentResponseDto student = studentService.getStudentById(id);

        ApiResponse<StudentResponseDto> response = new ApiResponse<>(
                true,
                "Student Retrieved Successfully",
                student
        );
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<StudentResponseDto>> createStudent(@RequestBody StudentRequestDto request) {
        StudentResponseDto createdStudent = studentService.createStudent(request);

        ApiResponse<StudentResponseDto> response = new ApiResponse<>(
                true,
                "Student Created Successfully",
                createdStudent
        );
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<StudentResponseDto>> updateStudent(
            @PathVariable Long id,
            @RequestBody StudentRequestDto request
    ) {
        StudentResponseDto updateStudent = studentService.updateStudent(id,request);

        ApiResponse<StudentResponseDto> response = new ApiResponse<>(
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

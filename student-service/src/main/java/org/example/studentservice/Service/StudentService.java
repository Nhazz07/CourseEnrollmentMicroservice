package org.example.studentservice.Service;

import lombok.RequiredArgsConstructor;
import org.example.studentservice.Dto.Request.StudentRequestDto;
import org.example.studentservice.Dto.Response.StudentResponseDto;
import org.example.studentservice.Entity.Student;
import org.example.studentservice.Mapper.StudentMapper;
import org.example.studentservice.Repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public interface StudentService {
    StudentResponseDto createStudent(StudentRequestDto request);

    List<StudentResponseDto> getAllStudents();

    StudentResponseDto getStudentById(Long id);

    StudentResponseDto updateStudent(Long id, StudentRequestDto request);

    void deleteStudent(Long id);
}

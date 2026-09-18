package org.example.studentservice.Service.Impl;

import lombok.RequiredArgsConstructor;
import org.example.studentservice.Dto.Request.StudentRequestDto;
import org.example.studentservice.Dto.Response.StudentResponseDto;
import org.example.studentservice.Entity.Student;
import org.example.studentservice.Exception.StudentNotFoundException;
import org.example.studentservice.Mapper.StudentMapper;
import org.example.studentservice.Repository.StudentRepository;
import org.example.studentservice.Service.StudentService;
import org.example.studentservice.Exception.EmailAlreadyExistException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    @Override
    @Transactional
    public StudentResponseDto createStudent(StudentRequestDto request) {

        if (studentRepository.existsByEmail(request.getEmail())) {
            throw new EmailAlreadyExistException(
                    "Email already exists: " + request.getEmail()
            );
        }

        Student student = studentMapper.toEntity(request);

        Student savedStudent = studentRepository.save(student);

        return studentMapper.toResponse(savedStudent);
    }

    @Override
    @Transactional(readOnly = true)
    public List<StudentResponseDto> getAllStudents() {

        return studentRepository.findAll()
                .stream()
                .map(studentMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public StudentResponseDto getStudentById(Long id) {

        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(
                        "Student not found with id: " + id
                ));

        return studentMapper.toResponse(student);
    }

    @Override
    @Transactional
    public StudentResponseDto updateStudent(
            Long id,
            StudentRequestDto request
    ) {

        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(
                        "Student not found with id: " + id
                ));

        boolean emailChanged = !student.getEmail()
                .equals(request.getEmail());

        if (emailChanged && studentRepository.existsByEmail(request.getEmail())) {
            throw new EmailAlreadyExistException(
                    "Email already exists: " + request.getEmail()
            );
        }

        studentMapper.updateEntity(student, request);

        Student updatedStudent = studentRepository.save(student);

        return studentMapper.toResponse(updatedStudent);
    }

    @Override
    @Transactional
    public void deleteStudent(Long id) {

        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(
                        "Student not found with id: " + id
                ));

        studentRepository.delete(student);
    }
}
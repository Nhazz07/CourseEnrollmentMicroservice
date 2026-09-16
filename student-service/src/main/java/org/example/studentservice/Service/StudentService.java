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
@Transactional
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    @Transactional(readOnly = true)
    public List<StudentResponseDto> getAllStudents() {
        return studentRepository.findAll().stream().map(studentMapper::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public StudentResponseDto getStudentById(Long id) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Student Not Found with Id: " + id));

        return studentMapper.toResponse(student);
    }

    @Transactional(readOnly = true)
    public StudentResponseDto createStudent(StudentRequestDto request) {
        if(studentRepository.existsByEmail(request.getEmail())){
            throw new RuntimeException("Email is already register");
        }
        Student student = studentMapper.toEntity(request);
        Student savedStudent = studentRepository.save(student);
        return studentMapper.toResponse(savedStudent);
    }

    @Transactional(readOnly = true)
    public StudentResponseDto updateStudent(Long id, StudentRequestDto request) {

        Student student = studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Student Not Found"));
        if(!student.getEmail().equals(request.getEmail()) && studentRepository.existsByEmail(request.getEmail())){
            throw new RuntimeException("Email is already registered");
        }

        studentMapper.updateEntity(student,request);

        Student updateStudent = studentRepository.save(student);

        return studentMapper.toResponse(updateStudent);

    }

    @Transactional(readOnly = true)
    public void deleteStudent(Long id) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Student not found with id: " + id));
        studentRepository.delete(student);
    }

    @Transactional(readOnly = true)
    public boolean studentExists(Long id) {
        return studentRepository.existsById(id);
    }
}

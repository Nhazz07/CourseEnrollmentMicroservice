package org.example.studentservice.Mapper;

import org.example.studentservice.Dto.Request.StudentRequestDto;
import org.example.studentservice.Dto.Response.StudentResponseDto;
import org.example.studentservice.Entity.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper {
    public Student toEntity(StudentRequestDto request){
        Student student = new Student();
        student.setName(request.getName());
        student.setEmail(request.getEmail());

        return student;
    }

    public StudentResponseDto toResponse(Student student){
        return new StudentResponseDto(
                student.getId(),
                student.getName(),
                student.getEmail()
        );
    }
    public void updateEntity(Student student, StudentRequestDto request){
        student.setName(request.getName());
        student.setEmail(request.getEmail());
    }

}

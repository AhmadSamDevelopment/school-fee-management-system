package com.code.student.mapper;

import com.code.student.entities.Student;
import com.code.student.dto.StudentDTO;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class StudentMapper {

    private final GradeMapper gradeMapper;

    public StudentDTO toDto(Student student) {
        StudentDTO a = new StudentDTO();
        a.setFirstName(student.getFirstName());
        a.setStatus(student.getStatus());
        a.setLastName(student.getLastName());
        a.setAddress(student.getAddress());
        a.setEmail(student.getEmail());
        a.setPhone(student.getPhone());
        a.setId(student.getId());
        a.setRollNumber(student.getRollNumber());
        a.setGrade(gradeMapper.toDto(student.getGrade()));
        return a;
    }

    public Student toEntity(StudentDTO student) {
        Student a = new Student();
        a.setFirstName(student.getFirstName());
        a.setStatus(student.getStatus());
        a.setLastName(student.getLastName());
        a.setAddress(student.getAddress());
        a.setEmail(student.getEmail());
        a.setPhone(student.getPhone());
        a.setRollNumber(student.getRollNumber());
        a.setId(student.getId());
        a.setGrade(gradeMapper.toEntity(student.getGrade()));
        return a;
    }

}

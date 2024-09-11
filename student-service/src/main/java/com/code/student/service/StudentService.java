package com.code.student.service;

import com.code.student.dto.StudentDTO;

import java.util.List;

public interface StudentService {

    StudentDTO create(StudentDTO request);

    StudentDTO findById(Long studentId);

    List<StudentDTO> listStudents();

    StudentDTO update(Long studentId, StudentDTO studentDTO);

    void activate(Long id);

    void deactivate(Long id);

}

package com.code.student.service.impl;

import com.code.student.entities.Student;
import com.code.student.enums.ErrorCode;
import com.code.student.enums.StudentStatus;
import com.code.student.exception.RecordNotFoundException;
import com.code.student.repository.StudentRepository;
import com.code.student.dto.StudentDTO;
import com.code.student.mapper.StudentMapper;
import com.code.student.service.StudentService;

import java.time.Instant;
import java.util.List;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    private StudentMapper studentMapper;

    @Override
    public StudentDTO create(StudentDTO request) {
        Student student = studentMapper.toEntity(request);
        student.setCreatedDate(Instant.now());
        student.setStatus(StudentStatus.ACTIVE);
        Student savedStudent = studentRepository.save(student);
        return studentMapper.toDto(savedStudent);
    }

    public Student getById(Long id) {
        return studentRepository
                .findById(id)
                .orElseThrow(() -> new RecordNotFoundException(ErrorCode.STUDENT_NOT_FOUND));
    }

    @Override
    public StudentDTO findById(Long id) {
        return studentMapper.toDto(getById(id));
    }

    @Override
    public List<StudentDTO> listStudents() {
        return studentRepository.findAll().stream().map(studentMapper::toDto).toList();
    }

    @Override
    public StudentDTO update(Long id, StudentDTO studentDTO) {
        Student student = getById(id);
        student.setFirstName(studentDTO.getFirstName());
        student.setLastName(studentDTO.getLastName());
        student.setEmail(studentDTO.getEmail());
        student.setPhone(studentDTO.getPhone());
        student.setAddress(studentDTO.getAddress());
        student.setRollNumber(studentDTO.getRollNumber());
        Student updatedStudent = studentRepository.save(student);
        return studentMapper.toDto(updatedStudent);
    }

    @Override
    public void activate(Long id) {
        Student student = getById(id);
        student.setStatus(StudentStatus.ACTIVE);
        studentRepository.save(student);
    }

    @Override
    public void deactivate(Long id) {
        Student student = getById(id);
        student.setStatus(StudentStatus.IN_ACTIVE);
        studentRepository.save(student);
    }

}

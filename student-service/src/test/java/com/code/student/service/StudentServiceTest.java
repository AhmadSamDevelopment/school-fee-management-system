package com.code.student.service;

import com.code.student.dto.StudentDTO;
import com.code.student.entities.Student;
import com.code.student.enums.StudentStatus;
import com.code.student.exception.RecordNotFoundException;
import com.code.student.mapper.StudentMapper;
import com.code.student.repository.StudentRepository;
import com.code.student.service.impl.StudentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private StudentMapper studentMapper;

    @InjectMocks
    private StudentServiceImpl studentService;

    private Student student;
    private StudentDTO studentDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        student = new Student();
        student.setId(1L);
        student.setFirstName("John");
        student.setLastName("Doe");
        student.setEmail("john.doe@example.com");
        student.setPhone("1234567890");
        student.setRollNumber("123");
        student.setStatus(StudentStatus.ACTIVE);
        student.setCreatedDate(Instant.now());

        studentDTO = new StudentDTO();
        studentDTO.setFirstName("John");
        studentDTO.setLastName("Doe");
        studentDTO.setEmail("john.doe@example.com");
        studentDTO.setPhone("1234567890");
        studentDTO.setRollNumber("123");
    }

    @Test
    void create_ShouldCreateNewStudent() {
        when(studentMapper.toEntity(any(StudentDTO.class))).thenReturn(student);
        when(studentRepository.save(any(Student.class))).thenReturn(student);
        when(studentMapper.toDto(any(Student.class))).thenReturn(studentDTO);

        StudentDTO createdStudent = studentService.create(studentDTO);

        assertNotNull(createdStudent);
        assertEquals(studentDTO.getFirstName(), createdStudent.getFirstName());
        assertEquals(StudentStatus.ACTIVE, student.getStatus());
        verify(studentRepository, times(1)).save(any(Student.class));
    }

    @Test
    void findById_ShouldReturnStudent() {
        when(studentRepository.findById(anyLong())).thenReturn(Optional.of(student));
        when(studentMapper.toDto(any(Student.class))).thenReturn(studentDTO);

        StudentDTO foundStudent = studentService.findById(1L);

        assertNotNull(foundStudent);
        assertEquals(studentDTO.getFirstName(), foundStudent.getFirstName());
        verify(studentRepository, times(1)).findById(1L);
    }

    @Test
    void findById_ShouldThrowRecordNotFoundException_WhenStudentNotFound() {
        when(studentRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(RecordNotFoundException.class, () -> studentService.findById(1L));
        verify(studentRepository, times(1)).findById(1L);
    }

    @Test
    void listStudents_ShouldReturnListOfStudents() {
        when(studentRepository.findAll()).thenReturn(Arrays.asList(student));
        when(studentMapper.toDto(any(Student.class))).thenReturn(studentDTO);

        List<StudentDTO> students = studentService.listStudents();

        assertEquals(1, students.size());
        verify(studentRepository, times(1)).findAll();
    }

    @Test
    void update_ShouldUpdateExistingStudent() {
        when(studentRepository.findById(anyLong())).thenReturn(Optional.of(student));
        when(studentRepository.save(any(Student.class))).thenReturn(student);
        when(studentMapper.toDto(any(Student.class))).thenReturn(studentDTO);

        StudentDTO updatedStudent = studentService.update(1L, studentDTO);

        assertNotNull(updatedStudent);
        assertEquals(studentDTO.getFirstName(), updatedStudent.getFirstName());
        verify(studentRepository, times(1)).save(student);
    }

    @Test
    void activate_ShouldSetStatusToActive() {
        when(studentRepository.findById(anyLong())).thenReturn(Optional.of(student));

        studentService.activate(1L);

        assertEquals(StudentStatus.ACTIVE, student.getStatus());
        verify(studentRepository, times(1)).save(student);
    }

    @Test
    void deactivate_ShouldSetStatusToInactive() {
        when(studentRepository.findById(anyLong())).thenReturn(Optional.of(student));

        studentService.deactivate(1L);

        assertEquals(StudentStatus.IN_ACTIVE, student.getStatus());
        verify(studentRepository, times(1)).save(student);
    }
}

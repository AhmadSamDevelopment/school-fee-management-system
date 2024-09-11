package com.code.student.controller;

import com.code.student.dto.StudentDTO;
import com.code.student.service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class StudentControllerTest {

    @Mock
    private StudentService studentService;

    @InjectMocks
    private StudentController studentController;

    private StudentDTO studentDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        studentDto = new StudentDTO();
        studentDto.setFirstName("John");
        studentDto.setLastName("Doe");
        studentDto.setEmail("john.doe@example.com");
    }

    @Test
    void create_ShouldReturnCreatedStudent() {
        when(studentService.create(any(StudentDTO.class))).thenReturn(studentDto);

        ResponseEntity<StudentDTO> response = studentController.create(studentDto);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(studentDto.getFirstName(), response.getBody().getFirstName());
        verify(studentService, times(1)).create(any(StudentDTO.class));
    }

    @Test
    void activate_ShouldActivateStudent() {
        doNothing().when(studentService).activate(anyLong());

        ResponseEntity<Void> response = studentController.activate(1L);

        assertNotNull(response);
        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
        verify(studentService, times(1)).activate(anyLong());
    }

    @Test
    void deactivate_ShouldDeactivateStudent() {
        doNothing().when(studentService).deactivate(anyLong());

        ResponseEntity<Void> response = studentController.deactivate(1L);

        assertNotNull(response);
        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
        verify(studentService, times(1)).deactivate(anyLong());
    }

    @Test
    void getById_ShouldReturnStudentById() {
        when(studentService.findById(anyLong())).thenReturn(studentDto);

        ResponseEntity<StudentDTO> response = studentController.getById(1L);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(studentDto.getFirstName(), response.getBody().getFirstName());
        verify(studentService, times(1)).findById(anyLong());
    }

    @Test
    void list_ShouldReturnListOfStudents() {
        when(studentService.listStudents()).thenReturn(Arrays.asList(studentDto));

        ResponseEntity<List<StudentDTO>> response = studentController.list();

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        verify(studentService, times(1)).listStudents();
    }

    @Test
    void update_ShouldReturnUpdatedStudent() {
        when(studentService.update(anyLong(), any(StudentDTO.class))).thenReturn(studentDto);

        ResponseEntity<StudentDTO> response = studentController.update(1L, studentDto);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(studentDto.getFirstName(), response.getBody().getFirstName());
        verify(studentService, times(1)).update(anyLong(), any(StudentDTO.class));
    }
}


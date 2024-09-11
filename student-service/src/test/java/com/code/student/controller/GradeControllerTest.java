package com.code.student.controller;
import com.code.student.dto.GradeDto;
import com.code.student.service.GradeService;
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

class GradeControllerTest {

    @Mock
    private GradeService gradeService;

    @InjectMocks
    private GradeController gradeController;

    private GradeDto gradeDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        gradeDto = new GradeDto();
        gradeDto.setName("A");
        gradeDto.setDescription("Excellent");
    }

    @Test
    void create_ShouldReturnCreatedGrade() {
        when(gradeService.create(any(GradeDto.class))).thenReturn(gradeDto);

        ResponseEntity<GradeDto> response = gradeController.create(gradeDto);

        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(gradeDto.getName(), response.getBody().getName());
        verify(gradeService, times(1)).create(any(GradeDto.class));
    }

    @Test
    void edit_ShouldReturnUpdatedGrade() {
        when(gradeService.update(anyLong(), any(GradeDto.class))).thenReturn(gradeDto);

        ResponseEntity<GradeDto> response = gradeController.edit(1L, gradeDto);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(gradeDto.getName(), response.getBody().getName());
        verify(gradeService, times(1)).update(anyLong(), any(GradeDto.class));
    }

    @Test
    void list_ShouldReturnListOfGrades() {
        when(gradeService.list()).thenReturn(Arrays.asList(gradeDto));

        ResponseEntity<List<GradeDto>> response = gradeController.list();

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        verify(gradeService, times(1)).list();
    }

    @Test
    void findById_ShouldReturnGradeById() {
        when(gradeService.findById(anyLong())).thenReturn(gradeDto);

        ResponseEntity<GradeDto> response = gradeController.findById(1L);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(gradeDto.getName(), response.getBody().getName());
        verify(gradeService, times(1)).findById(anyLong());
    }
}

package com.code.student.service;

import com.code.student.dto.GradeDto;
import com.code.student.entities.Grade;
import com.code.student.exception.RecordNotFoundException;
import com.code.student.mapper.GradeMapper;
import com.code.student.repository.GradeRepository;
import com.code.student.service.impl.GradeServiceImp;
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


public class GradeServiceImpTest {
    @Mock
    private GradeRepository gradeRepository;

    @Mock
    private GradeMapper gradeMapper;

    @InjectMocks
    private GradeServiceImp gradeService;

    private Grade grade;
    private GradeDto gradeDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        grade = new Grade();
        grade.setId(1L);
        grade.setName("A");
        grade.setDescription("Excellent");
        grade.setCreatedDate(Instant.now());

        gradeDto = new GradeDto();
        gradeDto.setName("A");
        gradeDto.setDescription("Excellent");
    }

    @Test
    void create_ShouldCreateNewGrade() {
        when(gradeMapper.toEntity(any(GradeDto.class))).thenReturn(grade);
        when(gradeRepository.save(any(Grade.class))).thenReturn(grade);
        when(gradeMapper.toDto(any(Grade.class))).thenReturn(gradeDto);

        GradeDto createdGrade = gradeService.create(gradeDto);

        assertNotNull(createdGrade);
        assertEquals(gradeDto.getName(), createdGrade.getName());
        verify(gradeRepository, times(1)).save(any(Grade.class));
    }

    @Test
    void update_ShouldUpdateExistingGrade() {
        when(gradeRepository.findById(anyLong())).thenReturn(Optional.of(grade));
        when(gradeRepository.save(any(Grade.class))).thenReturn(grade);
        when(gradeMapper.toDto(any(Grade.class))).thenReturn(gradeDto);

        GradeDto updatedGrade = gradeService.update(1L, gradeDto);

        assertNotNull(updatedGrade);
        assertEquals(gradeDto.getName(), updatedGrade.getName());
        verify(gradeRepository, times(1)).save(grade);
    }

    @Test
    void findById_ShouldReturnGrade() {
        when(gradeRepository.findById(anyLong())).thenReturn(Optional.of(grade));
        when(gradeMapper.toDto(any(Grade.class))).thenReturn(gradeDto);

        GradeDto foundGrade = gradeService.findById(1L);

        assertNotNull(foundGrade);
        assertEquals(gradeDto.getName(), foundGrade.getName());
        verify(gradeRepository, times(1)).findById(1L);
    }

    @Test
    void findById_ShouldThrowRecordNotFoundException_WhenGradeNotFound() {
        when(gradeRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(RecordNotFoundException.class, () -> gradeService.findById(1L));
        verify(gradeRepository, times(1)).findById(1L);
    }

    @Test
    void list_ShouldReturnListOfGrades() {
        when(gradeRepository.findAll()).thenReturn(Arrays.asList(grade));
        when(gradeMapper.toDto(any(Grade.class))).thenReturn(gradeDto);

        List<GradeDto> grades = gradeService.list();

        assertEquals(1, grades.size());
        verify(gradeRepository, times(1)).findAll();
    }
}

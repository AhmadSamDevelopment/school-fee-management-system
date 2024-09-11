package com.code.student.service.impl;

import com.code.student.dto.GradeDto;
import com.code.student.entities.Grade;
import com.code.student.enums.ErrorCode;
import com.code.student.exception.RecordNotFoundException;
import com.code.student.mapper.GradeMapper;
import com.code.student.repository.GradeRepository;
import com.code.student.service.GradeService;

import java.time.Instant;
import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GradeServiceImp implements GradeService {

    private final GradeRepository gradeRepository;
    private final GradeMapper gradeMapper;

    @Override
    public GradeDto create(GradeDto gradeDTO) {
        Grade grade = gradeMapper.toEntity(gradeDTO);
        grade.setCreatedDate(Instant.now());
        Grade savedGrade = gradeRepository.save(grade);
        return gradeMapper.toDto(savedGrade);
    }

    @Override
    public GradeDto update(Long id, GradeDto gradeDTO) {
        Grade grade = getById(id);
        grade.setName(gradeDTO.getName());
        grade.setDescription(gradeDTO.getDescription());
        grade.setUpdatedDate(Instant.now());
        Grade updatedGrade = gradeRepository.save(grade);
        return gradeMapper.toDto(updatedGrade);
    }

    public Grade getById(Long id) {
        return gradeRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(ErrorCode.GRADE_NOT_FOUND));
    }

    @Override
    public List<GradeDto> list() {
        return gradeRepository.findAll().stream().map(gradeMapper::toDto).toList();
    }

    @Override
    public GradeDto findById(Long id) {
        return gradeMapper.toDto(getById(id));
    }

}

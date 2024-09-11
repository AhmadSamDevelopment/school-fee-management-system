package com.code.student.service;

import com.code.student.dto.GradeDto;

import java.util.List;

public interface GradeService {

    GradeDto create(GradeDto gradeDTO);

    GradeDto update(Long id, GradeDto gradeDTO);

    List<GradeDto> list();

    GradeDto findById(Long id);
}

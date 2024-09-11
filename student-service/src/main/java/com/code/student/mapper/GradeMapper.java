package com.code.student.mapper;

import com.code.student.dto.GradeDto;
import com.code.student.entities.Grade;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;


@Configuration
@AllArgsConstructor
public class GradeMapper {

    public GradeDto toDto(Grade grade) {
        GradeDto gradeDto = new GradeDto();
        gradeDto.setName(grade.getName());
        gradeDto.setDescription(grade.getDescription());
        gradeDto.setId(grade.getId());
        return gradeDto;
    }

    public Grade toEntity(GradeDto dto) {
        Grade cus = new Grade();
        cus.setDescription(dto.getDescription());
        cus.setName(dto.getName());
        cus.setId(dto.getId());
        return cus;
    }
}

package com.code.student.controller;

import com.code.student.dto.GradeDto;
import com.code.student.service.GradeService;

import java.util.List;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/grades")
@AllArgsConstructor
public class GradeController {

    private final GradeService gradeService;

    @PostMapping
    public ResponseEntity<GradeDto> create(@Valid @RequestBody GradeDto gradeDto) {
        GradeDto createdGrade = gradeService.create(gradeDto);
        return new ResponseEntity<>(createdGrade, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GradeDto> edit(
            @PathVariable Long id, @Valid @RequestBody GradeDto gradeDto) {
        GradeDto updated = gradeService.update(id, gradeDto);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<GradeDto>> list() {
        List<GradeDto> grades = gradeService.list();
        return new ResponseEntity<>(grades, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GradeDto> findById(@PathVariable Long id) {
        GradeDto grade = gradeService.findById(id);
        return new ResponseEntity<>(grade, HttpStatus.OK);
    }

}

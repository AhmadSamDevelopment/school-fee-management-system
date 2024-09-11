package com.code.student.controller;

import com.code.student.dto.StudentDTO;
import com.code.student.service.StudentService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@AllArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @PostMapping
    public ResponseEntity<StudentDTO> create(@Valid @RequestBody StudentDTO studentDTO) {
        StudentDTO createdAccount = studentService.create(studentDTO);
        return ResponseEntity.ok(createdAccount);
    }

    @PatchMapping("/{studentId}/activate")
    public ResponseEntity<Void> activate(@PathVariable Long studentId) {
        studentService.activate(studentId);
        return ResponseEntity.accepted().build();
    }

    @PatchMapping("/{studentId}/deactivate")
    public ResponseEntity<Void> deactivate(@PathVariable Long studentId) {
        studentService.deactivate(studentId);
        return ResponseEntity.accepted().build();
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<StudentDTO> getById(@PathVariable Long studentId) {
        StudentDTO studentDTO = studentService.findById(studentId);
        return new ResponseEntity<>(studentDTO, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<StudentDTO>> list() {
        List<StudentDTO> students = studentService.listStudents();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @PutMapping("/{studentId}")
    public ResponseEntity<StudentDTO> update(@PathVariable Long studentId, @RequestBody StudentDTO studentDTO) {
        StudentDTO updatedAccount = studentService.update(studentId, studentDTO);
        return new ResponseEntity<>(updatedAccount, HttpStatus.OK);
    }

}

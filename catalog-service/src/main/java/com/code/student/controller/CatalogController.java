package com.code.student.controller;

import com.code.student.dto.CatalogDTO;
import com.code.student.service.CatalogService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/catalogs")
@AllArgsConstructor
public class CatalogController {

    private final CatalogService catalogService;

    @PostMapping
    public ResponseEntity<CatalogDTO> create(@Valid @RequestBody CatalogDTO catalogDTO) {
        CatalogDTO createdAccount = catalogService.create(catalogDTO);
        return ResponseEntity.ok(createdAccount);
    }

    @PatchMapping("/{catalogId}/activate")
    public ResponseEntity<Void> activate(@PathVariable Long catalogId) {
        catalogService.activate(catalogId);
        return ResponseEntity.accepted().build();
    }

    @PatchMapping("/{catalogId}/deactivate")
    public ResponseEntity<Void> deactivate(@PathVariable Long catalogId) {
        catalogService.deactivate(catalogId);
        return ResponseEntity.accepted().build();
    }

    @GetMapping("/{catalogId}")
    public ResponseEntity<CatalogDTO> getById(@PathVariable Long studentId) {
        CatalogDTO catalogDTO = catalogService.findById(studentId);
        return new ResponseEntity<>(catalogDTO, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<CatalogDTO>> list() {
        List<CatalogDTO> students = catalogService.list();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @PutMapping("/{catalogId}")
    public ResponseEntity<CatalogDTO> update(@PathVariable Long studentId, @RequestBody CatalogDTO catalogDTO) {
        CatalogDTO updatedAccount = catalogService.update(studentId, catalogDTO);
        return new ResponseEntity<>(updatedAccount, HttpStatus.OK);
    }

    @GetMapping("/{gradeId}/active")
    public ResponseEntity<List<CatalogDTO>> getActiveCatalogs(@PathVariable Long gradeId) {
        List<CatalogDTO> list = catalogService.findActiveCatalog(gradeId);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

}

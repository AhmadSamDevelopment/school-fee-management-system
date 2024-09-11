package com.code.student.service;

import com.code.student.dto.CatalogDTO;

import java.util.List;

public interface CatalogService {

    CatalogDTO create(CatalogDTO request);

    CatalogDTO findById(Long id);

    List<CatalogDTO> list();

    CatalogDTO update(Long id, CatalogDTO catalogDTO);

    void activate(Long id);

    void deactivate(Long id);

    List<CatalogDTO> findActiveCatalog(Long grade);

}

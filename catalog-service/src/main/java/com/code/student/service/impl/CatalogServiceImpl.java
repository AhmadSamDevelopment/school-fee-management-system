package com.code.student.service.impl;

import com.code.student.entities.Catalog;
import com.code.student.entities.Item;
import com.code.student.enums.ErrorCode;
import com.code.student.exception.RecordNotFoundException;
import com.code.student.mapper.ItemMapper;
import com.code.student.repository.CatalogRepository;
import com.code.student.dto.CatalogDTO;
import com.code.student.mapper.CatalogMapper;
import com.code.student.repository.ItemRepository;
import com.code.student.service.CatalogService;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class CatalogServiceImpl implements CatalogService {

    private final CatalogRepository catalogRepository;
    private final ItemRepository itemRepository;

    private final CatalogMapper catalogMapper;
    private final ItemMapper itemMapper;

    @Override
    public CatalogDTO create(CatalogDTO request) {
        Catalog catalog = catalogMapper.toEntity(request);
        catalog.setCreatedDate(Instant.now());
        catalog.setActive(true);
        Catalog savedCatalog = catalogRepository.save(catalog);

        catalog.getItems().forEach(item -> {
            item.setCatalog(savedCatalog);
            item.setCreatedDate(Instant.now());
            itemRepository.save(item);
        });
        return catalogMapper.toDto(savedCatalog);
    }

    public Catalog getById(Long id) {
        return catalogRepository
                .findById(id)
                .orElseThrow(() -> new RecordNotFoundException(ErrorCode.CATALOG_NOT_FOUND));
    }

    @Override
    public CatalogDTO findById(Long id) {
        return catalogMapper.toDto(getById(id));
    }

    @Override
    public List<CatalogDTO> list() {
        return catalogRepository.findAll().stream().map(catalogMapper::toDto).toList();
    }

    @Override
    public CatalogDTO update(Long id, CatalogDTO catalogDTO) {
        Catalog catalog = getById(id);
        catalog.setName(catalogDTO.getName());
        catalog.setActivationDate(catalogDTO.getActivationDate());
        catalog.setExpiryDate(catalogDTO.getExpiryDate());
        itemRepository.deleteAll(catalog.getItems());
        Catalog updatedCatalog = catalogRepository.save(catalog);

        catalogDTO.getItems().forEach(item -> {
            Item i = itemMapper.toEntity(item);
            i.setCatalog(updatedCatalog);
            i.setCreatedDate(Instant.now());
            itemRepository.save(i);
        });
        return catalogMapper.toDto(updatedCatalog);
    }

    @Override
    public void activate(Long id) {
        Catalog catalog = getById(id);
        catalog.setActive(true);
        catalogRepository.save(catalog);
    }

    @Override
    public void deactivate(Long id) {
        Catalog catalog = getById(id);
        catalog.setActive(false);
        catalogRepository.save(catalog);
    }

    @Override
    public List<CatalogDTO> findActiveCatalog(Long grade) {
        return catalogRepository.findActiveCatalogs(LocalDateTime.now(), grade).stream().map(catalogMapper::toDto).toList();
    }
}

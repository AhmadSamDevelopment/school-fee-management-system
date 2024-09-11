package com.code.student.mapper;

import com.code.student.entities.Catalog;
import com.code.student.dto.CatalogDTO;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class CatalogMapper {

    private final ItemMapper itemMapper;

    public CatalogDTO toDto(Catalog catalog) {
        CatalogDTO a = new CatalogDTO();
        a.setName(catalog.getName());
        a.setGrade(catalog.getGrade());
        a.setActivationDate(catalog.getActivationDate());
        a.setExpiryDate(catalog.getExpiryDate());
        a.setId(catalog.getId());
        a.setItems(catalog.getItems().stream().map(itemMapper::toDto).toList());
        return a;
    }

    public Catalog toEntity(CatalogDTO catalog) {
        Catalog a = new Catalog();
        a.setName(catalog.getName());
        a.setGrade(catalog.getGrade());
        a.setActivationDate(catalog.getActivationDate());
        a.setExpiryDate(catalog.getExpiryDate());
        a.setId(catalog.getId());
        a.setItems(catalog.getItems().stream().map(itemMapper::toEntity).toList());
        return a;
    }

}

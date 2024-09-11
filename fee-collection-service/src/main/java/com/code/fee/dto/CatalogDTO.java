package com.code.fee.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
public class CatalogDTO {

    private Long id;
    private String name;
    private LocalDateTime activationDate;
    private LocalDateTime expiryDate;
    private Long grade;
    private boolean active = true;
    private List<ItemDTO> items;
}

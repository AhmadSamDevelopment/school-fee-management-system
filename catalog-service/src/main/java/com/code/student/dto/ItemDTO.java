package com.code.student.dto;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;


@Getter
@Setter
@NoArgsConstructor
public class ItemDTO {

    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "price")
    private BigDecimal price;

}

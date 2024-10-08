package com.code.fee.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;


@Getter
@Setter
@NoArgsConstructor
public class InvoiceItemDTO {

    private Long id;

    private String name;
    private BigDecimal price;

}

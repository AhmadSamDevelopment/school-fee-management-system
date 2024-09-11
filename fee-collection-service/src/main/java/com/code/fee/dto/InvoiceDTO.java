package com.code.fee.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceDTO {

    private Long id;
    private String studentName;
    private String grade;
    private Long catalogId;
    private String startMonth;
    private String endMonth;
    private List<InvoiceItemDTO> items;



}

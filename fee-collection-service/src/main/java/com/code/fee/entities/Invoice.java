package com.code.fee.entities;

import jakarta.persistence.Entity;

import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "invoices")
public class Invoice extends BaseEntity {

    private String studentName;
    private String grade;
    private Long catalogId;
    private String startMonth;
    private String endMonth;

    @OneToMany(mappedBy = "invoice")
    private List<InvoiceItem> items;

}

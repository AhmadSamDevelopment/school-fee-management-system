package com.code.fee.enums;

import lombok.Getter;

@Getter
public enum InvoiceType {
    DEBIT(1, "Debit"),
    CREDIT(2, "Credit");


    private final Integer id;
    private final String title;

    InvoiceType(Integer id, String title) {
        this.id = id;
        this.title = title;
    }
}

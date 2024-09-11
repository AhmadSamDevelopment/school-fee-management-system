package com.code.student.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    CATALOG_NOT_FOUND("CATALOG_NOT_FOUND", "catalog doesn't exist");

    private final String code;
    private final String message;
}

package com.code.student.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    GRADE_NOT_FOUND("GRADE_NOT_FOUND", "grade not found"),
    STUDENT_NOT_FOUND("STUDENT_NOT_FOUND", "student not found");

    private final String code;
    private final String message;
}

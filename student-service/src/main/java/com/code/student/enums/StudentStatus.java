package com.code.student.enums;

import lombok.Getter;

@Getter
public enum StudentStatus {
    ACTIVE(1, "Active"),
    IN_ACTIVE(2, "Inactive");

    private final Integer id;
    private final String title;

    StudentStatus(Integer id, String title) {
        this.id = id;
        this.title = title;
    }
}

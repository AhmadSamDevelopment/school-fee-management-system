package com.code.fee.enums;

import lombok.Getter;

@Getter
public enum StudentStatus {
  SUCCESS(1, "Success"),
  FAILED(2, "Failed"),
  COMPLETED(3, "Completed"),;


  private final Integer id;
  private final String title;

  StudentStatus(Integer id, String title) {
    this.id = id;
    this.title = title;
  }
  }

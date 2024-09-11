package com.code.fee.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {
  NOT_ANY_CATALOG_FOUND("NOT_ANY_CATALOG_FOUND", "Not any catalog not found"),
  INVOICE_NOT_FOUND("INVOICE_NOT_FOUND", "Not any invoice not found");

  private String code;
  private String message;
}

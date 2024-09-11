package com.code.student.exception;

import com.code.student.enums.ErrorCode;
import org.springframework.http.HttpStatus;

public class BadRequestException extends AppException {

  public BadRequestException(ErrorCode errorCode) {
    super(errorCode, HttpStatus.BAD_REQUEST);
  }
}

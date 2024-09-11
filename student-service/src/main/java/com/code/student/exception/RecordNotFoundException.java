package com.code.student.exception;

import com.code.student.enums.ErrorCode;
import org.springframework.http.HttpStatus;

public class RecordNotFoundException extends AppException {

  public RecordNotFoundException(ErrorCode errorCode) {
    super(errorCode, HttpStatus.NOT_FOUND);
  }
}

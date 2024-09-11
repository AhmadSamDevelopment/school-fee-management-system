package com.code.fee.exception;


import com.code.fee.enums.ErrorCode;
import org.springframework.http.HttpStatus;

public class RecordNotFoundException extends AppException {

  public RecordNotFoundException(ErrorCode errorCode) {
    super(errorCode, HttpStatus.NOT_FOUND);
  }
}

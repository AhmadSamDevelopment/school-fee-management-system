package com.code.fee.exception;


import com.code.fee.enums.ErrorCode;
import org.springframework.http.HttpStatus;

public class BadRequestException extends AppException {

  public BadRequestException(ErrorCode errorCode) {
    super(errorCode, HttpStatus.BAD_REQUEST);
  }
}

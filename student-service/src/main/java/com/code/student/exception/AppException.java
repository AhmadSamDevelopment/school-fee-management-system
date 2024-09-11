package com.code.student.exception;

import com.code.student.enums.ErrorCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class AppException extends RuntimeException {
    private ErrorCode errorCode;
    private HttpStatus status;

    public AppException(ErrorCode errorCode, HttpStatus status) {
        super();
        this.errorCode = errorCode;
        this.status = status;
    }

    public AppException(ErrorCode errorCode) {
        super();
        this.errorCode = errorCode;
        this.status = HttpStatus.INTERNAL_SERVER_ERROR;
    }

}

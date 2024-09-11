package com.code.student.exception;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ExceptionResponse {
  private String message;
  private String error;
  private HttpStatus status;
  private LocalDateTime dateTime;
}

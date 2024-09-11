package com.code.fee.dto;

import com.code.fee.enums.StudentStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class StudentDTO {

  private Long id;

  private String firstName;
  private String lastName;
  private String phone;
  private String email;
  private String rollNumber;
  private String address;
  private StudentStatus status;
  private GradeDto grade;
}

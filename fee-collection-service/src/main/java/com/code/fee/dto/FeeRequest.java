package com.code.fee.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class FeeRequest {

    private Long studentId;
    private Long gradeId;
    private String startMonth;
    private String endMonth;
}

package com.code.fee.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class GradeDto {

    private Long id;

    @NotEmpty(message = "Name is required")
    private String name;

    private String description;

}

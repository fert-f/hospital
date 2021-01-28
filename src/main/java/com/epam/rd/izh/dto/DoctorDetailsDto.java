package com.epam.rd.izh.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class DoctorDetailsDto {
    @NotBlank
    long id;
    private String specialty;
    private String specification;
    private String experience;
}

package com.epam.rd.izh.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class DoctorDetailsDto {
    @NotNull
    private long id;
    private String specialty;
    private String specification;
    private String experience;
}

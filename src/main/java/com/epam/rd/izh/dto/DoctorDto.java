package com.epam.rd.izh.dto;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@EqualsAndHashCode(callSuper = false)
@Data
@Builder
public class DoctorDto extends DoctorDetailsDto {
    @NotNull
    long id;
    @Size(min = 2, max = 16)
    private String name;
    @Size(min = 2, max = 16)
    private String surname;
    private LocalDate birthday;
    private String specialty;
    private String specification;
    private String experience;
}

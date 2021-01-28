package com.epam.rd.izh.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@Builder
public class DoctorDto extends DoctorDetailsDto{
    @NotBlank
    long id;
    @Size(min=2, max=16)
    private String name;
    @Size(min=2, max=16)
    private String surname;
    private Date birthday;
    private String specialty;
    private String specification;
    private String experience;
}

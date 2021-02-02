package com.epam.rd.izh.entity;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@Builder
public class MyUser {
    private long id;
    @Min(4)
    private String login;
    @Size(min = 2, max = 16)
    private String name;
    @Size(min = 2, max = 16)
    private String surname;
    private LocalDate birthday;
    private String role;
}

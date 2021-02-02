package com.epam.rd.izh.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Data
public class RegisteredUserDto {
    @Min(4)
    private String login;
    @Size(min = 2, max = 16)
    private String name;
    @Size(min = 2, max = 16)
    private String surname;
    @NotBlank
    private String birthday;
    @NotBlank
    private String password;
    private String confirmPassword;
    private String role;
}

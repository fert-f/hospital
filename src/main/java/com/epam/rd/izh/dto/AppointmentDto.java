package com.epam.rd.izh.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentDto {
    private long rec_id;
    private LocalDate date_app;
    private LocalTime time_app;
    private long doctor_id;
    private long patient_id;
    private String record;
    private Boolean visit;
    private String doctorName;
    private String doctorSurname;
    private String specialty;
    private String specification;
    private String experience;
    private String patientName;
    private String patientSurname;
    private int patientAge;
}

package com.epam.rd.izh.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
public class TimeTableDto {
    private long rec_id;
    private LocalDate date_app;
    private LocalTime time_app;
    private long doctor_id;
    private long patient_id;
    private String record;
    private Boolean visit;

}

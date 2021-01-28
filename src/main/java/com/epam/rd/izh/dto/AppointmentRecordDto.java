package com.epam.rd.izh.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class AppointmentRecordDto {
    long record_id;
    long ap_id;
    long doctor_id;
    long patient_id;
    String record;
    Date date_appointment;
}

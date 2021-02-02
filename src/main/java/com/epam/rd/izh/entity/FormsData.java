package com.epam.rd.izh.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FormsData {
    private String am;
    private String pm;
    private String date;
    private long doctorId;
    private String time_app;
    private long patient_id;
}

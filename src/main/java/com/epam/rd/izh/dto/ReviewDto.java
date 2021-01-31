package com.epam.rd.izh.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDto {
    private long review_id;
    private long rw_patient_id;
    private long rw_doctor_id;
    private long rec_id;
    private String review;
}

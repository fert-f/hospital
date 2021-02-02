package com.epam.rd.izh.repository;

import com.epam.rd.izh.dto.ReviewDto;
import com.epam.rd.izh.mappers.ReviewMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReviewRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    ReviewMapper reviewMapper;

    public boolean saveReview(long patient_id, long doctor_id, long rec_id, String review) {
        return jdbcTemplate.update("INSERT INTO `reviews` (`rw_patient_id`, `rw_doctor_id`, `rec_id`, `review`) VALUES ( ?, ?, ?, ?)", patient_id, doctor_id, rec_id, review) > 0;
    }

    public List<ReviewDto> getReviewsOnDoctor(long doctor_id) {
        return jdbcTemplate.query("SELECT * FROM `reviews` WHERE  `rw_doctor_id` = ? ", reviewMapper, doctor_id);
    }
}

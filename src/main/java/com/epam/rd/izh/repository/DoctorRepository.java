package com.epam.rd.izh.repository;

import com.epam.rd.izh.dto.DoctorDto;
import com.epam.rd.izh.mappers.DoctorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DoctorRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    DoctorMapper doctorMapper;


    public List<DoctorDto> getAllDoctors() {
        return jdbcTemplate.query("SELECT doctor_details.*,users.* FROM `users` LEFT JOIN  `doctor_details` " +
                        "ON users.user_id = doctor_details.dd_user_id WHERE users.role = 'DOCTOR'",
                doctorMapper);
    }

    public DoctorDto getDoctorById(long id) {
        List<DoctorDto> doctorDtoList = jdbcTemplate.query("SELECT doctor_details.*,users.* FROM `users` LEFT JOIN  `doctor_details` " +
                        "ON users.user_id = doctor_details.dd_user_id WHERE users.role = 'DOCTOR' AND `user_id` = ? LIMIT 1",
                doctorMapper, id);
        return doctorDtoList.get(0);
    }

    public boolean saveDoctorDetails(long id, String specialty, String specification, String experience) {
        return jdbcTemplate.update("INSERT INTO `doctor_details`(`dd_user_id`, `specialty`, `specification`, `experience`)" +
                        " VALUES (?,?,?,?) ON DUPLICATE KEY UPDATE `specialty` = ? , `specification` = ? , `experience` = ?",
                id, specialty, specification, experience, specialty, specification, experience) != 0;
    }


}

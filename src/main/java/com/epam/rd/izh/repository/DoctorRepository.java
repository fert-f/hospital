package com.epam.rd.izh.repository;

import com.epam.rd.izh.dto.DoctorDto;
import com.epam.rd.izh.dto.TimeTableDto;
import com.epam.rd.izh.mappers.DoctorMapper;
import com.epam.rd.izh.mappers.TimeTableMapper;
import com.epam.rd.izh.util.TimeHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public class DoctorRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    DoctorMapper doctorMapper;

    @Autowired
    TimeTableMapper timeTableMapper;


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

    public boolean createDoctorDetails (long id) {
        return jdbcTemplate.update("INSERT INTO `doctor_details`(`dd_id`) VALUES (?)", id) !=0;
    }

    public boolean saveDoctorDetails (long id, String specialty, String specification, String experience) {
        return jdbcTemplate.update("INSERT INTO `doctor_details`(`dd_user_id`, `specialty`, `specification`, `experience`)" +
                        " VALUES (?,?,?,?) ON DUPLICATE KEY UPDATE `specialty` = ? , `specification` = ? , `experience` = ?",
                id,specialty,specification,experience,specialty,specification,experience) !=0;
    }


}

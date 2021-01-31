package com.epam.rd.izh.mappers;

import com.epam.rd.izh.dto.AppointmentDto;
import com.epam.rd.izh.util.TimeHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;

@Component
public class AppointmentMapper implements RowMapper<AppointmentDto> {

    @Autowired
    TimeHolder timeHolder;

    @Override
    public AppointmentDto mapRow(ResultSet resultSet, int i) throws SQLException {
        return AppointmentDto.builder()
                .rec_id(resultSet.getLong("rec_id"))
                .date_app(LocalDate.parse(resultSet.getString("date_app")))
                .time_app(LocalTime.parse(resultSet.getString("time_app")))
                .doctor_id(resultSet.getLong("doctor_id"))
                .patient_id(resultSet.getLong("patient_id"))
                .record(resultSet.getString("record"))
                .visit(resultSet.getBoolean("visit"))
                .doctorName(resultSet.getString("doctorName"))
                .doctorSurname(resultSet.getString("doctorSurname"))
                .specialty(resultSet.getString("specialty"))
                .specification(resultSet.getString("specification"))
                .experience(resultSet.getString("experience"))
                .patientName(resultSet.getString("patientName"))
                .patientSurname(resultSet.getString("patientSurname"))
                .patientAge(Period.between(LocalDate.parse(resultSet.getString("patientBirthday")), timeHolder.getDate()).getYears())
                .build();
    }
}

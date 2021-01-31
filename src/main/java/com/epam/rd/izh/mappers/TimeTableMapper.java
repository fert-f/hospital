package com.epam.rd.izh.mappers;

import com.epam.rd.izh.dto.TimeTableDto;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

@Component
public class TimeTableMapper implements RowMapper<TimeTableDto> {
    @Override
    public TimeTableDto mapRow(ResultSet resultSet, int i) throws SQLException {
        return TimeTableDto.builder()
                .rec_id(resultSet.getLong("rec_id"))
                .date_app(LocalDate.parse(resultSet.getString("date_app")))
                .time_app(LocalTime.parse(resultSet.getString("time_app")))
                .doctor_id(resultSet.getLong("doctor_id"))
                .patient_id(resultSet.getLong("patient_id"))
                .record(resultSet.getString("record"))
                .visit(resultSet.getObject("visit", Boolean.class))
                .build();
    }
}

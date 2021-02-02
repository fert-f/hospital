package com.epam.rd.izh.mappers;

import com.epam.rd.izh.dto.DoctorDto;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

@Component
public class DoctorMapper implements RowMapper<DoctorDto> {
    @Override
    public DoctorDto mapRow(ResultSet resultSet, int i) throws SQLException {
        return DoctorDto.builder()
                .id(resultSet.getLong("user_id"))
                .name(resultSet.getString("user_name"))
                .surname(resultSet.getString("user_surname"))
                .birthday(LocalDate.parse(resultSet.getString("user_birthday")))
                .specialty(resultSet.getString("specialty"))
                .specification(resultSet.getString("specification"))
                .experience(resultSet.getString("experience"))
                .build();
    }
}

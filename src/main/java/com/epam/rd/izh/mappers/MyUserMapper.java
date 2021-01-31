package com.epam.rd.izh.mappers;

import com.epam.rd.izh.entity.MyUser;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

@Component
public class MyUserMapper implements RowMapper<MyUser> {
    @Override
    public MyUser mapRow(ResultSet resultSet, int i) throws SQLException {
        return MyUser.builder()
                .id(resultSet.getLong("user_id"))
                .login(resultSet.getString("login"))
                .name(resultSet.getString("user_name"))
                .surname(resultSet.getString("user_surname"))
                .birthday(LocalDate.parse(resultSet.getString("user_birthday")))
                .role(resultSet.getString("role"))
                .build();
    }
}

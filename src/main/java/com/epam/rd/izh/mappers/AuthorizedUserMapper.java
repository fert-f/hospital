package com.epam.rd.izh.mappers;

import com.epam.rd.izh.entity.AuthorizedUser;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class AuthorizedUserMapper implements RowMapper<AuthorizedUser> {

    @Override
    public AuthorizedUser mapRow(ResultSet resultSet, int i) throws SQLException {
        return AuthorizedUser.builder()
                .id(resultSet.getLong("user_id"))
                .login(resultSet.getString("login"))
                .password(resultSet.getString("password"))
                .name(resultSet.getString("user_name"))
                .surname(resultSet.getString("user_surname"))
                .birthday(resultSet.getDate("user_birthday"))
                .role(resultSet.getString("role"))
                .build();
    }
}

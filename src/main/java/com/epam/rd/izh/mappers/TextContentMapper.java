package com.epam.rd.izh.mappers;

import com.epam.rd.izh.dto.TextContentDto;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class TextContentMapper implements RowMapper<TextContentDto> {
    @Override
    public TextContentDto mapRow(ResultSet resultSet, int i) throws SQLException {
        return TextContentDto.builder().pc_id(resultSet.getLong("pc_id"))
                .user_role(resultSet.getInt("user_role"))
                .page(resultSet.getString("page"))
                .content(resultSet.getString("content")).build();
    }
}

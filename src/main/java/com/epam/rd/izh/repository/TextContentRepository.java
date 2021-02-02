package com.epam.rd.izh.repository;

import com.epam.rd.izh.dto.TextContentDto;
import com.epam.rd.izh.mappers.TextContentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TextContentRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    TextContentMapper textContentMapper;

    public TextContentDto getTextContentByUserAndPage(String page) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<TextContentDto> textContentDtoList = jdbcTemplate.query("SELECT `page_content`.* FROM `page_content` INNER JOIN" +
                        "(SELECT roles.* FROM `roles` INNER JOIN " +
                        "(SELECT `role` FROM `users` WHERE `login` = ? LIMIT 1) AS y " +
                        "ON roles.role_name=y.role) AS x " +
                        "ON page_content.user_role = x.role_id WHERE `page` = ? ",
                textContentMapper, user.getUsername(), page);
        return textContentDtoList.get(0);
    }

}

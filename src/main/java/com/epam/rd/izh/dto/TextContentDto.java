package com.epam.rd.izh.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TextContentDto {
    private long pc_id;
    private int user_role;
    private String page;
    private String content;

}

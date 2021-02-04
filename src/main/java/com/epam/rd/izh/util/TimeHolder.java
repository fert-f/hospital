package com.epam.rd.izh.util;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Component
@Data
public class TimeHolder {

    private LocalDateTime time = LocalDateTime.now();
    private LocalDate date = LocalDate.parse("2021-02-03");

    public static List<LocalTime> am;
    public static List<LocalTime> pm;

    static {
        am = new ArrayList<>();
        am.add(LocalTime.of(8, 0));
        am.add(LocalTime.of(8, 30));
        am.add(LocalTime.of(9, 0));
        am.add(LocalTime.of(9, 30));
        am.add(LocalTime.of(10, 0));
        am.add(LocalTime.of(10, 30));
        am.add(LocalTime.of(11, 0));
        am.add(LocalTime.of(11, 30));
        am.add(LocalTime.of(12, 0));
        am.add(LocalTime.of(12, 30));
    }

    static {
        pm = new ArrayList<>();
        pm.add(LocalTime.of(13, 0));
        pm.add(LocalTime.of(13, 30));
        pm.add(LocalTime.of(14, 0));
        pm.add(LocalTime.of(14, 30));
        pm.add(LocalTime.of(15, 0));
        pm.add(LocalTime.of(15, 30));
        pm.add(LocalTime.of(16, 0));
        pm.add(LocalTime.of(16, 30));
        pm.add(LocalTime.of(17, 0));
        pm.add(LocalTime.of(17, 30));
        pm.add(LocalTime.of(18, 0));
        pm.add(LocalTime.of(18, 30));
    }
}

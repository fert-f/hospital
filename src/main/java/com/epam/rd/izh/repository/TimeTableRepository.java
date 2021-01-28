package com.epam.rd.izh.repository;

import com.epam.rd.izh.dto.TimeTableDto;
import com.epam.rd.izh.mappers.TimeTableMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public class TimeTableRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    TimeTableMapper timeTableMapper;

    public List<TimeTableDto> getTimeTableForDoctorToDay(long id, LocalDate date) {
        return jdbcTemplate.query("SELECT * FROM `timetable` WHERE `doctor_id` = ? AND `date_app` = ?", timeTableMapper, id, date);
    }

    public List<TimeTableDto> getTimeTableForDoctorToDayIsFree(long id, LocalDate date) {
        return jdbcTemplate.query("SELECT * FROM `timetable` WHERE `doctor_id` = ? AND `date_app` = ? AND `patient_id` IS NULL", timeTableMapper, id, date);
    }

    public List<TimeTableDto> getTimeTableForDoctorToDayIsBusy(long id, LocalDate date) {
        return jdbcTemplate.query("SELECT * FROM `timetable` WHERE `doctor_id` = ? AND `date_app` = ? AND `patient_id` IS NOT NULL", timeTableMapper, id, date);
    }

    public boolean setDayTimeTableToDoctorForDay(long id, LocalDate date, List<LocalTime> change) {
        int i = 0;
        for (LocalTime time : change) {
            if (jdbcTemplate.update("INSERT INTO `timetable` (`date_app`, `time_app`, `doctor_id`) VALUES (?, ?, ?) ON DUPLICATE KEY UPDATE `doctor_id` = `doctor_id`", date, time, id) > 0) {
                i++;
            }
        }
        return i > 0;
    }

    public boolean saveRecordOfAppointment (long doctorId, LocalDate date, LocalTime time, long patientId, String record) {
        return jdbcTemplate.update("UPDATE `timetable` SET `record` = ? , visit = `1` WHERE `date_app` = ? AND `time_app` = ? AND `doctor_id` = ? AND `patient_id` = ?", record, date, time, doctorId , patientId ) > 0;
    }

    public TimeTableDto getRecordOfAppointment (long id, LocalDate date, LocalTime time, long patientId ) {
         List<TimeTableDto> list = jdbcTemplate.query("SELECT * FROM `timetable` WHERE `date_app` = ? AND `time_app` = ? AND `doctor_id` = ? AND `patient_id` = ? LIMIT 1", timeTableMapper, date, time, id , patientId );
        return list.get(0);
    }

    public boolean recordPatient (long doctorId, LocalDate date, LocalTime time, long patientId ) {
        return jdbcTemplate.update("UPDATE `timetable` SET `patient_id` = ? WHERE `date_app` = ? AND `time_app` = ? AND `doctor_id` = ? AND `patient_id` = NULL", patientId, date, time, doctorId ) > 0;
    }

    public boolean unRecordPatient (long doctorId, LocalDate date, LocalTime time, long patientId ) {
        return jdbcTemplate.update("UPDATE `timetable` SET `patient_id` = NULL WHERE `date_app` = ? AND `time_app` = ? AND `doctor_id` = ? AND `patient_id` = ?", date, time, doctorId , patientId ) > 0;
    }

    public List<TimeTableDto> getRecordsOfVisits (LocalDate fDate,LocalDate sDate) {
        jdbcTemplate.query("SELECT * FROM `timetable` WHERE `date_app` BETWEEN ? AND ?",timeTableMapper, fDate,sDate);
        return null;
    }
}

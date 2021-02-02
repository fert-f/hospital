package com.epam.rd.izh.repository;

import com.epam.rd.izh.dto.AppointmentDto;
import com.epam.rd.izh.dto.TimeTableDto;
import com.epam.rd.izh.mappers.AppointmentMapper;
import com.epam.rd.izh.mappers.TimeTableMapper;
import com.epam.rd.izh.util.TimeHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;

@Repository
public class TimeTableRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    TimeTableMapper timeTableMapper;

    @Autowired
    AppointmentMapper appointmentMapper;

    @Autowired
    TimeHolder timeHolder;

    public LinkedList<TimeTableDto> getTimeTableForDoctorToDay(long id, LocalDate date) {
        return new LinkedList<>(jdbcTemplate.query("SELECT * FROM `timetable` WHERE `doctor_id` = ? AND `date_app` = ?", timeTableMapper, id, date));
    }

    public List<TimeTableDto> getTimeTableForDoctorToDayIsFree(long id, LocalDate date) {
        return jdbcTemplate.query("SELECT * FROM `timetable` WHERE `doctor_id` = ? AND `date_app` = ? AND `patient_id` IS NULL", timeTableMapper, id, date);
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

    public boolean deleteDayTimeTableToDoctorForDay(long id, LocalDate date, List<LocalTime> change) {
        int i = 0;
        for (LocalTime time : change) {
            if (jdbcTemplate.update("DELETE FROM `timetable` WHERE `date_app` = ? AND `time_app` = ? AND `doctor_id` = ? ", date, time, id) > 0) {
                i++;
            }
        }
        return i > 0;
    }

    public boolean recordPatient(long doctorId, LocalDate date, LocalTime time, long patientId) {
        return jdbcTemplate.update("UPDATE `timetable` SET `patient_id` = ? WHERE `date_app` = ? AND `time_app` = ? AND `doctor_id` = ? ", patientId, date, time, doctorId) > 0;
    }

    public void unRecordPatient(long recId, long patientId) {
        jdbcTemplate.update("UPDATE `timetable` SET `patient_id` = IF (`patient_id` = ?, NULL, `patient_id`) WHERE rec_id = ?", patientId, recId);
    }

    public boolean isChangeWork(long id, LocalDate date, int change) {
        LocalTime responseTime;
        if (change == 1) {
            responseTime = TimeHolder.am.get(0);
        } else if (change == 2) {
            responseTime = TimeHolder.pm.get(0);
        } else {
            return false;
        }
        List<TimeTableDto> tableList = jdbcTemplate.query("SELECT * FROM `timetable` WHERE `doctor_id` = ? AND `date_app` = ? AND `time_app` = ?",
                timeTableMapper, id, date, responseTime);
        return !tableList.isEmpty();
    }

    public TimeTableDto getTimeTableRecordById(long recId) {
        return jdbcTemplate.query("SELECT * FROM `timetable` WHERE `rec_id` = ?", timeTableMapper, recId).get(0);
    }

    public List<AppointmentDto> getPatientAppointments(long patientId, LocalDate date) {
        return jdbcTemplate.query("SELECT z.*, doctor_details.specialty, doctor_details.specification, doctor_details.experience FROM `doctor_details` INNER JOIN" +
                " (SELECT y.*,users.user_surname as doctorSurname, users.user_name as doctorName FROM `users` INNER JOIN" +
                " (SELECT x.*,users.user_name as patientName, users.user_surname as patientSurname, users.user_birthday as patientBirthday " +
                " FROM `users` INNER JOIN (SELECT * FROM `timetable` WHERE `patient_id` = ? AND `date_app` >= ?) AS x" +
                " ON users.user_id = x.patient_id) AS y" +
                " ON users.user_id = y.doctor_id) AS z" +
                " ON doctor_details.dd_user_id = z.doctor_id", appointmentMapper, patientId, date);
    }

    public List<AppointmentDto> getPatientOldAppointmentsToReview(long patientId, LocalDate date) {
        return jdbcTemplate.query("SELECT * FROM\n" +
                " (SELECT z.*, doctor_details.specialty, doctor_details.specification, doctor_details.experience FROM `doctor_details` INNER JOIN\n" +
                " (SELECT y.*,users.user_surname as doctorSurname, users.user_name as doctorName FROM `users` INNER JOIN\n" +
                " (SELECT x.*,users.user_name as patientName, users.user_surname as patientSurname, users.user_birthday as patientBirthday \n" +
                " FROM `users` INNER JOIN (SELECT * FROM `timetable` WHERE `patient_id` = ? AND visit = 1 AND`date_app` BETWEEN ? AND ?) AS x\n" +
                " ON users.user_id = x.patient_id) AS y\n" +
                " ON users.user_id = y.doctor_id) AS z\n" +
                " ON doctor_details.dd_user_id = z.doctor_id) AS k WHERE k.rec_id NOT IN (SELECT `rec_id` FROM `reviews`)", appointmentMapper, patientId, date.minusDays(14), date);
    }

    public boolean patientDidNotCome(long recId) {
        return jdbcTemplate.update("UPDATE `timetable` SET visit = 0 WHERE `rec_id` = ? ", recId) > 0;
    }

    public boolean patientCome(long recId) {
        return jdbcTemplate.update("UPDATE `timetable` SET visit = 1 WHERE `rec_id` = ? ", recId) > 0;
    }

    public boolean saveRecordOfAppointment(long recId, String record) {
        return jdbcTemplate.update("UPDATE `timetable` SET `record` = ? WHERE `rec_id` = ? ", record, recId) > 0;
    }

    public List<TimeTableDto> getDoctorRecordsOffPatient(long doctorId, long patientId) {
        return jdbcTemplate.query("SELECT * FROM `timetable` WHERE `doctor_id` = ? AND `patient_id` = ? AND `visit` IS NOT NULL", timeTableMapper, doctorId, patientId);
    }


    public List<String> getDoctorsDaysWorked(long doctorId) {
        return jdbcTemplate.queryForList("SELECT DISTINCT `date_app` FROM `timetable` WHERE `doctor_id` = ? AND `date_app` < ?", String.class, doctorId, timeHolder.getDate().plusDays(1));
    }
}

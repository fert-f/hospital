package com.epam.rd.izh.service;

import com.epam.rd.izh.dto.TimeTableDto;
import com.epam.rd.izh.entity.MyUser;
import com.epam.rd.izh.repository.TimeTableRepository;
import com.epam.rd.izh.repository.UserRepository;
import com.epam.rd.izh.util.TimeHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class DoctorService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    TimeTableRepository timeTableRepository;

    @Autowired
    TimeHolder timeHolder;

    public Map<TimeTableDto, MyUser> getDoctorDayAppointments(Authentication authentication) {
        Map<TimeTableDto, MyUser> result = new LinkedHashMap<>();
        List<TimeTableDto> time = timeTableRepository.getTimeTableForDoctorToDay(userRepository.getUserByLogin(authentication.getName()).getId(), timeHolder.getDate());
        for (TimeTableDto timeTableDto : time) {
            result.put(timeTableDto, userRepository.getUserById(timeTableDto.getPatient_id()));
        }
        return result;
    }

    public Map<LocalDate, Map<TimeTableDto, MyUser>> getDoctorTimeTableOnTwoWeeks(Authentication authentication) {
        Map<LocalDate, Map<TimeTableDto, MyUser>> result = new LinkedHashMap<>();
        for (int i = 1; i <= 14; i++) {
            LocalDate date = timeHolder.getDate().plusDays(i);
            List<TimeTableDto> time = timeTableRepository.getTimeTableForDoctorToDay(userRepository.getUserByLogin(authentication.getName()).getId(), date);
            if (time.size() > 0) {
                Map<TimeTableDto, MyUser> oneResult = new LinkedHashMap<>();
                for (TimeTableDto timeTableDto : time) {
                    oneResult.put(timeTableDto, userRepository.getUserById(timeTableDto.getPatient_id()));
                }
                result.put(date, oneResult);
            }
        }
        return result;
    }

    public boolean patientDidNotCome(long recId) {
        return timeTableRepository.patientDidNotCome(recId);
    }

    public boolean patientCome(long recId) {
        return timeTableRepository.patientCome(recId);
    }

    public boolean saveRecordOfAppointment(long recId, String record) {
        return timeTableRepository.saveRecordOfAppointment(recId, record);
    }

    public List<TimeTableDto> getOLdPatientRecords(Authentication authentication, long patientId) {
        return timeTableRepository.getDoctorRecordsOffPatient(userRepository.getUserByLogin(authentication.getName()).getId(), patientId);
    }

    public List<MyUser> getDoctorPatients(Authentication authentication) {
        return userRepository.getDoctorPatients(userRepository.getUserByLogin(authentication.getName()).getId());
    }

    public MyUser getUserById(long id) {
        return userRepository.getUserById(id);
    }
}

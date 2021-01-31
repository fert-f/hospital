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
import java.util.*;

@Service
public class DoctorService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    TimeTableRepository timeTableRepository;

    @Autowired
    TimeHolder timeHolder;

    public Map<TimeTableDto, MyUser > getDoctorDayAppointments (Authentication authentication){
        Map<TimeTableDto, MyUser> result = new LinkedHashMap<>();
        List<TimeTableDto> time = timeTableRepository.getTimeTableForDoctorToDay(userRepository.getUserByLogin(authentication.getName()).getId(),timeHolder.getDate());
        System.out.println(time.toString());
        for (TimeTableDto timeTableDto: time) {
            result.put(timeTableDto, userRepository.getUserById(timeTableDto.getPatient_id()));
        }
        System.out.println(result.toString());
        return  result;
    }

    public Map<LocalDate,Map<MyUser, TimeTableDto>> getDoctorTimeTableOnTwoWeeks (Authentication authentication){
        return null;
    }

    public boolean patientDidNotCome (long recId) {
        return timeTableRepository.patientDidNotCome(recId);
    }

    public boolean patientCome (long recId) {
        return timeTableRepository.patientCome(recId);
    }

    public boolean saveRecordOfAppointment(long recId, String record) {
        return timeTableRepository.saveRecordOfAppointment(recId,record);
    }
}

package com.epam.rd.izh.service;

import com.epam.rd.izh.dto.DoctorDetailsDto;
import com.epam.rd.izh.dto.DoctorDto;
import com.epam.rd.izh.dto.RegisteredDoctorDto;
import com.epam.rd.izh.dto.TimeTableDto;
import com.epam.rd.izh.repository.DoctorRepository;
import com.epam.rd.izh.repository.TextContentRepository;
import com.epam.rd.izh.repository.TimeTableRepository;
import com.epam.rd.izh.repository.UserRepository;
import com.epam.rd.izh.util.TimeHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class AdminService {

    @Autowired
    TextContentRepository textContentRepository;

    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TimeTableRepository timeTableRepository;

    @Autowired
    TimeHolder timeHolder;

    public List<DoctorDto> getListOfDoctors() {
        return doctorRepository.getAllDoctors();
    }

    public List<TimeTableDto> getOldRecordsOfVisits() {
        return timeTableRepository.getRecordsOfVisits(LocalDate.of(1970, 1, 1), timeHolder.getDate());
    }

    public boolean registerDoctor(RegisteredDoctorDto doctorDto) {
        if (userRepository.registerUser(doctorDto)) {
            return doctorRepository.saveDoctorDetails(userRepository.getUserByLogin(doctorDto.getLogin()).getId(),
                    doctorDto.getSpecialty(), doctorDto.getSpecification(), doctorDto.getExperience());
        }
        return false;
    }

    public boolean saveDoctorDetails (DoctorDetailsDto doctorDetailsDto) {
        return doctorRepository.saveDoctorDetails(doctorDetailsDto.getId(),doctorDetailsDto.getSpecialty(), doctorDetailsDto.getSpecification(), doctorDetailsDto.getExperience());
    }

    public DoctorDto getDoctorByID (long id){
        return doctorRepository.getDoctorById(id);
    }

    public List<TimeTableDto> getDateTimeTableForDoctorToDate (long id, String date) {
        return timeTableRepository.getTimeTableForDoctorToDay(id,LocalDate.parse(date));
    }

    public boolean isChangeDoctorWork (long id, LocalDate date, int change) {
        return timeTableRepository.isChangeWork(id,date,change);
    }

    public boolean isChangeDoctorWork (long id, String date, int change) {
        return timeTableRepository.isChangeWork(id,LocalDate.parse(date),change);
    }

    public boolean changeTimeTableToDoctorForDay (long id, String date, List<LocalTime> change, String trigger) {
        if (trigger != null){
            return timeTableRepository.setDayTimeTableToDoctorForDay(id, LocalDate.parse(date), change);
        }else {
            return timeTableRepository.deleteDayTimeTableToDoctorForDay(id, LocalDate.parse(date), change);
        }
    }

    public List<String> getDoctorsNamesLists () {
        List <String> doctorsNamesList = new ArrayList<>();
        for (DoctorDto doctor : doctorRepository.getAllDoctors()) {
            doctorsNamesList.add(doctor.getName()+" "+doctor.getSurname());
        }
        return doctorsNamesList;
    }
}

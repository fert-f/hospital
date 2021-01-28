package com.epam.rd.izh.service;

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
}

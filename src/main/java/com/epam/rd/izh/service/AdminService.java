package com.epam.rd.izh.service;

import com.epam.rd.izh.dto.*;
import com.epam.rd.izh.entity.MyUser;
import com.epam.rd.izh.repository.*;
import com.epam.rd.izh.util.TimeHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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
    ReviewRepository reviewRepository;

    @Autowired
    TimeHolder timeHolder;

    public List<DoctorDto> getListOfDoctors() {
        return doctorRepository.getAllDoctors();
    }

    public boolean registerDoctor(RegisteredDoctorDto doctorDto) {
        if (userRepository.registerUser(doctorDto)) {
            return doctorRepository.saveDoctorDetails(userRepository.getUserByLogin(doctorDto.getLogin()).getId(),
                    doctorDto.getSpecialty(), doctorDto.getSpecification(), doctorDto.getExperience());
        }
        return false;
    }

    public boolean saveDoctorDetails(DoctorDetailsDto doctorDetailsDto) {
        return doctorRepository.saveDoctorDetails(doctorDetailsDto.getId(), doctorDetailsDto.getSpecialty(), doctorDetailsDto.getSpecification(), doctorDetailsDto.getExperience());
    }

    public DoctorDto getDoctorByID(long id) {
        return doctorRepository.getDoctorById(id);
    }


    public boolean isChangeDoctorWork(long id, String date, int change) {
        return timeTableRepository.isChangeWork(id, LocalDate.parse(date), change);
    }

    public boolean changeTimeTableToDoctorForDay(long id, String date, List<LocalTime> change, String trigger) {
        if (trigger != null) {
            return timeTableRepository.setDayTimeTableToDoctorForDay(id, LocalDate.parse(date), change);
        } else {
            return timeTableRepository.deleteDayTimeTableToDoctorForDay(id, LocalDate.parse(date), change);
        }
    }

    public List<MyUser> getDoctorPatients(long doctorId) {
        return userRepository.getDoctorPatients(doctorId);
    }

    public Map<ReviewDto, MyUser> getReviewsOnDoctor(long doctor_id) {
        Map<ReviewDto, MyUser> result = new LinkedHashMap<>();
        List<ReviewDto> reviews = reviewRepository.getReviewsOnDoctor(doctor_id);
        for (ReviewDto review : reviews) {
            result.put(review, userRepository.getUserById(review.getRw_patient_id()));
        }
        return result;
    }

    public List<TimeTableDto> getPatientHistoryForAdmin(long doctorId, long patientId) {
        return timeTableRepository.getDoctorRecordsOffPatient(doctorId, patientId);
    }

    public MyUser getUserById(long id) {
        return userRepository.getUserById(id);
    }

    public Map<TimeTableDto, MyUser> getDoctorDayAppointments(long doctorId, String date) {
        Map<TimeTableDto, MyUser> result = new LinkedHashMap<>();
        List<TimeTableDto> time = timeTableRepository.getTimeTableForDoctorToDay(doctorId, LocalDate.parse(date));
        for (TimeTableDto timeTableDto : time) {
            result.put(timeTableDto, userRepository.getUserById(timeTableDto.getPatient_id()));
        }
        return result;
    }

    public List<String> getDoctorsDayOfWork(long doctorId) {
        LinkedList<String> result = new LinkedList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-d");
        List<String> incomeList = timeTableRepository.getDoctorsDaysWorked(doctorId);
        for (String date : incomeList) {
            result.add(LocalDate.parse(date).format(formatter));
        }
        return result;
    }
}

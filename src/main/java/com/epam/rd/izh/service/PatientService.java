package com.epam.rd.izh.service;

import com.epam.rd.izh.dto.AppointmentDto;
import com.epam.rd.izh.dto.DoctorDto;
import com.epam.rd.izh.dto.TimeTableDto;
import com.epam.rd.izh.repository.DoctorRepository;
import com.epam.rd.izh.repository.ReviewRepository;
import com.epam.rd.izh.repository.TimeTableRepository;
import com.epam.rd.izh.repository.UserRepository;
import com.epam.rd.izh.util.TimeHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

@Service
public class PatientService {

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

    public Map<LocalDate, List<LocalTime>> appointmentList(long doctorId, LocalDate date) {
        Map<LocalDate, List<LocalTime>> resultMap = new TreeMap<>();
        for (int i = 1; i <= 14; i++) {
            List<LocalTime> timeList = timeTableRepository.getTimeTableForDoctorToDayIsFree(doctorId, date.plusDays(i))
                    .stream().map(TimeTableDto::getTime_app).collect(Collectors.toList());
            if (timeList.size() != 0) {
                resultMap.put(date.plusDays(i), timeList);
            }
        }

        return resultMap;
    }

    public long getPatientId(Authentication authentication) {
        return userRepository.getUserByLogin(authentication.getName()).getId();
    }

    public DoctorDto getDoctorByID(long id) {
        return doctorRepository.getDoctorById(id);
    }

    public boolean recordPatient(long doctorId, String date, String time, long patientId) {
        return timeTableRepository.recordPatient(doctorId, LocalDate.parse(date), LocalTime.parse(time), patientId);
    }

    public List<AppointmentDto> getPatientAppointments(Authentication authentication) {
        return timeTableRepository.getPatientAppointments(userRepository.getUserByLogin(authentication.getName()).getId(), timeHolder.getDate());
    }

    public List<AppointmentDto> getPatientOldAppointmentsToReview(Authentication authentication) {
        return timeTableRepository.getPatientOldAppointmentsToReview(userRepository.getUserByLogin(authentication.getName()).getId(),
                timeHolder.getDate());
    }

    public void unRecordPatient(long recId, Authentication authentication) {
        timeTableRepository.unRecordPatient(recId, userRepository.getUserByLogin(authentication.getName()).getId());
    }

    public boolean saveReview(long rec_id, String review) {
        TimeTableDto record = timeTableRepository.getTimeTableRecordById(rec_id);
        return reviewRepository.saveReview(record.getPatient_id(), record.getDoctor_id(), record.getRec_id(), review);
    }
}

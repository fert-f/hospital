package com.epam.rd.izh.controller;

import com.epam.rd.izh.dto.AppointmentDto;
import com.epam.rd.izh.dto.ReviewDto;
import com.epam.rd.izh.entity.FormsData;
import com.epam.rd.izh.service.PatientService;
import com.epam.rd.izh.util.TimeHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PatientController {

    @Autowired
    TimeHolder timeHolder;

    @Autowired
    PatientService patientService;

    @GetMapping("/patient/doctors")
    public String adminDoctors(Model model) {
        if (!model.containsAttribute("doctors")) {
            model.addAttribute("doctors", patientService.getListOfDoctors());
        }
        return "doctors";
    }

    @GetMapping("/patient/doctor/{doctorId}")
    public String adminDoctorTimeWork(@PathVariable long doctorId, Model model, Authentication authentication) {
        model.addAttribute("timeForm", new FormsData());
        model.addAttribute("doctor", patientService.getDoctorByID(doctorId));
        model.addAttribute("appointmentList", patientService.appointmentList(doctorId, timeHolder.getDate()));
        model.addAttribute("userId", patientService.getPatientId(authentication));
        return "doctor";
    }

    @PostMapping("/patient/getAppointment")
    public String signUpToAppointment(@ModelAttribute("timeForm") FormsData appointment, Model model) {
        if (!patientService.recordPatient(appointment.getDoctorId(), appointment.getDate(), appointment.getTime_app(), appointment.getPatient_id())) {
            model.addAttribute("updateDoctorError", "Ошибка при попытке записи");
            return "redirect:/patient/doctor/" + appointment.getDoctorId();
        }
        return "redirect:/patient/doctors";
    }

    @PostMapping("/patient/dropAppointment")
    @ResponseBody
    public String dropAppointment(@RequestParam long recId, Authentication authentication) {
        patientService.unRecordPatient(recId, authentication);
        return "/patient/visits";
    }

    @GetMapping("/patient/visits")
    public String getAppointments(Model model, Authentication authentication) {
        model.addAttribute("timePatientRecord", patientService.getPatientAppointments(authentication));
        return "visits";
    }

    @GetMapping("/patient/reviews")
    public String reviews(Model model, Authentication authentication) {
        model.addAttribute("docReviewsForm", new AppointmentDto());
        model.addAttribute("appointments", patientService.getPatientOldAppointmentsToReview(authentication));
        return "reviews";
    }

    @PostMapping("/patient/reviewForm")
    public String getReviewForm(@RequestParam long recId, Model model) {
        model.addAttribute("reviewForm", new ReviewDto());
        model.addAttribute("recId", recId);
        return "/modules/reviewForm";
    }

    @PostMapping("/patient/saveReview")
    public String saveReview(@ModelAttribute("reviewForm") ReviewDto review, Model model) {
        if (patientService.saveReview(review.getRec_id(), review.getReview())) {
            model.addAttribute("updateError", "Сохранение отзыва не удалось");
        }
        return "redirect:/patient/reviews";
    }
}

package com.epam.rd.izh.controller;

import com.epam.rd.izh.dto.DoctorDetailsDto;
import com.epam.rd.izh.dto.RegisteredDoctorDto;
import com.epam.rd.izh.dto.TimeTableDto;
import com.epam.rd.izh.entity.FormsData;
import com.epam.rd.izh.service.AdminService;
import com.epam.rd.izh.util.TimeHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class AdminController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    AdminService adminService;

    @Autowired
    TimeHolder timeHolder;


    @GetMapping("/admin/doctors")
    public String adminDoctors(Model model) {
        if (!model.containsAttribute("doctors")) {
            model.addAttribute("doctors", adminService.getListOfDoctors());
        }
        if (!model.containsAttribute("editDoctorForm")) {
            model.addAttribute("editDoctorForm", new DoctorDetailsDto());
        }
        return "doctors";
    }

    @PostMapping("/admin/updateDoctor")
    public String adminUpdateDoctor(@Valid @ModelAttribute("editDoctorForm") DoctorDetailsDto registeredDoctorDetails,
                                    BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("updateDoctorError", "Что-то пошло не так");
            return "redirect:redirect:/admin/doctors";
        }
        if (adminService.saveDoctorDetails(registeredDoctorDetails)) {
            model.addAttribute("updateDoctorError", "Изменение пользователя неудалось");
        }
        return "redirect:/admin/doctors";
    }

    @GetMapping("/admin/doctorTimeWork/{doctorId}")
    public String adminDoctorTimeWork(@PathVariable long doctorId, Model model) {
        model.addAttribute("tomorrow", timeHolder.getDate().plusDays(1));
        if (!model.containsAttribute("doctor")) {
            model.addAttribute("doctor", adminService.getDoctorByID(doctorId));
        }
        if (!model.containsAttribute("dayForm")) {
            model.addAttribute("dayForm", new TimeTableDto());
        }
        return "doctorTimeWork";
    }

    @PostMapping("/admin/doctorTimeWork/{doctorId}/getDate")
    @GetMapping("/admin/doctorTimeWork/{doctorId}/getDate")
    private String getTimeTableForDate(@RequestParam String date, @PathVariable long doctorId, Model model) {
        model.addAttribute("doctorId", doctorId);
        model.addAttribute("dateForQuery", date);
        model.addAttribute("dayTimeTableForm", new FormsData());
        model.addAttribute("firstChange", adminService.isChangeDoctorWork(doctorId, date, 1));
        model.addAttribute("secondChange", adminService.isChangeDoctorWork(doctorId, date, 2));
        return "/modules/setTimeTable";
    }

    @PostMapping("/admin/doctorTimeWork/{doctorId}/setDate")
    private String saveTimeTableForDate(@ModelAttribute("dayTimeTableForm") FormsData dayTimeTableForm, @PathVariable long doctorId, Model model) {
        if (adminService.changeTimeTableToDoctorForDay(doctorId, dayTimeTableForm.getDate(), TimeHolder.pm, dayTimeTableForm.getPm()) ||
                adminService.changeTimeTableToDoctorForDay(doctorId, dayTimeTableForm.getDate(), TimeHolder.am, dayTimeTableForm.getAm())) {
            model.addAttribute("updateDoctorError", "Пользователь небыл изменен");
        }
        return "redirect:/admin/doctorTimeWork/" + doctorId;
    }

    @GetMapping("/admin/createDoctor")
    public String adminCreateDoctor(Model model) {
        if (!model.containsAttribute("registrationDoctorForm")) {
            model.addAttribute("registrationDoctorForm", new RegisteredDoctorDto());
        }
        return "createDoctor";
    }

    @PostMapping("/admin/createDoctor/proceed")
    public String processCreateDoctor(@Valid @ModelAttribute("registrationDoctorForm") RegisteredDoctorDto registeredDoctor,
                                      BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("updateError", "Заполните коректно все поля");
          return "redirect:/admin/createDoctor";
        }
        registeredDoctor.setRole("DOCTOR");
        registeredDoctor.setPassword(passwordEncoder.encode(registeredDoctor.getPassword()));
        if (adminService.registerDoctor(registeredDoctor)) {
            return "redirect:/admin/doctors";
        }
        return "redirect:/admin/createDoctor";
    }

    @GetMapping("/admin/visitRecords")
    public String adminVisitRecords(Model model) {
        model.addAttribute("doctorsLists", adminService.getListOfDoctors());
        model.addAttribute("docForm", new FormsData());
        return "visitRecords";
    }

    @GetMapping("/admin/reviews")
    public String adminReviews(Model model) {
        model.addAttribute("doctorsLists", adminService.getListOfDoctors());
        model.addAttribute("docReviewsForm", new FormsData());
        return "reviews";
    }

    @PostMapping("/admin/getSearchMenu")
    public String getSearchMenu(Model model, @RequestParam long doctorId) {
        model.addAttribute("searchForm", new FormsData());
        model.addAttribute("patientsList", adminService.getDoctorPatients(doctorId));
        model.addAttribute("doctor", adminService.getDoctorByID(doctorId));
        model.addAttribute("doctorDaysWorked", adminService.getDoctorsDayOfWork(doctorId));
        return "/modules/searchMenu";
    }

    @PostMapping("/admin/getReviews")
    public String getReviews(Model model, @RequestParam long doctorId) {
        model.addAttribute("reviewsList", adminService.getReviewsOnDoctor(doctorId));
        return "/modules/reviewForm";
    }

    @PostMapping("/admin/getPatientHistory")
    public String getPatientHistoryForAdmin(@RequestParam long doctorId, @RequestParam long patientId, Model model) {
        model.addAttribute("patient", adminService.getUserById(patientId));
        model.addAttribute("patientRecords", adminService.getPatientHistoryForAdmin(doctorId, patientId));
        return "/modules/patientHistory";
    }

    @PostMapping("/admin/getDayHistory")
    public String getDayHistory(@RequestParam long doctorId, @RequestParam String date, Model model) {
        model.addAttribute("todayMap", adminService.getDoctorDayAppointments(doctorId, date));
        return "/modules/oneDay";
    }
}
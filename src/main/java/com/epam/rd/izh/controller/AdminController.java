package com.epam.rd.izh.controller;

import com.epam.rd.izh.dto.DoctorDetailsDto;
import com.epam.rd.izh.dto.DoctorDto;
import com.epam.rd.izh.dto.RegisteredDoctorDto;
import com.epam.rd.izh.dto.RegisteredUserDto;
import com.epam.rd.izh.repository.DoctorRepository;
import com.epam.rd.izh.service.AdminService;
import com.epam.rd.izh.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    AdminService adminService;


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

    @GetMapping("/admin/doctorDetails")
    public String adminDoctorDetails() {

        return "/admin/doctorDetails";
    }

    @GetMapping("/admin/doctorTimeWork")
    public String adminDoctorTimeWork() {

        return "doctorTimeWork";
    }

    @GetMapping("/admin/createDoctor")
    public String adminCreateDoctor(Model model) {
        if(!model.containsAttribute("registrationDoctorForm")){
            model.addAttribute("registrationDoctorForm", new RegisteredDoctorDto());
        }
        return "createDoctor";
    }

    @PostMapping("/admin/createDoctor/proceed")
    public String processCreateDoctor (@Valid @ModelAttribute("registrationDoctorForm") RegisteredDoctorDto registeredDoctor,
    BindingResult bindingResult, RedirectAttributes redirectAttributes) {

//    if (bindingResult.hasErrors()) {
//      //логика отображения ошибки, не является обязательной
//      //...
//      //...
//      return "redirect:/admin/createDoctor";
//    }
        registeredDoctor.setRole("DOCTOR");
        registeredDoctor.setPassword(passwordEncoder.encode(registeredDoctor.getPassword()));
        if (adminService.registerDoctor(registeredDoctor)) {
            return "redirect:/admin/doctors";
        }
        return "redirect:/admin/createDoctor";
    }

    @GetMapping("/admin/visitRecords")
    public String adminVisitRecords() {

        return "/admin/visitRecords";
    }
}
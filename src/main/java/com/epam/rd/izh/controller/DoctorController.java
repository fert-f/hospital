package com.epam.rd.izh.controller;

import com.epam.rd.izh.dto.RegisteredDoctorDto;
import com.epam.rd.izh.dto.TimeTableDto;
import com.epam.rd.izh.repository.UserRepository;
import com.epam.rd.izh.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class DoctorController {

    @Autowired
    DoctorService doctorService;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/doctor/today")
    public String today (Model model, Authentication authentication){
        model.addAttribute("todayMap", doctorService.getDoctorDayAppointments(authentication));
        model.addAttribute("recordForm", new TimeTableDto());
        return "today";
    }

    @GetMapping("/doctor/didNotCome/${recId}")
    public String didNotCome (@PathVariable long recId, Model model) {
        if (!doctorService.patientDidNotCome(recId)) {
            model.addAttribute("updateError", "Ошибка обновления");
        }
        return "redirect:/doctor/today";
    }

    @PostMapping("/doctor/patientCome/")
    @ResponseBody
    public String come (@RequestParam long recId) {
        boolean result = doctorService.patientCome(recId);
        if (!result) {
            return "false";
        }
        return "true";
    }


    @PostMapping("/doctor/saveRecord")
    public String saveRecord (@ModelAttribute("recordForm")TimeTableDto recordSaveForm, Model model) {
        doctorService.saveRecordOfAppointment(recordSaveForm.getRec_id(),recordSaveForm.getRecord());
        return "redirect:/doctor/today";
    }
}

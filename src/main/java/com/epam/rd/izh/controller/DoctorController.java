package com.epam.rd.izh.controller;

import com.epam.rd.izh.dto.TimeTableDto;
import com.epam.rd.izh.entity.FormsData;
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

    @GetMapping("/doctor/today")
    public String today(Model model, Authentication authentication) {
        model.addAttribute("todayMap", doctorService.getDoctorDayAppointments(authentication));
        model.addAttribute("recordForm", new TimeTableDto());
        return "today";
    }

    @GetMapping("/doctor/didNotCome/{recId}")
    public String didNotCome(@PathVariable long recId, Model model) {
        if (!doctorService.patientDidNotCome(recId)) {
            model.addAttribute("updateError", "Ошибка обновления");
        }
        return "redirect:/doctor/today";
    }

    @PostMapping("/doctor/patientCome/")
    @ResponseBody
    public String come(@RequestParam long recId) {
        boolean result = doctorService.patientCome(recId);
        if (!result) {
            return "false";
        }
        return "true";
    }

    @PostMapping("/doctor/saveRecord")
    public String saveRecord(@ModelAttribute("recordForm") TimeTableDto recordSaveForm, Model model) {
        if (!doctorService.saveRecordOfAppointment(recordSaveForm.getRec_id(), recordSaveForm.getRecord())) {
            model.addAttribute("updateError", "Ошибка обновления записи");
        }
        return "redirect:/doctor/today";
    }

    @GetMapping("/doctor/timeTable")
    public String timeTable(Model model, Authentication authentication) {
        model.addAttribute("manyMap", doctorService.getDoctorTimeTableOnTwoWeeks(authentication));
        model.addAttribute("recordForm", new TimeTableDto());
        return "timeTable";
    }

    @GetMapping("/doctor/visitRecords")
    public String adminVisitRecords(Model model, Authentication authentication) {
        model.addAttribute("patientsList", doctorService.getDoctorPatients(authentication));
        model.addAttribute("docForm", new FormsData());
        return "visitRecords";
    }

    @PostMapping("/doctor/getPatientHistory")
    public String getPatientHistory(Model model, Authentication authentication, @RequestParam long patientId) {
        model.addAttribute("patient", doctorService.getUserById(patientId));
        model.addAttribute("patientRecords", doctorService.getOLdPatientRecords(authentication, patientId));
        return "/modules/patientHistory";
    }
}

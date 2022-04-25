package com.myclinik.controller;

import com.myclinik.model.Appointment;
import com.myclinik.service.IAppointmentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class CalendarController {
	@Autowired
	private IAppointmentService appointmentService;

	@GetMapping("/home")
	public String goToHomePage(Model model) {
		var appointments = (List<Appointment>) appointmentService.findAll();
		model.addAttribute("appointments", appointments);
        return ("calendar");
    }
}

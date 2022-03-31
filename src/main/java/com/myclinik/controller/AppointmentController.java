package com.myclinik.controller;

import com.myclinik.model.Appointment;
import com.myclinik.service.IAppointmentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class AppointmentController {
	@Autowired
	private IAppointmentService appointmentService;

	@GetMapping("/showAppointments")
	public String findAppointments(Model model) {
		var appointments = (List<Appointment>) appointmentService.findAll();
		model.addAttribute("appointments", appointments);
		return "showAppointments";
	}
}

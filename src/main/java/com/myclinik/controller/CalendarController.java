package com.myclinik.controller;

import com.myclinik.model.Appointment;
import com.myclinik.service.IAppointmentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CalendarController {
	@Autowired
	private IAppointmentService appointmentService;

	@GetMapping("/calendar")
	public String goToCalendar(
		Model model,
		@RequestParam(value = "initialDate", required = false) String initialDate,
		@RequestParam(value = "newAppointmentId", required = false) Long newAppointmentId,
		@RequestParam(value = "selectedAppointmentId", required = false) Long selectedAppointmentId,
		@RequestParam(value = "editedAppointmentId", required = false) Long editedAppointmentId
	){
		model.addAttribute("initialDate", initialDate);
		model.addAttribute("newAppointmentId", newAppointmentId);
		model.addAttribute("selectedAppointmentId", selectedAppointmentId);
		model.addAttribute("editedAppointmentId", editedAppointmentId);

		var appointments = (List<Appointment>) appointmentService.findAll();
		model.addAttribute("appointments", appointments);
        return ("calendar");
    }
}

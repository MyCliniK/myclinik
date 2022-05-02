package com.myclinik.controller;

import com.myclinik.model.Appointment;
import com.myclinik.model.Treatment;
import com.myclinik.model.Client;
import com.myclinik.service.IAppointmentService;
import com.myclinik.service.ITreatmentService;
import com.myclinik.service.IClientService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.time.LocalDateTime;

@Controller
public class AppointmentController {

	@Autowired
	private IAppointmentService appointmentService;
	@Autowired
	private ITreatmentService treatmentService;
	@Autowired
	private IClientService clientService;

	@GetMapping("/appointments")
	public String findAppointments(Model model) {
		var appointments = (List<Appointment>) appointmentService.findAll();
		model.addAttribute("appointments", appointments);
		return "listAppointments";
	}

	@RequestMapping(value = "/appointments", params = "clientId")
	public String findAppointmentsByClient(Model model, @RequestParam("clientId") Long clientId) {
		var allAppointments = (List<Appointment>) appointmentService.findAll();
		var appointments = allAppointments.stream().filter(appointment -> appointment.getClient().getId() == clientId).collect(java.util.stream.Collectors.toList());
		model.addAttribute("appointments", appointments);
		return "listAppointments";
	}

	@RequestMapping("/appointments/new")
	public String showNewAppointmentForm(Model model) {
		Appointment appointment = new Appointment();
		var treatments = (List<Treatment>) treatmentService.findAll();
		var clients = (List<Client>) clientService.findAll();
		model.addAttribute("appointment", appointment);
		model.addAttribute("treatments", treatments);
		model.addAttribute("clients", clients);
		return "new_appointment";
	}

	@RequestMapping(value = "/appointments/new", params = "clientId")
	public String showNewAppointmentForm(Model model, @RequestParam("clientId") Long clientId) {
		Appointment appointment = new Appointment();
		appointment.setClient(clientService.findOne(clientId));
		var treatments = (List<Treatment>) treatmentService.findAll();
		var clients = (List<Client>) clientService.findAll();
		model.addAttribute("appointment", appointment);
		model.addAttribute("treatments", treatments);
		model.addAttribute("clients", clients);
		return "new_appointment";
	}

	@RequestMapping(value = "/appointments/new", params = "date")
	public String showNewAppointmentForm(Model model, @RequestParam("date") String strDate) {
		Appointment appointment = new Appointment();
		LocalDateTime date = LocalDateTime.parse(strDate);
		appointment.setAppointmentDate(date);
		var treatments = (List<Treatment>) treatmentService.findAll();
		var clients = (List<Client>) clientService.findAll();
		model.addAttribute("appointment", appointment);
		model.addAttribute("treatments", treatments);
		model.addAttribute("clients", clients);
		return "new_appointment";
	}

	@PostMapping("/appointments/new/save")
	public String saveAppointment(@ModelAttribute("appointment") Appointment appointment) {
		if (appointment.getAppointmentDate() == null) {
			appointment.setAppointmentDate(LocalDateTime.now());
		}
		appointmentService.save(appointment);
		return "redirect:/calendar?initialDate=" + appointment.getAppointmentDate() + "&newAppointmentId=" + appointment.getId();
	}

	@RequestMapping("/appointments/edit/{appointmentId}")
	public String showEditAppointment(Model model, @PathVariable(name = "appointmentId") Long appointmentId) {
		Appointment appointment = appointmentService.get(appointmentId);
		var treatments = (List<Treatment>) treatmentService.findAll();
		var clients = (List<Client>) clientService.findAll();
		model.addAttribute("appointment", appointment);
		model.addAttribute("treatments", treatments);
		model.addAttribute("clients", clients);
		return "edit_appointment";
	}

	@RequestMapping("/appointments/delete/{appointmentId}")
	public String deleteAppointment(@PathVariable(name = "appointmentId") Long appointmentId) {
		appointmentService.delete(appointmentId);
		return "redirect:/appointments";
	}

	@RequestMapping("/appointments/update")
	public String updateAppointment(@RequestParam("id") Long id, @ModelAttribute("appointment") Appointment appointment) {
		appointmentService.update(id, appointment);
		return "redirect:/calendar?editedAppointmentId=" + appointment.getId() + "&initialDate=" + appointment.getAppointmentDate();
	}

	@PostMapping("/appointments/date/update")
	public String updateAppointmentDate(@RequestParam("id") Long id, @RequestParam("date") String strDate) {
		LocalDateTime newDate = LocalDateTime.parse(strDate);
		Appointment appointment = appointmentService.get(id);
		appointment.setAppointmentDate(newDate);
		appointmentService.update(id, appointment);
		return "calendar";
	}

}

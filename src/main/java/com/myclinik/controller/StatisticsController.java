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

@Controller
public class StatisticsController {

	@Autowired
	private IAppointmentService appointmentService;
	@Autowired
	private ITreatmentService treatmentService;
	@Autowired
	private IClientService clientService;

	@GetMapping("/statistics")
	public String findAppointments(Model model) {
		var clients = (List<Client>) clientService.findAll();
		var appointments = (List<Appointment>) appointmentService.findAll();
		model.addAttribute("appointments", appointments);
		model.addAttribute("clients", clients);
		return "statistics";
	}
	@RequestMapping(value = "/statistics", params= "clientId")
	public String filterByClient(Model model, @RequestParam("clientId")  Long clientId){
        var clients = (List<Client>) clientService.findAll();
		var allAppointments = (List<Appointment>) appointmentService.findAll();
		var appointments = allAppointments.stream().filter(appointment -> appointment.getClient().getId() == clientId).collect(java.util.stream.Collectors.toList());
		model.addAttribute("clients", clients);
		model.addAttribute("appointments", appointments);
        model.addAttribute("clientId", clientId);
        return "statistics";
	}

    @PostMapping("/statistics")
    public String selectByClient(@RequestParam("client") Client client, Model model){ 
		var clients = (List<Client>) clientService.findAll();
		var allAppointments = (List<Appointment>) appointmentService.findAll();
		var appointments = allAppointments.stream().filter(appointment -> appointment.getClient().getId() == client.getId()).collect(java.util.stream.Collectors.toList());
		model.addAttribute("clients", clients);
		model.addAttribute("appointments", appointments);
        model.addAttribute("clientId", client.getId());
        return "statistics";
    }


}
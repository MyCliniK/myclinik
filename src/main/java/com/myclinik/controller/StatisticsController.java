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

import java.util.ArrayList;
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
		var treatments = (List<Treatment>) treatmentService.findAll();
		model.addAttribute("appointments", appointments);
		model.addAttribute("treatments", treatments);
		model.addAttribute("clients", clients);
		return "statistics";
	}
	@RequestMapping(value = "/statistics", params= "clientId")
	public String filterByClient(Model model, @RequestParam("clientId")  Long clientId){
        var clients = (List<Client>) clientService.findAll();
		model.addAttribute("clients", clients);
        model.addAttribute("clientId", clientId);
        return "statistics";
	}

	@RequestMapping(value = "/statistics", params= "treatmentId")
	public String filterByTreatment(Model model, @RequestParam("treatmentId")  Long treatmentId){
		var treatments = (List<Treatment>) treatmentService.findAll();
		model.addAttribute("treatments", treatments);
		model.addAttribute("treatmentId", treatmentId);
		return "statistics";
	}
	@RequestMapping(value = "/statistics", params= "appointmentPaid")
	public String filterByPaid(Model model, @RequestParam("appointmentPaid")  Boolean appointmentPaid){
		var appointments = (List<Appointment>) appointmentService.findAll();
		model.addAttribute("appointments", appointments);
		model.addAttribute("appointmentPaid", appointmentPaid);
		return "statistics";
	}
	@RequestMapping(value = "/statistics", params= "appointmentDone")
	public String filterByDone(Model model, @RequestParam("appointmentDone")  Boolean appointmentDone){
		var appointments = (List<Appointment>) appointmentService.findAll();
		model.addAttribute("appointments", appointments);
		model.addAttribute("appointmentPaid", appointmentDone);
		return "statistics";
	}

	
    @PostMapping("/statistics")
    public String filterData(Model model, @RequestParam(name = "client", required = false) Client client, @RequestParam(name= "treatment", required = false) Treatment treatment, @RequestParam(name="paidAppointment", required = false) Boolean paidAppointment, @RequestParam(name="doneAppointment", required = false) Boolean doneAppointment){
        var treatments = treatmentService.findAll();
        var clients = (List<Client>) clientService.findAll();
        var allAppointments = (List<Appointment>) appointmentService.findAll();
		var appointments = allAppointments;
        if (client != null) appointments = allAppointments.stream().filter(appointment -> appointment.getClient().getId() == client.getId()).collect(java.util.stream.Collectors.toList());
		if (treatment !=null) appointments = appointments.stream().filter(appointment -> appointment.getTreatment().getId() == treatment.getId()).collect(java.util.stream.Collectors.toList());
		if (paidAppointment != null) appointments = appointments.stream().filter(appointment -> appointment.getPaid() == paidAppointment).collect(java.util.stream.Collectors.toList());
        if (doneAppointment != null) appointments = appointments.stream().filter(appointment -> appointment.getDone() == doneAppointment).collect(java.util.stream.Collectors.toList());
		model.addAttribute("treatments",treatments);
        model.addAttribute("clients", clients);
        model.addAttribute("appointments", appointments);
        if (client != null) model.addAttribute("clientId", client.getId());
        if (treatment != null) model.addAttribute("treatmentId", treatment.getId());
		model.addAttribute("paidAppointment", paidAppointment);
		model.addAttribute("doneAppointment", doneAppointment);
        return "statistics";
    }

	

}
//).stream().filter(appointment -> appointment.getTreatment().getId() == treatmentId).collect(java.util.stream.Collectors.toList());
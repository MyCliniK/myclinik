package com.myclinik.controller;

import com.myclinik.model.Appointment;
import com.myclinik.model.Treatment;
import com.myclinik.model.Client;
import com.myclinik.service.IAppointmentService;
import com.myclinik.service.ITreatmentService;
import com.myclinik.service.IClientService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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

import net.bytebuddy.asm.Advice.Local;

import java.time.LocalDateTime;
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

	private LocalDateTime inicioDate;
	private LocalDateTime finalDate;

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
        System.out.println("Has entrado al request de clientid");
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
		model.addAttribute("appointmentDone", appointmentDone);
		return "statistics";
	}
	@RequestMapping(value = "/statistics", params= "appointmentDate")
	public String filterByDate(Model model, @RequestParam ("appointmentDate") LocalDateTime appointmentDate){
		var appointments = (List<Appointment>) appointmentService.findAll();
		model.addAttribute("appointments", appointments);
		model.addAttribute("appointmentDate", appointmentDate);
		return "statistics";
	}

	
    @PostMapping("/statistics")
    public String filterData(Model model, @RequestParam(name = "client", required = false) Client client, @RequestParam(name= "treatment", required = false) Treatment treatment, @RequestParam(name="paidAppointment", required = false) Boolean paidAppointment, @RequestParam(name="doneAppointment", required = false) Boolean doneAppointment, @RequestParam(name="initialDate", required = false) String initialDate, @RequestParam(name="endDate", required = false) String endDate  ){
		var treatments = treatmentService.findAll();
        var clients = (List<Client>) clientService.findAll();
        var allAppointments = (List<Appointment>) appointmentService.findAll();
		var appointments = allAppointments;
		if (client != null) appointments = allAppointments.stream().filter(appointment -> appointment.getClient().getId() == client.getId()).collect(java.util.stream.Collectors.toList());
		if (treatment !=null) appointments = appointments.stream().filter(appointment -> appointment.getTreatment().getId() == treatment.getId()).collect(java.util.stream.Collectors.toList());
		if (paidAppointment != null) appointments = appointments.stream().filter(appointment -> appointment.getPaid() == paidAppointment).collect(java.util.stream.Collectors.toList());
        if (doneAppointment != null) appointments = appointments.stream().filter(appointment -> appointment.getDone() == doneAppointment).collect(java.util.stream.Collectors.toList());
		if (initialDate != "") inicioDate = LocalDateTime.parse(initialDate);
		if (endDate != "") finalDate = LocalDateTime.parse(endDate);
		if ((initialDate != "") && (endDate != "" )) appointments = appointments.stream().filter(appointment -> ((!appointment.getAppointmentDate().isBefore(inicioDate)) && (!appointment.getAppointmentDate().isAfter(finalDate)))).collect(java.util.stream.Collectors.toList());
		model.addAttribute("treatments",treatments);
        model.addAttribute("clients", clients);
        model.addAttribute("appointments", appointments);
        if (client != null) model.addAttribute("clientId", client.getId());
        if (treatment != null) model.addAttribute("treatmentId", treatment.getId());
		model.addAttribute("paidAppointment", paidAppointment);
		model.addAttribute("doneAppointment", doneAppointment);
		if (initialDate != null) model.addAttribute("initialDate", initialDate);
		if (endDate != null) model.addAttribute("endDate", endDate);
        return "statistics";
    }

	

}
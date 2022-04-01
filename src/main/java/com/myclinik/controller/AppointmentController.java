package com.myclinik.controller;

import com.myclinik.model.Appointment;
import com.myclinik.service.IAppointmentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class AppointmentController {
	@Autowired
	private IAppointmentService appointmentService;

	@GetMapping("/appointments")
	public String findAppointments(Model model) {
		var appointments = (List<Appointment>) appointmentService.findAll();
		model.addAttribute("appointments", appointments);
		return "listAppointments";
	}

    @RequestMapping("/appointments/new")
    public String showNewAppointmentForm(Model model) {
        Appointment appointment = new Appointment();
        model.addAttribute("appointment", appointment);
        return "new_appointment";
    }

    @PostMapping("/save")
    public String saveAppointment(@ModelAttribute("appointment") Appointment appointment) {
        appointmentService.save(appointment); 
        return "redirect:/appointments";
    }
    /*
    @RequestMapping("/appointments/edit/{appointmentId")
	public ModelAndView showEditAppointment(@PathVariable(name = "appointmentId") long appointmentId) {
		ModelAndView mav = new ModelAndView("edit_product");
        Appointment appointment = appointmentService.get(appointmentId);
		mav.addObject("appointment", appointment);
		
		return mav;
	}
	*/
	@RequestMapping("/appointments/delete/{appointmentId}")
	public String deleteAppointment(@PathVariable(name = "appointmentId") long appointmentId) {
		appointmentService.delete(appointmentId);
		return "redirect:/appointments";		
	}
    
}

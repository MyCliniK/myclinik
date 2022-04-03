package com.myclinik.controller;

import com.myclinik.model.Treatment;
import com.myclinik.service.ITreatmentService;

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
public class TreatmentController {
	@Autowired
	private ITreatmentService treatmentService;

	@GetMapping("/treatments")
	public String findTreatments(Model model) {
		var treatments = (List<Treatment>) treatmentService.findAll();
		model.addAttribute("treatments", treatments);
		return "listTreatments";
	}

    @RequestMapping("/treatments/new")
    public String showNewTreatmentForm(Model model) {
        Treatment treatment = new Treatment();
        model.addAttribute("treatment", treatment);
        return "new_treatment";
    }

    @PostMapping("/treatments/new/save")
    public String saveTreatment(@ModelAttribute("treatment") Treatment treatment) {
        treatmentService.save(treatment);
        return "redirect:/treatments";
    }

    @RequestMapping("/treatments/edit/{id}")
	public String showEditTreatment(Model model, @PathVariable(name = "id") long id) {

        Treatment treatment = treatmentService.get(id);
		model.addAttribute("treatment", treatment);

		return "edit_treatment";
	}

	@RequestMapping("/treatments/delete/{id}")
	public String deleteAppointment(@PathVariable(name = "id") long id) {
		treatmentService.delete(id);
		return "redirect:/treatments";
	}

	@RequestMapping("/treatments/update")
	public String updateTreatment(@RequestParam("id") Long id, @ModelAttribute("treatment") Treatment treatment) {
		treatmentService.update(id, treatment);
		return "redirect:/treatments";
	}

}

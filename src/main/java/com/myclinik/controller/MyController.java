package com.myclinik.controller;

import com.myclinik.model.Client;
import com.myclinik.service.IClientService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class MyController {
	@Autowired
	private IClientService clientService;

	@GetMapping("/showClients")
	public String findClients(Model model) {
		var clients = (List<Client>) clientService.findAll();
		model.addAttribute("clients", clients);
		return "showClients";
	}
	@GetMapping("/home")
	public String goToHomePage(Model model) {
        return ("home");
    }
}

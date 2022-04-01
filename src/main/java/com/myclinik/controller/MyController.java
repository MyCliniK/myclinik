package com.myclinik.controller;

import com.myclinik.model.Client;
import com.myclinik.service.IClientService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class MyController {
	@Autowired
	private IClientService clientService;

	@GetMapping("/clients")
	public String findClients(Model model) {
		var clients = (List<Client>) clientService.findAll();
		model.addAttribute("clients", clients);
		return "showClients";
	}
	@GetMapping("/clients/{itemid}")
	public String getClient(Model model, @PathVariable String id){
		var client  = clientService.findOne(Long.parseLong(id));
		model.addAttribute("client", client);
		return "client";
	}
}

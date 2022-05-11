package com.myclinik.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.security.core.Authentication;

@Controller
public class AppController {

	@GetMapping("/")
	public String goToIndex() {
		return ("index");
	}

	@GetMapping("/home")
	public String goToHomePage(Authentication authResult, Model model) {
		String role = authResult.getAuthorities().toString();

		if (role.contains("ADMIN")) {
			return ("redirect:/admin");
		} else if (role.contains("OPS")) {
			return "redirect:/calendar";
		} else if (role.contains("CONT")) {
			return "redirect:/statistics";
		} else {
			return "redirect:/";
		}

	}

	@GetMapping("/login")
	public String goToLogin() {
		return ("login");
	}

	@GetMapping("/access-denied")
	public String goToAccessDeniedPage() {
		return ("access-denied");
	}
}

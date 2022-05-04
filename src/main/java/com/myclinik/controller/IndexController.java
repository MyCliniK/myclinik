package com.myclinik.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
	public String goToIndex() {
        return ("index");
    }

    @GetMapping("/login")
	public String goToLogin() {
        return ("login");
    }

	@GetMapping("/home")
	public String goToHomePage() {
        return ("home");
    }

    @GetMapping("/access-denied")
    public String goToAccessDeniedPage() {
        return ("access-denied");
    }
}

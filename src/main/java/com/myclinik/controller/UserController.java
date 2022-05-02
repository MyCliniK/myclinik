package com.myclinik.controller;

import com.myclinik.model.User;
import com.myclinik.repository.UserRepository;
import com.myclinik.security.UserDetailsServiceImp;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import org.springframework.context.annotation.Bean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.Optional;

@Controller
public class UserController {

    @Autowired
	private UserDetailsServiceImp userService; //duda

    @GetMapping("/users")
	public String findUsers(Model model) {
		var users = (List<User>) userService.findAll();
		model.addAttribute("users", users);
		return "showUsers";
	}

	@GetMapping("/users/user")
	public String getUser(Model model, @RequestParam("username") String username){
		var user  = userService.findOne(username);
		model.addAttribute("user", user);
		return "user";
	}

	@RequestMapping("/users/new")
	public String createUser(Model model){
		var newuser = userService.createUser();
		model.addAttribute("user", newuser);
		return "new_user";
	}

	@PostMapping("/users/new/save")
	public String saveUser(@ModelAttribute("user") User user) {
		user.setEnabled(true);
		user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
		userService.saveUser(user);
		return "redirect:/users";
	}

	@RequestMapping ("/users/delete")
	public String deleteUser(@RequestParam("id") Long id) {
		userService.deleteUser(id);
		return "redirect:/users";
	}

	@RequestMapping ("/users/update")
	public String editUser(@RequestParam("username") String username, User u){
		userService.updateUser(username, u);
		return "redirect:/users";
	}
}

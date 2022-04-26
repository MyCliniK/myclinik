package com.myclinik.controller;

import com.myclinik.model.User;
import com.myclinik.repository.UserRepository;
import com.myclinik.service.IUserService;

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

import java.util.List;
import java.util.Optional;

@Controller
public class UserController {

    @Autowired
	private IUserService userService;

    @GetMapping("/users")
	public String findUsers(Model model) {
		var users = (List<User>) userService.findAll();
		model.addAttribute("users", users);
		return "showUsers";
	}

	@GetMapping("/users/user")
	public String getUser(Model model, @RequestParam("id") Integer id){
		var user  = userService.findOne(id);
		model.addAttribute("user", user);
		return "user";
	}

	@RequestMapping("/users/new-user")
	public String createUser(Model model){
		var newuser = userService.createUser();
		model.addAttribute("username", newuser);
		return "new_user";
	}

	@PostMapping("/users/new-user/save")
	public String saveUser(@ModelAttribute("username") User username) {
		userService.saveUser(username);
		return "redirect:/users";
	}

	@RequestMapping ("/users/delete")
	public String deleteUser(@RequestParam("id") Integer id) {
		userService.deleteUser(id);
		return "redirect:/users";
	}

	@RequestMapping ("/users/update")
	public String editUser(@RequestParam("id") Integer id, User username){
		userService.updateUser(id, username);
		return "redirect:/users";
	}
}

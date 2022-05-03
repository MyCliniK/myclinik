package com.myclinik.controller;

import com.myclinik.model.User;
import com.myclinik.model.Role;
import com.myclinik.repository.UserRepository;
import com.myclinik.service.IUserService;
import com.myclinik.service.IRoleService;

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
import java.util.Set;
import java.util.HashSet;

@Controller
public class UserController {

    @Autowired
	private IUserService userService;
    @Autowired
	private IRoleService roleService;

    @GetMapping("/users")
	public String findUsers(Model model) {
		var users = (List<User>) userService.findAll();
		model.addAttribute("users", users);
		return "showUsers";
	}

	@GetMapping("/users/user")
	public String getUser(Model model, @RequestParam("id") Long id){
		var user  = userService.get(id);
		var role = user.getRoles().iterator().next();
		var roles = roleService.findAll();
		model.addAttribute("user", user);
		model.addAttribute("roleId", role.getId());
		model.addAttribute("roles", roles);
		return "user";
	}

	@RequestMapping("/users/new")
	public String createUser(Model model){
		var newuser = new User();
		var roles = roleService.findAll();
		model.addAttribute("user", newuser);
		model.addAttribute("roles", roles);
		return "new_user";
	}

	@PostMapping("/users/new/save")
	public String saveUser(@ModelAttribute("user") User user, @RequestParam("role") Role role){
		Set<Role> roles = new HashSet<Role>();
		roles.add(role);
		user.setRoles(roles);
		user.setEnabled(true);
		user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
		userService.save(user);
		return "redirect:/users";
	}

	@RequestMapping ("/users/delete")
	public String deleteUser(@RequestParam("id") Long id) {
		userService.delete(id);
		return "redirect:/users";
	}

	@RequestMapping ("/users/update")
	public String editUser(@RequestParam("id") Long id, User user, Long roleId){
		Role role = roleService.get(roleId);
		Set<Role> roles = new HashSet<Role>();
		roles.add(role);
		user.setRoles(roles);

		user.setEnabled(true);
		if(user.getPassword() != null && !user.getPassword().isEmpty()){
			user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
		} else {
			user.setPassword(userService.get(id).getPassword());
		}

		userService.update(id, user);
		return "redirect:/users";
	}
}

package com.myclinik.service;

import com.myclinik.model.User;
import com.myclinik.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {
	@Autowired
	private UserRepository repository;

	@Override
	public List<User> findAll() {
		var users = (List<User>) repository.findAll();
		return users;
	}

	public User get(Long id) {
		var user = (User) repository.findById(id).get();
		return user;
	}

	public void save(User user) {
		repository.save(user);
	}

	public void delete(Long id) {
		repository.deleteById(id);
	}

	public void update(Long id, User newUser) {
		newUser.setId(id);
		repository.save(newUser);
	}
}

package com.myclinik.service;

import com.myclinik.model.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {
	List<User> findAll();
	User findOne(String username);
	User createUser();
	void saveUser(User user, String authority);
	void deleteUser(String username);
	void updateUser(String username, User newUser);
}

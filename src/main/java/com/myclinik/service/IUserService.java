package main.java.com.myclinik.service;

import com.myclinik.model.User;

import java.util.List;

public interface IUserService {
	List<User> findAll();
	User findOne(Integer id);
	User createUser();
	void saveUser(User u);
	void deleteUser(Integer id);
	void updateUser(Integer id, User newUser);
}
package com.myclinik.service;

import com.myclinik.model.User;

import java.util.List;

public interface IUserService {
	List<User> findAll();
	User get(Long id);
    void save(User user);
    void delete(Long id);
	void update(Long id, User user);
}

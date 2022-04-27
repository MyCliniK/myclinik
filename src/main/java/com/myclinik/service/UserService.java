package com.myclinik.service;

import com.myclinik.model.User;
import com.myclinik.model.Authority;
import com.myclinik.repository.UserRepository;
import com.myclinik.repository.AuthorityRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.HashMap;


@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository repository;
    @Autowired
    private AuthorityRepository authRepository;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = repository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return user;
    }

    public List<User> findAll() {
		var users = (List<User>) repository.findAll();
		return users;
	}

    public User findOne(String username) {
		var user = (User) repository.findByUsername(username);
		return user;
	}

	public User createUser(){
		User user = new User();
		return user;
	}

    public void saveUser(User user, String authority){
		repository.save(user);

		Authority auth = new Authority(user, authority);
		authRepository.save(auth);
	}

	public void deleteUser(String username){
	//	repository.deleteByUsername(username);
	}

	public void updateUser(String username, User newUser){
		newUser.setUsername(username);
		repository.save(newUser);
	}
}

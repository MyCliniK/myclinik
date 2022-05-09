package com.myclinik.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import com.myclinik.repository.UserRepository;
import com.myclinik.model.User;
import java.util.List;

public class UserDetailsServiceImp implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("Usuario no encontrado");
		}
		return new UserDetailsImp(user);
	}

	public List<User> findAll() {
		var users = (List<User>) userRepository.findAll();
		return users;
	}

	public User findOne(String username) {
		var user = (User) userRepository.findByUsername(username);
		return user;
	}

	public User createUser() {
		User user = new User();
		return user;
	}

	public void saveUser(User user) {
		userRepository.save(user);
	}

	public void deleteUser(Long id) {
		userRepository.deleteById(id);
	}

	public void updateUser(String username, User newUser) {
		newUser.setUsername(username);
		userRepository.save(newUser);
	}
}

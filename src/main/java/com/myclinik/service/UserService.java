package main.java.com.myclinik.service;
 
import org.springframework.beans.factory.annotation.Autowired;
import com.myclinik.model.User;
import main.java.com.myclinik.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
 
@Service
public class UserService implements org.springframework.security.core.userdetails.UserDetailsService {
 
    @Autowired
    private UserRepository repository;
 
    @Override
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

    public User findOne(Integer id) {
		var user = (User) repository.findById(id).get();
		return user;
	}
	public User createUser(){
		User user = new User();
		return user;
	}

    public void saveUser(User u){
		repository.save(u);
	}

	public void deleteUser(Integer id){
		repository.deleteById(id);
	}

	public void updateUser(Integer id, User newUser){
		newUser.setId(id);
		repository.save(newUser);
	}
}
package main.java.com.myclinik.service;
 
import org.springframework.beans.factory.annotation.Autowired;
import com.myclinik.model.User;
import main.java.com.myclinik.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
 
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
}
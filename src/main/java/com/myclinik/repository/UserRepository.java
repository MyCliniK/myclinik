package main.java.com.myclinik.repository;
 
import com.myclinik.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
 
public interface UserRepository extends JpaRepository<User, Integer>{
    public User findByUsername(String username);
}
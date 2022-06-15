package fr.doumbe.usermanagement.demo.dao;

import fr.doumbe.usermanagement.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDAO extends JpaRepository<User, Long> {

    User findByUsername(String username);
    User findById(String id);
}



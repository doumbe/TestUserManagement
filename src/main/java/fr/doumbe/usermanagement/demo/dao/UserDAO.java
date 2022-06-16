package fr.doumbe.usermanagement.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import fr.doumbe.usermanagement.demo.entity.User;

@Repository
public interface UserDAO extends JpaRepository<User, Long> {

    User findByUsername(String username);
    User findById(String id);
}



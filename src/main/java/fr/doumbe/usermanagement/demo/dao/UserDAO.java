package fr.doumbe.usermanagement.demo.dao;

import fr.doumbe.usermanagement.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDAO extends JpaRepository<User, Long> {

    //Optional<User> finByUsername(String username);
    User findByUsername(String username);
    //Optional<User> findById(String id);
    Optional<User> findUserById(String id);
    User deleteUserById(Long id);
    void deleteById(String id);

    User findByLastName(String lastname);
}



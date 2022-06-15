package fr.doumbe.usermanagement.demo.service;

import fr.doumbe.usermanagement.demo.dao.UserDAO;
import fr.doumbe.usermanagement.demo.entity.User;
import fr.doumbe.usermanagement.demo.rule.UserRule;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserDAO userDAO;

    private final UserRule userRule;

    public UserService(UserDAO userDAO, UserRule userRule) {
        this.userDAO = userDAO;
        this.userRule = userRule;
    }

    /**
     *
     * @return list of user in database
     */
    @Transactional(readOnly = true)
    public List<User> getAllUser() {
        return userDAO.findAll();
    }

    /**
     *
     * @param username refers to user to find in database
     * @return
     */
    @Transactional(readOnly = true)
    public User getUserByUsername(String username) {
        return userDAO.findByUsername(username);
    }

    @Transactional(readOnly = true)
    public Optional<User> findUserById(Long id) {
        return userDAO.findById(id);
    }

    /**
     *
     * @param user
     * @return the user added in database
     */
    @Transactional
    public User addUser(User user) {
        return userDAO.save(user);
    }

}

package fr.doumbe.usermanagement.demo.service;

import fr.doumbe.usermanagement.demo.dao.UserDAO;
import fr.doumbe.usermanagement.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private final UserDAO userDAO;
    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
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

    /**
     *
     * @param id
     * @return delete user in database
     */
    @Transactional
    public User deleteUserById(Long id) {
        userDAO.deleteById(id);
        return null;
    }
    @Transactional
    public void deleteById(Long id) {
        userDAO.deleteById(id);
    }
    public Map<String, Object> getUserByPage(int page, int size) {
        Map<String, Object> result = new HashMap<>();
        PageRequest pageable = PageRequest.of(page, size);
        Page<User> userPage = userDAO.findAll(pageable);
        result.put("data", userPage.getContent());
        result.put("Total_of_pages", userPage.getTotalPages());
        result.put("Total_of_elements", userPage.getTotalElements());
        result.put("Current_page", userPage.getNumber());
        return result;
    }

    public User getUserByLastName(String lastname) {
        return userDAO.findByLastName(lastname);
    }

}

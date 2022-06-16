package fr.doumbe.usermanagement.demo.controller;

import fr.doumbe.usermanagement.demo.entity.User;
import fr.doumbe.usermanagement.demo.exception.AddUserException;
import fr.doumbe.usermanagement.demo.exception.SearchUserException;
import fr.doumbe.usermanagement.demo.rule.UserRule;
import fr.doumbe.usermanagement.demo.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@Api( description="API for the CRUD of users")

public class UserController {

    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;
    private final UserRule userRule;

    /**
     * Constructor
     * @param userService
     * @param userRule
     */
    public UserController(UserService userService, UserRule userRule) {
        this.userService = userService;
        this.userRule = userRule;
    }

    /**
     * used to find all users in database
     * @return list of all users in database
     */
    @ApiOperation(value = "list of all users in database")
    @RequestMapping(value = "users", method = RequestMethod.GET)
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUser();
        return ResponseEntity.ok().body(users);
    }

    /**
     * used to find user associated to username in database
     * @param username refers to the name of the user to find
     * @return ResponseEntity<User>
     */
    @ApiOperation(value = "used to find user associated to username in database")
    @GetMapping("/search")
    public ResponseEntity<User> getUser(@RequestParam("username") String username) {
        long time = System.currentTimeMillis();
        logger.info("### starting getUserByUsername ... ###");
        User user = userService.getUserByUsername(username);
        if (user != null) {
            time = System.currentTimeMillis() - time;
            logger.info("### Ending GetUsersByLastName ..., time : {} ###", time);
            return ResponseEntity.ok().body(user);
        }
        time = System.currentTimeMillis() - time;
        logger.info("### Ending GetUsersByLastName ..., time : {} ###", time);
        throw new SearchUserException("User not found");
    }

    /**
     * used to find user associated to username in database
     * @param id refers to the name of the user to find
     * @return ResponseEntity<User>
     */
    @ApiOperation(value = "used to find user id associated to username in database")
    //@GetMapping("/search/{id}")
    @GetMapping("/{id}")
    public ResponseEntity<Optional<User>> getUserById(@PathVariable("id") Long id) {
        long time = System.currentTimeMillis();
        logger.info("### starting getUserByUsername ... ###");
        Optional<User> user = userService.findUserById(id);
        if (user.isPresent()) {
            time = System.currentTimeMillis() - time;
            logger.info("### Ending GetUsersByLastName ..., time : {} ###", time);
            return ResponseEntity.ok().body(user);
        }
        time = System.currentTimeMillis() - time;
        logger.info("### Ending GetUsersByLastName ..., time : {} ###", time);
        throw new SearchUserException("User not found");
    }

    /**
     * used to add user in database
     * @param user
     * @return ResponseEntity<> with object containing userId, status, result
     */
    @ApiOperation(value = "used to add user in database")
    @PostMapping(value = "/addUser", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> addUser(@Valid @RequestBody User user) {
        long time = System.currentTimeMillis();
        System.out.println(user);
        logger.info("### starting add user ... ###");
        if (!userRule.isFrenchCountry(user.getCountry())) {
            time = System.currentTimeMillis() - time;
            logger.info("### Ending add user ..., time : {} ###", time);
            throw new AddUserException("Country Not Allowed");
        }
        if (!userRule.isMajor(user.getBirthdate())) {
            time = System.currentTimeMillis() - time;
            logger.info("### Ending add user ..., time : {} ###", time);
            throw new AddUserException("Age lower than 18");
        }

        User userSaved = userService.addUser(user);

        Map<String, Object> result = new HashMap<>();
        result.put("userId", userSaved.getId());
        result.put("status", HttpStatus.CREATED);
        result.put("result", "SUCCESS");

        time = System.currentTimeMillis() - time;
        logger.info("### Ending add user ..., time : {} ###", time);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @ApiOperation(value = "used to add user in database")
    @DeleteMapping("/search/{id}")
    public void deleteUserById(@PathVariable String id) {
        User user = userService.deleteUserById(Long.valueOf(id));
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}

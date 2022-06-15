package fr.doumbe.usermanagement.demo.init;

import fr.doumbe.usermanagement.demo.dao.UserDAO;
import fr.doumbe.usermanagement.demo.entity.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import static java.lang.String.format;

@Component
public class DataInit implements CommandLineRunner {

    private final UserDAO userDAO;

    //private static final DateFormat d = new SimpleDateFormat();

    public DataInit(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    /**
     *
     * @param args
     */
    @Override
    public void run(String... args) throws Exception {
        long count = userDAO.count();

        if (count == 0) {
            User user = new User();
            user.setUsername("Doumbe");
            user.setBirthdate(LocalDate.of(1990, 3, 17));
            user.setCountry("france");
            userDAO.save(user);

        }
    }
}

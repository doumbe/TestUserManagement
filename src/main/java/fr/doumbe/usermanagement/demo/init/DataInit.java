package fr.doumbe.usermanagement.demo.init;

import fr.doumbe.usermanagement.demo.dao.UserDAO;
import fr.doumbe.usermanagement.demo.entity.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataInit implements CommandLineRunner {
    private final UserDAO userDAO;
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
            user.setBirthdate(LocalDate.of(2020, 3, 17));
            user.setCountry("france");
            user.setGenre("M");
            user.setPhoneNumber(+601403322L);
            userDAO.save(user);

            User user1 = new User();
            user1.setUsername("Guillaume");
            user1.setBirthdate(LocalDate.of(1990, 3, 17));
            user1.setCountry("france");
            user1.setGenre("M");
            user1.setPhoneNumber(+620301425L);
            userDAO.save(user1);

            User user2 = new User();
            user2.setUsername("Reda");
            user2.setBirthdate(LocalDate.of(1990, 3, 17));
            user2.setCountry("France");
            user2.setGenre("M");
            user2.setPhoneNumber(+710101414L);
            userDAO.save(user2);
        }
    }
}

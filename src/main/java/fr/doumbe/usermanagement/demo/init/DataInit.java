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
            user.setLastName("TRAORE");
            user.setBirthdate(LocalDate.of(2020, 3, 17));
            user.setCountry("france");
            user.setGenre("M");
            user.setPhoneNumber(+601403322L);
            userDAO.save(user);

            User user1 = new User();
            user1.setUsername("Guillaume");
            user1.setLastName("Cassini");
            user1.setBirthdate(LocalDate.of(1990, 3, 17));
            user1.setCountry("france");
            user1.setGenre("M");
            user1.setPhoneNumber(+620301425L);
            userDAO.save(user1);

            User user2 = new User();
            user2.setUsername("Reda");
            user2.setLastName("Mohammed");
            user2.setBirthdate(LocalDate.of(1990, 3, 17));
            user2.setCountry("France");
            user2.setGenre("M");
            user2.setPhoneNumber(+710101414L);
            userDAO.save(user2);
/*
            User user3 = new User();
            user3.setBirthdate(LocalDate.of(1990, 3, 17));
            user3.setCountry("France");
            user3.setGenre("M");
            user3.setPhoneNumber(null);
            userDAO.save(user2);

            User user4 = new User();
            user4.setUsername("Jean");
            user4.setBirthdate(LocalDate.of(1990, 3, 17));
            user4.setCountry("France");
            user4.setGenre("F");
            user4.setPhoneNumber(null);
            userDAO.save(user2);

            User user5 = new User();
            user5.setUsername("Paul");
            user5.setBirthdate(LocalDate.of(1990, 3, 17));
            user5.setCountry("France");
            user5.setGenre("M");
            user5.setPhoneNumber(+710101414L);
            userDAO.save(user2);

            User user6 = new User();
            user6.setUsername("Emilie");
            user6.setBirthdate(LocalDate.of(1990, 3, 17));
            user6.setCountry("France");
            user6.setGenre("F");
            user6.setPhoneNumber(+710101414L);
            userDAO.save(user2);

            User user7 = new User();
            user7.setUsername("Jeanne");
            user7.setBirthdate(LocalDate.of(1990, 3, 17));
            user7.setCountry("France");
            user7.setGenre("F");
            user7.setPhoneNumber(+710101414L);
            userDAO.save(user2);*/
        }
    }
}

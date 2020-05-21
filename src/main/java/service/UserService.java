package service;

import entity.User;
import org.telegram.telegrambots.meta.api.objects.Update;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class UserService {

    public static void addUser(long id ,Update update){

        EntityManagerFactory entityManagerFactory  = Persistence.createEntityManagerFactory("UsersDB");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        User user = entityManager.find(User.class, id);

        if (user == null){

            user = new User();
            user.setId(update.getMessage().getChatId());
            user.setUserFirstName(update.getMessage().getFrom().getFirstName());
            user.setUserLastName(update.getMessage().getFrom().getLastName());
            user.setUsername(update.getMessage().getFrom().getUserName());

            entityManager.persist(user);
        }  else {
            System.out.println("Exist");
        }
        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();

    }

}

package dao.impl;

import config.HibernateConfig;
import dao.UserDao;
import entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.telegram.telegrambots.meta.api.objects.Update;

import javax.persistence.Query;


public class UserDaoImpl implements UserDao {

    @Override
    public void addUser(long id, Update update) {

        Session session = HibernateConfig.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        User user = session.get(User.class, id);

        if (user == null){
            user = new User();
            user.setId(update.getMessage().getChatId());
            user.setUserFirstName(update.getMessage().getFrom().getFirstName());
            user.setUserLastName(update.getMessage().getFrom().getLastName());
            user.setUsername(update.getMessage().getFrom().getUserName());
            user.setLastCallBackQuery("/start");

            session.save(user);
            transaction.commit();
            session.close();
        } else {
            System.out.println("Exist");
        }

    }

    @Override
    public void updateCallBackQueryField(long id, Update update) {
        Session session = HibernateConfig.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("update User user set lastCallBackQuery=:lastCommand where user.id =: i");
        query.setParameter("lastCommand", update.getCallbackQuery().getData());
        query.setParameter("i", id);
        query.executeUpdate();

        transaction.commit();
        session.close();
    }
}

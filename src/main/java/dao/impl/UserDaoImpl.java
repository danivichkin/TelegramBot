package dao.impl;

import config.HibernateConfig;
import dao.UserDao;
import entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.telegram.telegrambots.meta.api.objects.Update;

import javax.persistence.Query;
import java.util.List;


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

    @Override
    public void setDefaultCallBack(long id, Update update) {
        Session session = HibernateConfig.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        User user = session.get(User.class, id);
        user.setLastCallBackQuery("/default");
        session.update(user);

        transaction.commit();
        session.close();
    }

    @Override
    public void setCustomCallBack(long id, Update update, String customCallBack) {
        Session session = HibernateConfig.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        User user = session.get(User.class, id);
        user.setLastCallBackQuery(customCallBack);
        session.update(user);

        transaction.commit();
        session.close();
    }

    @Override
    public User getUserById(long id, Update update) {
        Session session = HibernateConfig.getSessionFactory().openSession();
        return session.get(User.class, id);
    }

    @Override
    public String getLastCommand(long id, Update update) {
        Session session = HibernateConfig.getSessionFactory().openSession();

        Query query = session.createQuery("from User user where user.id=:i");
        query.setParameter("i", id);

        List list = query.getResultList();
        User user = (User) list.get(0);
        String lastCommand = user.getLastCallBackQuery();

        session.close();
        return lastCommand;
    }
}

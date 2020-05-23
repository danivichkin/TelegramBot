package dao.impl;

import config.HibernateConfig;
import dao.NoteDao;
import entity.Note;
import entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.telegram.telegrambots.meta.api.objects.Update;
import service.UserService;

import javax.persistence.Query;
import java.util.List;

public class NoteDaoImpl implements NoteDao {

    UserService userService = new UserService();

    @Override
    public void addNote(long id, Update update) {
        Session session = HibernateConfig.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        Note note = new Note();
        User user = userService.getUserById(id, update);
        note.setDescription(update.getMessage().getText());
        note.setUser(user);

        session.save(note);
        transaction.commit();
        session.close();
    }

    @Override
    public List getAllNotes(long id, Update update) {
        Session session = HibernateConfig.getSessionFactory().openSession();

        Query query = session.createQuery("from Note note where note.user.id=:i");
        query.setParameter("i", id);

        List list = query.getResultList();

        session.close();
        return list;
    }

}

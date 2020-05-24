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
    public void createNote(long id, Update update) {
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
    public void addNote(long id, Update update, Note note) {
        Session session = HibernateConfig.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        User user = userService.getUserById(id, update);
        note.setUser(user);
        session.save(note);

        transaction.commit();
        session.close();
    }

    @Override
    public void deleteNoteByTitle(long id, Update update, String title) {
        Session session = HibernateConfig.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("from Note note where note.title=:title");
        query.setParameter("title", title);

        List<Note> list = query.getResultList();
        Note note = list.get(0);
        session.delete(note);

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

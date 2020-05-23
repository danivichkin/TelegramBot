package service;

import dao.impl.NoteDaoImpl;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

public class NoteService {

    NoteDaoImpl noteDaoImpl = new NoteDaoImpl();

    public void createNote(long id, Update update){
        noteDaoImpl.addNote(id, update);
    }

    public List getAllNotes(long id, Update update) {return noteDaoImpl.getAllNotes(id, update);}

}

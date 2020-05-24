package service;

import dao.impl.NoteDaoImpl;
import entity.Note;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

public class NoteService {

    NoteDaoImpl noteDaoImpl = new NoteDaoImpl();

    public void deleteNoteByTitle(long id, Update update, String title){noteDaoImpl.deleteNoteByTitle(id, update, title);}

    public void createNote(long id, Update update){
        noteDaoImpl.createNote(id, update);
    }

    public void addNote(long id, Update update, Note note){noteDaoImpl.addNote(id, update, note);}

    public List getAllNotes(long id, Update update) {return noteDaoImpl.getAllNotes(id, update);}

}

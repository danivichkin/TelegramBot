package dao;

import entity.Note;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

public interface NoteDao {
    void createNote(long id, Update update);
    void addNote(long id, Update update, Note note);
    void deleteNoteByTitle(long id, Update update, String title);
    List getAllNotes(long id, Update update);
}

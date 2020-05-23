package dao;

import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

public interface NoteDao {
    void addNote(long id, Update update);
    List getAllNotes(long id, Update update);
}

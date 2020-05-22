package dao;

import org.telegram.telegrambots.meta.api.objects.Update;

public interface UserDao {
    void addUser(long id, Update update);
    void updateCallBackQueryField(long id, Update update);
}

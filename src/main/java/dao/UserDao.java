package dao;

import entity.User;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface UserDao {
    void addUser(long id, Update update);
    void updateCallBackQueryField(long id, Update update);
    void setDefaultCallBack(long id, Update update);
    User getUserById(long id, Update update);
    String getLastCommand(long id, Update update);

}

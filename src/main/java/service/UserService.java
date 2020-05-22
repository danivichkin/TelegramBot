package service;

import dao.impl.UserDaoImpl;
import org.telegram.telegrambots.meta.api.objects.Update;

public class UserService {

    UserDaoImpl userDaoImpl = new UserDaoImpl();

    public UserService() {}

    public void saveUser(long id, Update update){
        userDaoImpl.addUser(id, update);
    }

    public void updateLastCommand(long id, Update update){
        userDaoImpl.updateCallBackQueryField(id, update);
    }

}

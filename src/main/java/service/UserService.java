package service;

import dao.impl.UserDaoImpl;
import entity.User;
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

    public void setDefaultCallBack(long id, Update update){userDaoImpl.setDefaultCallBack(id, update);}

    public User getUserById(long id, Update update) {return userDaoImpl.getUserById(id, update);}

    public String getLastCommand(long id, Update update) { return userDaoImpl.getLastCommand(id, update); }

}

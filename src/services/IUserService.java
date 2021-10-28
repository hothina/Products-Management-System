package services;

import model.User;

import java.util.List;

public interface IUserService {
    User getById(int id) throws Exception;

    List<User> getUsers() throws Exception;

    void addUser(User user) throws Exception;

    void updateUser(User user) throws Exception;
}

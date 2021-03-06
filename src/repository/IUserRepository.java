package repository;

import model.User;

import java.io.IOException;
import java.util.List;

public interface IUserRepository {
    User getById(long id);

    List <User> getUsers();

    boolean exist (long id);

    void add(User newUser);

    void update(User user) ;
}

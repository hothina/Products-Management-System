package services;

import model.User;
import repository.IUserRepository;
import repository.UserRepository;

import java.util.List;

public class UserServices implements IUserService {

    private IUserRepository userRepository;

    public UserServices() {
        userRepository = new UserRepository();
    }

    @Override
    public User getById(long id) throws Exception {
        User user = userRepository.getById(id);
        if (user != null)
            throw new Exception("user already exists");
        return user;
    }

    @Override
    public List<User> getUsers() throws Exception {
        return userRepository.getUsers();
    }

    @Override
    public void addUser(User newUser) throws Exception {
        if (userRepository.exist(newUser.getId()))
            throw new Exception("user already exists");
        userRepository.add(newUser);

    }

    @Override
    public void updateUser(User user) throws Exception {
        if (userRepository.exist( user.getId()))
            userRepository.update(user);
        else
            throw new Exception("user not already exists");

    }
}

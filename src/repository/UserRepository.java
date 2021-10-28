package repository;

import model.User;

import java.util.ArrayList;
import java.util.List;

public class UserRepository implements IUserRepository{

    private List<User> userList;

    public UserRepository(){
        userList = new ArrayList<>();
        userList.add(new User(1,"Nguyen Van An",1997,"0901244559","Hue"));
        userList.add(new User(2,"Nguyen Van Binh",1990,"0901345656","Hue"));
        userList.add(new User(3,"Tran Van Van",1995,"0901242334","Hue"));
    }
    @Override
    public User getById(long id) {
        for (User user : userList) {
            if (user.getId() == id)
                return user;
        }
        return null;
    }

    @Override
    public List<User> getUsers() {
        return userList;
    }

    @Override
    public boolean exist(long id) {
        return getById(id) != null;
    }

    @Override
    public void add(User newUser) {
        userList.add(newUser);

    }

    @Override
    public void update(User user) {
        User oldUser = getById(user.getId());
        User.transferFields(oldUser, user);

    }
}

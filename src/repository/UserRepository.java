package repository;

import exception.ProductException;
import model.Role;
import model.User;
import model.UserStatus;
import utils.CsvUtils;
import utils.DateUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserRepository implements IUserRepository{


    private final String USER_PATH ="data\\users.csv";

    public UserRepository(){
    }
    @Override
    public User getById(long id) {
        List<User> userList = getUsers();
        for (User user : userList) {
            if (user.getId() == id)
                return user;
        }
        return null;
    }

    @Override
    public List<User> getUsers() {
        List<User> newUserList = new ArrayList<>();
        try {
            List<String> records = CsvUtils.read(USER_PATH);
            for (String record : records) {
                newUserList.add(new User(record));
            }
            return newUserList;
        } catch (IOException ex) {
            throw new ProductException("getUsers error");
        }

    }

    @Override
    public boolean exist(long id) {
        return getById(id) != null;
    }

    @Override
    public void add(User newUser) throws IOException {
        List<User> userList = getUsers();
        userList.add(newUser);
        CsvUtils.write(USER_PATH, userList);

    }

    @Override
    public void update(User user) {
        User oldUser = getById(user.getId());
        User.transferFields(oldUser, user);
    }

    public static void main(String[] args) {
        UserRepository userRepository = new UserRepository();
        try {
            userRepository.add(new User(1, "Nguyen Van",
                    UserStatus.AVAILABLE, Role.ADMIN, DateUtils.stringToDate("10-10-2090 00:00:00"),
                    "0123456789", "Hue"));
//            userRepository.update(new User(2, "Nguyen Thanh",
//                    UserStatus.AVAILABLE, Role.USER, DateUtils.StringToDate("1-1-2095 00:00:00"),
//                    "0909489483", "Hue"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

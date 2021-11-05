package views;

import model.Role;
import model.User;
import model.UserStatus;
import services.IUserService;
import services.UserServices;
import utils.DateUtils;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class UserView {

    private IUserService userService;
    private Scanner scanner;

    public UserView() {
        scanner = new Scanner(System.in);
        userService = new UserServices();
    }

    public void addUser() {
        System.out.print("Id:");
        long id = scanner.nextLong();
        System.out.print("Ho va ten:");
        String fullName = scanner.next();
        System.out.print("Ngay sinh(31-12-1970):");
        String birthDay = scanner.next();
        Date date = DateUtils.stringToDate(birthDay);
        System.out.print("So dien thoai");
        String phoneNumber = scanner.next();
        System.out.print("Dia chi");
        String address = scanner.next();

        User user = new User(id, fullName, date, phoneNumber, address);
        user.setRole(Role.USER);
        user.setStatus(UserStatus.AVAILABLE);

//        try{
//            userService.addUser(user);
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
    }

    public static void main(String[] args) {
        UserView a = new UserView();
        a.addUser();
    }

    public void showUsers() {
        try {
            System.out.println("--------------------DANH SACH NGUOI DUNG------------------");
//            System.out.println("userList");

            System.out.printf("%-5s %-30s %-30s\n", "Id", "Ho ten", "Sdt");
            List<User> userList = userService.getUsers();

            for (User user : userList) {
                System.out.printf("%-5s %-30s %-30s\n", user.getId(), user.getFullName(), user.getPhoneNumber());
            }

            System.out.println("------------------------------------------------------------");
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public void updateUser() {
        System.out.print("Id:");
        long id = scanner.nextLong();
        System.out.print("Ho va Ten:");
        String fullName = scanner.next();
        System.out.print("Ngay Sinh (01-12-2021):");
        String birthDay = scanner.next();
        System.out.print("So dien thoai:");
        String phoneNumber = scanner.next();
        System.out.print("Dia Chi:");
        String address = scanner.next();
        System.out.print("Trang thai (1:Lock; 2:Available)");
        int status = scanner.nextInt();

        User user = new User(id, fullName, DateUtils.stringToDate(birthDay), phoneNumber, address);
        user.setStatus(UserStatus.fromValue(status));
        try {
            userService.updateUser(user);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}

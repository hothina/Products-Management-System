package views;

import model.Role;
import model.User;
import model.UserStatus;
import repository.UserRepository;
import services.IUserService;
import services.UserServices;
import sort.SortUser;
import utils.DateUtils;

import java.util.*;
import java.util.regex.Pattern;

public class UserView {

    private IUserService userService;
    private Scanner scanner;
    private MenuView me;
    public static final String NAME_REGEX = "^([A-Z]+[a-z]*[ ]?)+$";
    public static final String NUMBER_PHONE_REGEX = "^[0][1-9][0-9]{8,9}$";
   static Scanner input = new Scanner(System.in);


    public UserView(MenuView m) {
        scanner = new Scanner(System.in);
        userService = new UserServices();
        me=m;
    }

    public boolean isFormatFullName(String fullName) {

        return Pattern.compile(NAME_REGEX).matcher(fullName).matches();
    }
    public boolean isPhoneNumber(String phoneNumber){
        return Pattern.compile(NUMBER_PHONE_REGEX).matcher(phoneNumber).matches();
    }

    public void addUser() {
        try {
            System.out.print("Id (la mot so): ");
            String ids = scanner.next();
            int id = Integer.parseInt(ids);
            System.out.print("Ho va ten (vd: Tran Nhi): ");
            String fullName = input.nextLine();
            if(!isFormatFullName(fullName)){
                System.out.println("Nhap sai (vd: TranNhi)");
                nextAdd();
            }
            System.out.print("Ngay sinh (31-12-1970): ");
            String birthDay = scanner.next();
            Date date = DateUtils.stringToDate(birthDay);
            System.out.print("So dien thoai (vd 0123456789): ");
            String phoneNumber = scanner.next();
            if (!isPhoneNumber(phoneNumber)){
                System.out.println("Nhap sai (vd: 0123456789)");
               nextAdd();
            }
            System.out.print("Dia chi (vd Quang Binh): ");
            String address = input.nextLine();
            if (!isFormatFullName(address)){
                System.out.println("Nhap sai (vd: QuangBinh)");
               nextAdd();
            }
            User user = new User(id, fullName, date, phoneNumber, address);
            user.setRole(Role.USER);
            user.setStatus(UserStatus.AVAILABLE);
            userService.addUser(user);
            System.out.println("Đã thêm người dùng.");


        } catch (Exception e) {
//            System.out.println(e.getMessage());
            nextAdd();
        }

    }

    private void nextAdd() {
        System.out.println("Tiếp tục:  Nhập Y         Trở về: Nhập N");
        String s = scanner.next();
        if (s.equalsIgnoreCase("y")) {
            addUser();
        } else if (s.equalsIgnoreCase("n")){

           me.menu();

        }
    }

//    public static void main(String[] args) {
//        UserView a = new UserView();
//        a.showUsers();
//        a.updateUser();
//        a.addUser();
//    }

    public void showUsers() {
        try {
            System.out.println("\t\t--------------------DANH SACH NGUOI DUNG------------------");


            System.out.printf("\t\t\t%-5s %-30s %-30s\n", "Id", "Ho ten", "Sdt");
            List<User> userList = userService.getUsers();
            SortUser sortUser = new SortUser();
            Collections.sort(userList,sortUser);

            for (User user : userList) {
                System.out.printf("\t\t\t%-5s %-30s %-30s\n", user.getId(), user.getFullName(), user.getPhoneNumber());
            }

            System.out.println("\t\t-----------------------------------------------------------");
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public void updateUser() {

        try {
            UserRepository userRepository = new UserRepository();
            List<User> Users = userRepository.getUsers();
            System.out.print("Id (la mot so): ");
            boolean check = false;
            String ids = scanner.next();
            long id = Long.parseLong(ids);
            for (User user : Users) {
                if (user.getId() == id) {
                    System.out.print("Ho va ten (Tran Nhi): ");

                    String fullName = input.nextLine();
                    if(!isFormatFullName(fullName)){
                        System.out.println("Nhap sai (vd: TranNhi)");
                        nextUpdate();
                    }
                    System.out.print("Ngay sinh (vd 31-12-1970): ");
                    String birthDay = scanner.next();
                    Date date = DateUtils.stringToDate(birthDay);
                    System.out.print("So dien thoai (0123456789): ");
                    String phoneNumber = scanner.next();
                    if (!isPhoneNumber(phoneNumber)){
                        System.out.println("Nhap sai (vd: 0123456789)");
                        nextUpdate();
                    }
                    System.out.print("Dia chi (Quang Binh): ");
                    String address = input.nextLine();
                    if (!isFormatFullName(address)){
                        System.out.println("Nhap sai (vd: Quang Binh)");
                        nextUpdate();

                    }
                    User user1 = new User(id, fullName, date, phoneNumber, address);
                    user1.setRole(Role.USER);
                    user1.setStatus(UserStatus.AVAILABLE);
                    userService.updateUser(user1);
                    check = true;
                }
            }
            if (!check) {

                System.out.println("khong ton tai!!");
                nextUpdate();
            } else {

                System.out.println("Đã cập nhật người dùng ");
            }
        } catch (Exception e) {
//            System.out.println(e.getMessage());
            nextUpdate();
        }
    }

    private void nextUpdate() {
        System.out.println("Tiếp tục:  Nhập Y         Trở về: Nhập N ");
        String s = scanner.next();
        if (s.equalsIgnoreCase("y")) {
            updateUser();
        } else if (s.equalsIgnoreCase("n")){
            me.menu();
        }
    }


}

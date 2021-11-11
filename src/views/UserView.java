package views;

import model.Role;
import model.User;
import model.UserStatus;
import repository.UserRepository;
import services.IUserService;
import services.UserServices;
import utils.DateUtils;

import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.regex.Pattern;

public class UserView {

    private IUserService userService;
    private Scanner scanner;
    public static final String NAME_REGEX = "^([A-Z]+[a-z]{1,6}){1,6}";
    public static final String NUMBER_PHONE_REGEX = "^[0][1-9][0-9]{8,9}$";


    public UserView() {
        scanner = new Scanner(System.in);
        userService = new UserServices();
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
            Integer id = Integer.parseInt(ids);
            System.out.print("Ho va ten (vd: TranNhi): ");
            String fullName = scanner.next();
            if(!isFormatFullName(fullName)){
                System.out.println("Nhap sai (vd: TranNhi)");
                System.out.println("Nhap Y để quay lai: ");
                String s = scanner.next();
                if (s.equalsIgnoreCase("y")){
                    addUser();
                } else {
                    System.exit(0);
                }
            }
            System.out.print("Ngay sinh (31-12-1970): ");
            String birthDay = scanner.next();
            Date date = DateUtils.stringToDate(birthDay);
            System.out.print("So dien thoai (vd 0123456789): ");
            String phoneNumber = scanner.next();
            if (!isPhoneNumber(phoneNumber)){
                System.out.println("Nhap sai (vd: 0123456789)");
                System.out.println("Nhap Y để quay lai: ");
                String s = scanner.next();
                if (s.equalsIgnoreCase("y")){
                    addUser();
                } else {
                    System.exit(0);
                }
            }
            System.out.print("Dia chi (vd QuangBinh): ");
            String address = scanner.next();
            if (!isFormatFullName(address)){
                System.out.println("Nhap sai (vd: QuangBinh)");
                System.out.println("Nhap Y để quay lai: ");
                String s = scanner.next();
                if (s.equalsIgnoreCase("y")){
                    addUser();
                } else {
                    System.exit(0);
                }
            }
            User user = new User(id, fullName, date, phoneNumber, address);
            user.setRole(Role.USER);
            user.setStatus(UserStatus.AVAILABLE);
            userService.addUser(user);


        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Nhap Y để quay lai: ");
            String s = scanner.next();
            if (s.equalsIgnoreCase("y")){
                addUser();
            } else {
                System.exit(0);
            }
        }

    }

    public static void main(String[] args) {
        UserView a = new UserView();
        a.addUser();
    }

    public void showUsers() {
        try {
            System.out.println("--------------------DANH SACH NGUOI DUNG------------------");


            System.out.printf("%-5s %-30s %-30s\n", "Id", "Ho ten", "Sdt");
            List<User> userList = userService.getUsers();

            for (User user : userList) {
                System.out.printf("%-5s %-30s %-30s\n", user.getId(), user.getFullName(), user.getPhoneNumber());
            }

            System.out.println("---------------------------------------------------------");
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
            Long id = Long.parseLong(ids);
            for (User user : Users) {
                if (user.getId() == id) {
                    System.out.print("Ho va ten (TranNhi): ");
                    String fullName = scanner.next();
                    if(!isFormatFullName(fullName)){
                        System.out.println("Nhap sai (vd: TranNhi)");
                        System.out.println("Nhap Y để quay lai: ");
                        String s = scanner.next();
                        if (s.equalsIgnoreCase("y")){
                            updateUser();
                        }else {
                            System.exit(0);
                        }
                    }
                    System.out.print("Ngay sinh (vd 31-12-1970): ");
                    String birthDay = scanner.next();
                    Date date = DateUtils.stringToDate(birthDay);
                    System.out.print("So dien thoai (0123456789): ");
                    String phoneNumber = scanner.next();
                    if (!isPhoneNumber(phoneNumber)){
                        System.out.println("Nhap sai (vd: 0123456789)");
                        System.out.println("Nhap Y để quay lai: ");
                        String s = scanner.next();
                        if (s.equalsIgnoreCase("y")){
                            updateUser();
                        }else {
                            System.exit(0);
                        }
                    }
                    System.out.print("Dia chi (QuangBinh): ");
                    String address = scanner.next();
                    if (!isFormatFullName(address)){
                        System.out.println("Nhap sai (vd: QuangBinh)");
                        System.out.println("Nhap Y để quay lai: ");
                        String s = scanner.next();
                        if (s.equalsIgnoreCase("y")){
                            updateUser();
                        }else {
                            System.exit(0);
                        }

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
                System.out.println("Nhap Y để quay lai: ");
                String s = scanner.next();
                if (s.equalsIgnoreCase("y")){
                    updateUser();
                }else {
                    System.exit(0);
                }
            } else {
                System.out.println("Đã cập nhật người dùng ");
                System.out.println("Nhap Y để quay lai: ");
                String s = scanner.next();
                if (s.equalsIgnoreCase("y")){
                    updateUser();
                }else {
                    System.exit(0);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Nhap Y để quay lai: ");
            String s = scanner.next();
            if (s.equalsIgnoreCase("y")){
                updateUser();
            }else {
                System.exit(0);
            }
        }
    }


}

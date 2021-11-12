import model.*;
import repository.AccountRepository;
import repository.DrinkRepository;
import repository.UserRepository;
import utils.CsvUtils;
import utils.DateUtils;
import views.MenuView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) throws IOException {
        boolean admin = false;
        boolean Login = false;
        AccountRepository accountRepository = new AccountRepository();
        List<Account> Accounts = accountRepository.getAccount();

        UserRepository userRepository = new UserRepository();


        do {
            Scanner sc = new Scanner(System.in);
            System.out.println("Đăng nhập hệ thống");
            System.out.print("Username:");
            String Username = sc.next();
            System.out.print("Password:");
            String Password = sc.next();
            for (Account s : Accounts) {
                if (Username.equalsIgnoreCase(s.getName()) && Password.equals(s.getPassword())) {
                    Login = true;
                    User u = userRepository.getById(s.getId());
                    if (Role.ADMIN.equals(u.getRole())) {
                        admin = true;

                    }
                }
            }
        } while (!Login);
        if (admin) {
            MenuView menuView = new MenuView();
            menuView.menu();
        } else {
            System.out.println("Toi la user:");
        }

    }
}

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
//    private static final String ACCOUNT_PATH = "data\\accounts.csv";

    public static void main(String[] args) throws IOException {
        boolean admin = false;
        boolean Login = false;
        AccountRepository accountRepository = new AccountRepository() ;
        List<Account> Accounts = accountRepository.getAccount();
//        Account Admin = new Account("Admin","123",1);
//        Accounts.add(Admin);
//        Account User1 = new Account("User1","123",2);
//        Accounts.add(User1);
//
//        for (Account account : Accounts){
//        accountRepository.add(account);}



        UserRepository userRepository = new UserRepository();
//        try {
//            userRepository.add(new User(1, "Toi la Admin",
//                    UserStatus.AVAILABLE, Role.ADMIN, DateUtils.StringToDate("10-10-2021 00:00:00"),
//                    "0123456789", "Hue"));
//            userRepository.update(new User(2, "Nguyen Va",
//                    UserStatus.AVAILABLE, Role.USER, DateUtils.StringToDate("1-1-2021 00:00:00"),
//                    "0909489483", "Hue"));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        UserRepository userRepository = new UserRepository();
//        AccountRepository accountRepository = new AccountRepository() ;
        DrinkRepository drinkRepository = new DrinkRepository();
        List<Drink> list = drinkRepository.getDrink();

//       System.out.println(list);
//        System.out.println(Accounts);
//        for(Account account: Accounts){
//            if(account.getId() ==2){
//                accountRepository.remove(account,Accounts);
//                break;
//            }
//        }


        do{
            Scanner sc = new Scanner(System.in);
            System.out.println("Đăng nhập hệ thống");
            System.out.print("Username:");
            String Username = sc.nextLine();
            System.out.print("Password:");
            String Password = sc.nextLine();
            for(Account s : Accounts){
                if(Username.equals(s.getName())&&Password.equals(s.getPassword())){
                    Login = true;
                    User u= userRepository.getById(s.getId());
                    if(Role.ADMIN.equals(u.getRole())){
                        admin=true;

                    }
                }
            }
        } while (!Login);
        if(admin){
            MenuView menuView=new MenuView();
            menuView.show();}
        else{
            System.out.println("Toi la user:");
        }

    }
}

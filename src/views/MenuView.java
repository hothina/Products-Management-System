package views;

import java.util.Scanner;

public class MenuView {
    private final UserView userView = new UserView(this);
    private final DrinkView drinkView = new DrinkView(this);
    private final OrderView orderView = new OrderView(this);

    Scanner scanner = new Scanner(System.in);

    public void showDrink1(boolean admin) {

        try {
            int choice ;

            showDrink();
            do {
                String ch = scanner.next();
                choice = Integer.parseInt(ch);
                switch (choice) {
                    case 1:
                        drinkView.showDrinks();
                        break;
                    case 2:
                        drinkView.addDrink();
                        break;
                    case 3:
                        drinkView.updateDrink();
                        break;
                    case 4:
                        menu(admin);
                        break;
                }
                System.out.print(" Tiếp tục:  Nhấn Y        ");
                String character = scanner.next();
                if (character.equalsIgnoreCase("y")) {
                    showDrink();

                } else {
                    menu(admin);
                }

            } while (choice != 0);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.print(" Tiếp tục:  Nhấn Y         ");
            String s = scanner.next();
            if (s.equalsIgnoreCase("y")){
                showDrink1(admin);
            } else {
                menu(admin);
            }

        }

    }

    public void showUser1(boolean admin) {
        try {
            int choice ;
            showUser();
            do {
                String sr = scanner.next();
                choice = Integer.parseInt(sr);
                switch (choice) {
                    case 1:
                        userView.showUsers();
                        break;
                    case 2:
                        userView.addUser();
                        break;
                    case 3:
                        userView.updateUser();
                        break;
                    case 4:
                        menu(admin);
                        break;
                }

                System.out.print(" Tiếp tục:  Nhấn Y           ");
                String character = scanner.next();
                if (character.equalsIgnoreCase("y")) {
                    showUser();
                } else {
                    menu(admin);
                }

            } while (choice != 0);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.print(" Tiếp tục:  Nhấn Y          ");
            String s = scanner.next();
            if (s.equalsIgnoreCase("y")){
                showUser1(admin);
            } else {
                menu(admin);
            }
        }
    }

    public void sowOrder1(Boolean admin){
        try {
            int choice ;
            showOrders();
            do {
                String sr = scanner.next();
                choice = Integer.parseInt(sr);
                switch (choice) {
                    case 1:
                        orderView.showOrderList();
                        break;
                    case 2:
                        orderView.addOrder(admin);
                        break;
                    case 3:
                        menu(admin);
                        break;
                } System.out.print(" Tiếp tục:  Nhấn Y            ");
                String character = scanner.next();
                if (character.equalsIgnoreCase("y")) {
                    showOrders();
                } else {
                    menu(admin);
                }
            } while (choice != 0);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.print(" Tiếp tục:  Nhấn Y           ");
            String s = scanner.next();
            if (s.equalsIgnoreCase("y")){
               sowOrder1(admin);
            } else {
                menu(admin);
            }
        }
    }



    public void showUser() {
        System.out.println("\t\t\t\t\t----------------- USER --------------------");
        System.out.println("\t\t\t\t\t|   1. Danh sách người dùng:               |");
        System.out.println("\t\t\t\t\t|   2. Thêm người dùng:                    |");
        System.out.println("\t\t\t\t\t|   3. Sửa thông tin người dùng:           |");
        System.out.println("\t\t\t\t\t|   4. Quay lại menu chính:                |");
        System.out.println("\t\t\t\t\t-------------------------------------------  ");
        System.out.print("\t\t\tChọn chức năng: ");
    }

    public void showDrink() {
        System.out.println("\t\t\t\t\t-------------------- Drink ------------------");
        System.out.println("\t\t\t\t\t|   1. Danh sách đồ uống:                    |");
        System.out.println("\t\t\t\t\t|   2. Thêm đồ uống:                         |");
        System.out.println("\t\t\t\t\t|   3. Sửa thông tin đồ uống:                |");
        System.out.println("\t\t\t\t\t|   4. Quay lại menu chính:                  |");
        System.out.println("\t\t\t\t\t---------------------------------------------");
        System.out.print(" \t\t\tChọn chức năng: ");
    }

    public void showMenu() {
        System.out.println("\t\t\t\t\t-------------------- Welcome ------------------");
        System.out.println("\t\t\t\t\t|   1. Quản lý người dùng:                     |");
        System.out.println("\t\t\t\t\t|   2. Quản lý đồ uống:                        |");
        System.out.println("\t\t\t\t\t|   3. Quản lý hóa đơn đặt hàng:               |");
        System.out.println("\t\t\t\t\t|   0. Thoát chương trình:                     |");
        System.out.println("\t\t\t\t\t------------------------------------------------");
        System.out.print(" \t\t\tChọn chức năng: ");
    }
    public void showOrders() {
        System.out.println("\t\t\t\t\t-------------------- ORDER ------------------");
        System.out.println("\t\t\t\t\t|   1. Danh sách hóa đơn:                     |");
        System.out.println("\t\t\t\t\t|   2. Thêm hóa đơn đặt hàng:                 |");
        System.out.println("\t\t\t\t\t|   3. Quay lại menu chính:                   |");
        System.out.println("\t\t\t\t\t-----------------------------------------------");
        System.out.print(" \t\t\tChọn chức năng: ");
    }


    public void menu(boolean admin) {
        try {
            int choice ;
            showMenu();
            do {
                String ch = scanner.next();
                choice = Integer.parseInt(ch);
                switch (choice) {
                    case 1:
                        showUser1(admin);
                        break;
                    case 2:
                        showDrink1(admin);

                        break;
                    case 3:
                        sowOrder1(admin);
                        break;

                    case 0:
                        System.exit(0);
                        break;
                }
                System.out.print(" Bạn muốn tiếp tục: nhấn Y  ");
                String character = scanner.next();
                if (character.equalsIgnoreCase("y")) {
                    menu(admin);
                } else  {
                    System.exit(0);
                }
            } while (choice != 0);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.print(" Bạn muốn tiếp tục: nhấn Y    ");
            String s = scanner.next();
            if (s.equalsIgnoreCase("y")){
                menu(admin);
            } else {
                System.exit(0);
            }
        }
    }

    public void showMenuUser() {
        System.out.println("\t\t\t\t\t-------------------- Welcome ------------------");
        System.out.println("\t\t\t\t\t|   1. Đặt hàng:                               |");
        System.out.println("\t\t\t\t\t|   0. Thoát chương trình:                     |");
        System.out.println("\t\t\t\t\t------------------------------------------------");
        System.out.print(" \t\t\tChọn chức năng: ");
    }
    public void menuUser(boolean admin) {
        try {
            int choice ;
            showMenuUser();
            do {
                String ch = scanner.next();
                choice = Integer.parseInt(ch);
                switch (choice) {
                    case 1:
                        orderView.addOrder(admin);
                        break;
                    case 0:
                        System.exit(0);
                        break;
                }
                System.out.print(" Bạn muốn tiếp tục: nhấn Y      ");
                String character = scanner.next();
                if (character.equalsIgnoreCase("y")) {
                    menuUser(admin);
                } else  {
                    System.exit(0);
                }
            } while (choice != 0);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.print(" Bạn muốn tiếp tục: nhấn Y    ");
            String s = scanner.next();
            if (s.equalsIgnoreCase("y")){
                menu(admin);
            } else {
                System.exit(0);
            }
        }

    }



    public static void main(String[] args) {
        MenuView a = new MenuView();
        a.sowOrder1(true);
    }
}

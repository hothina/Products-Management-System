package views;

import java.util.Scanner;

public class MenuView {
    private final UserView userView = new UserView();
    private final DrinkView drinkView = new DrinkView();
    private final OrderView orderView = new OrderView();

    Scanner scanner = new Scanner(System.in);

    public void showDrink1() {

        try {
            int choice = 0;

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
                    case 0:
                        System.exit(0);
                        break;
                }
                System.out.print(" Bạn muốn tiếp tục: nhấn Y\n Trở về: nhấn N  ");
                String character = scanner.next();
                if (character.equalsIgnoreCase("y")) {
                    showDrink();
                } else if (character.equalsIgnoreCase("n")) {
                    menu();
                } else {
                    System.exit(0);
                }
            } while (choice != 0);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.print("Nhấn Y để tiếp tục: ");
            String s = scanner.next();
            if (s.equalsIgnoreCase("y")){
                showDrink1();
            } else {
                menu();
            }

        }

    }

    public void showUser1() {
        try {
            int choice = 0;
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
                    case 0:
                        System.exit(0);
                        break;
                }

                System.out.print(" Bạn muốn tiếp tục: nhấn Y\n Trở về: Nhấn N  ");
                String character = scanner.next();
                if (character.equalsIgnoreCase("y")) {
                    showUser();
                } else if (character.equalsIgnoreCase("n")) {
                    menu();
                } else {
                    System.exit(0);
                }

            } while (choice != 0);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.print("Nhấn Y để tiếp tục: ");
            String s = scanner.next();
            if (s.equalsIgnoreCase("y")){
                showUser1();
            } else {
                menu();
            }
        }
    }

    public void sowOrder1(){
        try {
            int choice =0;
            showOrders();
            do {
                String sr = scanner.next();
                choice = Integer.parseInt(sr);
                switch (choice) {
                    case 1:
                        orderView.showOrderList();
                        break;
                    case 2:
                        orderView.addOrder();
                        break;
                    case 0:
                        System.exit(0);
                } System.out.print(" Bạn muốn tiếp tục: nhấn Y\n Trở về: nhấn N  ");
                String character = scanner.next();
                if (character.equalsIgnoreCase("y")) {
                    showOrders();
                } else if (character.equalsIgnoreCase("n")) {
                    menu();
                } else {
                    System.exit(0);
                }
            } while (choice != 0);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.print("Nhấn Y để tiếp tục: ");
            String s = scanner.next();
            if (s.equalsIgnoreCase("y")){
               sowOrder1();
            } else {
                menu();
            }
        }
    }

    public void showUser() {
        System.out.println("\t\t\t\t\t----------------- USER --------------------");
        System.out.println("\t\t\t\t\t|   1. Danh sách người dùng:               |");
        System.out.println("\t\t\t\t\t|   2. Thêm người dùng:                    |");
        System.out.println("\t\t\t\t\t|   3. Sửa thông tin người dùng:           |");
        System.out.println("\t\t\t\t\t|   0. Thóat chương trình:                 |");
        System.out.println("\t\t\t\t\t-------------------------------------------  ");
        System.out.print("\t\t\tChọn chức năng: ");
    }

    public void showDrink() {
        System.out.println("\t\t\t\t\t-------------------- Drink ------------------");
        System.out.println("\t\t\t\t\t|   1. Danh sách đồ uống:                    |");
        System.out.println("\t\t\t\t\t|   2. Thêm đồ uống:                         |");
        System.out.println("\t\t\t\t\t|   3. Sửa thông tin đồ uống:                |");
        System.out.println("\t\t\t\t\t|   0. Thoát chương trình:                   |");
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
        System.out.println("\t\t\t\t\t|   0. Thoát chương trình:                    |");
        System.out.println("\t\t\t\t\t-----------------------------------------------");
        System.out.print(" \t\t\tChọn chức năng: ");
    }

    public void menu() {
        try {
            int choice = 0;
            showMenu();
            do {
                String ch = scanner.next();
                choice = Integer.parseInt(ch);
                switch (choice) {
                    case 1:
                        showUser1();
                        break;
                    case 2:
                        showDrink1();
                        break;
                    case 3:
                        sowOrder1();
                    case 0:
                        System.exit(0);
                        break;
                }
                System.out.print(" Bạn muốn tiếp tục: nhấn Y\n Thoát chương trình: nhấn N  ");
                String character = scanner.next();
                if (character.equalsIgnoreCase("y")) {
                    menu();
                } else if (character.equalsIgnoreCase("n")) {
                    System.exit(0);
                }
            } while (choice != 0);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.print("Nhấn Y để quay lại: ");
            String s = scanner.next();
            if (s.equalsIgnoreCase("y")){
                menu();
            } else {
                System.exit(0);
            }
        }
    }

    public static void main(String[] args) {
        MenuView a = new MenuView();
        a.sowOrder1();
    }
}

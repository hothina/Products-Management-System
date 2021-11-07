package views;

import java.util.Scanner;

public class MenuView {
    private final UserView userView = new UserView();
    private final DrinkView drinkView = new DrinkView();
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
                System.out.print(" Bạn muốn tiếp tục: nhấn Y\n Thoát chương trình: nhấn N  ");
                String character = scanner.next();
                if (character.equalsIgnoreCase("y")) {
                    showDrink();
                } else if (character.equalsIgnoreCase("n")) {
                    System.exit(0);
                }
            } while (choice != 0);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.print("Nhấn Y để quay lại: ");
            String s = scanner.next();
            if (s.equalsIgnoreCase("y"))
                showDrink1();

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

                System.out.print(" Bạn muốn tiếp tục: nhấn Y\n Thoát chương trình: nhấn N  ");
                String character = scanner.next();
                if (character.equalsIgnoreCase("y")) {
                    showUser1();
                } else if (character.equalsIgnoreCase("n")) {
                    System.exit(0);
                }
            } while (choice != 0);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.print("Nhấn Y để quay lại: ");
            String s = scanner.next();
            if (s.equalsIgnoreCase("y"))
                showDrink1();

        }
    }

    public void showUser() {
        System.out.println("----------------- USER --------------------");
        System.out.println("|   1. Danh sách người dùng:               |");
        System.out.println("|   2. Thêm người dùng:                    |");
        System.out.println("|   3. Sửa thông tin người dùng:           |");
        System.out.println("|   0. Thóat chương trình:                 |");
        System.out.println("-------------------------------------------  ");
        System.out.print("Chọn chức năng: ");
    }

    public void showDrink() {
        System.out.println("-------------------- Drink ------------------");
        System.out.println("|   1. Danh sách đồ uống:                    |");
        System.out.println("|   2. Thêm đồ uống:                         |");
        System.out.println("|   3. Sửa thông tin đồ uống:                |");
        System.out.println("|   0. Thoát chương trình:                   |");
        System.out.println("---------------------------------------------");
        System.out.print(" Chọn chức năng: ");
    }

    public void showMenu() {
        System.out.println("-------------------- Welcome ------------------");
        System.out.println("|   1. Quản lý người dùng:                     |");
        System.out.println("|   2. Quản lý đồ uống:                        |");
        System.out.println("|   3. Quản lý đặt hàng:                       |");
        System.out.println("|   4. Quản lý hóa đơn:                        |");
        System.out.println("|   0. Thoát chương trình:                     |");
        System.out.println("------------------------------------------------");
        System.out.print(" Chọn chức năng: ");
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
                    case 0:
                        System.exit(0);
                        break;
                }
                System.out.print(" Bạn muốn tiếp tục: nhấn Y\n Thoát chương trình: nhấn N  ");
                String character = scanner.next();
                if (character.equalsIgnoreCase("y")) {
                    showUser1();
                } else if (character.equalsIgnoreCase("n")) {
                    System.exit(0);
                }
            } while (choice != 0);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.print("Nhấn Y để quay lại: ");
            String s = scanner.next();
            if (s.equalsIgnoreCase("y"))
                menu();
        }

    }
}

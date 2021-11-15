package views;

import model.Drink;

import repository.DrinkRepository;
import services.DrinkServices;
import services.IDrinkServices;
import sort.SortDrink;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class DrinkView {
    public static final String DRINK_REGEX = "^([A-Z]+[a-z]*[ ]?)+$";
    private IDrinkServices drinkServices;
    private MenuView me;
    Scanner input = new Scanner(System.in);

    Scanner scanner;

    public DrinkView(MenuView m) {
        scanner = new Scanner(System.in);
        drinkServices = new DrinkServices();
        me = m;
    }

    public boolean isDrinkFormat(String name) {
        return Pattern.compile(DRINK_REGEX).matcher(name).matches();
    }

    public void showDrinks() {
        try {
            System.out.println("\t\t\t\t\t--------------------  DANH SÁCH ĐỒ UỐNG  ------------------------");


            System.out.printf("\t\t\t\t\t\t%-5s %-20s %-20s %-10s\n", "Id", "Tên đồ uống", "Số lượng", "giá");
            List<Drink> drinkList = drinkServices.getDrink();
            SortDrink sortDrink = new SortDrink();
            Collections.sort(drinkList, sortDrink);

            for (Drink drink : drinkList) {
                System.out.printf("\t\t\t\t\t\t%-5s %-20s %-20s %s d\n", drink.getId(), drink.getName(), drink.getQuantity(), drink.getPrice());
            }

            System.out.println("\t\t\t\t\t----------------------------------------------------------------");
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public void addDrink() {
        try {
            System.out.print("Id (là số): ");
            String ids = scanner.next();
            int id = Integer.parseInt(ids);
            System.out.print("Tên đồ uống (vd Tra Xanh): ");
            String name = input.nextLine();
            if (!isDrinkFormat(name)) {
                System.out.println("Nhập sai (vd: Tra Xanh)");
               nextAddDrink();

            }else {
                System.out.print("Số lượng: ");
                String quantitys = scanner.next();
                int quantity = Integer.parseInt(quantitys);
                System.out.print("Giá: ");
                String prices = scanner.next();
                long price = Long.parseLong(prices);

                Drink drink = new Drink(id, name, quantity, price);
                drinkServices.addDrink(drink);
                System.out.println("Đã thêm đồ uống");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            nextAddDrink();

        }
    }

    private void nextAddDrink() {

        System.out.println("Tiếp tục thêm đồ uống : Nhập Y            Trở về: Nhập N  ");
        String s = scanner.next();
        if (s.equalsIgnoreCase("y")) {
            addDrink();
        } else if (s.equalsIgnoreCase("n")){
            me.showDrink1(true);
        }else {
            me.menu(true);
        }
    }

    public void updateDrink() {
        try {
            DrinkRepository drinkRepository = new DrinkRepository();
            List<Drink> Drinks = drinkRepository.getDrink();
            System.out.print("Tên đồ uống ( vd Tra Xanh): ");
            String name = input.nextLine();

            boolean check = false;
            for (Drink drink : Drinks) {
                if (name.equalsIgnoreCase(drink.getName())) {
                    System.out.println(drink.getName());

                    System.out.print("Số lượng: ");

                    String quantitys = scanner.next();
                    int quantity = Integer.parseInt(quantitys);
//                    System.out.print(quantity);
                    System.out.print("Giá: ");
                    String prices = scanner.next();
                    long price = Long.parseLong(prices);
//                    System.out.print(price);
                    Drink drink1 = new Drink(drink.getId(), name, quantity, price);

                    drinkServices.updateDrink(drink1);
                    check = true;

                }
            }
            if (check) {
                System.out.println("Đã cập nhật đồ uống");

            } else {
                System.out.println("Không tồn tại đồ uống ");
                nextUpdate();

            }

        } catch (Exception e) {
            e.getStackTrace();

           nextUpdate();
        }
    }

    private void nextUpdate() {
        System.out.print("Tiếp tục cập nhật đồ uống: Nhập Y        Trở về: Nhập N ");
        String s = scanner.next();
        if (s.equalsIgnoreCase("y")){
            updateDrink();
        } else if (s.equalsIgnoreCase("n")){
            me.showDrink1(true);
        } else {
            me.menu(true);
        }
    }
}




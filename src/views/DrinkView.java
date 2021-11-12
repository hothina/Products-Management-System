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
    Scanner input = new Scanner(System.in);

    Scanner scanner;

    public DrinkView() {
        scanner = new Scanner(System.in);
        drinkServices = new DrinkServices();
    }

    public boolean isDrinkFormat(String name) {
        return Pattern.compile(DRINK_REGEX).matcher(name).matches();
    }

    public void showDrinks() {
        try {
            System.out.println("\t\t\t\t\t--------------------  DANH SACH DO UONG  ------------------------");


            System.out.printf("\t\t\t\t\t\t%-5s %-20s %-20s %-10s\n", "Id", "Ten do uong", "So luong", "gia");
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
            System.out.print("Id (la mot so): ");
            String ids = scanner.next();
            int id = Integer.parseInt(ids);
            System.out.print("Ten do uong (Tra Xanh): ");
            String name = input.nextLine();
            if (!isDrinkFormat(name)) {
                System.out.println("Nhap sai (vd: Tra Xanh)");
               nextAddDrink();

            }
            System.out.print("So luong: ");
            String quantitys = scanner.next();
            int quantity = Integer.parseInt(quantitys);
            System.out.print("Gia: ");
            String prices = scanner.next();
            long price = Long.parseLong(prices);

            Drink drink = new Drink(id, name, quantity, price);
            drinkServices.addDrink(drink);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            nextAddDrink();

        }
    }

    private void nextAddDrink() {
        System.out.println("Nhap Y để quay lai: ");
        String s = scanner.next();
        if (s.equalsIgnoreCase("y")) {
            addDrink();
        } else {
            System.exit(0);
        }
    }

    public void updateDrink() {
        try {
            DrinkRepository drinkRepository = new DrinkRepository();
            List<Drink> Drinks = drinkRepository.getDrink();
            System.out.print("Ten do uong (TraXanh): ");
            String name = input.nextLine();

            boolean check = false;
            for (Drink drink : Drinks) {
                if (name.equalsIgnoreCase(drink.getName())) {
                    System.out.println(drink.getName());

                    System.out.print("So luong: ");

                    String quantitys = scanner.next();
                    int quantity = Integer.parseInt(quantitys);
                    System.out.println(quantity);
                    System.out.print("Gia: ");
                    String prices = scanner.next();
                    long price = Long.parseLong(prices);
                    System.out.println(price);
                    Drink drink1 = new Drink(drink.getId(), name, quantity, price);

                    drinkServices.updateDrink(drink1);
                    check = true;

                }
            }
            if (check) {
                System.out.println("Da cap nhat do uong!!");

            } else {
                System.out.println("Khong ton tai do uong");
                nextUpdate();

            }

        } catch (Exception e) {
            e.getStackTrace();

           nextUpdate();
        }
    }

    private void nextUpdate() {
        System.out.print("Nhập Y để tiếp tục : ");
        String s = scanner.next();
        if (s.equalsIgnoreCase("y")){
            updateDrink();
        } else {
            System.exit(0);
        }
    }


    public static void main(String[] args) {
        DrinkView a = new DrinkView();
        a.showDrinks();
        a.updateDrink();
        a.addDrink();
    }
}




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
    public static final String DRINK_REGEX = "^([A-Z]+[a-z]{1,6}){1,6}";
    private IDrinkServices drinkServices;
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
            Integer id = Integer.parseInt(ids);
            System.out.print("Ten do uong (TraXanh): ");
            String name = scanner.next();
            if (!isDrinkFormat(name)) {
                System.out.println("Nhap sai (vd: TranNhi)");
                System.out.println("Nhap Y để quay lai: ");
                String s = scanner.next();
                if (s.equalsIgnoreCase("y")) {
                    addDrink();
                } else {
                    System.exit(0);
                }

            }
            System.out.print("So luong: ");
            String quantitys = scanner.next();
            Integer quantity = Integer.parseInt(quantitys);
            System.out.print("Gia: ");
            String prices = scanner.next();
            Long price = Long.parseLong(prices);

            Drink drink = new Drink(id, name, quantity, price);
            drinkServices.addDrink(drink);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Nhap Y để quay lai: ");
            String s = scanner.next();
            if (s.equalsIgnoreCase("y")) {
                addDrink();
            } else {
                System.exit(0);
            }

        }
    }

    public void updateDrink() {
        try {
            DrinkRepository drinkRepository = new DrinkRepository();
            List<Drink> Drinks = drinkRepository.getDrink();
            System.out.print("Ten do uong (TraXanh): ");
            String name = scanner.next();

            boolean check = false;
            for (Drink drink : Drinks) {
                if (name.equalsIgnoreCase(drink.getName())) {
                    System.out.println(drink.getName());

                    System.out.print("So luong: ");

                    String quantitys = scanner.next();
                    Integer quantity = Integer.parseInt(quantitys);
                    System.out.println(quantity);
                    System.out.print("Gia: ");
                    String prices = scanner.next();
                    Long price = Long.parseLong(prices);
                    System.out.println(price);
                    Drink drink1 = new Drink(drink.getId(), name, quantity, price);

                    drinkServices.updateDrink(drink1);
                    check = true;

                }
            }
            if (check) {
                System.out.println("Da cap nhat do uong!!");
                System.out.print("  Nhap Y để tiep tuc: ");
                String s = scanner.next();
                if (s.equalsIgnoreCase("y")) {
                    updateDrink();
                } else {
                    MenuView mn = new MenuView();
                    mn.showDrink1();
                }
            } else {
                System.out.println("Khong ton tai do uong");
                System.out.print("  Nhap Y để tiep tuc: ");
                String s1 = scanner.next();
                if (s1.equalsIgnoreCase("y")){
                    updateDrink();
                } else {
                    MenuView mn = new MenuView();
                    mn.showDrink1();
                }

            }

        } catch (Exception e) {

            System.out.print("Nhap Y để quay lai : ");
            String s = scanner.next();
            if (s.equalsIgnoreCase("y")){
                updateDrink();
            } else {
                System.exit(0);
            }

        }
    }

    public static void main(String[] args) {
        DrinkView a = new DrinkView();
        a.showDrinks();
    }
}




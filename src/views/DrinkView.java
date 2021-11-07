package views;

import model.Drink;
import model.User;
import repository.DrinkRepository;
import services.DrinkServices;
import services.IDrinkServices;

import java.util.List;
import java.util.Scanner;

public class DrinkView {
    private IDrinkServices drinkServices;
    Scanner scanner;

    public DrinkView() {
        scanner = new Scanner(System.in);
        drinkServices = new DrinkServices();
    }

    public void showDrinks() {
        try {
            System.out.println("-----------------------  DANH SACH DO UONG  --------------------------");


            System.out.printf("\n%-5s %-25s %-20s %-20s\n", "Id", "Ten do uong", "So luong", "gia");
            List<Drink> drinkList = drinkServices.getDrink();

            for (Drink drink : drinkList) {
                System.out.printf("%-5s %-25s %-20s %-20s\n", drink.getId(), drink.getName(), drink.getQuantity(), drink.getPrice());
            }

            System.out.println("---------------------------------------------------------------------");
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public void addDrink() {
        try {
            System.out.print("Id (la mot so): ");
            String ids = scanner.next();
            Integer id = Integer.parseInt(ids);
            System.out.print("Ten do uong (Tra Xanh): ");
            String name = scanner.next();
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
            System.out.println("Thêm thành công");
            System.out.println("Nhap Y để quay lai: ");

            String s = scanner.next();
            if (s.equalsIgnoreCase("y"))
                addDrink();
        }
    }

    public void updateDrink() {
        try {
            DrinkRepository drinkRepository = new DrinkRepository();
            List<Drink> Drinks = drinkRepository.getDrink();
            System.out.print("Ten do uong (TraXanh): ");
            String name = scanner.next();

            boolean check = false;
            for (Drink drink: Drinks) {
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
                    check=true;

                }
            }
            if (check) {
                System.out.println("Da cap nhat do uong!!");
                System.out.print("  Nhap Y để tiep tuc: ");
                String s = scanner.next();
                if (s.equalsIgnoreCase("y"))
                    updateDrink();}
            else {
                    System.out.println("Khong ton tai do uong");
                    System.out.print("  Nhap Y để tiep tuc: ");
                    String s1 = scanner.next();
                    if (s1.equalsIgnoreCase("y"))
                        updateDrink();
                }

        } catch (Exception e) {

            System.out.print("Nhap Y để quay lai : ");
            String s = scanner.next();
            if (s.equalsIgnoreCase("y"))
                updateDrink();
        }
    }

    public static void main(String[] args) {
        DrinkView a = new DrinkView();
        a.addDrink();
    }
}




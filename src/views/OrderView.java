package views;

import model.Drink;
import model.OrderItem;
import repository.DrinkRepository;
import repository.IDrinkRepository;
import services.*;
import utils.DateUtils;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class OrderView {
    private IOrderItemServices orderItemServices;
    private IOrderServices orderServices;
    private IDrinkServices drinkServices;
    Scanner scanner = new Scanner(System.in);

    public OrderView() {
        orderItemServices = new OrderItemServices();
        drinkServices = new DrinkServices();
        orderServices = new OrderServices();
    }

    public void showOrderItems() {
        try {
            System.out.println("------------------------------- Đặt Hàng -----------------------------------------------");
            System.out.printf("\n%-5s %-10s %-12s %-12s %-12s \n", "Id", "Id Sp", "Tên Sp ", "Giá", "Số lượng");
            List<OrderItem> orderItemList = orderItemServices.getOrderItem();

            for (OrderItem orderItem : orderItemList) {
                System.out.printf("%-5s %-10s %-12s %-12s %-12s \n", orderItem.getId(), orderItem.getDrinkId(),
                        orderItem.getDrinkName(), orderItem.getPrice(), orderItem.getQuantity());
            }
            System.out.println("----------------------------------------------------------------------------------------");
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public void addOrder() {
        DrinkView drinkView = new DrinkView();
        drinkView.showDrinks();
        try {

            System.out.print(" Id Drink (la mot so): ");
            int idD = scanner.nextInt();
            List<Drink> drinkList = drinkServices.getDrink();
            boolean check = false;
            for (Drink drink : drinkList) {
                if (idD == drink.getId()) {
                    int drinkId = drink.getId();
                    long price = drink.getPrice();
                    String name = drink.getName();

                    System.out.println("Soluong: ");
                    int quantity = scanner.nextInt();
                    if (quantity <= drink.getQuantity()) {
                        drink.setQuantity(drink.getQuantity() - quantity);
                        drinkServices.updateDrink(drink);
                    } else {
                        System.out.println("Qua so luong hien co");
                        System.out.print("  Nhap Y để tiep tuc: ");
                        String s = scanner.next();
                        if (s.equalsIgnoreCase("y")) {
                            addOrder();
                        } else {
                            System.exit(0);
                        }
                    }

                    OrderItem orderItem = new OrderItem(DateUtils.currentTimeSecond(), drinkId, name, price, quantity);
                    orderItemServices.addOrderItem(orderItem);

                    check = true;
                }
            }
            if (check) {

                System.out.println("Da dat hang!!");
                System.out.print("  Nhap Y để tiep tuc: ");
                String s = scanner.next();
                if (s.equalsIgnoreCase("y")) {
                    addOrder();
                } else {
                    System.exit(0);
                }
            } else {
                System.out.println("Khong ton tai do uong");
                System.out.print("  Nhap Y để tiep tuc: ");
                String s1 = scanner.next();
                if (s1.equalsIgnoreCase("y")) {
                    addOrder();
                } else {
                    System.exit(0);
                }

            }
        } catch (Exception e) {
            System.out.print("Nhap Y để quay lai : ");
            String s = scanner.next();
            if (s.equalsIgnoreCase("y")) {
                addOrder();
            } else {
                System.exit(0);
            }
        }
    }

    public static void main(String[] args) {
        OrderView orderView = new OrderView();
        orderView.addOrder();
    }
}

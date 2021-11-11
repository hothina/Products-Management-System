package views;

import model.Drink;
import model.Order;
import model.OrderItem;
import services.*;
import utils.DateUtils;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class OrderView {
    public static final String NAME_REGEX = "^([A-Z]+[a-z]{1,6}){1,6}";
    public static final String NUMBER_PHONE_REGEX = "^[0][1-9][0-9]{8,9}$";
    private IOrderItemServices orderItemServices;
    private IOrderServices orderServices;
    private IDrinkServices drinkServices;
    Scanner scanner = new Scanner(System.in);

    public OrderView() {
        orderItemServices = new OrderItemServices();
        drinkServices = new DrinkServices();
        orderServices = new OrderServices();
    }
    public boolean isFormatName(String fullName) {

        return Pattern.compile(NAME_REGEX).matcher(fullName).matches();
    }
    public boolean isPhoneNumber(String phoneNumber){
        return Pattern.compile(NUMBER_PHONE_REGEX).matcher(phoneNumber).matches();
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

    public void addOrderItem() {

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
                            addOrderItem();
                        } else {
                            System.exit(0);
                        }
                    }

                    long total=quantity* drink.getPrice();;
                    OrderItem orderItem = new OrderItem(DateUtils.currentTimeSecond(), drinkId, name, price, quantity,total);
                    orderItemServices.addOrderItem(orderItem);

                    check = true;
                }
            }
            if (check) {

                System.out.println("Da dat hang!!");

            } else {
                System.out.println("Khong ton tai do uong");
                System.out.print("  Nhap Y để tiep tuc: ");
                String s1 = scanner.next();
                if (s1.equalsIgnoreCase("y")) {
                    addOrderItem();
                } else {
                    System.exit(0);
                }

            }
        } catch (Exception e) {
            System.out.print("Nhap Y để quay lai : ");
            String s = scanner.next();
            if (s.equalsIgnoreCase("y")) {
                addOrderItem();
            } else {
                System.exit(0);
            }
        }
    }
    public void addOrder(){
        DrinkView drinkView = new DrinkView();
        drinkView.showDrinks();
      try {
          System.out.print("Ho va ten(vd: TranNhi): ");

        String name = scanner.next();
        if (!isFormatName(name)){
            System.out.println("Nhap sai (vd TranNhi)");
            System.out.println("Nhap Y để quay lai: ");
            String s = scanner.next();
            if (s.equalsIgnoreCase("y")){
                addOrder();
            } else {
                System.exit(0);
            }
        }
        System.out.print("So dien thoai:(vd: 0909429345): ");
        String phoneNumber = scanner.next();
        if (!isPhoneNumber(phoneNumber)){
            System.out.println("Nhap sai (vd: 0123456789)");
            System.out.println("Nhap Y để quay lai: ");
            String s = scanner.next();
            if (s.equalsIgnoreCase("y")){
                addOrder();
            } else {
                System.exit(0);
            }
        }
        System.out.print("Dia chi:(vd: QuangBinh): ");
        String address = scanner.next();
        if (!isFormatName(address)){
            System.out.println("Nhap sai (vd: QuangBinh)");
            System.out.println("Nhap Y để quay lai: ");
            String s = scanner.next();
            if (s.equalsIgnoreCase("y")){
                addOrder();
            } else {
                System.exit(0);
            }
        }
        System.out.print("Ngay tao:(vd: 10-10-2021): ");
        String createdAt= scanner.next();
        Date date = DateUtils.stringToDate(createdAt);
        List<OrderItem> orderItemList = orderItemServices.getOrderItem();
        long start = orderItemList.size();


        int choice;
        addOrderItem();


        do {
            System.out.print("Muon dat them: Bam 1\n Da dat du hang: bam 2:  ");
            choice = scanner.nextInt();
            switch (choice){
                case 1:
                    System.out.print("Muon dat them: ");
                    addOrderItem();
                    break;
                case 2:
                    System.out.println("Da dat du hang");
                default:
                    break;
            }
        } while (choice!=2);
//                 character = scanner.next();
//          System.out.println(orderItemServices.getOrderItem().size());
         long end = orderItemServices.getOrderItem().size();

//     int      String orderItems ;
//           OrderItem orderItem =new OrderItem(orderItems);
        long total = 2;


        Order order = new Order(DateUtils.currentTimeSecond(),name,phoneNumber,address,start,end,total,date);

        orderServices.addOrder(order);
      }catch (Exception e){
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
        orderView.addOrderItem();
    }
}

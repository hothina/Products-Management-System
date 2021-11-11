package views;

import model.Drink;
import model.Order;
import model.OrderItem;
import services.*;
import sort.SortOrder;
import utils.DateUtils;

import java.io.IOException;
import java.util.*;
import java.util.regex.Pattern;

public class OrderView {
    public static final String NAME_REGEX = "^([A-Z]+[a-z]{1,6}){1,6}";
    public static final String NUMBER_PHONE_REGEX = "^[0][1-9][0-9]{8,9}$";
    private IOrderItemServices orderItemServices;
    private IOrderServices orderServices;
    private IDrinkServices drinkServices;


    MenuView mn = new MenuView();

    Scanner scanner = new Scanner(System.in);

    public OrderView() {
        orderItemServices = new OrderItemServices();
        drinkServices = new DrinkServices();
        orderServices = new OrderServices();
    }

    public boolean isFormatName(String fullName) {

        return Pattern.compile(NAME_REGEX).matcher(fullName).matches();
    }

    public boolean isPhoneNumber(String phoneNumber) {
        return Pattern.compile(NUMBER_PHONE_REGEX).matcher(phoneNumber).matches();
    }

    public void showOrderList() {
        try {
            System.out.println("\t\t\t------------------------------------------- ORDER lIST -------------------------------------------------------");
            System.out.printf("\n\t\t\t\t%-10s %-10s %-15s %-10s %s\n", "Id", "Tên KH", "SDT ", "Địa chỉ", "Ngày tạo");

            List<Order> orderList = orderServices.getOrder();
            SortOrder sortOrder = new SortOrder();
            Collections.sort(orderList, sortOrder);
            long totalList =0;
            for (Order order: orderList) {
                System.out.printf("\n\t\t\t\t%-10d %-10s %-15s %-10s %s ",order.getId(), order.getName(), order.getPhoneNumber(),order.getAddress(),order.getCreatedAt());

                List<OrderItem> orderItemList = new ArrayList<>();
                List<OrderItem> orderItemListAll = orderItemServices.getOrderItem();
                long totalItem =0;
                for (OrderItem odt : orderItemListAll) {
                    if (odt.getIdOrder() == order.getId()) {
                        orderItemList.add(odt);
                        totalItem=totalItem+odt.getTotal();

                    }
                }
                System.out.println("\t\t"+totalItem+" d");
                totalList=totalList+totalItem;
            }

            System.out.println("\n\n\t\t\t--------------------------------------------------------------------------------        "+totalList+" d");
            System.out.println("\n\t\t\t-----------------------------------------------------------------------------------------------------------");
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public void showOrder(long idOrder) {

        List<Order> orderList = orderServices.getOrder();

        for (Order order : orderList) {
            if (idOrder == order.getId()) {
                System.out.println("\t\t\t---------------------------------- HOA DON ---------------------------------");
                System.out.println("\t\t\t\t\tTen KH: "+order.getName() + "    Dia chi: " + order.getAddress() + "  SDT:" + order.getPhoneNumber() + "\n\t\t\t\t\tNgay tao:  " + order.getCreatedAt());
                break;
            }
        }
        List<OrderItem> orderItemList = new ArrayList<>();
        List<OrderItem> orderItemListAll = orderItemServices.getOrderItem();
        long total =0;
        for (OrderItem odt : orderItemListAll) {
            if (odt.getIdOrder() == idOrder) {
                orderItemList.add(odt);
                total=total+odt.getTotal();

            }
        }

        for (OrderItem or : orderItemList) {

            System.out.printf("\n\t\t\t\t\t%-5d %-5d %-12s %-10d %-8d %d  d ", or.getId(), or.getDrinkId(), or.getDrinkName(), or.getQuantity(), or.getPrice(), or.getTotal());

//            total = total+or.getTotal();

        }System.out.println("\n\n                                                           Tong tien:  "+total +" d");
        System.out.println("\t\t\t------------------------------------------------------------------------------");


    }


    public void addOrderItem(long idOrder) {

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
                            addOrderItem(idOrder);
                        } else {
                            System.exit(0);
                        }
                    }

                    long total = quantity * drink.getPrice();

                    OrderItem orderItem = new OrderItem(DateUtils.currentTimeSecond(), drinkId, name, price, quantity, total, idOrder);
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
                    addOrderItem(idOrder);
                } else {
                    System.exit(0);
                }

            }
        } catch (Exception e) {
            System.out.print("Nhap Y để quay lai : ");
            String s = scanner.next();
            if (s.equalsIgnoreCase("y")) {
                addOrderItem(idOrder);
            } else {
                System.exit(0);
            }
        }
    }

    public void addOrder() {
        DrinkView drinkView = new DrinkView();
        drinkView.showDrinks();
        try {
            long id = DateUtils.currentTimeSecond();
            System.out.print("Ho va ten(vd: TranNhi): ");

            String name = scanner.next();
            if (!isFormatName(name)) {
                System.out.println("Nhap sai (vd TranNhi)");
                System.out.println("Nhap Y để quay lai: ");
                String s = scanner.next();
                if (s.equalsIgnoreCase("y")) {
                    addOrder();
                } else {
                    System.exit(0);
                }
            }
            System.out.print("So dien thoai:(vd: 0909429345): ");
            String phoneNumber = scanner.next();
            if (!isPhoneNumber(phoneNumber)) {
                System.out.println("Nhap sai (vd: 0123456789)");
                System.out.println("Nhap Y để quay lai: ");
                String s = scanner.next();
                if (s.equalsIgnoreCase("y")) {
                    addOrder();
                } else {
                    System.exit(0);
                }
            }
            System.out.print("Dia chi:(vd: QuangBinh): ");
            String address = scanner.next();
            if (!isFormatName(address)) {
                System.out.println("Nhap sai (vd: QuangBinh)");
                System.out.println("Nhap Y để quay lai: ");
                String s = scanner.next();
                if (s.equalsIgnoreCase("y")) {
                    addOrder();
                } else {
                    System.exit(0);
                }
            }
            System.out.print("Ngay tao:(vd: 10-10-2021): ");
            String createdAt = scanner.next();
            Date date = DateUtils.stringToDate(createdAt);


            addOrderItem(id);

            int choice;

            do {
                System.out.print("Muon dat them: Bam 1\n Da dat du hang: bam 2:  ");
                choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        System.out.print("Muon dat them: ");
                        addOrderItem(id);
                        break;
                    case 2:
                        System.out.println("Da dat du hang");
                    default:
                        break;
                }
            } while (choice != 2);
            //            long end = orderItemServices.getOrderItem().size();

            Order order = new Order(id, name, phoneNumber, address,date);
            orderServices.addOrder(order);
            System.out.println(" In hóa đơn: Bấm Y                Quay lại: Bấm N");
            String s = scanner.next();
            if (s.equalsIgnoreCase("y")){
                showOrder(id);
            } else if (s.equalsIgnoreCase("n")){
                addOrder();

            } else { mn.showMenu();
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
        orderView.showOrderList();
    }
}

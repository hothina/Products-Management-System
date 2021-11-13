package views;

import model.Drink;
import model.Order;
import model.OrderItem;
import services.*;
import sort.SortOrder;
import utils.DateUtils;
import java.util.*;
import java.util.regex.Pattern;

public class OrderView {
    public static final String NAME_REGEX = "^([A-Z]+[a-z]*[ ]?)+$";
    public static final String NUMBER_PHONE_REGEX = "^[0][1-9][0-9]{8,9}$";
    private IOrderItemServices orderItemServices;
    private IOrderServices orderServices;
    private IDrinkServices drinkServices;
    private MenuView me;

    Scanner scanner = new Scanner(System.in);
    static Scanner input = new Scanner(System.in);

    public OrderView(MenuView m) {
        orderItemServices = new OrderItemServices();
        drinkServices = new DrinkServices();
        orderServices = new OrderServices();
        me=m;
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
            long totalList = 0;
            for (Order order : orderList) {
                System.out.printf("\n\t\t\t\t%-10d %-10s %-15s %-10s %s ", order.getId(), order.getName(), order.getPhoneNumber(), order.getAddress(), order.getCreatedAt());

                List<OrderItem> orderItemList = new ArrayList<>();
                List<OrderItem> orderItemListAll = orderItemServices.getOrderItem();
                long totalItem = 0;
                for (OrderItem odt : orderItemListAll) {
                    if (odt.getIdOrder() == order.getId()) {
                        orderItemList.add(odt);
                        totalItem = totalItem + odt.getTotal();

                    }
                }
                System.out.println("\t\t" + totalItem + " d");
                totalList = totalList + totalItem;
            }

            System.out.println("\n\n\t\t\t                                                                          "+"TỔNG  " + totalList + " d");
            System.out.println("\n\t\t\t-----------------------------------------------------------------------------------------------------------");
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public void showOrder(long idOrder) {

        List<Order> orderList = orderServices.getOrder();

        for (Order order : orderList) {
            if (idOrder == order.getId()) {
                System.out.println("\t\t\t---------------------------------- HÓA ĐƠN ---------------------------------");
                System.out.println("\t\t\t\t\tTên KH: " + order.getName() + "       Địa chỉ: " + order.getAddress() + "        SDT:" + order.getPhoneNumber() + "\n\t\t\t\t\tNgày tạo:  " + order.getCreatedAt());
                break;
            }
        }
        List<OrderItem> orderItemList = new ArrayList<>();
        List<OrderItem> orderItemListAll = orderItemServices.getOrderItem();
        long total = 0;
        for (OrderItem odt : orderItemListAll) {
            if (odt.getIdOrder() == idOrder) {
                orderItemList.add(odt);
                total = total + odt.getTotal();

            }
        }

        for (OrderItem or : orderItemList) {

            System.out.printf("\n\t\t\t\t\t%-5d %-5d %-12s %-10d %-8d %d  d ", or.getId(), or.getDrinkId(), or.getDrinkName(), or.getQuantity(), or.getPrice(), or.getTotal());


        }
        System.out.println("\n\n                                                           Tổng tiền:  " + total + " d");
        System.out.println("\t\t\t------------------------------------------------------------------------------");


    }


    public void addOrderItem(long idOrder) {

        try {

            System.out.print(" Id Drink : ");
            int idD = scanner.nextInt();
            List<Drink> drinkList = drinkServices.getDrink();
            boolean check1 = false;
            boolean check2 = false;
            for (Drink drink : drinkList) {
                if (idD == drink.getId()) {
                    int drinkId = drink.getId();
                    long price = drink.getPrice();
                    String name = drink.getName();

                    System.out.print("Số lượng: ");
                    int quantity = scanner.nextInt();
                    if (quantity <= drink.getQuantity()) {

                        drink.setQuantity(drink.getQuantity() - quantity);
                        drinkServices.updateDrink(drink);

                        long total = quantity * drink.getPrice();

                        OrderItem orderItem = new OrderItem(DateUtils.currentTimeSecond(), drinkId, name, price, quantity, total, idOrder);
                        orderItemServices.addOrderItem(orderItem);

                        check1 = true;
                    } else {
                        System.out.println("Quá số lượng hiện có");
                        nextAdd(idOrder);
                        check2 = true;
                    }

                }
            }
            if (check1) {

                System.out.println("Đã đặt hàng ");

            } else if(!check2){
                System.out.println("Không tồn tại đồ uống");
                nextAdd(idOrder);

            }
        } catch (Exception e) {
           nextAdd(idOrder);
        }
    }



    private void nextAdd(long idOrder) {
        System.out.print("  Tiếp tục đặt hàng: Nhập Y                Quay lai: Nhập N");
        String s1 = scanner.next();
        if (s1.equalsIgnoreCase("y")) {
            addOrderItem(idOrder);
        } else if (s1.equalsIgnoreCase("n")){
            me.sowOrder1(true);
        } else {
            me.menu(true);
        }
    }



    public void addOrder(boolean admin) {
        DrinkView drinkView = new DrinkView(me);
        drinkView.showDrinks();

        try {
            long id = DateUtils.currentTimeSecond();
            System.out.print("Tên (vd: Tran Nhi): ");

            String name = input.nextLine();
            if (!isFormatName(name)) {
                System.out.println("Nhập sai (vd Tran Nhi)");
                nextAdd1(admin);
            }else {
                System.out.print("Số điện thoại (vd: 0909429345): ");
                String phoneNumber = scanner.next();
                if (!isPhoneNumber(phoneNumber)) {
                    System.out.println("Nhập sai (vd: 0123456789)");
                    nextAdd1(admin);
                }
                else {
                    System.out.print("Địa chỉ (vd: Quang Binh): ");
                    String address = input.nextLine();
                    if (!isFormatName(address)) {
                        System.out.println("Nhập sai (vd: Quang Binh)");
                        nextAdd1(admin);
                    } else {
                        System.out.print("Ngày tạo:(vd: 10-10-2021): ");
                        String createdAt = scanner.next();
                        Date date = DateUtils.stringToDate(createdAt);


                        addOrderItem(id);
                        int choice;


                        do {
                            System.out.print("Muốn đặt thêm: Nhập 1          Không đặt thêm: Nhập 2  ");
                            String ch = scanner.next();

                            choice = Integer.parseInt(ch);
                            switch (choice) {
                                case 1:
                                    System.out.print("Muốn đặt thêm ");
                                    addOrderItem(id);
                                    break;
                                case 2:
                                    System.out.println("Đã đặt đủ hàng");
                                default:
                                    break;
                            }
                        } while (choice != 2);

                        Order order = new Order(id, name, phoneNumber, address, date);
                        orderServices.addOrder(order);
                        System.out.println(" In hóa đơn: Nhập Y      ");
                        String s = scanner.next();
                        if (s.equalsIgnoreCase("y")) {
                            showOrder(id);
                        } else {
                            if(admin){
                                me.menu(admin);}
                            else{
                                me.menuUser(admin);
                            }
                        }
                    }
                }
            }

        } catch (Exception e) {
            e.getStackTrace();
            nextAdd1(admin);
        }
    }




    private void nextAdd1(boolean admin) {
        System.out.print("Tiếp tục thêm hóa đơn : Nhập Y    ");
        String s = scanner.next();
        if (s.equalsIgnoreCase("y")) {
            addOrder(admin);
        } else {
            if(admin)
                me.menu(admin);
            else
                me.menuUser(admin);
        }
    }



//    public static void main(String[] args) {
//        OrderView orderView = new OrderView();
//        orderView.addOrder();
//        orderView.showOrderList();
//    }
}

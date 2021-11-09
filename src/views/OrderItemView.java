package views;

import model.OrderItem;
import services.DrinkServices;
import services.IDrinkServices;
import services.IOrderItemServices;
import services.OrderItemServices;

import java.util.List;
import java.util.Scanner;

public class OrderItemView {
    private IOrderItemServices orderItemServices;
    public IDrinkServices drinkServices;
    Scanner scanner = new Scanner(System.in);
    public OrderItemView(){
        orderItemServices = new OrderItemServices();
        drinkServices = new DrinkServices();
    }
    public void showOrderItems(){
        try {
            System.out.println("------------------------------- Đặt Hàng -----------------------------------------------");
            System.out.printf("\n%-5s %-10s %-15s %-15s %-15s %s \n", "Id", "Id Sp", "Tên Sp ", "Giá","Số lượng", "Ngày tạo");
            List<OrderItem> orderItemList = orderItemServices.getOrderItem();

            for (OrderItem orderItem: orderItemList){
                System.out.printf("%-5s %-10s %-15s %-15s %-15s %s\n",orderItem.getId(),orderItem.getDrinkId(),
                        orderItem.getDrinkName(),orderItem.getPrice(), orderItem.getQuantity(),orderItem.getCreatedAt());
            }
            System.out.println("----------------------------------------------------------------------------------------");
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public static void main(String[] args) {
        OrderItemView a = new OrderItemView();
        a.showOrderItems();
    }
}

package services;

import model.OrderItem;

import java.util.List;

public interface IOrderItemServices {
    OrderItem getById(long id);
    List<OrderItem> getOrderItem();
    void addOrderItem(OrderItem newOrderItem);
    void updateOrderItem(OrderItem orderItem);
}

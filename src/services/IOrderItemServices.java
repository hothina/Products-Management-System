package services;

import model.OrderItem;

import java.util.List;

public interface IOrderItemServices {
    OrderItem getById(long id) throws Exception;
    List<OrderItem> getOrderItem() throws Exception;
    void addOrderItem(OrderItem newOrderItem) throws Exception;
    void updateOrderItem(OrderItem orderItem) throws Exception;
}

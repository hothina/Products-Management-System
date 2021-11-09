package repository;

import model.OrderItem;

import java.io.IOException;
import java.util.List;

public interface IOrderItemRepository {
    OrderItem getById(long id);
    List<OrderItem> getOrderItem();
    boolean exist(long id) ;
    void add(OrderItem newOrderItem) throws IOException;
    void update(OrderItem orderItem) throws IOException;
}

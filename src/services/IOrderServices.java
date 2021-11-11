package services;

import model.Order;

import java.util.List;

public interface IOrderServices {

    Order getById(long id);

    List<Order> getOrder();

    void addOrder(Order newOrder);

    void updateOrder(Order order);
}

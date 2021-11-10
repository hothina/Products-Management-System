package repository;

import model.Order;

import java.util.List;

public class OrderRepository implements IOrderRepository{
    @Override
    public Order getById(long id) {
        return null;
    }

    @Override
    public List<Order> getOrder() {
        return null;
    }

    @Override
    public boolean exist(long id) {
        return false;
    }

    @Override
    public void add(Order newOrder) {

    }

    @Override
    public void updare(Order order) {

    }
}

package repository;

import model.Order;
import model.User;
import utils.CsvUtils;

import java.util.ArrayList;
import java.util.List;

public class OrderRepository implements IOrderRepository {
    private final String ORDER_PATH = "data\\order.csv";

    public OrderRepository() {
    }

    @Override
    public Order getById(long id) {
        List<Order> orderList = getOrder();
        for (Order order : orderList) {
            if (order.getId() == id)
                return order;
        }
        return null;
    }

    @Override
    public List<Order> getOrder() {
        List<Order> newOrderList = new ArrayList<>();
        List<String> records = CsvUtils.read(ORDER_PATH);
        for (String record : records) {
            newOrderList.add(new Order(record));
        }
        return newOrderList;
    }

    @Override
    public boolean exist(long id) {
        if (this.getById(id) != null)
            return true;
        else return false;
    }

    @Override
    public void add(Order newOrder) {
        List<Order> orderList = getOrder();
        orderList.add(newOrder);
        CsvUtils.write(ORDER_PATH, orderList);

    }

    @Override
    public void update(Order order) {
        List<Order> orderList = getOrder();

        for (Order or : orderList){
            if (or.getId() == order.getId()){
                Order.transferFields(or, order);
            }
        }
        CsvUtils.write(ORDER_PATH, orderList);

    }
}

package services;

import model.OrderItem;
import repository.IOrderItemRepository;
import repository.OrderItemRepository;

import java.util.List;

public class OrderItemServices implements IOrderItemServices{
    private IOrderItemRepository orderItemRepository;

    public OrderItemServices() {
        orderItemRepository = new OrderItemRepository();
    }
    @Override
    public OrderItem getById(long id) throws Exception {
        OrderItem orderItem = orderItemRepository.getById(id);
        if (orderItem != null)
            throw new Exception("OrderItem already exists");
        return orderItem;
    }

    @Override
    public List<OrderItem> getOrderItem() throws Exception {
        return orderItemRepository.getOrderItem();
    }

    @Override
    public void addOrderItem(OrderItem newOrderItem) throws Exception {
        if (orderItemRepository.exist(newOrderItem.getId()))
            throw new Exception("OrderItem already exists");
            orderItemRepository.add(newOrderItem);

    }

    @Override
    public void updateOrderItem(OrderItem orderItem) throws Exception {
        if (orderItemRepository.exist(orderItem.getId())){
            orderItemRepository.update(orderItem);
        } else {
            throw new Exception("OrderItem not already exists");
        }

    }
}

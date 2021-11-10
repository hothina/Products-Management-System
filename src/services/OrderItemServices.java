package services;

import exception.NotFoundException;
import model.OrderItem;
import repository.IOrderItemRepository;
import repository.OrderItemRepository;

import java.util.List;

public class OrderItemServices implements IOrderItemServices {
    private IOrderItemRepository orderItemRepository;

    public OrderItemServices() {
        orderItemRepository = new OrderItemRepository();
    }

    @Override
    public OrderItem getById(long id)  {
        OrderItem orderItem = orderItemRepository.getById(id);
        if (orderItem == null)
            throw new NotFoundException("OrderItem not found");
        return orderItem;
    }

    @Override
    public List<OrderItem> getOrderItem() {
        return orderItemRepository.getOrderItem();
    }

    @Override
    public void addOrderItem(OrderItem newOrderItem) {
        if (orderItemRepository.exist(newOrderItem.getId()))
            throw new IllegalArgumentException("OrderItem already exists");
        orderItemRepository.add(newOrderItem);

    }

    @Override
    public void updateOrderItem(OrderItem orderItem) {
        if (orderItemRepository.exist(orderItem.getId()))
            orderItemRepository.update(orderItem);
    }
}

package repository;

import model.Order;

import java.io.IOException;
import java.util.List;

public interface IOrderRepository {
   Order getById(long id);
   List<Order> getOrder();
   boolean exist(long id);


}

package repository;

import exception.ProductException;
import model.OrderItem;
import utils.CsvUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OrderItemRepository implements IOrderItemRepository{
    private final String ORDERITEM_PATH = "data\\orderItem.csv";
    public OrderItemRepository(){}


    @Override
    public OrderItem getById(long id) {
        List<OrderItem> orderItemList= getOrderItem();
        for (OrderItem or : orderItemList){
            if (or.getId()== id){
                return or;
            }
        }
        return null;
    }

    @Override
    public List<OrderItem> getOrderItem() {
        List<OrderItem> newOrderItemList = new ArrayList<>();
        try {
            List<String> records = CsvUtils.read(ORDERITEM_PATH);
            for (String record: records){
                newOrderItemList.add(new OrderItem(record));

            } return newOrderItemList;
        }  catch (IOException ex) {
            throw new ProductException("getOrderItem error");
        }
    }

    @Override
    public boolean exist(long id) {
        return getById(id)!= null;
    }

    @Override
    public void add(OrderItem newOrderItem) throws IOException {
        List<OrderItem> orderItemList= getOrderItem();
        orderItemList.add(newOrderItem);
        CsvUtils.write(ORDERITEM_PATH, orderItemList);

    }

    @Override
    public void update(OrderItem orderItem) throws IOException {
        List<OrderItem> orderItemList= getOrderItem();
        for (OrderItem or : orderItemList){
            if (or.getId()== orderItem.getId()){
                OrderItem.transferFields(or, orderItem);
            }
        }
        CsvUtils.write(ORDERITEM_PATH, orderItemList);
    }
}

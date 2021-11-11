package sort;

import model.Order;

import java.util.Comparator;

public class SortOrder implements Comparator<Order> {

    @Override
    public int compare(Order o1, Order o2) {
        if (o1.getId()>o2.getId()){
            return -1;
        } else {
            return 1;
        }
    }
}

package model;

import utils.DateUtils;

import java.io.File;
import java.io.Serializable;
import java.util.Date;


public class OrderItem implements Serializable {
    private long id;
    private int quantity;
    private long price;
    private int drinkId;
    private String drinkName;

    public OrderItem() {
    }

    public OrderItem(long id, int drinkId,String drinkName, long price, int quantity) {
        this.id = id;
        this.drinkId = drinkId;
        this.drinkName = drinkName;
        this.price = price;
        this.quantity = quantity;

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public int getDrinkId() {
        return drinkId;
    }

    public void setDrinkId(int drinkId) {
        this.drinkId = drinkId;
    }

    public String getDrinkName() {
        return drinkName;
    }

    public void setDrinkName(String drinkName) {
        this.drinkName = drinkName;
    }

    @Override
    public String toString() {
        return String.format("%d,%d,%s,%d,%d",id,drinkId,drinkName,price,quantity);
    }

    public static void  transferFields(OrderItem oldOrderItem, OrderItem newOrderItem){
        oldOrderItem.id = newOrderItem.id;
        oldOrderItem.quantity = newOrderItem.quantity;
        oldOrderItem.price = newOrderItem.price;
        oldOrderItem.drinkId = newOrderItem.drinkId;
        oldOrderItem.drinkName = newOrderItem.drinkName;
    }
    public OrderItem(String raw){
        String[] fields = raw.split(",");
        id = Long.parseLong(fields[0]);
        drinkId = Integer.parseInt(fields[1]);
        drinkName = fields[2];
        price = Long.parseLong(fields[3]);
        quantity = Integer.parseInt(fields[4]);

    }
}

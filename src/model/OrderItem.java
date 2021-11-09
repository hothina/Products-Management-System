package model;

import utils.DateUtils;

import java.io.File;
import java.io.Serializable;
import java.util.Date;


public class OrderItem implements Serializable {
    private long id;
    private int quantity;
    private Date createdAt;
    private long price;
    private int drinkId;
    private String drinkName;

    public OrderItem() {
    }

    public OrderItem(long id, int quantity, Date createdAt, long price, int drinkId, String drinkName) {
        this.id = id;
        this.quantity = quantity;
        this.createdAt = createdAt;
        this.price = price;
        this.drinkId = drinkId;
        this.drinkName = drinkName;
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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
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
        return String.format("%d,%d,%s,%d,%d,%d",id,drinkId,drinkName,price,quantity,createdAt);
    }

    public static void  transferFields(OrderItem oldOrderItem, OrderItem newOrderItem){
        oldOrderItem.id = newOrderItem.id;
        oldOrderItem.quantity = newOrderItem.quantity;
        oldOrderItem.createdAt = newOrderItem.createdAt;
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
        createdAt = DateUtils.stringToDate(fields[5]);
    }
}

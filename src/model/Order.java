package model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


public class Order implements Serializable {
    private long id;
    private String name;
    private String phoneNumber;
    private String address;
    private List<OrderItem> orderItems;
    private Date createdAt;
    private long total;

    public Order(){}

    public Order(long id, String name, String phoneNumber, String address, List<OrderItem> orderItems, Date createdAt, long total) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.orderItems = orderItems;
        this.createdAt = createdAt;
        this.total = total;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }
    public static void  transferFields(Order oldOrder, Order newOrder){
        oldOrder.id = newOrder.id;
        oldOrder.name = newOrder.name;
        oldOrder.phoneNumber = newOrder.phoneNumber;
        oldOrder.address = newOrder.address;
        oldOrder.orderItems = newOrder.orderItems;
        oldOrder.createdAt = newOrder.createdAt;
        oldOrder.total = newOrder.total;
    }

    public Order(String raw){
        String[] fileds = raw.split(",");
        id = Long.parseLong(fileds[0]);
        name = fileds[1];
        phoneNumber = fileds[2];
        address = fileds[3];


    }
}

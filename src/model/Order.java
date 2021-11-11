package model;

import utils.DateUtils;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


public class Order implements Serializable {
    private long id;
    private String name;
    private String phoneNumber;
    private String address;
//    private List<OrderItem> orderItems;
    private Date createdAt;
    private long total;
    private long start;
    private long end;



    public Order(long id, String name, String phoneNumber, String address, Date createdAt, long total) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;

        this.createdAt = createdAt;
        this.total = total;
    }

    public Order(long id, String name, String phoneNumber, String address,  long start, long end,long total, Date createdAt) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.start = start;
        this.end = end;
        this.total = total;
        this.createdAt = createdAt;
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

    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public long getEnd() {
        return end;
    }

    public void setEnd(long end) {
        this.end = end;
    }

    public static void  transferFields(Order oldOrder, Order newOrder){
        oldOrder.id = newOrder.id;
        oldOrder.name = newOrder.name;
        oldOrder.phoneNumber = newOrder.phoneNumber;
        oldOrder.address = newOrder.address;
//        oldOrder.orderItems = newOrder.orderItems;
//        oldOrder.createdAt = newOrder.createdAt;

        oldOrder.total = newOrder.total;
        oldOrder.start = newOrder.start;
        oldOrder.end = newOrder.end;
    }

    @Override
    public String toString() {
        return String.format("%d,%s,%s,%s,%d,%d,%d,%s",id,name,phoneNumber,address,start,end,total,DateUtils.dateToString(createdAt)) ;
    }

    public Order(String raw){
        String[] fileds = raw.split(",");
        id = Long.parseLong(fileds[0]);
        name = fileds[1];
        phoneNumber = fileds[2];
        address = fileds[3];
        start = Long.parseLong(fileds[4]);
        end = Long.parseLong(fileds[5]);

//         String orderItems = fileds[4];
//        OrderItem orderItem =new OrderItem(orderItems);
        total = Long.parseLong(fileds[6]);
        createdAt = DateUtils.stringToDate(fileds[7]);
    }
}

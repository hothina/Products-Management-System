package model;

public class Drink {
    private long id;
    private String name;
    private int quantity;
    private double price;

    public Drink(){}

    public Drink(int id, String name, int quantity, double price) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format("%d,%s,%d,%d",id,name,quantity,price);

    }

    public static void  transferFields(Drink oldDrink, Drink newDrink){
        oldDrink.id = newDrink.id;
        oldDrink.name = newDrink.name;
        oldDrink.quantity= newDrink.quantity;
        oldDrink.price = newDrink.price;

    }
}

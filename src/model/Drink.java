package model;

public class Drink {
    private int id;
    private String name;
    private int quantity;
    private int price;

    public Drink(){}

    public Drink(int id, String name, int quantity, int price) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public Drink(String raw) {
        String[] fileds = raw.split(",");
        id = Integer.parseInt(fileds[0]);
        name = fileds[1];
        quantity = Integer.parseInt(fileds[2]);
        price = Integer.parseInt(fileds[3]);
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

    public void setPrice(int price) {
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

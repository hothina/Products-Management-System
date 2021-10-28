package repository;

import model.Drink;
import model.User;

import java.util.ArrayList;
import java.util.List;

public class DrinkRepository implements IDrinkRepository {
    private List<Drink> drinkList;

    public DrinkRepository(){
        drinkList = new ArrayList<>();
        drinkList.add(new Drink(1,"Tra dao",25,20000));
        drinkList.add(new Drink(2,"Tra bi dao",29,15000));
        drinkList.add(new Drink(3,"Tra chanh",32,20000));
        drinkList.add(new Drink(4,"Sua chua da",35,15000));
        drinkList.add(new Drink(5,"Nuoc chanh",18,20000));
        drinkList.add(new Drink(6,"Cam ep",30,20000));
    }
    @Override
    public Drink getById(long id) {
        for (Drink drink : drinkList) {
            if (drink.getId() == id)
                return drink;
        }
        return null;
    }

    @Override
    public List<Drink> getDrink() {
        return drinkList;
    }

    @Override
    public boolean exist(long id) {
        return getById(id) != null;
    }

    @Override
    public void add(Drink newDrink) {
        drinkList.add(newDrink);

    }

    @Override
    public void update(Drink drink) {
        Drink oldDrink = getById(drink.getId());
        Drink.transferFields(oldDrink, drink);

    }
}

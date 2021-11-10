package repository;

import model.Drink;
import utils.CsvUtils;

import java.util.ArrayList;
import java.util.List;

public class DrinkRepository implements IDrinkRepository {
    private final String DRINK_PATH = "data\\drinks.csv";

    public DrinkRepository() {
    }

    @Override
    public Drink getById(int id) {
        List<Drink> drinkList = getDrink();
        for (Drink drink : drinkList) {
            if (drink.getId() == id)
                return drink;
        }
        return null;


    }

    @Override
    public List<Drink> getDrink() {
       List<Drink> newDrinkList = new ArrayList<>();

           List<String> records = CsvUtils.read(DRINK_PATH);
           for(String record : records) {
               newDrinkList.add(new Drink(record));
           }
           return newDrinkList;


    }

    @Override
    public boolean exist(int id) {
        return getById(id) != null;
    }

    @Override
    public void add(Drink newDrink) {
        List<Drink> drinkList = getDrink();
        drinkList.add(newDrink);
        CsvUtils.write(DRINK_PATH, drinkList);
    }

    @Override
    public void update(Drink drink) {
        List<Drink> drinkList = getDrink();
        for (Drink dr :drinkList){
            if (dr.getName().equalsIgnoreCase(drink.getName())){
                Drink.transferFields(dr, drink);
            }
        }
        CsvUtils.write(DRINK_PATH,drinkList);

    }


}

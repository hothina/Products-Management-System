package services;

import model.Drink;

import repository.DrinkRepository;
import repository.IDrinkRepository;


import java.util.List;

public class DrinkServices implements IDrinkServices {
    private IDrinkRepository drinkRepository;

    public DrinkServices() {

        drinkRepository = new DrinkRepository();
    }


    @Override
    public Drink getByID(int id) throws Exception {

        Drink drink = drinkRepository.getById(id);
        if (drink != null)
            throw new Exception("Drink alredy exists");
        return drink;
    }

    @Override
    public List<Drink> getDrink() {
        return drinkRepository.getDrink();
    }

    @Override
    public void addDrink(Drink newDrink) throws Exception {
        if (drinkRepository.exist(newDrink.getId()))
            throw new Exception("user already exists");
        drinkRepository.add(newDrink);
    }


    @Override
    public void updateDrink(Drink drink) throws Exception {
        if (drinkRepository.exist(drink.getId()))
            drinkRepository.update(drink);
        else
            throw new Exception("user already exists");


    }
}

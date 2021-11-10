package services;

import exception.NotFoundException;
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
    public Drink getById(int id)  {
        Drink drink = drinkRepository.getById(id);
        if (drink != null)
            throw new NotFoundException("Drink alredy exists");
        return drink;
    }

    @Override
    public List<Drink> getDrink() {
        return drinkRepository.getDrink();
    }

    @Override
    public void addDrink(Drink newDrink)  {
        if (drinkRepository.exist(newDrink.getId()))
            throw new IllegalArgumentException("user already exists");
        drinkRepository.add(newDrink);
    }


    @Override
    public void updateDrink(Drink drink) {
        if (drinkRepository.exist(drink.getId()))
            drinkRepository.update(drink);

    }
}

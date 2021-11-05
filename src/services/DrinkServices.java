package services;

import model.Drink;

import repository.DrinkRepository;
import repository.IDrinkRepository;


import java.util.List;

public class DrinkServices implements IDrinkServices{
    private IDrinkRepository drinkRepository;

    public DrinkServices(){

        drinkRepository = new DrinkRepository();
    }

    @Override
    public Drink getByID(long id) throws Exception {
        Drink drink = drinkRepository.getById(id);
        if(drink != null)
            throw new Exception("Drink alredy exists");
        return drink;
    }

    @Override
    public List<Drink> getDrink() {
        return drinkRepository.getDrink();
    }



    @Override
    public void add(Drink newDrink) throws Exception {
        if(drinkRepository.exist(newDrink.getId()))
            throw new Exception("user already exists");
        drinkRepository.add(newDrink);
    }

    @Override
    public void update(Drink drink) throws Exception {
        if(drinkRepository.exist(drink.getId()))
            throw new Exception("user already exists");
        drinkRepository.update(drink);


    }
}

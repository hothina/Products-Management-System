package services;

import model.Drink;

import java.io.IOException;
import java.util.List;

public interface IDrinkServices {
    Drink getById(int id);

    List<Drink> getDrink();

    void addDrink(Drink newDrink);

    void updateDrink(Drink drink);


}

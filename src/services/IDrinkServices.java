package services;

import model.Drink;
import java.io.IOException;
import java.util.List;

public interface IDrinkServices {


    Drink getByID(int id) throws Exception;

    List<Drink> getDrink() throws Exception;

    void addDrink(Drink newDrink) throws Exception;

    void updateDrink(Drink drink) throws Exception;



}

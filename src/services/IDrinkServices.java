package services;

import model.Drink;
import java.io.IOException;
import java.util.List;

public interface IDrinkServices {
    Drink getByID(long id) throws Exception;

    List<Drink> getDrink() throws Exception;

    void add(Drink newDrink) throws Exception;

    void update(Drink drink) throws Exception;

}

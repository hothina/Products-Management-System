package repository;

import model.Drink;
import model.User;

import java.io.IOException;
import java.util.List;

public interface IDrinkRepository {

    Drink getById(long id);
    List<Drink> getDrink();

    boolean exist (long id);

    void add(Drink newDrink) throws IOException;

    void update(Drink drink);
}

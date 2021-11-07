package repository;

import model.Drink;
import model.User;

import java.io.IOException;
import java.util.List;

public interface IDrinkRepository {

    Drink getById(int id);
    List<Drink> getDrink();

    boolean exist (int id);

    void add(Drink newDrink) throws IOException;

    void update(Drink drink) throws IOException;
}

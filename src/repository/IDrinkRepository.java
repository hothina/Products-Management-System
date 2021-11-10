package repository;

import model.Drink;

import java.util.List;

public interface IDrinkRepository {

    Drink getById(int id);

    List<Drink> getDrink();

    boolean exist(int id);

    void add(Drink newDrink);

    void update(Drink drink);
}

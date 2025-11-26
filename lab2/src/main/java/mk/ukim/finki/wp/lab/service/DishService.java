package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Dish;

import java.util.List;
public interface DishService {
    List<Dish> listDishes();
    Dish findById(Long id);
    Dish create(String name, String cuisine, int preparationTime);
    Dish update(Long id, String name, String cuisine, int preparationTime);
    void delete(Long id);
}

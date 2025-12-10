package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Dish;

import java.util.List;
public interface DishService {
    List<Dish> listDishes();
    Dish findById(Long id);
    void delete(Long id);
    List<Dish> findDishesByChefId (Long chefId);
    Dish create(String name, String cuisine, int preparationTime, Long chefId);
    Dish update(Long id, String name, String cuisine, int preparationTime, Long chefId);

}

package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Dish;

import java.util.List;
import java.util.Optional;

public interface DishService {
    List<Dish> listDishes();
    Optional<Dish> findByDishId(String dishId);
}
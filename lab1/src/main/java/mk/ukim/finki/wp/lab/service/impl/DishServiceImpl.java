package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Dish;
import mk.ukim.finki.wp.lab.repository.DishRepository;
import mk.ukim.finki.wp.lab.service.DishService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class DishServiceImpl implements DishService {

    private final DishRepository dishRepo;

    public DishServiceImpl(DishRepository dishRepo) {
        this.dishRepo = dishRepo;
    }


    @Override
    public List<Dish> listDishes() {
        return dishRepo.findAll();
    }

    @Override
    public Optional<Dish> findByDishId(String dishId) {
        return dishRepo.findByDishId(dishId);
    }
}

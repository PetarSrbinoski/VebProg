package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Chef;
import mk.ukim.finki.wp.lab.model.Dish;
import mk.ukim.finki.wp.lab.repository.ChefRepository;
import mk.ukim.finki.wp.lab.repository.DishRepository;
import mk.ukim.finki.wp.lab.service.ChefService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChefServiceImpl implements ChefService {
    private final ChefRepository chefRepo;
    private final DishRepository dishRepo;

    public ChefServiceImpl(ChefRepository chefRepo, DishRepository dishRepo) {
        this.chefRepo = chefRepo;
        this.dishRepo = dishRepo;
    }


    @Override
    public List<Chef> listChefs() {
        return chefRepo.findAll();
    }

    @Override
    public Optional<Chef> findById(Long id) {
        return chefRepo.findById(id);
    }

    @Override
    public Chef addDishToChef(Long chefId, String dishId) {
        Optional<Chef> chefO = chefRepo.findById(chefId);
        Optional<Dish> dishO = dishRepo.findByDishId(dishId);

        if (chefO.isEmpty() || dishO.isEmpty()) throw new IllegalArgumentException();

        Chef chef = chefO.get();
        Dish dish = dishO.get();

        chef.getDishes().add(dish);
        return chefRepo.save(chef);
    }
}

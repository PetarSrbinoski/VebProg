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
    public Chef create(String firstName, String lastName, String bio) {
        Chef chef = new Chef(firstName, lastName, bio);
        return chefRepo.save(chef);
    }

    @Override
    public Chef update(Long id, String firstName, String lastName, String bio) {
        Chef chef = chefRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Chef not found"));

        chef.setFirstName(firstName);
        chef.setLastName(lastName);
        chef.setBio(bio);

        return chefRepo.save(chef);
    }

    @Override
    public void delete(Long id) {
        chefRepo.deleteById(id);
    }

    @Override
    public Chef addDishToChef(Long chefId, Long dishId) {
        Chef chef = chefRepo.findById(chefId)
                .orElseThrow(() -> new IllegalArgumentException("Chef not found"));

        Dish dish = dishRepo.findById(dishId)
                .orElseThrow(() -> new IllegalArgumentException("Dish not found"));

        chef.getDishes().add(dish);
        chefRepo.save(chef);

        return chef;
    }

}

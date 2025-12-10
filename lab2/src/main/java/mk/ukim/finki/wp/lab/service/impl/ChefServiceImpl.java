package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Chef;
import mk.ukim.finki.wp.lab.model.Dish;
import mk.ukim.finki.wp.lab.repository.ChefRepository;
import mk.ukim.finki.wp.lab.repository.DishRepository;
import mk.ukim.finki.wp.lab.service.ChefService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; // Важно за ORM операции

import java.util.List;
import java.util.Optional;

@Service
public class ChefServiceImpl implements ChefService {

    private final ChefRepository chefRepository;
    private final DishRepository dishRepository;

    public ChefServiceImpl(ChefRepository chefRepository, DishRepository dishRepository) {
        this.chefRepository = chefRepository;
        this.dishRepository = dishRepository;
    }

    @Override
    public List<Chef> listChefs() {
        return chefRepository.findAll();
    }

    @Override
    public Optional<Chef> findById(Long id) {
        return chefRepository.findById(id);
    }

    @Override
    @Transactional
    public Chef create(String firstName, String lastName, String bio) {
        Chef chef = new Chef(firstName, lastName, bio);
        return chefRepository.save(chef);
    }

    @Override
    @Transactional
    public Chef update(Long id, String firstName, String lastName, String bio) {
        Chef chef = chefRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Chef not found"));
        chef.setFirstName(firstName);
        chef.setLastName(lastName);
        chef.setBio(bio);
        return chefRepository.save(chef);
    }

    @Override
    public void delete(Long id) {
        chefRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Chef addDishToChef(Long chefId, Long dishId) {
        Chef chef = chefRepository.findById(chefId)
                .orElseThrow(() -> new RuntimeException("Chef not found"));
        Dish dish = dishRepository.findById(dishId)
                .orElseThrow(() -> new RuntimeException("Dish not found"));

        // Ја поставуваме релацијата на страната на ManyToOne (Dish)
        dish.setChef(chef);
        dishRepository.save(dish);

        // Враќање на готвачот за ChefController
        return chef;
    }
}
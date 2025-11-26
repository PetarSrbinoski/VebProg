package mk.ukim.finki.wp.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Chef;
import mk.ukim.finki.wp.lab.model.Dish;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class DataHolder {
    public static List<Chef> chefs;
    public static List<Dish> dishes;

    @PostConstruct
    public void init() {
        chefs = new ArrayList<>(5);
        dishes = new ArrayList<>(5);

        chefs.add(new Chef((long)1, "Gordon", "Ramsay", "Chef 1", new ArrayList<>()));
        chefs.add(new Chef((long)2, "Jamie", "Oliver", "Chef 2", new ArrayList<>()));

        dishes.add(new Dish("1", "Carbonara", "Italian", 20));
        dishes.add(new Dish("2", "Ajvar", "Macedonian", 30));
    }
}

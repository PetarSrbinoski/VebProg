//package mk.ukim.finki.wp.lab.bootstrap;
//
//import jakarta.annotation.PostConstruct;
//import mk.ukim.finki.wp.lab.model.Chef;
//import mk.ukim.finki.wp.lab.model.Dish;
//import org.springframework.stereotype.Component;
//
//import java.util.ArrayList;
//import java.util.List;
//@Component
//public class DataHolder {
//    public static List<Chef> chefs;
//    public static List<Dish> dishes;
//
//    @PostConstruct
//    public void init() {
//        chefs = new ArrayList<>(5);
//        dishes = new ArrayList<>(5);
//
//        chefs.add(new Chef("Gordon", "Ramsay", "Chef 1"));
//        chefs.add(new Chef("Jamie", "Oliver", "Chef 2"));
//        chefs.add(new Chef("Petar", "Srbinoski", "Chef 3"));
//
//        dishes.add(new Dish( "Carbonara", "Italian", 20));
//        dishes.add(new Dish( "Ajvar", "Macedonian", 30));
//        dishes.add(new Dish( "Jajca", "Macedonian", 15));
//        dishes.add(new Dish( "Torta", "Spanish", 90));
//    }
//}

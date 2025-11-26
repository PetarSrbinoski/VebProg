package mk.ukim.finki.wp.lab.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Chef {

    private static long counter = 0L; // авто-counter

    private Long id;
    private String firstName;
    private String lastName;
    private String bio;
    private List<Dish> dishes;

    public Chef() {
        this.dishes = new ArrayList<>();
    }

    public Chef(String firstName, String lastName, String bio) {
        this.id = ++counter;          // авто-генерирање ID
        this.firstName = firstName;
        this.lastName = lastName;
        this.bio = bio;
        this.dishes = new ArrayList<>();
    }
}

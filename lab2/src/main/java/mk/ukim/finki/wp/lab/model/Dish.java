package mk.ukim.finki.wp.lab.model;

import lombok.Data;

@Data
public class Dish {

    // static counter for auto-generated ids
    private static Long counter = 0L;

    private Long id;          // internal Long id (never in forms)
    private String dishId;    // logical string id from the assignment
    private String name;
    private String cuisine;
    private int preparationTime;

    public Dish() {
        // empty constructor for frameworks
    }

    public Dish(String dishId, String name, String cuisine, int preparationTime) {
        this.id = ++counter;          // auto-generate id
        this.dishId = dishId;
        this.name = name;
        this.cuisine = cuisine;
        this.preparationTime = preparationTime;
    }
}

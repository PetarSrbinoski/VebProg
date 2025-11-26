package mk.ukim.finki.wp.lab.model;

import lombok.Data;

@Data
public class Dish {

    private static Long counter = 0L;

    private Long id;              // REAL identifier (used everywhere now)
    private String name;
    private String cuisine;
    private int preparationTime;

    public Dish() {}

    public Dish(String name, String cuisine, int preparationTime) {
        this.id = ++counter;
        this.name = name;
        this.cuisine = cuisine;
        this.preparationTime = preparationTime;
    }
}

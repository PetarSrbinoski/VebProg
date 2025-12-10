package mk.ukim.finki.wp.lab.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Dish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String cuisine;

    private int preparationTime;

    @ManyToOne
    @JoinColumn(name = "chef_id")
    private Chef chef;

    public Dish() {
    }

    public Dish(String name, String cuisine, int preparationTime) {
        this.name = name;
        this.cuisine = cuisine;
        this.preparationTime = preparationTime;
    }
}

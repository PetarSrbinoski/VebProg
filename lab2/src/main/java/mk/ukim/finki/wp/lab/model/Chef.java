package mk.ukim.finki.wp.lab.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Chef {
    long id;
    String firstName;
    String lastName;
    String bio;
    List<Dish> dishes;
}

package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.model.Dish;
import mk.ukim.finki.wp.lab.service.ChefService;
import mk.ukim.finki.wp.lab.service.DishService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/dishes")
public class DishController {

    private final DishService dishService;
    private final ChefService chefService;

    public DishController(DishService dishService, ChefService chefService) {
        this.dishService = dishService;
        this.chefService = chefService;
    }

    @GetMapping
    public String getDishesPage(@RequestParam(required = false) Long chefId, @RequestParam(required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("error", error);
        }

        List<Dish> dishes = dishService.listDishes();

        if (chefId != null) {
            dishes = dishService.findDishesByChefId(chefId);
        }

        model.addAttribute("dishes", dishes);
        model.addAttribute("chefs", chefService.listChefs());
        return "listDishes";
    }

    @GetMapping("/dish-form")
    public String getDishForm(@RequestParam(required = false) Long id,
                              @RequestParam(required = false) Long chefId,
                              Model model) {
        Dish dish;
        if (id != null) {
            dish = dishService.findById(id);
            model.addAttribute("formTitle", "Edit Dish");
            model.addAttribute("action", "/dishes/edit/" + id);
        } else {
            dish = new Dish();
            model.addAttribute("formTitle", "Add Dish");
            model.addAttribute("action", "/dishes/add");
        }

        if (chefId != null) {
            chefService.findById(chefId).ifPresent(dish::setChef);
        }

        model.addAttribute("dish", dish);
        model.addAttribute("chefs", chefService.listChefs());
        return "dish-form";
    }

    @PostMapping("/add")
    public String saveDish(@RequestParam String name,
                           @RequestParam String cuisine,
                           @RequestParam int preparationTime,
                           @RequestParam Long chefId) {
        dishService.create(name, cuisine, preparationTime, chefId);
        return "redirect:/dish?chefId=" + chefId;
    }

    @PostMapping("/edit/{id}")
    public String editDish(@PathVariable Long id,
                           @RequestParam String name,
                           @RequestParam String cuisine,
                           @RequestParam int preparationTime,
                           @RequestParam Long chefId) {
        dishService.update(id, name, cuisine, preparationTime, chefId);
        return "redirect:/dishes";
    }

    @PostMapping("/delete/{id}")
    public String deleteDish(@PathVariable Long id) {
        dishService.delete(id);
        return "redirect:/dishes";
    }
}
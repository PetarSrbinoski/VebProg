package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.model.Dish;
import mk.ukim.finki.wp.lab.service.ChefService;
import mk.ukim.finki.wp.lab.service.DishService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class DishController {

    private final DishService dishService;
    private final ChefService chefService;

    public DishController(DishService dishService, ChefService chefService) {
        this.dishService = dishService;
        this.chefService = chefService;
    }

    @GetMapping("/dishes")
    public String getDishesPage(@RequestParam(required = false) String error,
                                Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        model.addAttribute("dishes", dishService.listDishes());
        return "listDishes";
    }

    @GetMapping("/dishes/dish-form")
    public String getAddDishPage(Model model) {
        model.addAttribute("dish", new Dish());
        model.addAttribute("formTitle", "Add Dish");
        model.addAttribute("action", "/dishes/add");
        return "dish-form";
    }

    @GetMapping("/dishes/dish-form/{id}")
    public String getEditDishForm(@PathVariable Long id, Model model) {
        try {
            Dish dish = dishService.findById(id);
            model.addAttribute("dish", dish);
            model.addAttribute("formTitle", "Edit Dish");
            model.addAttribute("action", "/dishes/edit/" + id);
            return "dish-form";
        } catch (RuntimeException ex) {
            return "redirect:/dishes?error=DishNotFound";
        }
    }

    @PostMapping("/dishes/add")
    public String saveDish(@RequestParam String name,
                           @RequestParam String cuisine,
                           @RequestParam int preparationTime) {
        dishService.create(name, cuisine, preparationTime);
        return "redirect:/dishes";
    }



    @PostMapping("/dishes/edit/{id}")
    public String editDish(@PathVariable Long id,
                           @RequestParam String name,
                           @RequestParam String cuisine,
                           @RequestParam int preparationTime) {
        dishService.update(id, name, cuisine, preparationTime);
        return "redirect:/dishes";
    }

    @PostMapping("/dishes/delete/{id}")
    public String deleteDish(@PathVariable Long id) {
        dishService.delete(id);
        return "redirect:/dishes";
    }


    @GetMapping("/dish")
    public String getDishSelectionPage(@RequestParam Long chefId, Model model) {
        var chef = chefService.findById(chefId)
                .orElseThrow(() -> new RuntimeException("Chef not found"));

        model.addAttribute("dishes", dishService.listDishes());
        model.addAttribute("chefId", chefId);
        model.addAttribute("chefName", chef.getFirstName() + " " + chef.getLastName());

        return "dishesList"; // dishesList.html
    }

    @PostMapping("/dish")
    public String addDishToChef(@RequestParam Long chefId,
                                @RequestParam Long dishId) {
        chefService.addDishToChef(chefId, dishId);
        return "redirect:/chefDetails?chefId=" + chefId;
    }
}

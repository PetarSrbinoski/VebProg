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

    // 4.1 Листање на јадења (CRUD)
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

    // 7.2 Форма за додавање
    @GetMapping("/dishes/dish-form")
    public String getAddDishPage(Model model) {
        model.addAttribute("dish", new Dish());
        model.addAttribute("formTitle", "Add Dish");
        model.addAttribute("action", "/dishes/add");
        return "dish-form";
    }

    // 7.1 Форма за едитирање
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

    // 4.2 Додавање ново јадење
    @PostMapping("/dishes/add")
    public String saveDish(@RequestParam String dishId,
                           @RequestParam String name,
                           @RequestParam String cuisine,
                           @RequestParam int preparationTime) {
        dishService.create(dishId, name, cuisine, preparationTime);
        return "redirect:/dishes";
    }

    // 4.3 Ажурирање јадење
    @PostMapping("/dishes/edit/{id}")
    public String editDish(@PathVariable Long id,
                           @RequestParam String dishId,
                           @RequestParam String name,
                           @RequestParam String cuisine,
                           @RequestParam int preparationTime) {
        dishService.update(id, dishId, name, cuisine, preparationTime);
        return "redirect:/dishes";
    }

    // 4.4 Бришење јадење
    @PostMapping("/dishes/delete/{id}")
    public String deleteDish(@PathVariable Long id) {
        dishService.delete(id);
        return "redirect:/dishes";
    }

    // 8. Интеграција со Chef – избор јадење за готвач (бивши /dish servlet)

    // Прикажи листа на јадења за даден chef
    @GetMapping("/dish")
    public String getDishSelectionPage(@RequestParam Long chefId, Model model) {
        var chef = chefService.findById(chefId)
                .orElseThrow(() -> new RuntimeException("Chef not found"));

        model.addAttribute("dishes", dishService.listDishes());
        model.addAttribute("chefId", chefId);
        model.addAttribute("chefName", chef.getFirstName() + " " + chef.getLastName());

        return "dishesList"; // dishesList.html
    }

    // Додај избрано јадење на готвач и оди на chefDetails
    @PostMapping("/dish")
    public String addDishToChef(@RequestParam Long chefId,
                                @RequestParam String dishId) {
        chefService.addDishToChef(chefId, dishId);
        return "redirect:/chefDetails?chefId=" + chefId;
    }
}

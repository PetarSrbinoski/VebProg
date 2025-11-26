package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.service.ChefService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ChefController {

    private final ChefService chefService;

    public ChefController(ChefService chefService) {
        this.chefService = chefService;
    }

    // Листа на готвачи (GET)
    @GetMapping("/listChefs")
    public String getChefsPage(Model model) {
        model.addAttribute("chefs", chefService.listChefs());
        return "listChefs";
    }

    // Земете селектиран chef и пренасочете кон избор на јадење
    @PostMapping("/listChefs")
    public String selectChef(@RequestParam Long chefId) {
        return "redirect:/dish?chefId=" + chefId;
    }

    // Детали за готвач
    @GetMapping("/chefDetails")
    public String getChefDetails(@RequestParam Long chefId, Model model) {
        var chef = chefService.findById(chefId)
                .orElseThrow(() -> new RuntimeException("Chef not found"));

        model.addAttribute("chefName", chef.getFirstName() + " " + chef.getLastName());
        model.addAttribute("chefBio", chef.getBio());
        model.addAttribute("dishes", chef.getDishes());
        return "chefDetails";
    }
}

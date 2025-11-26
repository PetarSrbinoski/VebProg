package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.model.Chef;
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

//    // Листање на сите готвачи (CRUD "admin" view)
//    @GetMapping("/chefs")
//    public String getChefsCrudPage(Model model) {
//        model.addAttribute("chefs", chefService.listChefs());
//        return "listChefsCrud"; // посебен template за CRUD
//    }
//
//    // Празна форма за додавање нов готвач
//    @GetMapping("/chefs/chef-form")
//    public String getAddChefForm(Model model) {
//        model.addAttribute("chef", new Chef());
//        model.addAttribute("formTitle", "Add Chef");
//        model.addAttribute("action", "/chefs/add");
//        return "chef-form";
//    }

//    // Форма за уредување постоечки готвач
//    @GetMapping("/chefs/chef-form/{id}")
//    public String getEditChefForm(@PathVariable Long id, Model model) {
//        Chef chef = chefService.findById(id)
//                .orElseThrow(() -> new RuntimeException("Chef not found"));
//
//        model.addAttribute("chef", chef);
//        model.addAttribute("formTitle", "Edit Chef");
//        model.addAttribute("action", "/chefs/edit/" + id);
//        return "chef-form";
//    }

//    // Додавање нов готвач (CREATE)
//    @PostMapping("/chefs/add")
//    public String addChef(@RequestParam String firstName,
//                          @RequestParam String lastName,
//                          @RequestParam String bio) {
//        chefService.create(firstName, lastName, bio);
//        return "redirect:/chefs";
//    }
//
//    // Ажурирање готвач (UPDATE)
//    @PostMapping("/chefs/edit/{id}")
//    public String editChef(@PathVariable Long id,
//                           @RequestParam String firstName,
//                           @RequestParam String lastName,
//                           @RequestParam String bio) {
//        chefService.update(id, firstName, lastName, bio);
//        return "redirect:/chefs";
//    }
//
//    // Бришење готвач (DELETE)
//    @PostMapping("/chefs/delete/{id}")
//    public String deleteChef(@PathVariable Long id) {
//        chefService.delete(id);
//        return "redirect:/chefs";
//    }


    @GetMapping("/listChefs")
    public String getChefsSelectionPage(Model model) {
        model.addAttribute("chefs", chefService.listChefs());
        return "listChefs";
    }

    @PostMapping("/listChefs")
    public String selectChef(@RequestParam Long chefId) {
        return "redirect:/dish?chefId=" + chefId;
    }

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

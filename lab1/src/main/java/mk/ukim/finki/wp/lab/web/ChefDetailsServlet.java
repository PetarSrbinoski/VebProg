package mk.ukim.finki.wp.lab.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab.model.Chef;
import mk.ukim.finki.wp.lab.service.ChefService;
import mk.ukim.finki.wp.lab.service.DishService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.ISpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.util.Optional;

@WebServlet(name = "ChefDetailsServlet", urlPatterns = "/chefDetails")
public class ChefDetailsServlet extends HttpServlet {
    private final ChefService chefService;
    private final DishService dishService;
    private final ISpringTemplateEngine springTemplateEngine;

    public ChefDetailsServlet(ChefService chefService, DishService dishService, ISpringTemplateEngine springTemplateEngine) {
        this.chefService = chefService;
        this.dishService = dishService;
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange webExchange = JakartaServletWebApplication.buildApplication(getServletContext()).buildExchange(req, resp);
        WebContext context = new WebContext(webExchange);

        Optional<Chef> chef = chefService.findById(Long.parseLong(req.getParameter("chefId")));
        context.setVariable("chefName", chef.get().getFirstName() + " " + chef.get().getLastName());
        context.setVariable("chefBio", chef.get().getBio());
        context.setVariable("dishes", chef.get().getDishes());

        springTemplateEngine.process("chefDetails.html", context, resp.getWriter());
    }
}

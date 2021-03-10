package recipeapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import recipeapp.domain.Category;
import recipeapp.domain.UnitOfMeasure;
import recipeapp.repositories.CategoryRepository;
import recipeapp.repositories.UnitOfMeasureRepository;
import recipeapp.services.RecipeService;

import java.util.Optional;

@Controller
@RequestMapping({"", "/", "/index"})
public class IndexController {

    private final RecipeService recipeService;

    @Autowired
    public IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping
    public String getIndexPage(Model model) {
        model.addAttribute("recipes", recipeService.getRecipes());
        return "index";
    }

}

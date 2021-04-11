package recipeapp.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import recipeapp.commands.RecipeCommand;
import recipeapp.domain.Recipe;
import recipeapp.exceptions.NotFoundException;
import recipeapp.services.RecipeService;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/recipe")
public class RecipeController {

    private final RecipeService recipeService;
    private final String RECIPE_RECIPEFORM_URL = "recipe/recipeform";
    
    private RecipeCommand cache = null;

    @Autowired
    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/{id}/show")
    public String showById(@PathVariable String id, Model model) {
        model.addAttribute("recipe", recipeService.findById(Long.parseLong(id)));
        return "recipe/show";
    }

    @GetMapping("/new")
    public String newRecipe(Model model) {
        model.addAttribute("recipe", new RecipeCommand());
        return RECIPE_RECIPEFORM_URL;
    }

    @PostMapping
    public String saveOrUpdate(@Valid @ModelAttribute("recipe") RecipeCommand recipeCommand,
                               BindingResult bindingResult) {

        if (this.cache != null) {
            this.restoreAttributes(recipeCommand);
        }

        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(objectError -> {
                log.debug(objectError.toString());
            });

            return RECIPE_RECIPEFORM_URL;
        }

        this.cache = null;

        RecipeCommand savedCommand = recipeService.saveRecipeCommand(recipeCommand);
        savedCommand.getIngredients().forEach(ingredient -> {
            System.out.println(ingredient.getId() + " " + ingredient.getDescription());
        });

        return "redirect:/recipe/" + savedCommand.getId() + "/show";
    }

    private void restoreAttributes(RecipeCommand recipe) {
        recipe.setImage(this.cache.getImage());
        recipe.setIngredients(this.cache.getIngredients());
        recipe.setCategories(this.cache.getCategories());
    }

    @GetMapping("{id}/update")
    public String updateRecipe(@PathVariable String id, Model model) {
        RecipeCommand recipe = recipeService.findCommandById(Long.parseLong(id));
        this.cache = recipe;
        model.addAttribute("recipe", recipe);
        return RECIPE_RECIPEFORM_URL;
    }

    @GetMapping("{id}/delete")
    public String deleteRecipe(@PathVariable String id) {
        log.debug("Deleting id: " + id);
        recipeService.deleteById(Long.parseLong(id));
        return "redirect:/";
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ModelAndView handleNotFound(Exception exception) {
        log.error("Handling Not Found Exception");
        log.error(exception.getMessage());

        ModelAndView modelAndView = new ModelAndView("404error");
        modelAndView.addObject("exception", exception);

        return modelAndView;
    }

}

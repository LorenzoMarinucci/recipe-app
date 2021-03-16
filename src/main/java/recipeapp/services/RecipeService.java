package recipeapp.services;

import org.springframework.stereotype.Service;
import recipeapp.commands.RecipeCommand;
import recipeapp.domain.Recipe;

import java.util.Set;

@Service
public interface RecipeService {

    Set<Recipe> getRecipes();

    Recipe findById(Long id);

    RecipeCommand saveRecipeCommand(RecipeCommand command);

    RecipeCommand findCommandById(long id);
}

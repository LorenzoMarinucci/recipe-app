package recipeapp.services;

import recipeapp.commands.IngredientCommand;
import recipeapp.domain.Ingredient;
import recipeapp.domain.Recipe;

public interface IngredientService {

    IngredientCommand findByRecipeIdAndIngredientId(long recipeId, long ingredientId);

    IngredientCommand saveIngredientCommand(IngredientCommand command);
}

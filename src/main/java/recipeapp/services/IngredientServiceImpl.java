package recipeapp.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import recipeapp.commands.IngredientCommand;
import recipeapp.converters.IngredientToIngredientCommand;
import recipeapp.domain.Recipe;
import recipeapp.repositories.RecipeRepository;

import java.util.Optional;

@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService {

    private final IngredientToIngredientCommand ingredientConverter;
    private final RecipeRepository recipeRepository;

    public IngredientServiceImpl(IngredientToIngredientCommand ingredientConverter, RecipeRepository recipeRepository) {
        this.ingredientConverter = ingredientConverter;
        this.recipeRepository = recipeRepository;
    }

    @Override
    public IngredientCommand findByRecipeIdAndIngredientId(long recipeId, long ingredientId) {
        Optional<Recipe> optionalRecipe = recipeRepository.findById(recipeId);
        if (optionalRecipe.isEmpty()) {
            //error handling
            log.debug("Recipe id not found: " + recipeId);
        }

        Recipe recipe = optionalRecipe.get();

        Optional<IngredientCommand> optionalIngredientCommand = recipe.getIngredients()
                .stream()
                .filter(ingredient -> ingredient.getId().equals(ingredientId))
                .map(ingredientConverter::convert)
                .findFirst();

        if (optionalIngredientCommand.isEmpty()) {
            //error handling
            log.debug("Ingredient id not found: " + ingredientId);
        }

        return optionalIngredientCommand.get();

    }
}

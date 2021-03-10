package recipeapp.services;

import org.springframework.stereotype.Service;
import recipeapp.domain.Recipe;

import java.util.Set;

@Service
public interface RecipeService {

    Set<Recipe> getRecipes();

}

package recipeapp.repositories;

import org.springframework.data.repository.CrudRepository;
import recipeapp.domain.Recipe;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {
}

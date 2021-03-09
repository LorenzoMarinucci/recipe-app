package recipeapp.repositories;

import org.springframework.data.repository.CrudRepository;
import recipeapp.domain.Category;

public interface CategoryRepository extends CrudRepository<Category, Long> {
}

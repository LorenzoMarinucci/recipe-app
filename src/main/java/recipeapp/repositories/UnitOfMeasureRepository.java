package recipeapp.repositories;

import org.springframework.data.repository.CrudRepository;
import recipeapp.domain.UnitOfMeasure;

public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure, Long> {
}

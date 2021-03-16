package recipeapp.services;

import org.springframework.stereotype.Service;
import recipeapp.commands.UnitOfMeasureCommand;
import recipeapp.converters.UnitOfMeasureToUnitOfMeasureCommand;
import recipeapp.repositories.UnitOfMeasureRepository;

import java.util.HashSet;
import java.util.Set;

@Service
public class UnitOfMeasureServiceImpl implements UnitOfMeasureService {

    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final UnitOfMeasureToUnitOfMeasureCommand uomConverter;

    public UnitOfMeasureServiceImpl(UnitOfMeasureRepository unitOfMeasureRepository, UnitOfMeasureToUnitOfMeasureCommand uomConverter) {
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.uomConverter = uomConverter;
    }

    @Override
    public Set<UnitOfMeasureCommand> listAllUoms() {
        Set<UnitOfMeasureCommand> list = new HashSet<>();
        unitOfMeasureRepository.findAll().forEach(uom -> list.add(uomConverter.convert(uom)));
        return list;
    }
}

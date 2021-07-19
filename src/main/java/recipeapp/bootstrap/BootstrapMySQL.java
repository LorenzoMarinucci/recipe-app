package recipeapp.bootstrap;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import recipeapp.domain.*;
import recipeapp.repositories.CategoryRepository;
import recipeapp.repositories.RecipeRepository;
import recipeapp.repositories.UnitOfMeasureRepository;

import java.math.BigDecimal;
import java.util.Optional;

@Slf4j
@Component
@Profile({"dev", "prod"})
public class BootstrapMySQL implements CommandLineRunner {

    private CategoryRepository categoryRepository;
    private RecipeRepository recipeRepository;
    private UnitOfMeasureRepository unitOfMeasureRepository;

    @Autowired
    public BootstrapMySQL(CategoryRepository categoryRepository, RecipeRepository recipeRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {

        log.debug("Loading bootstrap data MySQL");

        //FIND CATEGORIES

        if (categoryRepository.count() == 0) {
            log.debug("Loading Categories");
            loadCategories();
        }

        if (unitOfMeasureRepository.count() == 0) {
            log.debug("Loading UOMs");
            loadUOMs();
        }

        Optional<Category> optionalMexican = categoryRepository.findByDescription("Mexican");
        if (!optionalMexican.isPresent()) {
            throw new RuntimeException("Category not found");
        }
        Category mexicanCategory = optionalMexican.get();

        Optional<Category> optionalAmerican = categoryRepository.findByDescription("American");
        if (!optionalAmerican.isPresent()) {
            throw new RuntimeException("Category not found");
        }
        Category americanCategory = optionalAmerican.get();

        Optional<Category> optionalItalian = categoryRepository.findByDescription("Italian");
        if (!optionalItalian.isPresent()) {
            throw new RuntimeException("Category not found");
        }
        Category italianCategory = optionalItalian.get();

        Optional<Category> optionalFastFood = categoryRepository.findByDescription("Fast Food");
        if (!optionalFastFood.isPresent()) {
            throw new RuntimeException("Category not found");
        }
        Category fastFoodCategory = optionalFastFood.get();

        //FIND UNITS OF MEASURE

        Optional<UnitOfMeasure> ounceOptional = unitOfMeasureRepository.findByDescription("Ounce");
        if (!ounceOptional.isPresent()) {
            throw new RuntimeException("Unit of Measure not found");
        }
        UnitOfMeasure ounceUOM = ounceOptional.get();

        Optional<UnitOfMeasure> teaspoonOptional = unitOfMeasureRepository.findByDescription("Teaspoon");
        if (!teaspoonOptional.isPresent()) {
            throw new RuntimeException("Unit of Measure not found");
        }
        UnitOfMeasure teaspoonUOM = teaspoonOptional.get();

        Optional<UnitOfMeasure> tablespoonOptional = unitOfMeasureRepository.findByDescription("Tablespoon");
        if (!tablespoonOptional.isPresent()) {
            throw new RuntimeException("Unit of Measure not found");
        }
        UnitOfMeasure tablespoonUOM = tablespoonOptional.get();

        Optional<UnitOfMeasure> cupOptional = unitOfMeasureRepository.findByDescription("Cup");
        if (!cupOptional.isPresent()) {
            throw new RuntimeException("Unit of Measure not found");
        }
        UnitOfMeasure cupUOM = cupOptional.get();

        Optional<UnitOfMeasure> pinchOptional = unitOfMeasureRepository.findByDescription("Pinch");
        if (!pinchOptional.isPresent()) {
            throw new RuntimeException("Unit of Measure not found");
        }
        UnitOfMeasure pinchUOM = pinchOptional.get();

        Optional<UnitOfMeasure> eachOptional = unitOfMeasureRepository.findByDescription("Each");
        if (!eachOptional.isPresent()) {
            throw new RuntimeException("Unit of Measure not found");
        }
        UnitOfMeasure eachUOM = eachOptional.get();

        Optional<UnitOfMeasure> pintOptional = unitOfMeasureRepository.findByDescription("Pint");
        if (!pintOptional.isPresent()) {
            throw new RuntimeException("Unit of Measure not found");
        }
        UnitOfMeasure pintUOM = pintOptional.get();

        Optional<UnitOfMeasure> ripeOptional = unitOfMeasureRepository.findByDescription("Ripe");
        if (!ripeOptional.isPresent()) {
            throw new RuntimeException("Unit of Measure not found");
        }
        UnitOfMeasure ripeUOM = ripeOptional.get();

        Optional<UnitOfMeasure> dashOptional = unitOfMeasureRepository.findByDescription("Dash");
        if (!dashOptional.isPresent()) {
            throw new RuntimeException("Unit of Measure not found");
        }
        UnitOfMeasure dashUOM = dashOptional.get();

        //INITIALIZATION

        Recipe perfectGuacamole = new Recipe();
        perfectGuacamole.setDifficulty(Difficulty.EASY);
        perfectGuacamole.setPrepTime(10);
        perfectGuacamole.setDescription("Perfect Guacamole");
        Notes guacamoleNotes = new Notes();
        guacamoleNotes.setRecipeNotes("Once you have basic guacamole down, feel free to experiment with variations including strawberries, peaches, pineapple, mangoes, even watermelon. One classic Mexican guacamole has pomegranate seeds and chunks of peaches in it (a Diana Kennedy favorite). You can get creative with homemade guacamole!\n" +
                "\n" +
                "    Simple Guacamole: The simplest version of guacamole is just mashed avocados with salt. Don't let the lack of availability of other ingredients stop you from making guacamole.\n" +
                "    Quick guacamole: For a very quick guacamole just take a 1/4 cup of salsa and mix it in with your mashed avocados.\n" +
                "    Don't have enough avocados? To extend a limited supply of avocados, add either sour cream or cottage cheese to your guacamole dip. Purists may be horrified, but so what? It tastes great.\n");
        perfectGuacamole.setNotes(guacamoleNotes);
        perfectGuacamole.setDirections("1. Cut the avocado, remove flesh:\n" +
                "\n" +
                "Cut the avocados in half. Remove the pit. Score the inside of the avocado with a blunt knife" +
                " and scoop out the flesh with a spoon. (See How to Cut and Peel an Avocado.) Place in a bowl.\n\n" +
                "\n2. Mash with a fork: \n\nUsing a fork, roughly mash the avocado. (Don't overdo it! " +
                "The guacamole should be a little chunky.)\n\n" +
                "\n3. Add salt, lime juice, and the rest:\n" +
                "\n" +
                "Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance " +
                "to the richness of the avocado and will help delay the avocados from turning brown.\n" +
                "\n" +
                "Add the chopped onion, cilantro, black pepper, and chiles. Chili " +
                "peppers vary individually in their hotness. So, start with a half of one chili pepper and " +
                "add to the guacamole to your desired degree of hotness.\n" +
                "\n" +
                "Remember that much of this is done to taste because of the variability in the fresh ingredients. " +
                "Start with this recipe and adjust to your taste.\n" +
                "Chilling tomatoes hurts their flavor, so if you want to add chopped tomato to your guacamole, " +
                "add it just before serving." +
                "\n4. Serve:\n" +
                "\n" +
                "Serve immediately, or if making a few hours ahead, place plastic wrap on the surface of the " +
                "guacamole and press down to cover it and to prevent air reaching it. (The oxygen in the air " +
                "causes oxidation which will turn the guacamole brown.) Refrigerate until ready to serve. ");
        perfectGuacamole.setCookTime(0);
        perfectGuacamole.setServings(4);
        perfectGuacamole.setUrl("https://www.simplyrecipes.com/recipes/perfect_guacamole/");
        perfectGuacamole.setSource("Simply Recipes");
        perfectGuacamole.getCategories().add(mexicanCategory);
        perfectGuacamole.getCategories().add(americanCategory);
        perfectGuacamole.addIngredient(new Ingredient("ripe avocados", new BigDecimal(2), eachUOM));
        perfectGuacamole.addIngredient(new Ingredient("salt, more to taste", new BigDecimal(".25"), teaspoonUOM));
        perfectGuacamole.addIngredient(new Ingredient("fresh lime juice or lemon juice", new BigDecimal(1), tablespoonUOM));
        perfectGuacamole.addIngredient(new Ingredient("minced red onion or thinly sliced green onion", new BigDecimal(".25"), tablespoonUOM));
        perfectGuacamole.addIngredient(new Ingredient("serrano chiles, stems and seeds removed, minced", new BigDecimal(2), eachUOM));
        perfectGuacamole.addIngredient(new Ingredient("cilantro (leaves and tender stems), finely chopped", new BigDecimal(2), tablespoonUOM));
        perfectGuacamole.addIngredient(new Ingredient("freshly grated black pepper", new BigDecimal(1), dashUOM));
        perfectGuacamole.addIngredient(new Ingredient("ripe tomato, seeds and pulp removed, chopped", new BigDecimal("0.5"), eachUOM));
        perfectGuacamole.addIngredient(new Ingredient("Red radishes or jicama, to garnish", new BigDecimal(1), eachUOM));
        perfectGuacamole.addIngredient(new Ingredient("Tortilla chips, to serve", new BigDecimal(6), eachUOM));

        recipeRepository.save(perfectGuacamole);
        System.out.println("Guacamole saved");

        //CHICKEN TACOS

        Recipe tacosRecipe = new Recipe();
        tacosRecipe.setDescription("Spicy Grilled Chicken Tacos");
        tacosRecipe.setUrl("https://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/");
        tacosRecipe.setSource("Simply Recipes");
        tacosRecipe.setPrepTime(20);
        tacosRecipe.setCookTime(15);
        tacosRecipe.setServings(3);
        tacosRecipe.setDifficulty(Difficulty.MODERATE);
        tacosRecipe.setDirections("\n" +
                "1. Prepare a gas or charcoal grill for medium-high, direct heat\n" +
                "2. Make the marinade and coat the chicken:\n" +
                "\n" +
                "In a large bowl, stir together the chili powder, oregano, cumin, sugar, salt, garlic and orange zest. Stir in the orange juice and olive oil to make a loose paste. Add the chicken to the bowl and toss to coat all over.\n" +
                "\n" +
                "Set aside to marinate while the grill heats and you prepare the rest of the toppings.\n" +
                "3. Grill the chicken:\n" +
                "\n" +
                "Grill the chicken for 3 to 4 minutes per side, or until a thermometer inserted into the thickest part of the meat registers 165F. Transfer to a plate and rest for 5 minutes.\n" +
                "4. Warm the tortillas:\n" +
                "\n" +
                "Place each tortilla on the grill or on a hot, dry skillet over medium-high heat. As soon as you see pockets of the air start to puff up in the tortilla, turn it with tongs and heat for a few seconds on the other side.\n" +
                "\n" +
                "Wrap warmed tortillas in a tea towel to keep them warm until serving.\n" +
                "5. Assemble the tacos:\n" +
                "\n" +
                "Slice the chicken into strips. On each tortilla, place a small handful of arugula. Top with chicken slices, sliced avocado, radishes, tomatoes, and onion slices. Drizzle with the thinned sour cream. Serve with lime wedges. ");

        Notes tacosNotes = new Notes();
        tacosNotes.setRecipeNotes("We have a family motto and it is this: Everything goes better in a tortilla.\n" +
                "\n" +
                "Any and every kind of leftover can go inside a warm tortilla, usually with a healthy dose of pickled jalapenos. I can always sniff out a late-night snacker when the aroma of tortillas heating in a hot pan on the stove comes wafting through the house.\n" +
                "\n" +
                "Today's tacos are more purposeful – a deliberate meal instead of a secretive midnight snack!\n" +
                "\n" +
                "First, I marinate the chicken briefly in a spicy paste of ancho chile powder, oregano, cumin, and sweet orange juice while the grill is heating. You can also use this time to prepare the taco toppings.\n" +
                "\n" +
                "Grill the chicken, then let it rest while you warm the tortillas. Now you are ready to assemble the tacos and dig in. The whole meal comes together in about 30 minutes! ");

        tacosRecipe.setNotes(tacosNotes);

        tacosRecipe.getCategories().add(mexicanCategory);
        tacosRecipe.getCategories().add(americanCategory);

        tacosRecipe.addIngredient(new Ingredient("ancho chili powder", new BigDecimal(2), tablespoonUOM));
        tacosRecipe.addIngredient(new Ingredient("dried oregano", new BigDecimal(1), teaspoonUOM));
        tacosRecipe.addIngredient(new Ingredient("dried cumin", new BigDecimal(1), teaspoonUOM));
        tacosRecipe.addIngredient(new Ingredient("sugar", new BigDecimal(1), teaspoonUOM));
        tacosRecipe.addIngredient(new Ingredient("salt ", new BigDecimal("0.5"), teaspoonUOM));
        tacosRecipe.addIngredient(new Ingredient("clove garlic, finely chopped", new BigDecimal(1), eachUOM));
        tacosRecipe.addIngredient(new Ingredient("finely grated orange zest", new BigDecimal(1), tablespoonUOM));
        tacosRecipe.addIngredient(new Ingredient("fresh-squeezed orange juice", new BigDecimal(3), tablespoonUOM));
        tacosRecipe.addIngredient(new Ingredient("olive oil", new BigDecimal(2), tablespoonUOM));
        tacosRecipe.addIngredient(new Ingredient("skinless, boneless chicken thighs (1 1/4 pounds)", new BigDecimal(5), eachUOM));
        tacosRecipe.addIngredient(new Ingredient("small corn tortillas ", new BigDecimal(8), eachUOM));
        tacosRecipe.addIngredient(new Ingredient("packed baby arugula (3 ounces)", new BigDecimal(3), cupUOM));
        tacosRecipe.addIngredient(new Ingredient("medium ripe avocados, sliced", new BigDecimal(2), eachUOM));
        tacosRecipe.addIngredient(new Ingredient("radishes, thinly sliced", new BigDecimal(4), eachUOM));
        tacosRecipe.addIngredient(new Ingredient("cherry tomatoes, halved", new BigDecimal("0.5"), pintUOM));
        tacosRecipe.addIngredient(new Ingredient("red onion, thinly sliced ", new BigDecimal("0.25"), eachUOM));
        tacosRecipe.addIngredient(new Ingredient("Roughly chopped cilantro", new BigDecimal(1), eachUOM));
        tacosRecipe.addIngredient(new Ingredient("sour cream", new BigDecimal("0.5"), cupUOM));
        tacosRecipe.addIngredient(new Ingredient("milk", new BigDecimal("0.25"), cupUOM));
        tacosRecipe.addIngredient(new Ingredient("lime, cut into wedges", new BigDecimal(1), eachUOM));

        recipeRepository.save(tacosRecipe);
        System.out.println("Spicy Tacos Saved");
    }

    private void loadCategories() {
        Category cat1 = new Category();
        cat1.setDescription("American");
        categoryRepository.save(cat1);
        Category cat2 = new Category();
        cat2.setDescription("Mexican");
        categoryRepository.save(cat2);
        Category cat3 = new Category();
        cat3.setDescription("Italian");
        categoryRepository.save(cat3);
        Category cat4 = new Category();
        cat4.setDescription("Fast Food");
        categoryRepository.save(cat4);
    }

    private void loadUOMs() {
        UnitOfMeasure uom1 = new UnitOfMeasure();
        uom1.setDescription("Teaspoon");
        unitOfMeasureRepository.save(uom1);
        UnitOfMeasure uom2 = new UnitOfMeasure();
        uom2.setDescription("Tablespoon");
        unitOfMeasureRepository.save(uom2);
        UnitOfMeasure uom3 = new UnitOfMeasure();
        uom3.setDescription("Cup");
        unitOfMeasureRepository.save(uom3);
        UnitOfMeasure uom4 = new UnitOfMeasure();
        uom4.setDescription("Pinch");
        unitOfMeasureRepository.save(uom4);
        UnitOfMeasure uom5 = new UnitOfMeasure();
        uom5.setDescription("Each");
        unitOfMeasureRepository.save(uom5);
        UnitOfMeasure uom6 = new UnitOfMeasure();
        uom6.setDescription("Ripe");
        unitOfMeasureRepository.save(uom6);
        UnitOfMeasure uom7 = new UnitOfMeasure();
        uom7.setDescription("Pint");
        unitOfMeasureRepository.save(uom7);
        UnitOfMeasure uom8 = new UnitOfMeasure();
        uom8.setDescription("Ounce");
        unitOfMeasureRepository.save(uom8);
        UnitOfMeasure uom9 = new UnitOfMeasure();
        uom9.setDescription("Dash");
        unitOfMeasureRepository.save(uom9);
    }


}

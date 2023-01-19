package com.example.webdevelopment_part3.services;
import com.example.webdevelopment_part3.model.Recipe;

import java.util.Map;

public interface RecipeService {
    Integer addRecipe(Recipe recipe);

    Recipe putRecipe(int id, Recipe recipe);

    boolean deleteRecipe(int id);

    Recipe getRecipe(int id);

    Map<Integer, Recipe> getAllRecipes();

    byte[] downloadDataFileRecipe();
}

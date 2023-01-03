package com.example.webdevelopment_part3.services.impl;

import com.example.webdevelopment_part3.model.Recipe;
import com.example.webdevelopment_part3.services.RecipeService;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.TreeMap;
@Service
public class RecipeServiceImpl implements RecipeService {
    private static Map<Integer, Recipe> recipes = new TreeMap<>();
    private static int recipeID = 0;

    @Override
    public Recipe addRecipe(Recipe recipe) {
        recipes.putIfAbsent(recipeID++, recipe);
        return recipe;
    }

    @Override
    public Recipe getRecipe(int ID) {
        return recipes.get(ID);
    }
}

package com.example.webdevelopment_part3.services.impl;

import com.example.webdevelopment_part3.model.Recipe;
import com.example.webdevelopment_part3.services.RecipeService;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.TreeMap;

@Service
public class RecipeServiceImpl implements RecipeService {
    private static final Map<Integer, Recipe> recipes = new TreeMap<>();
    private static int recipeID = 1;

    @Override
    public Integer addRecipe(Recipe recipe) {
        recipes.putIfAbsent(recipeID, recipe);
        return recipeID++;
    }

    @Override
    public Recipe putRecipe(int id, Recipe recipe) {
        if (recipes.containsKey(recipeID)) {
            recipes.put(recipeID, recipe);
            return recipe;
        }
        return null;
    }

    @Override
    public boolean deleteRecipe(int id) {
        if (recipes.containsKey(recipeID)) {
            recipes.remove(recipeID);
            return true;
        }
        return false;
    }

    @Override
    public Recipe getRecipe(int id) {
        return recipes.get(recipeID);
    }

    @Override
    public void getAllRecipes() {
        for(Map.Entry<Integer, Recipe> entry : recipes.entrySet()) {
            System.out.printf("%s=>%s\n", entry.getKey(), entry.getValue());
        }
    }
}

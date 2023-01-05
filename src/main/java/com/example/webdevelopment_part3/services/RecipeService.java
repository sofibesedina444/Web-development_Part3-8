package com.example.webdevelopment_part3.services;

import com.example.webdevelopment_part3.model.Recipe;

import java.util.Map;

public interface RecipeService {
    Recipe addRecipe(Recipe recipe);
    Recipe getRecipe(int id);
}

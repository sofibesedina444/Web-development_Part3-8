package com.example.webdevelopment_part3.services.impl;

import com.example.webdevelopment_part3.model.Ingredient;
import com.example.webdevelopment_part3.services.IngredientService;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.TreeMap;
@Service
public class IngredientServiceImpl implements IngredientService {
    private static Map<Integer, Ingredient> ingredients = new TreeMap<>();
    private static int ingredientID = 0;
    @Override
    public Ingredient addIngredient(Ingredient ingredient) {
        ingredients.putIfAbsent(ingredientID++, ingredient);
        return ingredient;
    }

    @Override
    public Ingredient getIngredient(int id) {
        return ingredients.get(ingredientID);
    }
}

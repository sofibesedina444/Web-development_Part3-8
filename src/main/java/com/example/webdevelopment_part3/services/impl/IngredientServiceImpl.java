package com.example.webdevelopment_part3.services.impl;

import com.example.webdevelopment_part3.model.Ingredient;
import com.example.webdevelopment_part3.services.IngredientService;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.TreeMap;

@Service
public class IngredientServiceImpl implements IngredientService {
    private static final Map<Integer, Ingredient> ingredients = new TreeMap<>();
    private static Integer ingredientID = 0;

    @Override
    public Integer addIngredient(Ingredient ingredient) {
        Integer newId = ++ingredientID;
        ingredients.putIfAbsent(newId, ingredient);
        return newId;
    }

    @Override
    public Ingredient putIngredient(int id, Ingredient ingredient) {
        if (ingredients.containsKey(ingredientID)) {
            ingredients.put(ingredientID, ingredient);
            return ingredient;
        }
        return null;
    }

    @Override
    public boolean deleteIngredient(int id) {
        if (ingredients.containsKey(ingredientID)) {
            ingredients.remove(ingredientID);
            return true;
        }
        return false;
    }

    @Override
    public Ingredient getIngredient(int id) {
        return ingredients.get(ingredientID);
    }

    @Override
    public void getAllIngredients() {
        for (Map.Entry<Integer, Ingredient> entry : ingredients.entrySet()) {
            System.out.printf("%s=>%s\n", entry.getKey(), entry.getValue());
        }
    }
}

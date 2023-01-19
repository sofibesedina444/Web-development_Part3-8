package com.example.webdevelopment_part3.services;
import com.example.webdevelopment_part3.model.Ingredient;

import java.util.Map;

public interface IngredientService {
    Integer addIngredient (Ingredient ingredient);

    Ingredient putIngredient(int id, Ingredient ingredient);

    boolean deleteIngredient(int id);

    Ingredient getIngredient (int id);

    Map<Integer, Ingredient> getAllIngredients();

    byte[] downloadDataFileIngredient();
}

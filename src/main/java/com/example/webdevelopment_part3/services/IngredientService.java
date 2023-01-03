package com.example.webdevelopment_part3.services;
import com.example.webdevelopment_part3.model.Ingredient;

public interface IngredientService {
    Ingredient addIngredient (Ingredient ingredient);
    Ingredient getIngredient (int id);
}

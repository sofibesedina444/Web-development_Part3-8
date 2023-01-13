package com.example.webdevelopment_part3.services;

public interface FileService {
    boolean saveToFileIngredient(String json);

    String readFromFileIngredient();

    boolean saveToFileRecipe(String json);

    String readFromFileRecipe();
}

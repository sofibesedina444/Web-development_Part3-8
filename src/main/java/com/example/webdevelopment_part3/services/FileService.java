package com.example.webdevelopment_part3.services;
import java.io.File;

public interface FileService {
    boolean saveToFileIngredient(String json);

    String readFromFileIngredient();

    boolean clearDataFileIngredient();

    File getDataFileIngredient();

    boolean saveToFileRecipe(String json);

    String readFromFileRecipe();

    boolean clearDataFileRecipe();

    File getDataFileRecipe();
}

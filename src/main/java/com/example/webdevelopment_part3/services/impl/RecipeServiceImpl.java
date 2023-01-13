package com.example.webdevelopment_part3.services.impl;

import com.example.webdevelopment_part3.model.Ingredient;
import com.example.webdevelopment_part3.model.Recipe;
import com.example.webdevelopment_part3.services.FileService;
import com.example.webdevelopment_part3.services.RecipeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.TreeMap;

@Service
public class RecipeServiceImpl implements RecipeService {
    private static Map<Integer, Recipe> recipes = new TreeMap<>();
    private static int recipeID = 0;
    private final FileService fileService;

    public RecipeServiceImpl(FileService fileService) {
        this.fileService = fileService;
    }

    @Override
    public Integer addRecipe(Recipe recipe) {
        Integer newId = ++recipeID;
        recipes.putIfAbsent(newId, recipe);
        saveToFileRecipe();
        return newId;
    }

    @Override
    public Recipe putRecipe(int id, Recipe recipe) {
        if (recipes.containsKey(recipeID)) {
            recipes.put(recipeID, recipe);
            saveToFileRecipe();
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

    private void saveToFileRecipe() {
        try {
            String json = new ObjectMapper().writeValueAsString(recipes);
            fileService.saveToFileRecipe(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private void readFromFileRecipe() {
        try {
            String json = fileService.readFromFileRecipe();
            recipes = new ObjectMapper().readValue(json, new TypeReference<Map<Integer, Recipe>>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
    @PostConstruct
    private void init() {
        readFromFileRecipe();
    }
}

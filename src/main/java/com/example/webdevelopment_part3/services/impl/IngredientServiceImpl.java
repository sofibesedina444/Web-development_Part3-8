package com.example.webdevelopment_part3.services.impl;

import com.example.webdevelopment_part3.model.Ingredient;
import com.example.webdevelopment_part3.model.Recipe;
import com.example.webdevelopment_part3.services.FileService;
import com.example.webdevelopment_part3.services.IngredientService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

@Service
public class IngredientServiceImpl implements IngredientService {
    private static Map<Integer, Ingredient> ingredients = new TreeMap<>();
    private static Integer ingredientID = 0;
    private final FileService fileService;

    public IngredientServiceImpl(FileService fileService) {
        this.fileService = fileService;
    }

    @Override
    public Integer addIngredient(Ingredient ingredient) {
        Integer newId = ++ingredientID;
        ingredients.putIfAbsent(newId, ingredient);
        saveToFileIngredient();
        return newId;
    }

    @Override
    public Ingredient putIngredient(int id, Ingredient ingredient) {
        if (ingredients.containsKey(ingredientID)) {
            ingredients.put(ingredientID, ingredient);
            saveToFileIngredient();
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

    @Nullable
    @Override
    public Map<Integer, Ingredient> getAllIngredients() {
        return new HashMap<>(ingredients);
    }

    @Nullable
    @Override
    public byte[] downloadDataFileIngredient() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Ingredient ingredient : ingredients.values()) {
            stringBuilder.append(ingredient).append("\n").append(" ").append("\n");
        }
        return stringBuilder.toString().getBytes(StandardCharsets.UTF_8);
    }

    private void saveToFileIngredient() {
        try {
            String json = new ObjectMapper().writeValueAsString(ingredients);
            fileService.saveToFileIngredient(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private void readFromFileIngredient() {
        try {
            String json = fileService.readFromFileIngredient();
            ingredients = new ObjectMapper().readValue(json, new TypeReference<Map<Integer, Ingredient>>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @PostConstruct
    private void init() {
        readFromFileIngredient();
    }
}

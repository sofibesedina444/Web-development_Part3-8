package com.example.webdevelopment_part3.controller;

import com.example.webdevelopment_part3.model.Recipe;
import com.example.webdevelopment_part3.services.RecipeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/recipe")
@RestController
public class RecipeController {
    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity createRecipe(@RequestBody Recipe recipe) {
        Recipe createRecipe = recipeService.addRecipe(recipe);
        return ResponseEntity.ok(createRecipe);
    }

    @GetMapping("/recipeID")
    @ResponseBody
    public ResponseEntity getRecipe(@PathVariable int recipeID) {
        Recipe recipe = recipeService.getRecipe(recipeID);
        if(recipe == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(recipe);
    }
}

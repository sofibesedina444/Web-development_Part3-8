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
    public ResponseEntity<Integer> createRecipe(@RequestBody Recipe recipe) {
        Integer createRecipe = recipeService.addRecipe(recipe);
        return ResponseEntity.ok(createRecipe);
    }

    @PutMapping("/{recipeID}")
    @ResponseBody
    public ResponseEntity<Recipe> editRecipe(@PathVariable int recipeID, @RequestBody Recipe recipe) {
        Recipe editRecipe = recipeService.putRecipe(recipeID, recipe);
        if (recipe == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(editRecipe);
    }

    @DeleteMapping("/{recipeID}")
    @ResponseBody
    public ResponseEntity<Void> deleteRecipe(@PathVariable int recipeID) {
        if (recipeService.deleteRecipe(recipeID)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{recipeID}")
    @ResponseBody
    public ResponseEntity<Recipe> getRecipe(@PathVariable int recipeID) {
        Recipe getRecipe = recipeService.getRecipe(recipeID);
        if(getRecipe == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(getRecipe);
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<Void> getAllRecipes() {
        recipeService.getAllRecipes();
        return ResponseEntity.ok().build();
    }
}

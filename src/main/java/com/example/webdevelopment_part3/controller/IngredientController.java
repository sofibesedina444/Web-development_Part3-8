package com.example.webdevelopment_part3.controller;

import com.example.webdevelopment_part3.model.Ingredient;
import com.example.webdevelopment_part3.services.IngredientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/ingredient")
@RestController
public class IngredientController {
    private final IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<Integer> createIngredient(@RequestBody Ingredient ingredient) {
        Integer createIngredient = ingredientService.addIngredient(ingredient);
        return ResponseEntity.ok(createIngredient);
    }

    @PutMapping("/{ingredientID}")
    @ResponseBody
    public ResponseEntity<Ingredient> editIngredient(@PathVariable int ingredientID, @RequestBody Ingredient ingredient) {
        Ingredient editIngredient = ingredientService.putIngredient(ingredientID, ingredient);
        if (ingredient == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(editIngredient);
    }

    @DeleteMapping("/{ingredientID}")
    @ResponseBody
    public ResponseEntity<Void> deleteIngredient(@PathVariable int ingredientID) {
        if (ingredientService.deleteIngredient(ingredientID)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{ingredientID}")
    @ResponseBody
    public ResponseEntity<Ingredient> getIngredient(@PathVariable int ingredientID) {
        Ingredient getIngredient = ingredientService.getIngredient(ingredientID);
        if (getIngredient == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(getIngredient);
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<Void> getAllIngredients() {
        ingredientService.getAllIngredients();
        return ResponseEntity.ok().build();
    }
}

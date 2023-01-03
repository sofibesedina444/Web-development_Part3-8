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

    @PostMapping("/create")
    @ResponseBody
    public ResponseEntity createIngredient(@RequestBody Ingredient ingredient) {
        Ingredient createIngredient = ingredientService.addIngredient(ingredient);
        return ResponseEntity.ok(createIngredient);
    }

    @GetMapping("/get/{ingredientID}")
    @ResponseBody
    public ResponseEntity getIngredient(@PathVariable int ingredientID) {
        Ingredient ingredient = ingredientService.getIngredient(ingredientID);
        if(ingredient == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ingredient);
    }
}

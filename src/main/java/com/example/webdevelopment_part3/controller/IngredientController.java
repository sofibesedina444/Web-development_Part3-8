package com.example.webdevelopment_part3.controller;

import com.example.webdevelopment_part3.model.Ingredient;
import com.example.webdevelopment_part3.services.IngredientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/ingredient")
@RestController
@Tag(name = "Ингридиент", description = "Операции CRUD")
public class IngredientController {
    private final IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @PostMapping
    @ResponseBody
    @Operation(summary = "Добавление ингридиента в список")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ингридиент создан", content = {
                    @Content(mediaType = "application/json", array =
                    @ArraySchema(schema =
                    @Schema(implementation = Ingredient.class)))
            }
            ),
            @ApiResponse(responseCode = "400", description = "Ингридиент не создан", content = {
                    @Content(mediaType = "application/json", array =
                    @ArraySchema(schema =
                    @Schema(implementation = Ingredient.class)))
            }
            )
    }
    )
    public ResponseEntity<Integer> createIngredient(@RequestBody Ingredient ingredient) {
        Integer createIngredient = ingredientService.addIngredient(ingredient);
        return ResponseEntity.ok(createIngredient);
    }

    @PutMapping("/{ingredientID}")
    @ResponseBody
    @Operation(summary = "Редактирование добавленного ингридиента", description = "Поиск ингридиента по id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ингридиент изменен", content = {
                    @Content(mediaType = "application/json", array =
                    @ArraySchema(schema =
                    @Schema(implementation = Ingredient.class)))
            }
            ),
            @ApiResponse(responseCode = "400", description = "Ингридиент не изменен", content = {
                    @Content(mediaType = "application/json", array =
                    @ArraySchema(schema =
                    @Schema(implementation = Ingredient.class)))
            }
            )
    }
    )
    public ResponseEntity<Ingredient> editIngredient(@PathVariable int ingredientID, @RequestBody Ingredient ingredient) {
        Ingredient editIngredient = ingredientService.putIngredient(ingredientID, ingredient);
        if (ingredient == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(editIngredient);
    }

    @DeleteMapping("/{ingredientID}")
    @ResponseBody
    @Operation(summary = "Удаление добавленного ингридиента из списка", description = "Поиск ингридиента по id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ингридиент удален", content = {
                    @Content(mediaType = "application/json", array =
                    @ArraySchema(schema =
                    @Schema(implementation = Ingredient.class)))
            }
            ),
            @ApiResponse(responseCode = "400", description = "Ингридиент не удален", content = {
                    @Content(mediaType = "application/json", array =
                    @ArraySchema(schema =
                    @Schema(implementation = Ingredient.class)))
            }
            )
    }
    )
    public ResponseEntity<Void> deleteIngredient(@PathVariable int ingredientID) {
        if (ingredientService.deleteIngredient(ingredientID)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{ingredientID}")
    @ResponseBody
    @Operation(summary = "Поиск добавленного в список ингридиента", description = "Поиск ингридиента по id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ингридиент найден", content = {
                    @Content(mediaType = "application/json", array =
                    @ArraySchema(schema =
                    @Schema(implementation = Ingredient.class)))
            }
            ),
            @ApiResponse(responseCode = "400", description = "Ингридиент не найден", content = {
                    @Content(mediaType = "application/json", array =
                    @ArraySchema(schema =
                    @Schema(implementation = Ingredient.class)))
            }
            )
    }
    )
    public ResponseEntity<Ingredient> getIngredient(@PathVariable int ingredientID) {
        Ingredient getIngredient = ingredientService.getIngredient(ingredientID);
        if (getIngredient == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(getIngredient);
    }

    @GetMapping
    @ResponseBody
    @Operation(summary = "Получение всех ингридиентов из списка")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ингридиенты найдены", content = {
                    @Content(mediaType = "application/json", array =
                    @ArraySchema(schema =
                    @Schema(implementation = Ingredient.class)))
            }
            ),
            @ApiResponse(responseCode = "400", description = "Ингридиенты не найдены", content = {
                    @Content(mediaType = "application/json", array =
                    @ArraySchema(schema =
                    @Schema(implementation = Ingredient.class)))
            }
            )
    }
    )
    public ResponseEntity<Void> getAllIngredients() {
        ingredientService.getAllIngredients();
        return ResponseEntity.ok().build();
    }
}

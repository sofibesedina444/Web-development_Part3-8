package com.example.webdevelopment_part3.controller;

import com.example.webdevelopment_part3.model.Ingredient;
import com.example.webdevelopment_part3.model.Recipe;
import com.example.webdevelopment_part3.services.RecipeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping("/recipe")
@RestController
@Tag(name = "Рецепт", description = "Операции CRUD и скачивание файла txt")
public class RecipeController {
    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @PostMapping
    @ResponseBody
    @Operation(summary = "Добавление рецепта в список")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Рецепт создан", content = {
                    @Content(mediaType = "application/json", array =
                    @ArraySchema(schema =
                    @Schema(implementation = Ingredient.class)))
            }
            ),
            @ApiResponse(responseCode = "400", description = "Рецепт не создан", content = {
                    @Content(mediaType = "application/json", array =
                    @ArraySchema(schema =
                    @Schema(implementation = Ingredient.class)))
            }
            )
    }
    )
    public ResponseEntity<Integer> createRecipe(@RequestBody Recipe recipe) {
        Integer createRecipe = recipeService.addRecipe(recipe);
        return ResponseEntity.ok(createRecipe);
    }

    @PutMapping("/{recipeID}")
    @ResponseBody
    @Operation(summary = "Редактирование добавленного рецепта", description = "Поиск рецепта по id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Рецепт изменен", content = {
                    @Content(mediaType = "application/json", array =
                    @ArraySchema(schema =
                    @Schema(implementation = Ingredient.class)))
            }
            ),
            @ApiResponse(responseCode = "400", description = "Рецепт не изменен", content = {
                    @Content(mediaType = "application/json", array =
                    @ArraySchema(schema =
                    @Schema(implementation = Ingredient.class)))
            }
            )
    }
    )
    public ResponseEntity<Recipe> editRecipe(@PathVariable int recipeID, @RequestBody Recipe recipe) {
        Recipe editRecipe = recipeService.putRecipe(recipeID, recipe);
        if (recipe == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(editRecipe);
    }

    @DeleteMapping("/{recipeID}")
    @ResponseBody
    @Operation(summary = "Удаление добавленного рецепта из списка", description = "Поиск рецепта по id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Рецепт удален", content = {
                    @Content(mediaType = "application/json", array =
                    @ArraySchema(schema =
                    @Schema(implementation = Ingredient.class)))
            }
            ),
            @ApiResponse(responseCode = "400", description = "Рецепт не удален", content = {
                    @Content(mediaType = "application/json", array =
                    @ArraySchema(schema =
                    @Schema(implementation = Ingredient.class)))
            }
            )
    }
    )
    public ResponseEntity<Void> deleteRecipe(@PathVariable int recipeID) {
        if (recipeService.deleteRecipe(recipeID)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{recipeID}")
    @ResponseBody
    @Operation(summary = "Поиск добавленного в список рецепта", description = "Поиск рецепта по id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Рецепт найден", content = {
                    @Content(mediaType = "application/json", array =
                    @ArraySchema(schema =
                    @Schema(implementation = Ingredient.class)))
            }
            ),
            @ApiResponse(responseCode = "400", description = "Рецепт не найден", content = {
                    @Content(mediaType = "application/json", array =
                    @ArraySchema(schema =
                    @Schema(implementation = Ingredient.class)))
            }
            )
    }
    )
    public ResponseEntity<Recipe> getRecipe(@PathVariable int recipeID) {
        Recipe getRecipe = recipeService.getRecipe(recipeID);
        if(getRecipe == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(getRecipe);
    }

    @GetMapping
    @ResponseBody
    @Operation(summary = "Получение всех рецептов из списка")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Рецепты найдены", content = {
                    @Content(mediaType = "application/json", array =
                    @ArraySchema(schema =
                    @Schema(implementation = Ingredient.class)))
            }
            ),
            @ApiResponse(responseCode = "400", description = "Рецепты не найдены", content = {
                    @Content(mediaType = "application/json", array =
                    @ArraySchema(schema =
                    @Schema(implementation = Ingredient.class)))
            }
            )
    }
    )
    public Map<Integer, Recipe> getAllRecipes() {
        return recipeService.getAllRecipes();
    }

    @GetMapping("/downloadRecipe")
    @Operation(summary = "Скачивание файла со списком рецептов в формате txt")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Запрос выполнился", content = {
                    @Content(mediaType = "txt", array =
                    @ArraySchema(schema =
                    @Schema(implementation = Ingredient.class)))
            }
            ),
            @ApiResponse(responseCode = "400", description = "Ошибка в параметрах запроса", content = {
                    @Content(mediaType = "txt", array =
                    @ArraySchema(schema =
                    @Schema(implementation = Ingredient.class)))
            }
            ),
            @ApiResponse(responseCode = "404", description = "URL неверный или такого действия нет в веб-приложении",
                    content = {
                            @Content(mediaType = "txt", array =
                            @ArraySchema(schema =
                            @Schema(implementation = Ingredient.class)))
                    }
            ),
            @ApiResponse(responseCode = "500", description = "Ошибка на сервере",
                    content = {
                            @Content(mediaType = "txt", array =
                            @ArraySchema(schema =
                            @Schema(implementation = Ingredient.class)))
                    }
            )
    }
    )
    public ResponseEntity<byte[]> downloadDataFileRecipe() {
        byte[] data = recipeService.downloadDataFileRecipe();
        if (data == null) {
            return ResponseEntity.badRequest().build();
        } else {
            return ResponseEntity.ok()
                    .contentLength(data.length)
                    .contentType(MediaType.TEXT_PLAIN)
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"RecipeLog.txt\"")
                    .body(data);
        }
    }
}

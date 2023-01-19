package com.example.webdevelopment_part3.controller;

import com.example.webdevelopment_part3.model.Ingredient;
import com.example.webdevelopment_part3.model.Recipe;
import com.example.webdevelopment_part3.services.IngredientService;
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

@RequestMapping("/ingredient")
@RestController
@Tag(name = "Ингредиент", description = "Операции CRUD и скачивание файла txt")
public class IngredientController {
    private final IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @PostMapping
    @ResponseBody
    @Operation(summary = "Добавление ингредиента в список")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ингредиент создан", content = {
                    @Content(mediaType = "application/json", array =
                    @ArraySchema(schema =
                    @Schema(implementation = Ingredient.class)))
            }
            ),
            @ApiResponse(responseCode = "400", description = "Ингредиент не создан", content = {
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
    @Operation(summary = "Редактирование добавленного ингредиента", description = "Поиск ингредиента по id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ингредиент изменен", content = {
                    @Content(mediaType = "application/json", array =
                    @ArraySchema(schema =
                    @Schema(implementation = Ingredient.class)))
            }
            ),
            @ApiResponse(responseCode = "400", description = "Ингредиент не изменен", content = {
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
    @Operation(summary = "Удаление добавленного ингредиента из списка", description = "Поиск ингредиента по id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ингредиент удален", content = {
                    @Content(mediaType = "application/json", array =
                    @ArraySchema(schema =
                    @Schema(implementation = Ingredient.class)))
            }
            ),
            @ApiResponse(responseCode = "400", description = "Ингредиент не удален", content = {
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
    @Operation(summary = "Поиск добавленного в список ингредиента", description = "Поиск ингредиента по id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ингредиент найден", content = {
                    @Content(mediaType = "application/json", array =
                    @ArraySchema(schema =
                    @Schema(implementation = Ingredient.class)))
            }
            ),
            @ApiResponse(responseCode = "400", description = "Ингредиент не найден", content = {
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
    @Operation(summary = "Получение всех ингредиентов из списка")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ингредиенты найдены", content = {
                    @Content(mediaType = "application/json", array =
                    @ArraySchema(schema =
                    @Schema(implementation = Ingredient.class)))
            }
            ),
            @ApiResponse(responseCode = "400", description = "Ингридеенты не найдены", content = {
                    @Content(mediaType = "application/json", array =
                    @ArraySchema(schema =
                    @Schema(implementation = Ingredient.class)))
            }
            )
    }
    )
    public Map<Integer, Ingredient> getAllIngredients() {
        return ingredientService.getAllIngredients();
    }

    @GetMapping("/downloadIngredient")
    @Operation(summary = "Скачивание файла со списком ингредиентов в формате txt")
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
    public ResponseEntity<byte[]> downloadDataFileIngredient() {
        byte[] data = ingredientService.downloadDataFileIngredient();
        if (data == null) {
            return ResponseEntity.badRequest().build();
        } else {
            return ResponseEntity.ok()
                    .contentLength(data.length)
                    .contentType(MediaType.TEXT_PLAIN)
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"IngredientLog.txt\"")
                    .body(data);
        }
    }
}

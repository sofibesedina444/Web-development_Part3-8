package com.example.webdevelopment_part3.controller;

import com.example.webdevelopment_part3.model.Ingredient;
import com.example.webdevelopment_part3.services.FileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@RestController
@RequestMapping("/files")
@Tag(name = "Файл", description = "Работа с файлами")
public class FileController {
    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @GetMapping("/exportIngredient")
    @Operation(summary = "Скачивание файла со списком ингредиентов в формате json")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Запрос выполнился", content = {
                    @Content(mediaType = "application/json", array =
                    @ArraySchema(schema =
                    @Schema(implementation = Ingredient.class)))
            }
            ),
            @ApiResponse(responseCode = "400", description = "Ошибка в параметрах запроса", content = {
                    @Content(mediaType = "application/json", array =
                    @ArraySchema(schema =
                    @Schema(implementation = Ingredient.class)))
            }
            ),
            @ApiResponse(responseCode = "404", description = "URL неверный или такого действия нет в веб-приложении",
                    content = {
                            @Content(mediaType = "application/json", array =
                            @ArraySchema(schema =
                            @Schema(implementation = Ingredient.class)))
                    }
            ),
            @ApiResponse(responseCode = "500", description = "Ошибка на сервере",
                    content = {
                            @Content(mediaType = "application/json", array =
                            @ArraySchema(schema =
                            @Schema(implementation = Ingredient.class)))
                    }
            )
    }
    )
    public ResponseEntity<InputStreamResource> exportDataFileIngredient() throws FileNotFoundException {
        File file = fileService.getDataFileIngredient();
        if (file.exists()) {
            InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .contentLength(file.length())
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"IngredientLog.json\"")
                    .body(resource);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PutMapping(value = "/importIngredient", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Обновление файла со списком ингредиентов")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Запрос выполнился", content = {
                    @Content(mediaType = "application/json", array =
                    @ArraySchema(schema =
                    @Schema(implementation = Ingredient.class)))
            }
            ),
            @ApiResponse(responseCode = "400", description = "Ошибка в параметрах запроса", content = {
                    @Content(mediaType = "application/json", array =
                    @ArraySchema(schema =
                    @Schema(implementation = Ingredient.class)))
            }
            ),
            @ApiResponse(responseCode = "404", description = "URL неверный или такого действия нет в веб-приложении",
                    content = {
                    @Content(mediaType = "application/json", array =
                    @ArraySchema(schema =
                    @Schema(implementation = Ingredient.class)))
            }
            ),
            @ApiResponse(responseCode = "500", description = "Ошибка на сервере",
                    content = {
                            @Content(mediaType = "application/json", array =
                            @ArraySchema(schema =
                            @Schema(implementation = Ingredient.class)))
                    }
            )
    }
    )
    public ResponseEntity<Void> importDataFileIngredient(@RequestParam MultipartFile multipartFile) {
        fileService.clearDataFileIngredient();
        File file = fileService.getDataFileIngredient();
        try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            IOUtils.copy(multipartFile.getInputStream(), fileOutputStream);
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @GetMapping("/exportRecipe")
    @Operation(summary = "Скачивание файла со списком рецептов в формате json")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Запрос выполнился", content = {
                    @Content(mediaType = "application/json", array =
                    @ArraySchema(schema =
                    @Schema(implementation = Ingredient.class)))
            }
            ),
            @ApiResponse(responseCode = "400", description = "Ошибка в параметрах запроса", content = {
                    @Content(mediaType = "application/json", array =
                    @ArraySchema(schema =
                    @Schema(implementation = Ingredient.class)))
            }
            ),
            @ApiResponse(responseCode = "404", description = "URL неверный или такого действия нет в веб-приложении",
                    content = {
                            @Content(mediaType = "application/json", array =
                            @ArraySchema(schema =
                            @Schema(implementation = Ingredient.class)))
                    }
            ),
            @ApiResponse(responseCode = "500", description = "Ошибка на сервере",
                    content = {
                            @Content(mediaType = "application/json", array =
                            @ArraySchema(schema =
                            @Schema(implementation = Ingredient.class)))
                    }
            )
    }
    )
    public ResponseEntity<InputStreamResource> exportDataFileRecipe() throws FileNotFoundException {
        File file = fileService.getDataFileRecipe();
        if (file.exists()) {
            InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).contentLength(file.length())
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"RecipeLog.json\"")
                    .body(resource);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PutMapping(value = "/importRecipe", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Обновление файла со списком рецептов")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Запрос выполнился", content = {
                    @Content(mediaType = "application/json", array =
                    @ArraySchema(schema =
                    @Schema(implementation = Ingredient.class)))
            }
            ),
            @ApiResponse(responseCode = "400", description = "Ошибка в параметрах запроса", content = {
                    @Content(mediaType = "application/json", array =
                    @ArraySchema(schema =
                    @Schema(implementation = Ingredient.class)))
            }
            ),
            @ApiResponse(responseCode = "404", description = "URL неверный или такого действия нет в веб-приложении",
                    content = {
                            @Content(mediaType = "application/json", array =
                            @ArraySchema(schema =
                            @Schema(implementation = Ingredient.class)))
                    }
            ),
            @ApiResponse(responseCode = "500", description = "Ошибка на сервере",
                    content = {
                            @Content(mediaType = "application/json", array =
                            @ArraySchema(schema =
                            @Schema(implementation = Ingredient.class)))
                    }
            )
    }
    )
    public ResponseEntity<Void> importDataFileRecipe(@RequestParam MultipartFile multipartFile) {
        fileService.clearDataFileRecipe();
        File file = fileService.getDataFileRecipe();
        try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            IOUtils.copy(multipartFile.getInputStream(), fileOutputStream);
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}

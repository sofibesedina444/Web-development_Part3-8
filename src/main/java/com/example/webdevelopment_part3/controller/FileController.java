package com.example.webdevelopment_part3.controller;

import com.example.webdevelopment_part3.services.FileService;
import io.swagger.v3.oas.annotations.Operation;
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
    @Operation(summary = "Скачивание файла со списком ингредиентов")
    public ResponseEntity<InputStreamResource> downloadDataFileIngredient() throws FileNotFoundException {
        File file = fileService.getDataFileIngredient();
        if (file.exists()) {
            InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).contentLength(file.length())
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"IngredientLog.json\"")
                    .body(resource);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PutMapping(value = "/importIngredient", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Обновление файла со списком ингредиентов")
    public ResponseEntity<Void> uploadDataFileIngredient(@RequestParam MultipartFile multipartFile) {
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
    @Operation(summary = "Скачивание файла со списком рецептов")
    public ResponseEntity<InputStreamResource> downloadDataFileRecipe() throws FileNotFoundException {
        File file = fileService.getDataFileRecipe();
        if (file.exists()) {
            InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).contentLength(file.length())
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"IngredientLog.json\"")
                    .body(resource);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PutMapping(value = "/importRecipe", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Обновление файла со списком рецептов")
    public ResponseEntity<Void> uploadDataFileRecipe(@RequestParam MultipartFile multipartFile) {
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

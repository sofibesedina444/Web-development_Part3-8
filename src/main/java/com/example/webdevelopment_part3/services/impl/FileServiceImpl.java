package com.example.webdevelopment_part3.services.impl;

import com.example.webdevelopment_part3.services.FileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class FileServiceImpl implements FileService {
    @Value("${path.to.data.file.ingredient}")
    private String dataFileIngredientPath;
    @Value("${name.of.data.file.ingredient}")
    private String dataFileIngredientName;
    @Value("${path.to.data.file.recipe}")
    private String dataFileRecipePath;
    @Value("${name.of.data.file.recipe}")
    private String dataFileRecipeName;

    @Override
    public boolean saveToFileIngredient(String json) {
        try {
            clearDataFileIngredient();
            Files.writeString(Path.of(dataFileIngredientPath, dataFileIngredientName), json);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    @Override
    public String readFromFileIngredient() {
        try {
            return Files.readString(Path.of(dataFileIngredientPath, dataFileIngredientName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean clearDataFileIngredient() {
        try {
            Path path = Path.of(dataFileIngredientPath, dataFileIngredientName);
            Files.deleteIfExists(path);
            Files.createFile(path);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public File getDataFileIngredient() {
        return new File(dataFileIngredientPath + "/" + dataFileIngredientName);
    }

    @Override
    public boolean saveToFileRecipe(String json) {
        try {
            clearDataFileRecipe();
            Files.writeString(Path.of(dataFileRecipePath, dataFileRecipeName), json);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    @Override
    public String readFromFileRecipe() {
        try {
            return Files.readString(Path.of(dataFileRecipePath, dataFileRecipeName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean clearDataFileRecipe() {
        try {
            Path path = Path.of(dataFileRecipePath, dataFileRecipeName);
            Files.deleteIfExists(path);
            Files.createFile(path);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public File getDataFileRecipe() {
        return new File(dataFileRecipePath + "/" + dataFileRecipeName);

    }
}

package com.example.webdevelopment_part3.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Recipe {
    private String name;
    private int time;
    private List<Ingredient> ingredients;
    private List<String> instruction;
}

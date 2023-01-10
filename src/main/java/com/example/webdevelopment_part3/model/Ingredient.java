package com.example.webdevelopment_part3.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Ingredient {
    private String name;
    private int amount;
    private String measure;
}

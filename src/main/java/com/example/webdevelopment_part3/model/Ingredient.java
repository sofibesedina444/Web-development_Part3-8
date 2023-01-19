package com.example.webdevelopment_part3.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ingredient {
    private String name;
    private int amount;
    private String measure;

    @Override
    public String toString() {
        return name + " - " + amount + " " + measure;
    }
}

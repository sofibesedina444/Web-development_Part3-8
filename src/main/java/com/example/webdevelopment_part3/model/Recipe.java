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

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(name).append("\n")
                .append("Время приготовления: ").append(time).append(" минут").append("\n")
                .append("Ингредиенты: ").append("\n");
        for(Ingredient ingredient : ingredients) {
            stringBuilder.append("• ").append(ingredient).append("\n");
        }
        stringBuilder.append("Инструкция приготовления: ").append("\n");
        for (String step : instruction) {
            stringBuilder.append(step).append("\n");
        }
        return stringBuilder.toString();
    }
}

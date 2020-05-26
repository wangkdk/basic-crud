package study.basiccrud.module.food.controller;

import lombok.Builder;
import lombok.Data;
import study.basiccrud.module.food.entity.FoodTypes;

@Data
public class FoodSearchCondition {

    private FoodTypes type;

    private String name;

    @Builder
    public FoodSearchCondition(FoodTypes type, String name) {
        this.type = type;
        this.name = name;
    }
}

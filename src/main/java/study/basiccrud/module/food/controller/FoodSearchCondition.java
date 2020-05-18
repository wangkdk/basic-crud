package study.basiccrud.module.food.controller;

import lombok.Data;
import study.basiccrud.module.food.entity.FoodTypes;

@Data
public class FoodSearchCondition {

    private FoodTypes type;

    private String name;

}

package study.basiccrud.module.food.dto;

import lombok.Data;
import study.basiccrud.module.food.entity.FoodTypes;

@Data
public class FoodResponseDto {

    private Long id;
    private FoodTypes type;
    private String name;
    private Integer price;
    private String desc;
}

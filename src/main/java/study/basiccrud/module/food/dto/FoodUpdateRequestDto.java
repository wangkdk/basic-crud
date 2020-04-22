package study.basiccrud.module.food.dto;

import lombok.Builder;
import lombok.Data;
import study.basiccrud.module.food.entity.FoodTypes;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class FoodUpdateRequestDto {

    private FoodTypes type;

    @NotEmpty
    private String name;

    @NotNull
    private Integer price;

    private String desc;

    @Builder
    public FoodUpdateRequestDto(FoodTypes type, String name, Integer price, String desc) {
        this.type = type;
        this.name = name;
        this.price = price;
        this.desc = desc;
    }
}

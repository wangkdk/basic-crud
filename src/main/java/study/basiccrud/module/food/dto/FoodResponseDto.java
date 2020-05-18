package study.basiccrud.module.food.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import study.basiccrud.module.food.entity.Food;
import study.basiccrud.module.food.entity.FoodReview;
import study.basiccrud.module.food.entity.FoodTypes;
import study.basiccrud.module.food.entity.QFood;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class FoodResponseDto {

    private Long id;
    private FoodTypes type;
    private String name;
    private Integer price;
    private String desc;
    private List<FoodReviewResponseDto> foodReviews = new ArrayList<>();

    @QueryProjection
    public FoodResponseDto(Long id, FoodTypes type, String name, Integer price, String desc) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.price = price;
        this.desc = desc;
    }

    public FoodResponseDto(Food food) {
        this.id = food.getId();
        this.type = food.getType();
        this.name = food.getName();
        this.price = food.getPrice();
        this.desc = food.getDesc();

        this.foodReviews = food.getFoodReviews().stream()
                .map(FoodReviewResponseDto::new)
                .collect(Collectors.toList());
    }
}

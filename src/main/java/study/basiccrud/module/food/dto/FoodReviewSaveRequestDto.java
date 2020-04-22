package study.basiccrud.module.food.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class FoodReviewSaveRequestDto {

    @NotEmpty
    private String comment;

    @NotNull
    @Max(10)
    private Integer score;

    @NotNull
    private Long foodId;

    @Builder
    public FoodReviewSaveRequestDto(String comment, Integer score, Long foodId) {
        this.comment = comment;
        this.score = score;
        this.foodId = foodId;
    }
}

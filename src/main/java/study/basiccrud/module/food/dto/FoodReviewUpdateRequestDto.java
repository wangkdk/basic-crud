package study.basiccrud.module.food.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class FoodReviewUpdateRequestDto {

    @NotEmpty
    private String comment;

    @NotNull
    @Max(10)
    private Integer score;

    @Builder
    public FoodReviewUpdateRequestDto(String comment, Integer score) {
        this.comment = comment;
        this.score = score;
    }
}

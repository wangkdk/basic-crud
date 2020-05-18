package study.basiccrud.module.food.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;
import study.basiccrud.module.food.entity.FoodReview;

@Data
@NoArgsConstructor
public class FoodReviewResponseDto {

    private Long id;
    private String comment;
    private Integer score;

    @QueryProjection
    public FoodReviewResponseDto(Long id, String comment, Integer score) {
        this.id = id;
        this.comment = comment;
        this.score = score;
    }

    public FoodReviewResponseDto(FoodReview fr) {
        this.id = fr.getId();
        this.comment = fr.getComment();
        this.score = fr.getScore();
    }
}

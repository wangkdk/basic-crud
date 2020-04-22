package study.basiccrud.module.food.entity;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Getter
@EqualsAndHashCode(of = "id")
@Entity
public class FoodReview {

    @Id @GeneratedValue
    @Column(name = "food_review_id")
    private Long id;

    @Column(name = "food_review_comment", nullable = false)
    private String comment;

    @Column(name = "food_review_score", nullable = false)
    @Min(0) @Max(10)
    private Integer score;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "food_id")
    private Food food;

    @Builder
    public FoodReview(String comment, Integer score, Food food) {
        this.comment = comment;
        this.score = score;
        this.food = food;
    }
}

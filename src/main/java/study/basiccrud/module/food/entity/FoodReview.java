package study.basiccrud.module.food.entity;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

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

}

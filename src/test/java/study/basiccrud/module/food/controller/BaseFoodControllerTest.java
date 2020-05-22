package study.basiccrud.module.food.controller;

import org.springframework.beans.factory.annotation.Autowired;
import study.basiccrud.common.BaseControllerTest;
import study.basiccrud.module.food.entity.Food;
import study.basiccrud.module.food.entity.FoodReview;
import study.basiccrud.module.food.entity.FoodTypes;
import study.basiccrud.module.food.repository.FoodRepository;
import study.basiccrud.module.food.repository.FoodReviewRepository;

public class BaseFoodControllerTest extends BaseControllerTest {

    @Autowired
    FoodRepository foodRepository;

    @Autowired
    FoodReviewRepository foodReviewRepository;

    protected Food generateFood(int idx) {
        Food food = Food.builder()
                .type(FoodTypes.KOREAN)
                .name("test" + idx)
                .price(10000)
                .desc("음식")
                .build();

        Food savedFood = foodRepository.save(food);

        generateFoodReview(savedFood, 6);
        generateFoodReview(savedFood, 8);

        return savedFood;
    }

    protected FoodReview generateFoodReview(Food food, Integer score) {
        FoodReview foodReview = FoodReview.builder()
                .food(food)
                .score(score)
                .comment("맛있었습니다.")
                .build();
        return foodReviewRepository.save(foodReview);
    }
}

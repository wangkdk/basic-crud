package study.basiccrud.module.food.controller;

import org.springframework.beans.factory.annotation.Autowired;
import study.basiccrud.common.BaseControllerTest;
import study.basiccrud.module.food.entity.Food;
import study.basiccrud.module.food.entity.FoodTypes;
import study.basiccrud.module.food.repository.FoodRepository;

public class BaseFoodControllerTest extends BaseControllerTest {

    @Autowired
    FoodRepository foodRepository;

    protected Food generateFood(int idx) {
        Food food = Food.builder()
                .type(FoodTypes.KOREAN)
                .name("test" + idx)
                .price(10000)
                .desc("음식")
                .build();

        return foodRepository.save(food);
    }
}

package study.basiccrud.module.food.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import study.basiccrud.module.food.dto.FoodReviewSaveRequestDto;
import study.basiccrud.module.food.entity.Food;
import study.basiccrud.module.food.entity.FoodReview;
import study.basiccrud.module.food.repository.FoodRepository;
import study.basiccrud.module.food.repository.FoodReviewRepository;

@RequiredArgsConstructor
@Service
public class FoodReviewService {

    private final FoodReviewRepository foodReviewRepository;
    private final FoodRepository foodRepository;

    public Long saveFoodReview(FoodReviewSaveRequestDto foodReviewSaveRequestDto) {
        Food food = foodRepository.findById(foodReviewSaveRequestDto.getFoodId())
                .orElseThrow(() -> new IllegalArgumentException("해당 음식이 존재하지 않습니다. id =" + foodReviewSaveRequestDto.getFoodId()));

        FoodReview savedFoodReview = FoodReview.builder()
                .comment(foodReviewSaveRequestDto.getComment())
                .score(foodReviewSaveRequestDto.getScore())
                .food(food)
                .build();

        return savedFoodReview.getId();
    }
}

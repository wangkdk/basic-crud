package study.basiccrud.module.food.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.basiccrud.module.food.dto.FoodSaveRequestDto;
import study.basiccrud.module.food.entity.Food;
import study.basiccrud.module.food.repository.FoodRepository;

@RequiredArgsConstructor
@Service
public class FoodService {

    private final FoodRepository foodRepository;

    @Transactional
    public Long saveFood(FoodSaveRequestDto saveRequestDto) {
        Food food = Food.builder()
                .type(saveRequestDto.getType())
                .name(saveRequestDto.getName())
                .price(saveRequestDto.getPrice())
                .desc(saveRequestDto.getDesc())
                .build();
        Food savedFood = foodRepository.save(food);
        return savedFood.getId();
    }
}

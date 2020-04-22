package study.basiccrud.module.food.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.basiccrud.module.food.dto.FoodSaveRequestDto;
import study.basiccrud.module.food.dto.FoodUpdateRequestDto;
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

    @Transactional
    public Long updateFood(Long id, FoodUpdateRequestDto foodUpdateDto) {
        // TODO : 모든 IllegalArgumentException 을 잡아서 ResponseEntity 로 return 할 수 없으니 custom exception 을 만들어서 사용해야할듯
        // TODO : 더 나은 방법을 생각해봐야함
        Food food = foodRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 음식이 존재하지 않습니다 id=" + id));
        food.updateFood(foodUpdateDto.getType(), foodUpdateDto.getName(), foodUpdateDto.getPrice(), foodUpdateDto.getDesc());
        return food.getId();
    }
}

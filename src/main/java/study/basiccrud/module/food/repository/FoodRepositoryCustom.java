package study.basiccrud.module.food.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import study.basiccrud.module.food.controller.FoodSearchCondition;
import study.basiccrud.module.food.dto.FoodResponseDto;
import study.basiccrud.module.food.entity.Food;

public interface FoodRepositoryCustom {

    Page<FoodResponseDto> searchPage(Pageable pageable, FoodSearchCondition condition);
}

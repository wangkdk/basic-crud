package study.basiccrud.module.food.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.basiccrud.module.food.entity.Food;

public interface FoodRepository extends JpaRepository<Food, Long> {
}

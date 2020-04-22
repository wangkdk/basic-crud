package study.basiccrud.module.food.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.basiccrud.module.food.entity.FoodReview;

public interface FoodReviewRepository extends JpaRepository<FoodReview, Long> {
}

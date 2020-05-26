package study.basiccrud.module.food.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import study.basiccrud.module.food.controller.FoodSearchCondition;
import study.basiccrud.module.food.dto.FoodResponseDto;
import study.basiccrud.module.food.dto.FoodReviewResponseDto;
import study.basiccrud.module.food.entity.Food;
import study.basiccrud.module.food.entity.FoodReview;
import study.basiccrud.module.food.entity.FoodTypes;

import javax.persistence.EntityManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class FoodRepositoryImplTest {

    @Autowired
    EntityManager em;
    JPAQueryFactory queryFactory;

    @Autowired
    FoodRepository foodRepository;

    @Autowired
    FoodReviewRepository foodReviewRepository;

    @BeforeEach
    public void init() {
        queryFactory = new JPAQueryFactory(em);
    }


    @Test
    void getFoodQueryResults() {
        Food food = Food.builder()
                .type(FoodTypes.AMERICAN)
                .price(10000)
                .desc("맛있는 햄버거")
                .name("딥 치즈 버거")
                .build();
        Food savedFood = foodRepository.save(food);

        FoodReview review1 = FoodReview.builder()
                .food(savedFood)
                .score(8)
                .comment("맛있게 잘 먹었습니다.")
                .build();

        FoodReview review2 = FoodReview.builder()
                .food(savedFood)
                .score(6)
                .comment("조금 짰습니다.")
                .build();

        foodReviewRepository.save(review1);
        foodReviewRepository.save(review2);

        em.flush();
        em.clear();

        PageRequest pageRequest = PageRequest.of(0, 10);
        FoodSearchCondition searchCondition = FoodSearchCondition.builder()
                .build();

        Page<FoodResponseDto> foodResponseDtos = foodRepository.searchPage(pageRequest, searchCondition);
        List<FoodResponseDto> foods = foodResponseDtos.getContent();
        List<FoodReviewResponseDto> foodReviews = foods.get(0).getFoodReviews();
        assertEquals(1, foods.size());
        assertEquals(2, foodReviews.size());

    }
}
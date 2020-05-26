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
import study.basiccrud.module.food.entity.Food;
import study.basiccrud.module.food.entity.FoodTypes;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class FoodRepositoryImplTest {

    @Autowired
    EntityManager em;
    JPAQueryFactory queryFactory;

    @Autowired
    FoodRepository foodRepository;

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
        foodRepository.save(food);

        PageRequest pageRequest = PageRequest.of(0, 10);
        FoodSearchCondition searchCondition = FoodSearchCondition.builder()
                .build();

        Page<FoodResponseDto> foodResponseDtos = foodRepository.searchPage(pageRequest, searchCondition);
        assertEquals(1, foodResponseDtos.getContent().size());

    }
}
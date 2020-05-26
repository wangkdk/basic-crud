package study.basiccrud.module.food.repository;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import study.basiccrud.module.food.controller.FoodSearchCondition;
import study.basiccrud.module.food.dto.FoodResponseDto;
import study.basiccrud.module.food.entity.Food;
import study.basiccrud.module.food.entity.FoodTypes;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.util.StringUtils.hasText;
import static study.basiccrud.module.food.entity.QFood.food;

public class FoodRepositoryImpl extends QuerydslRepositorySupport implements FoodRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public FoodRepositoryImpl(EntityManager em) {
        super(Food.class);
        this.queryFactory = new JPAQueryFactory(em);
    }

    /**
     * 컬렉션을 페치조인하면 데이터가 뻥튀기 된다.
     * @param pageable
     * @param condition
     * @return
     */
    @Override
    public Page<FoodResponseDto> searchPage(Pageable pageable, FoodSearchCondition condition) {

        /**
         * 데이터가 뻥튀기 되어서 제대로된 데이터를 가져올 수 없다.
         */
//        QueryResults<FoodResponseDto> queryResults = queryFactory.
//                select(new QFoodResponseDto(
//                        food.id,
//                        food.type,
//                        food.name,
//                        food.price,
//                        food.desc,
//                        food.foodReviews
//                ))
//                .from(food)
//                .join(food.foodReviews, foodReview)
//                .fetchJoin()
//                .offset(pageable.getOffset())
//                .limit(pageable.getPageSize())
//                .fetchResults();

        /**
         * to one 관계인 것만 먼저 fetch join 처리 후
         * @BatchSize 를 통해 collection 들은 따로 처리해 주어야 페이징이 가능해진다.
         */
        QueryResults<Food> queryResults = getFoodQueryResults(pageable, condition);

        List<Food> content = queryResults.getResults();
        long total = queryResults.getTotal();

        // TODO : 만약 리뷰의 컬럼으로 condition 이 걸린다면..?
        List<FoodResponseDto> collect = getFoodResponseDtos(content);

        return new PageImpl<>(collect, pageable, total);
    }

    private List<FoodResponseDto> getFoodResponseDtos(List<Food> content) {
        return content.stream().map(FoodResponseDto::new)
                    .collect(Collectors.toList());
    }

    private QueryResults<Food> getFoodQueryResults(Pageable pageable, FoodSearchCondition condition) {
        return queryFactory
                    .selectFrom(food)
                    .where(
                            typeEq(condition.getType()),
                            nameEq(condition.getName())
                    )
                    .offset(pageable.getOffset())
                    .limit(pageable.getPageSize())
                    .fetchResults();
    }

    private BooleanExpression typeEq(FoodTypes types) {
        return types != null ? food.type.eq(types) : null;
    }

    private BooleanExpression nameEq(String name) {
        return hasText(name) ? food.name.eq(name) : null;
    }
}

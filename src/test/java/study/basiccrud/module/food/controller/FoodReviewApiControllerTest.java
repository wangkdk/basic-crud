package study.basiccrud.module.food.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import study.basiccrud.module.food.dto.FoodReviewSaveRequestDto;
import study.basiccrud.module.food.entity.Food;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class FoodReviewApiControllerTest extends BaseFoodControllerTest{

    @DisplayName("리뷰 저장 테스트")
    @Test
    void saveFoodReview() throws Exception {

        Food savedFood = this.generateFood(1);
        FoodReviewSaveRequestDto foodReviewSaveRequestDto = FoodReviewSaveRequestDto.builder()
                .comment("맛있게 잘 먹었습니다.")
                .score(8)
                .foodId(savedFood.getId())
                .build();

        mockMvc.perform(post("/api/v1/food/review")
                .content(this.objectMapper.writeValueAsString(foodReviewSaveRequestDto))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isOk());
    }

    @DisplayName("리뷰 저장 테스트 - 존재하지않는 음식")
    @Test
    void saveFoodReview_NotFound() throws Exception {
        FoodReviewSaveRequestDto foodReviewSaveRequestDto = FoodReviewSaveRequestDto.builder()
                .comment("맛있게 잘 먹었습니다.")
                .score(8)
                .foodId(123123L)
                .build();

        mockMvc.perform(post("/api/v1/food/review")
                .content(this.objectMapper.writeValueAsString(foodReviewSaveRequestDto))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @DisplayName("리뷰 저장 테스트 - 잘못된 입력값")
    @Test
    void saveFoodReview_Wrong_Input() throws Exception {
        Food savedFood = this.generateFood(1);
        FoodReviewSaveRequestDto foodReviewSaveRequestDto = FoodReviewSaveRequestDto.builder()
                .comment("")
                .score(15)
                .foodId(savedFood.getId())
                .build();

        mockMvc.perform(post("/api/v1/food/review")
                .content(this.objectMapper.writeValueAsString(foodReviewSaveRequestDto))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isBadRequest());
    }
}
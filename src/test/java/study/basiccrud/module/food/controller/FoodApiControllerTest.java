package study.basiccrud.module.food.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import study.basiccrud.module.food.dto.FoodSaveRequestDto;
import study.basiccrud.module.food.dto.FoodUpdateRequestDto;
import study.basiccrud.module.food.entity.Food;
import study.basiccrud.module.food.entity.FoodTypes;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class FoodApiControllerTest extends BaseFoodControllerTest{

    @Test
    @DisplayName("음식 저장 테스트")
    void saveFood() throws Exception {
        FoodSaveRequestDto food = FoodSaveRequestDto.builder()
                .type(FoodTypes.AMERICAN)
                .name("봉골레")
                .price(15000)
                .desc("모시조개로 맛을 낸 파스타")
                .build();

        mockMvc.perform(post("/api/v1/food")
                .content(objectMapper.writeValueAsString(food))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isOk())
        ;
    }

    @Test
    @DisplayName("음식 저장 잘못된 input 값 테스트")
    void saveFood_wrong_input() throws Exception {
        FoodSaveRequestDto food = FoodSaveRequestDto.builder()
                .type(FoodTypes.AMERICAN)
                .name("")
                .price(15000)
                .desc("모시조개로 맛을 낸 파스타")
                .build();

        mockMvc.perform(post("/api/v1/food")
                .content(objectMapper.writeValueAsString(food))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isBadRequest())
        ;
    }

    @Test
    @DisplayName("음식 수정 테스트")
    void updateFood() throws Exception {
        Food savedFood = this.generateFood(1);

        FoodUpdateRequestDto food = FoodUpdateRequestDto.builder()
                .type(FoodTypes.AMERICAN)
                .name("돼지 갈비찜2")
                .price(10000)
                .desc("테스트용 입니다")
                .build();

        mockMvc.perform(put("/api/v1/food/{id}", savedFood.getId())
                .content(objectMapper.writeValueAsString(food))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isOk());

        Food findFood = foodRepository.findById(savedFood.getId()).get();
        assertEquals(FoodTypes.AMERICAN, findFood.getType());
        assertEquals("돼지 갈비찜2", findFood.getName());
        assertEquals(10000, findFood.getPrice());
        assertEquals("테스트용 입니다", findFood.getDesc());
    }

    @Test
    @DisplayName("존재하지 않는 음식 업데이트")
    void updateFood_NotFound() throws Exception {
        FoodUpdateRequestDto food = FoodUpdateRequestDto.builder()
                .type(FoodTypes.AMERICAN)
                .name("돼지 갈비찜2")
                .price(10000)
                .desc("테스트용 입니다")
                .build();

        mockMvc.perform(put("/api/v1/food/123213")
                .content(objectMapper.writeValueAsString(food))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("음식 조회하기")
    void getFood() throws Exception {
        //Given
        Food food = generateFood(1);

        // When & Then
        this.mockMvc.perform(get("/api/v1/food/{id}", food.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").exists())
                .andExpect(jsonPath("type").exists())
                .andExpect(jsonPath("name").exists())
                .andExpect(jsonPath("price").exists())
                .andExpect(jsonPath("desc").exists());
    }

    @Test
    @DisplayName("없는 음식 조회시에 Not Found")
    void getFood_NotFound() throws Exception {

        // When & Then
        this.mockMvc.perform(get("/api/v1/food/123123"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("30개 음식 리스트 10개씩 두번째 페이지 조회")
    void getFoods() throws Exception {
        // Given
        IntStream.range(0, 30).forEach(this::generateFood);

        em.flush();
        em.clear();

        // When & Then
        this.mockMvc.perform(get("/api/v1/food")
                    .param("page", "1")
                    .param("size", "10")
                    .param("sort", "name,DESC")
        )
                .andDo(print())
                .andExpect(jsonPath("content[0].id").exists())
                .andExpect(jsonPath("pageable.pageSize").value(10))
                .andExpect(jsonPath("pageable.pageNumber").value(1))
                .andExpect(jsonPath("totalElements").value(30))
                .andExpect(jsonPath("totalPages").value(3))
                .andExpect(jsonPath("sort").exists())
                .andExpect(jsonPath("content[0].foodReviews[0]").exists())
                .andExpect(status().isOk());

    }

}
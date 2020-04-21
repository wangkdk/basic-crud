package study.basiccrud.module.food.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import study.basiccrud.module.food.dto.FoodSaveRequestDto;
import study.basiccrud.module.food.entity.FoodTypes;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class FoodApiControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    @DisplayName("음식 저장성공하는 테스트")
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
}
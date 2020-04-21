package study.basiccrud.module.food.controller;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import study.basiccrud.module.food.dto.FoodSaveRequestDto;

@Component
public class FoodValidator {

    public void validate(FoodSaveRequestDto foodCreateDto, Errors errors) {
        // validate
//        errors.rejectValue("a", "wrongValue", "");

    }
}

package study.basiccrud.module.food.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import study.basiccrud.module.food.dto.FoodSaveRequestDto;
import study.basiccrud.module.food.service.FoodService;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/food")
public class FoodApiController {

    private final FoodService foodService;

    private final FoodValidator foodValidator;

    @PostMapping
    public ResponseEntity saveFood(@RequestBody @Valid FoodSaveRequestDto foodCreateDto, Errors errors) {

        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }

//        foodValidator.validate(foodCreateDto, errors);

//        if (errors.hasErrors()) {
//            return ResponseEntity.badRequest().body(errors);
//        }

        return ResponseEntity.ok().body(foodService.saveFood(foodCreateDto));
    }
}

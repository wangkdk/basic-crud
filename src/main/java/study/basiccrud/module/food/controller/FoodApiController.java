package study.basiccrud.module.food.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import study.basiccrud.module.food.dto.FoodSaveRequestDto;
import study.basiccrud.module.food.dto.FoodUpdateRequestDto;
import study.basiccrud.module.food.entity.Food;
import study.basiccrud.module.food.repository.FoodRepository;
import study.basiccrud.module.food.service.FoodService;

import javax.validation.Valid;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/food")
public class FoodApiController {

    private final FoodService foodService;
    private final FoodRepository foodRepository;
    private final FoodValidator foodValidator;

    @GetMapping
    public ResponseEntity getFoods(Pageable pageable) {
        Page<Food> list = foodRepository.findAll(pageable);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity getFood(@PathVariable Long id) {
        Optional<Food> food = foodRepository.findById(id);
        if (food.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(food.get());
    }

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

    @PutMapping("/{id}")
    public ResponseEntity updateFood(@PathVariable Long id,
                                     @RequestBody @Valid FoodUpdateRequestDto foodUpdateDto, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().body(foodService.updateFood(id, foodUpdateDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteFood(@PathVariable Long id) {
        return ResponseEntity.ok(foodService.deleteFood(id));
    }
}

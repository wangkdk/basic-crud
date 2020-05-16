package study.basiccrud.module.food.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import study.basiccrud.module.food.dto.FoodReviewSaveRequestDto;
import study.basiccrud.module.food.dto.FoodReviewUpdateRequestDto;
import study.basiccrud.module.food.entity.FoodReview;
import study.basiccrud.module.food.repository.FoodRepository;
import study.basiccrud.module.food.repository.FoodReviewRepository;
import study.basiccrud.module.food.service.FoodReviewService;

import javax.validation.Valid;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/food/review")
public class FoodReviewApiController {

    private final FoodReviewService foodReviewService;
    private final FoodReviewRepository foodReviewRepository;
    private final FoodRepository foodRepository;

    @GetMapping
    public ResponseEntity getFoodReviews(Pageable pageable) {
        Page<FoodReview> list = foodReviewRepository.findAll(pageable);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity getFoodReview(@PathVariable Long id) {
        Optional<FoodReview> foodReview = foodReviewRepository.findById(id);
        if (foodReview.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(foodReview.get());
    }

    @PostMapping
    public ResponseEntity saveFoodReview(@RequestBody @Valid FoodReviewSaveRequestDto foodReviewSaveRequestDto, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(foodReviewService.saveFoodReview(foodReviewSaveRequestDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity updateFoodReview(@PathVariable Long id,
            @RequestBody @Valid FoodReviewUpdateRequestDto foodReviewUpdateRequestDto, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(foodReviewService.updateFoodReview(id, foodReviewUpdateRequestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteFoodReview(@PathVariable Long id) {
        return ResponseEntity.ok(foodReviewService.deleteFoodReview(id));
    }
}

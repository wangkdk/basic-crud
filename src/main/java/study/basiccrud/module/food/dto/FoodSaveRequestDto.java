package study.basiccrud.module.food.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import study.basiccrud.module.food.entity.FoodTypes;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

// @RequestBody 를 사용하면 Jackson Library 가 파싱해주기 때문에 기본생성자가 필요없다. @ModelAttribute 사용할때에는 기본생성자 필요함
@Data
public class FoodSaveRequestDto {

    private FoodTypes type;

    @NotEmpty
    private String name;

    @NotNull
    private Integer price;

    private String desc;

}

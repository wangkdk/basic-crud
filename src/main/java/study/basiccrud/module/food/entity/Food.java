package study.basiccrud.module.food.entity;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@EqualsAndHashCode(of = "id")
@Entity
@NoArgsConstructor
public class Food {

    @Id @GeneratedValue
    @Column(name = "food_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private FoodTypes type;

    @Column(name = "food_name", nullable = false)
    private String name;

    @Column(name = "food_price", nullable = false)
    private Integer price;

    private String desc;

    @Builder
    public Food(FoodTypes type, String name, Integer price, String desc) {
        this.type = type;
        this.name = name;
        this.price = price;
        this.desc = desc;
    }
}

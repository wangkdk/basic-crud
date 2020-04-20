package study.basiccrud.module.food.entity;

import lombok.EqualsAndHashCode;

import javax.persistence.*;

@EqualsAndHashCode(of = {"id"})
@Entity
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
}

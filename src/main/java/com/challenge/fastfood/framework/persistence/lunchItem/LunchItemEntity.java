package com.challenge.fastfood.framework.persistence.lunchItem;

import com.challenge.fastfood.entities.LunchItemType;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "lunch_item")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class LunchItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private LunchItemType type;

    @Column(name = "price")
    private float price;

    @Column(name= "status")
    private Boolean status;

}

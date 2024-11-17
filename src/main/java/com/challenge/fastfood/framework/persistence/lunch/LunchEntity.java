package com.challenge.fastfood.framework.persistence.lunch;

import com.challenge.fastfood.framework.persistence.client.ClientEntity;
import com.challenge.fastfood.framework.persistence.lunchItem.LunchItemEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Table(name = "lunch")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class LunchEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="usuario_id")
    private ClientEntity client;

    @ManyToMany
    @JoinTable(
            name="lunch_lunch_item",
            joinColumns = @JoinColumn(name = "lunch_id"),
            inverseJoinColumns = @JoinColumn(name = "lunch_item_id"))
    private List<LunchItemEntity> lunchItems;

    @Column(name="status")
    private String status;

    @Column(name= "price_total")
    private double priceTotal;

    @Column(name= "date")
    private LocalDateTime date;

}

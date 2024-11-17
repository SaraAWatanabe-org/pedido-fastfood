package com.challenge.fastfood.framework.persistence.payment;


import jakarta.persistence.*;
import lombok.*;

@Table(name = "payment")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class PaymentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true)
    private String cpf;

    @Column(nullable = true , name= "priceTotal")
    private double priceTotal;

    @Column(nullable = true, name= "numberLunch")
    private Long numberLunch;

    @Column(nullable = true)
    private String status;

    @Column(nullable = true, name= "transactionId")
    private String transactionId;
}

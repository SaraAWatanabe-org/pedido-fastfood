package com.challenge.fastfood.framework.persistence.client;

import com.challenge.fastfood.framework.persistence.lunch.LunchEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Table(name = "client")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class ClientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "cpf")
    private String cpf;

    @OneToMany
    private List<LunchEntity> lunches;


}

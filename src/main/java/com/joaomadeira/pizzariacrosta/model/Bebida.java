package com.joaomadeira.pizzariacrosta.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "bebidas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Bebida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false)
    private Integer estoque;

    @Column(nullable = false)
    private Boolean status;

}
package com.joaomadeira.pizzariacrosta.model;

import com.joaomadeira.pizzariacrosta.model.enums.TamanhoPizza;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "pizzas")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Pizza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 100, nullable = false)
    private String sabor;

    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    private TamanhoPizza tamanho;

    @Column(nullable = false, precision = 6, scale = 2)
    private BigDecimal preco;

    @Column(nullable = false)
    private boolean status;

}
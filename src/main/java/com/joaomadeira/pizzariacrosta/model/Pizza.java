package com.joaomadeira.pizzariacrosta.model;

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

    @Column(nullable = false, precision = 6, scale = 2)
    private BigDecimal preco;

    @Column(nullable = false)
    private boolean status;

    @Column(name = "url_imagem", length = 100)
    private String urlImagem;

}
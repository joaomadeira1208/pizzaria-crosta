package com.joaomadeira.pizzariacrosta.model;

import com.joaomadeira.pizzariacrosta.model.enums.TamanhoPizza;
import com.joaomadeira.pizzariacrosta.model.id.PedidoPizzaId;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "pedido_pizzas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PedidoPizza {

    @EmbeddedId
    private PedidoPizzaId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("pedidoId")
    @JoinColumn(name = "pedido_id", nullable = false)
    private Pedido pedido;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("pizzaId")
    @JoinColumn(name = "pizza_id", nullable = false)
    private Pizza pizza;

    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    private TamanhoPizza tamanho;

    @Column(nullable = false)
    private Integer quantidade;

}
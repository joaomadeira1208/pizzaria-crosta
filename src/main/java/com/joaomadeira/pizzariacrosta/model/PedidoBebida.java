package com.joaomadeira.pizzariacrosta.model;

import com.joaomadeira.pizzariacrosta.model.id.PedidoBebidaId;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "pedido_bebidas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PedidoBebida {

    @EmbeddedId
    private PedidoBebidaId id;

    @ManyToOne
    @MapsId("pedidoId")
    @JoinColumn(name = "pedido_id", nullable = false)
    private Pedido pedido;

    @ManyToOne
    @MapsId("bebidaId")
    @JoinColumn(name = "bebida_id", nullable = false)
    private Bebida bebida;

    @Column(nullable = false)
    private Integer quantidade;

}
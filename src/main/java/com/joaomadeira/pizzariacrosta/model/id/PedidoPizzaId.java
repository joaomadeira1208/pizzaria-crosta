package com.joaomadeira.pizzariacrosta.model.id;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PedidoPizzaId implements Serializable {

    private Integer pedidoId;
    private Integer pizzaId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PedidoPizzaId)) return false;
        PedidoPizzaId that = (PedidoPizzaId) o;
        return Objects.equals(pedidoId, that.pedidoId) &&
                Objects.equals(pizzaId, that.pizzaId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pedidoId, pizzaId);
    }

}
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
public class PedidoBebidaId implements Serializable {

    private Integer pedidoId;
    private Integer bebidaId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PedidoBebidaId)) return false;
        PedidoBebidaId that = (PedidoBebidaId) o;
        return Objects.equals(pedidoId, that.pedidoId) &&
                Objects.equals(bebidaId, that.bebidaId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pedidoId, bebidaId);
    }

}
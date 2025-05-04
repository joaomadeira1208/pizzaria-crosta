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
public class PizzaIngredienteId implements Serializable {

    private Integer pizzaId;
    private Integer ingredienteId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PizzaIngredienteId that)) return false;
        return Objects.equals(pizzaId, that.pizzaId) &&
                Objects.equals(ingredienteId, that.ingredienteId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pizzaId, ingredienteId);
    }

}
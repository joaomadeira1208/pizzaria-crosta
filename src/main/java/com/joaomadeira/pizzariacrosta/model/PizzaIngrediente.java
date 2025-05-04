package com.joaomadeira.pizzariacrosta.model;

import com.joaomadeira.pizzariacrosta.model.id.PizzaIngredienteId;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "pizza_ingredientes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PizzaIngrediente {

    @EmbeddedId
    private PizzaIngredienteId id;

    @ManyToOne
    @MapsId("pizzaId")
    @JoinColumn(name = "pizza_id", nullable = false)
    private Pizza pizza;

    @ManyToOne
    @MapsId("ingredienteId")
    @JoinColumn(name = "ingrediente_id", nullable = false)
    private Ingrediente ingrediente;

}
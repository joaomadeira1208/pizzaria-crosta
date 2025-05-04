package com.joaomadeira.pizzariacrosta.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "funcionarios")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Funcionario {

    @Id
    @Column(name = "id")
    private Integer id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private Pessoa pessoa;

    @Column(length = 100, nullable = false)
    private String cargo;

    @Column
    private Integer turno;

    @Column(nullable = false)
    private boolean status;

}

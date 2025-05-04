package com.joaomadeira.pizzariacrosta.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "gerentes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Gerente {

    @Id
    @Column(name = "id")
    private Integer id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private Funcionario funcionario;

    @Column(nullable = false)
    private boolean status;

}
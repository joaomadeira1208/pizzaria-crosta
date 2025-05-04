package com.joaomadeira.pizzariacrosta.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "pedidos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "func_aceitou_id")
    private Funcionario funcionarioAceitou;

    @Column(name = "data_hora", nullable = false)
    private LocalDateTime dataHora;

    @Column(name = "status_entrega", nullable = false, length = 50)
    private String statusEntrega;

    @Column(name = "valor_total", nullable = false, precision = 8, scale = 2)
    private BigDecimal valorTotal;

    @Column(name = "endereco_entrega", nullable = false, length = 100)
    private String enderecoEntrega;

}
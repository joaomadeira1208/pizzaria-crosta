package com.joaomadeira.pizzariacrosta.service;

import com.joaomadeira.pizzariacrosta.dto.PagamentoRequestDTO;
import com.joaomadeira.pizzariacrosta.dto.PagamentoResponseDTO;
import com.joaomadeira.pizzariacrosta.exception.CriacaoPagamentoException;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import org.springframework.stereotype.Service;

@Service
public class PagamentoService {

    public PagamentoResponseDTO criarIntencaoPagamento(PagamentoRequestDTO dto, String moeda) {
        PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
                .setAmount(dto.getAmount())
                .setCurrency(moeda)
                .build();
        try {
            PaymentIntent paymentIntent = PaymentIntent.create(params);
            return PagamentoResponseDTO.builder()
                    .clientSecret(paymentIntent.getClientSecret())
                    .build();
        } catch (StripeException e) {
            throw new CriacaoPagamentoException("Erro ao criar intenção de pagamento: " + e.getMessage());
        }
    }

}

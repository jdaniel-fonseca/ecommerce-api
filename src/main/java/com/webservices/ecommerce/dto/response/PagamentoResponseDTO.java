package com.webservices.ecommerce.dto.response;

import com.webservices.ecommerce.entities.Pagamento;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@NoArgsConstructor
public class PagamentoResponseDTO {

    private Long id;
    private Instant momentoPagamento;
    private BigDecimal valorPago;
    private Integer paymentStatus;
    private Long pedidoId;

    public PagamentoResponseDTO(Pagamento pagamento) {
        this.id = pagamento.getId();
        this.momentoPagamento = pagamento.getMomentoPagamento();
        this.valorPago = pagamento.getValorPago();
        this.paymentStatus = pagamento.getPaymentStatus();

        if (pagamento.getPedido() != null) {
            this.pedidoId = pagamento.getPedido().getId();
        }
    }
}

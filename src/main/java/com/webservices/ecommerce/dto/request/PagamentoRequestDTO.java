package com.webservices.ecommerce.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PagamentoRequestDTO {

    @NotNull(message = "O valor pago é obrigatório")
    @DecimalMin(
            value = "0.01",
            inclusive = true,
            message = "O valor pago deve ser maior que zero"
    )
    private BigDecimal valorPago;

    @NotNull(message = "O ID do pedido é obrigatório")
    private Long pedidoId;
}

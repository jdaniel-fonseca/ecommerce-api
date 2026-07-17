package com.webservices.ecommerce.dto.request;

import com.webservices.ecommerce.enums.OrderStatus;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoRequestDTO {

    @NotNull(message = "O ID do cliente é obrigatório.")
    private Long clienteId;

    @Valid
    @NotEmpty(message = "O pedido deve possuir pelo menos um item.")
    private Set<ItemPedidoRequestDTO> itens;

    @Valid
    private PagamentoRequestDTO pagamento;

    private OrderStatus orderStatus;
}

package com.webservices.ecommerce.dto.response;

import com.webservices.ecommerce.entities.ItemPedido;
import com.webservices.ecommerce.entities.Pedido;
import com.webservices.ecommerce.enums.OrderStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
public class PedidoResponseDTO {

    private Long id;
    private Instant instante;
    private OrderStatus orderStatus;
    private ClienteResponseDTO cliente;
    private Set<ItemPedidoResponseDTO> itens = new HashSet<ItemPedidoResponseDTO>();
    private PagamentoResponseDTO pagamento;
    private BigDecimal total;

    public PedidoResponseDTO(Pedido pedido) {
        this.id = pedido.getId();
        this.instante = pedido.getInstante();
        this.orderStatus = pedido.getOrderStatus();
        this.cliente = new ClienteResponseDTO(pedido.getCliente());

        for (ItemPedido item : pedido.getItens()) {
            this.itens.add(new ItemPedidoResponseDTO(item));
        }

        if (pedido.getPagamento() != null) {
            this.pagamento = new PagamentoResponseDTO(pedido.getPagamento());
        }
        this.total = pedido.getValorTotal();
    }
}

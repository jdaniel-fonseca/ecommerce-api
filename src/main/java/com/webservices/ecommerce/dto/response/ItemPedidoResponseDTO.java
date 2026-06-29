package com.webservices.ecommerce.dto.response;

import com.webservices.ecommerce.entities.ItemPedido;
import com.webservices.ecommerce.entities.pk.ItemPedidoPK;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class ItemPedidoResponseDTO {

    private ItemPedidoPK id = new ItemPedidoPK();
    private Integer quantidade;
    private BigDecimal precoUnitario;

    public ItemPedidoResponseDTO(ItemPedido itemPedido){
        this.id = itemPedido.getId();
        this.quantidade = itemPedido.getQuantidade();
        this.precoUnitario = itemPedido.getPrecoUnitario();
    }
}

package com.webservices.ecommerce.services;

import com.webservices.ecommerce.dto.request.ItemPedidoRequestDTO;
import com.webservices.ecommerce.dto.request.PedidoRequestDTO;
import com.webservices.ecommerce.dto.response.PedidoResponseDTO;
import com.webservices.ecommerce.entities.*;
import com.webservices.ecommerce.enums.PaymentStatus;
import com.webservices.ecommerce.repositories.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ClienteRepository clienteRepository;
    private final PagamentoRepository pagamentoRepository;
    private final ItemPedidoRepository itemPedidoRepository;
    private final ProdutoRepository produtoRepository;

    public PedidoService(PedidoRepository pedidoRepository, ClienteRepository clienteRepository,  PagamentoRepository pagamentoRepository,  ItemPedidoRepository itemPedidoRepository, ProdutoRepository produtoRepository) {
        this.pedidoRepository = pedidoRepository;
        this.clienteRepository = clienteRepository;
        this.pagamentoRepository = pagamentoRepository;
        this.itemPedidoRepository = itemPedidoRepository;
        this.produtoRepository = produtoRepository;
    }

    public List<PedidoResponseDTO> findAll() {
        List<Pedido> pedidos = pedidoRepository.findAll();
        List<PedidoResponseDTO> pedidoResponseDTOS = new ArrayList<>();
        for (Pedido pedido : pedidos) {
            pedidoResponseDTOS.add(responseConverter(pedido));
        }
        return pedidoResponseDTOS;
    }

    private PedidoResponseDTO responseConverter(Pedido pedido) {
        return new PedidoResponseDTO(pedido);
    }

    private Pedido requestConverter(PedidoRequestDTO pedidoRequestDTO) {
        Pedido pedido = new Pedido();
        Cliente cliente = clienteRepository.findById(pedidoRequestDTO.getClienteId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        pedido.setCliente(cliente);

        if (pedidoRequestDTO.getPagamento() != null) {

        }
        Pagamento pagamento = new Pagamento();
        pagamento.setPaymentStatus(PaymentStatus.PENDENTE);
        pagamento.setValorPago(pedidoRequestDTO.getPagamento().getValorPago());
        pagamento.setPedido(pedido);
        pedido.setPagamento(pagamento);
        Set<ItemPedidoRequestDTO> itemPedidoRequestDTOS = pedidoRequestDTO.getItens();
        Set<ItemPedido> itemPedido = new HashSet<>();

        for (ItemPedidoRequestDTO itemPedidoRequestDTO : itemPedidoRequestDTOS) {
            ItemPedido item = new ItemPedido();
            Produto produto = produtoRepository.findById(itemPedidoRequestDTO.getProdutoId())
                    .orElseThrow(() -> new RuntimeException("Produto não encontrado."));
            item.setQuantidade(itemPedidoRequestDTO.getQuantidade());
            item.setPedido(pedido);
            item.setProduto(produto);
            item.setPrecoUnitario(produto.getPreco());
            itemPedido.add(item);
        }
        pedido.getItens().addAll(itemPedido);
        return pedido;
    }


}

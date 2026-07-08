package com.webservices.ecommerce.services;

import com.webservices.ecommerce.dto.request.ItemPedidoRequestDTO;
import com.webservices.ecommerce.dto.request.PedidoRequestDTO;
import com.webservices.ecommerce.dto.response.PedidoResponseDTO;
import com.webservices.ecommerce.entities.*;
import com.webservices.ecommerce.enums.PaymentStatus;
import com.webservices.ecommerce.exceptions.DatabaseException;
import com.webservices.ecommerce.exceptions.ResourceNotFoundException;
import com.webservices.ecommerce.repositories.*;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ClienteRepository clienteRepository;
    private final ProdutoRepository produtoRepository;

    public PedidoService(PedidoRepository pedidoRepository, ClienteRepository clienteRepository,  PagamentoRepository pagamentoRepository,  ItemPedidoRepository itemPedidoRepository, ProdutoRepository produtoRepository) {
        this.pedidoRepository = pedidoRepository;
        this.clienteRepository = clienteRepository;
        this.produtoRepository = produtoRepository;
    }

    public List<PedidoResponseDTO> findAll() {
        List<Pedido> pedidos = pedidoRepository.findAll();
        List<PedidoResponseDTO> pedidoResponseDTOS = new ArrayList<>();
        for (Pedido pedido : pedidos) {
            pedidoResponseDTOS.add(new PedidoResponseDTO(pedido));
        }
        return pedidoResponseDTOS;
    }

    public PedidoResponseDTO findById(Long id) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));
        return new PedidoResponseDTO(pedido);
    }

    @Transactional
    public PedidoResponseDTO create(PedidoRequestDTO pedidoRequestDTO) {
        try {
            Pedido pedido = pedidoRepository.save(requestConverter(pedidoRequestDTO));
            return new PedidoResponseDTO(pedido);
        }
        catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    @Transactional
    public void deleteById(Long id) {
        try {
            Pedido pedido = pedidoRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException(id));

            pedidoRepository.delete(pedido);
        }
        catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    @Transactional
    public PedidoResponseDTO update(PedidoRequestDTO pedidoRequestDTO, Long id) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));

        updateData(pedido, pedidoRequestDTO);

        return new PedidoResponseDTO(pedido);
    }

    private Pedido requestConverter(PedidoRequestDTO pedidoRequestDTO) {
        Pedido pedido = new Pedido();
        Cliente cliente = clienteRepository.findById(pedidoRequestDTO.getClienteId())
                .orElseThrow(() -> new ResourceNotFoundException(pedidoRequestDTO.getClienteId()));
        pedido.setCliente(cliente);

        if (pedidoRequestDTO.getPagamento() != null) {
            Pagamento pagamento = new Pagamento();
            pagamento.setPaymentStatus(PaymentStatus.PENDENTE);
            pagamento.setValorPago(pedidoRequestDTO.getPagamento().getValorPago());
            pagamento.setPedido(pedido);
            pedido.setPagamento(pagamento);
        }
        Set<ItemPedidoRequestDTO> itemPedidoRequestDTOS = pedidoRequestDTO.getItens();
        Set<ItemPedido> itemPedido = new HashSet<>();

        for (ItemPedidoRequestDTO itemPedidoRequestDTO : itemPedidoRequestDTOS) {
            ItemPedido item = new ItemPedido();
            Produto produto = produtoRepository.findById(itemPedidoRequestDTO.getProdutoId())
                    .orElseThrow(() -> new ResourceNotFoundException(itemPedidoRequestDTO.getProdutoId()));
            item.setQuantidade(itemPedidoRequestDTO.getQuantidade());
            item.setPedido(pedido);
            item.setProduto(produto);
            item.setPrecoUnitario(produto.getPreco());
            itemPedido.add(item);
        }
        pedido.getItens().addAll(itemPedido);
        return pedido;
    }

    private void updateData(Pedido pedido, PedidoRequestDTO dto) {
        Cliente cliente = clienteRepository.findById(dto.getClienteId())
                .orElseThrow(() -> new ResourceNotFoundException(dto.getClienteId()));

        pedido.setCliente(cliente);

        if (dto.getPagamento() != null) {
            Pagamento pagamento = pedido.getPagamento();

            if (pagamento == null) {
                pagamento = new Pagamento();
                pagamento.setPedido(pedido);
                pagamento.setPaymentStatus(PaymentStatus.PENDENTE);
            }

            pagamento.setValorPago(dto.getPagamento().getValorPago());
            pedido.setPagamento(pagamento);
        }

        if (dto.getOrderStatus() != null) {
            pedido.setOrderStatus(dto.getOrderStatus());
        }
    }
}

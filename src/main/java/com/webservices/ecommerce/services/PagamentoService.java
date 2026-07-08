package com.webservices.ecommerce.services;

import com.webservices.ecommerce.dto.request.PagamentoRequestDTO;
import com.webservices.ecommerce.dto.response.PagamentoResponseDTO;
import com.webservices.ecommerce.entities.Pagamento;
import com.webservices.ecommerce.entities.Pedido;
import com.webservices.ecommerce.enums.PaymentStatus;
import com.webservices.ecommerce.exceptions.DatabaseException;
import com.webservices.ecommerce.exceptions.ResourceNotFoundException;
import com.webservices.ecommerce.repositories.PagamentoRepository;
import com.webservices.ecommerce.repositories.PedidoRepository;
import jakarta.persistence.*;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PagamentoService {

    private final PagamentoRepository pagamentoRepository;
    private final PedidoRepository pedidoRepository;

    public PagamentoService(PagamentoRepository pagamentoRepository, PedidoRepository pedidoRepository) {
        this.pagamentoRepository = pagamentoRepository;
        this.pedidoRepository = pedidoRepository;
    }

    public List<PagamentoResponseDTO> findAll() {
        List<Pagamento> pagamentos = pagamentoRepository.findAll();
        List<PagamentoResponseDTO> pagamentoResponseDTOS = new ArrayList<>();

        for (Pagamento pagamento : pagamentos) {
            pagamentoResponseDTOS.add(new PagamentoResponseDTO(pagamento));
        }
        return pagamentoResponseDTOS;
    }

    public PagamentoResponseDTO findById(Long id) {
        Pagamento pagamento = pagamentoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));

        return new PagamentoResponseDTO(pagamento);
    }

    public PagamentoResponseDTO update(PagamentoRequestDTO pagamentoRequestDTO, Long id) {
        Pagamento pagamento = pagamentoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));

        updateData(pagamentoRequestDTO, pagamento);
        return new PagamentoResponseDTO(pagamento);
    }

    public PagamentoResponseDTO create(PagamentoRequestDTO pagamentoRequestDTO) {
        Pagamento pagamento = convertRequestDto(pagamentoRequestDTO);
        Pagamento pagamentoSalvo = pagamentoRepository.save(pagamento);
        return new PagamentoResponseDTO(pagamentoSalvo);
    }

    public void deleteById(Long id) {
        try {
            Pagamento pagamento = pagamentoRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException(id));

            pagamentoRepository.delete(pagamento);
        }
        catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    private void updateData(PagamentoRequestDTO pagamentoRequestDTO, Pagamento pagamento) {

        Pedido pedido = pedidoRepository.findById(pagamentoRequestDTO.getPedidoId())
                .orElseThrow(() -> new ResourceNotFoundException(pagamentoRequestDTO.getPedidoId()));
        pagamento.setPedido(pedido);

        pagamento.setValorPago(pagamentoRequestDTO.getValorPago());
    }

    private Pagamento convertRequestDto(PagamentoRequestDTO pagamentoRequestDTO) {
        Pagamento pagamento = new Pagamento();
        Pedido pedido = pedidoRepository.findById(pagamento.getId())
                .orElseThrow(() -> new ResourceNotFoundException(pagamento.getId()));

        pagamento.setPedido(pedido);

        pagamento.setMomentoPagamento(pedido.getPagamento().getMomentoPagamento());
        pagamento.setId(pedido.getPagamento().getId());
        pagamento.setPaymentStatus(
                PaymentStatus.valueOf(pedido.getPagamento().getPaymentStatus())
        );

        pagamento.setValorPago(pedido.getPagamento().getValorPago());

        return pagamento;
    }
}

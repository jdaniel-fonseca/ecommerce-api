package com.webservices.ecommerce.controllers;

import com.webservices.ecommerce.dto.request.PagamentoRequestDTO;
import com.webservices.ecommerce.dto.response.PagamentoResponseDTO;
import com.webservices.ecommerce.services.PagamentoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/pagamentos")
public class PagamentoCotroller {


    public PagamentoService pagamentoService;

    public PagamentoCotroller(PagamentoService pagamentoService) {
        this.pagamentoService = pagamentoService;
    }

    @GetMapping
    public ResponseEntity<Page<PagamentoResponseDTO>> findAll(@PageableDefault(size = 30) Pageable pageable) {
        Page<PagamentoResponseDTO> pagamentoResponseDTOS = pagamentoService.findAll(pageable);
        return ResponseEntity.ok().body(pagamentoResponseDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PagamentoResponseDTO> findById(@PathVariable Long id) {
        PagamentoResponseDTO pagamentoResponseDTO = pagamentoService.findById(id);
        return ResponseEntity.ok().body(pagamentoResponseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PagamentoResponseDTO> update(@PathVariable Long id, @RequestBody PagamentoRequestDTO pagamentoRequestDTO) {
        PagamentoResponseDTO pagamentoResponseDTO = pagamentoService.update(pagamentoRequestDTO, id);
        return ResponseEntity.ok().body(pagamentoResponseDTO);
    }

    @PostMapping
    public ResponseEntity<PagamentoResponseDTO> create(@RequestBody PagamentoRequestDTO pagamentoRequestDTO) {
        PagamentoResponseDTO pagamentoResponseDTO = pagamentoService.create(pagamentoRequestDTO);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(pagamentoResponseDTO.getId())
                .toUri();

        return ResponseEntity.created(uri).body(pagamentoResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        pagamentoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

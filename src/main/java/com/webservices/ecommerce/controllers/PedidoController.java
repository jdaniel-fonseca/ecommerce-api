package com.webservices.ecommerce.controllers;

import com.webservices.ecommerce.dto.request.PedidoRequestDTO;
import com.webservices.ecommerce.dto.response.PedidoResponseDTO;
import com.webservices.ecommerce.services.PedidoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @GetMapping
    public ResponseEntity<Page<PedidoResponseDTO>> findAll(@PageableDefault(size = 30) Pageable pageable) {
        Page<PedidoResponseDTO> pedidoResponseDTOS = pedidoService.findAll(pageable);
        return ResponseEntity.ok().body(pedidoResponseDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoResponseDTO> findById(@PathVariable Long id) {
        PedidoResponseDTO pedidoResponseDTO = pedidoService.findById(id);
        return ResponseEntity.ok().body(pedidoResponseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PedidoResponseDTO> update(@RequestBody PedidoRequestDTO pedidoRequestDTO, @PathVariable Long id) {
        PedidoResponseDTO pedidoResponseDTO = pedidoService.update(pedidoRequestDTO, id);
        return ResponseEntity.ok().body(pedidoResponseDTO);
    }

    @PostMapping
    public ResponseEntity<PedidoResponseDTO> create(@RequestBody PedidoRequestDTO pedidoRequestDTO){
        PedidoResponseDTO pedidoResponseDTO = pedidoService.create(pedidoRequestDTO);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(pedidoResponseDTO.getId())
                .toUri();
        return ResponseEntity.created(uri).body(pedidoResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        pedidoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

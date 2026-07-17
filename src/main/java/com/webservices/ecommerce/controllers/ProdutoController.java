package com.webservices.ecommerce.controllers;

import com.webservices.ecommerce.dto.request.ProdutoRequestDTO;
import com.webservices.ecommerce.dto.response.ProdutoResponseDTO;
import com.webservices.ecommerce.services.ProdutoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping
    public ResponseEntity<List<ProdutoResponseDTO>> findAll() {
        List<ProdutoResponseDTO> produtoResponseDTOS = produtoService.findAll();
        return ResponseEntity.ok().body(produtoResponseDTOS);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ProdutoResponseDTO> findById(@PathVariable Long id) {
        ProdutoResponseDTO produtoResponseDTO = produtoService.findById(id);
        return ResponseEntity.ok().body(produtoResponseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoResponseDTO> update(@RequestBody ProdutoRequestDTO produtoRequestDTO, @PathVariable Long id) {
        ProdutoResponseDTO pedidoResponseDTO = produtoService.update(produtoRequestDTO, id);
        return ResponseEntity.ok().body(pedidoResponseDTO);
    }

    @PostMapping
    public ResponseEntity<ProdutoResponseDTO> create(@RequestBody ProdutoRequestDTO produtoRequestDTO){
        ProdutoResponseDTO produtoResponseDTO = produtoService.create(produtoRequestDTO);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(produtoResponseDTO.getId())
                .toUri();
        return ResponseEntity.created(uri).body(produtoResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        produtoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

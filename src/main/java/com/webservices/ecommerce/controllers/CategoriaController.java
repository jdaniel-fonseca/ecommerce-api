package com.webservices.ecommerce.controllers;

import com.webservices.ecommerce.dto.request.CategoriaRequestDTO;
import com.webservices.ecommerce.dto.response.CategoriaResponseDTO;
import com.webservices.ecommerce.services.CategoriaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping
    public ResponseEntity<List<CategoriaResponseDTO>> findAll() {
        List<CategoriaResponseDTO> categorias = categoriaService.findAll();
        return ResponseEntity.ok().body(categorias);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaResponseDTO> findById(@PathVariable Long id) {
        CategoriaResponseDTO categoriaResponseDTO = categoriaService.findById(id);
        return ResponseEntity.ok().body(categoriaResponseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaResponseDTO> update(@PathVariable Long id, @RequestBody CategoriaRequestDTO categoriaRequestDTO) {
        CategoriaResponseDTO categoria = categoriaService.update(categoriaRequestDTO, id);
        return ResponseEntity.ok().body(categoria);
    }

    @PostMapping
    public ResponseEntity<CategoriaResponseDTO> insert(@RequestBody CategoriaRequestDTO categoriaRequestDTO) {
        CategoriaResponseDTO categoriaResponseDTO = categoriaService.save(categoriaRequestDTO);
        URI uri =  ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(categoriaResponseDTO.getId())
                .toUri();
        return ResponseEntity.created(uri).body(categoriaResponseDTO);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        categoriaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

package com.webservices.ecommerce.controllers;
import com.webservices.ecommerce.dto.request.EnderecoRequestDTO;
import com.webservices.ecommerce.dto.response.EnderecoResponseDTO;
import com.webservices.ecommerce.services.EnderecoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {

    private final EnderecoService enderecoService;

    public EnderecoController(EnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }

    @GetMapping
    public ResponseEntity<Page<EnderecoResponseDTO>> getEnderecos(@PageableDefault(size = 30) Pageable pageable) {
        Page<EnderecoResponseDTO> enderecoResponseDTOS = enderecoService.findAll(pageable);
        return ResponseEntity.ok().body(enderecoResponseDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnderecoResponseDTO> getEnderecoById(@PathVariable Long id) {
        EnderecoResponseDTO enderecoResponseDTO = enderecoService.findById(id);
        return ResponseEntity.ok().body(enderecoResponseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EnderecoResponseDTO> update(@PathVariable Long id, @RequestBody EnderecoRequestDTO enderecoRequestDTO) {
        EnderecoResponseDTO endereco = enderecoService.update(enderecoRequestDTO, id);
        return ResponseEntity.ok().body(endereco);
    }

    @PostMapping
    public ResponseEntity<EnderecoResponseDTO> create(@RequestBody EnderecoRequestDTO enderecoRequestDTO) {
        EnderecoResponseDTO enderecoResponseDTO = enderecoService.create(enderecoRequestDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").
                buildAndExpand(enderecoResponseDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(enderecoResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        enderecoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

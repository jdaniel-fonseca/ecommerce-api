package com.webservices.ecommerce.controllers;
import com.webservices.ecommerce.dto.request.ClienteRequestDTO;
import com.webservices.ecommerce.dto.response.ClienteResponseDTO;
import com.webservices.ecommerce.services.ClienteService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public ResponseEntity<Page<ClienteResponseDTO>> listarClientes(@PageableDefault(size = 30) Pageable pageable){
        Page<ClienteResponseDTO> clientes = clienteService.findAll(pageable);
        return ResponseEntity.ok().body(clientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> getClientesById(@PathVariable Long id){
        ClienteResponseDTO clienteResponseDTO = clienteService.findById(id);
        return ResponseEntity.ok().body(clienteResponseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> update(@PathVariable Long id, @RequestBody ClienteRequestDTO clienteRequestDTO){
        ClienteResponseDTO cliente = clienteService.update(clienteRequestDTO, id);
        return ResponseEntity.ok().body(cliente);
    }

    @PostMapping
    public ResponseEntity<ClienteResponseDTO> create(@RequestBody ClienteRequestDTO clienteRequestDTO){
        ClienteResponseDTO clienteResponseDTO = clienteService.create(clienteRequestDTO);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(clienteResponseDTO.getId())
                .toUri();
        return ResponseEntity.created(uri).body(clienteResponseDTO);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        clienteService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

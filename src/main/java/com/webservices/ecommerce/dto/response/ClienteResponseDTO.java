package com.webservices.ecommerce.dto.response;

import com.webservices.ecommerce.entities.Cliente;
import com.webservices.ecommerce.entities.Endereco;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
public class ClienteResponseDTO {

    private Long id;
    private String name;
    private String email;
    private String telefone;
    private Instant criadoEm;
    private EnderecoResponseDTO endereco;

    public ClienteResponseDTO(Cliente cliente) {
        this.id = cliente.getId();
        this.name = cliente.getName();
        this.email = cliente.getEmail();
        this.telefone = cliente.getTelefone();
        this.criadoEm = cliente.getCriadoEm();
        if (cliente.getEndereco() != null) {
            this.endereco = new EnderecoResponseDTO(cliente.getEndereco());
        }
        else  {
            this.endereco = null;
        }
    }
}

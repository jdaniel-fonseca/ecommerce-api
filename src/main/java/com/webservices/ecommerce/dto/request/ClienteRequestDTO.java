package com.webservices.ecommerce.dto.request;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteRequestDTO {

    private String nome;
    private String email;
    private String telefone;
    private String senha;
    private EnderecoRequestDTO endereco;

}

package com.webservices.ecommerce.dto.request;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteRequestDTO {

    @NotEmpty
    private String nome;
    @NotEmpty
    @Email
    private String email;
    @NotEmpty
    private String telefone;
    @NotEmpty
    @Min(7)
    @Max(100)
    private String senha;
    private EnderecoRequestDTO enderecoRequestDTO;
}

package com.webservices.ecommerce.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnderecoRequestDTO {

    @NotBlank(message = "Informar a rua é obrigatório.")
    private String rua;

    @NotBlank(message = "Informar o número é obrigatório.")
    private String numero;

    @NotBlank(message = "Informar a cidade é obrigatório.")
    private String cidade;

    @NotBlank(message = "Informar o estado é obrigatório.")
    private String estado;

    @NotBlank(message = "Informar o cep é obrigatório.")
    private String cep;

}

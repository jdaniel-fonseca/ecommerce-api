package com.webservices.ecommerce.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnderecoRequestDTO {

    private String rua;
    private String numero;
    private String cidade;
    private String estado;
    private String cep;

}

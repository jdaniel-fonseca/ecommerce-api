package com.webservices.ecommerce.dto.autenticacao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AutenticacaoDTO {

    private  String email;
    private String senha;

}

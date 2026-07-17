package com.webservices.ecommerce.dto.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoriaRequestDTO {

    @NotEmpty(message = "O nome é obrigatório.")
    private String nome;

    @NotEmpty(message = "A descrição é obrigatória.")
    private String descricao;

}

package com.webservices.ecommerce.dto.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoriaRequestDTO {

    @NotEmpty
    private String nome;
    @NotEmpty
    private String descricao;

}

package com.webservices.ecommerce.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class TagRequestDTO {
    @NotBlank(message = "O nome é obrigatório.")
    private String nome;
}

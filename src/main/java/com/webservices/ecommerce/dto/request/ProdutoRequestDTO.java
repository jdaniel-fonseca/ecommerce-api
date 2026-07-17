package com.webservices.ecommerce.dto.request;

import com.webservices.ecommerce.enums.ProductStatus;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoRequestDTO {

    @NotBlank(message = "O nome do produto é obrigatório")
    @Size(max = 120, message = "O nome deve ter no máximo 120 caracteres")
    private String nome;

    @NotBlank(message = "A descrição é obrigatória")
    @Size(max = 500, message = "A descrição deve ter no máximo 500 caracteres")
    private String descricao;

    @NotNull(message = "O preço é obrigatório")
    @DecimalMin(
            value = "0.01",
            message = "O preço deve ser maior que zero"
    )
    private BigDecimal preco;

    @NotNull(message = "O status do produto é obrigatório")
    private ProductStatus status;

    @NotNull(message = "O estoque é obrigatório")
    @Min(value = 0, message = "O estoque não pode ser negativo")
    private Integer estoque;

    @NotNull(message = "O ID da categoria é obrigatório")
    private Long categoriaId;

    @NotEmpty(message = "O produto deve possuir pelo menos uma tag")
    private Set<@NotNull(message = "O ID da tag não pode ser nulo") Long> tagId;
}

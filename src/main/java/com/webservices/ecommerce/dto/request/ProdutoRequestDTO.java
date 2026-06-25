package com.webservices.ecommerce.dto.request;

import com.webservices.ecommerce.enums.ProductStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoRequestDTO {

    private String nome;
    private String descricao;
    private BigDecimal preco;
    private ProductStatus status;
    private Integer estoque;

    private Long categoriaId;
    private Set<Long> tagId;
}

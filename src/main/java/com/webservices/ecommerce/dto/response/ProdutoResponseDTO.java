package com.webservices.ecommerce.dto.response;

import com.webservices.ecommerce.entities.Produto;
import com.webservices.ecommerce.entities.Tag;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
public class ProdutoResponseDTO {

    private Long id;
    private String nome;
    private String descricao;
    private BigDecimal preco;
    private Integer productStatus;
    private Integer estoque;
    private Long categoriaId;
    private Set<TagResponseDTO> tags = new HashSet<TagResponseDTO>();

    public ProdutoResponseDTO(Produto produto) {
        this.id = produto.getId();
        this.nome = produto.getNome();
        this.descricao = produto.getDescricao();
        this.preco = produto.getPreco();
        this.productStatus = produto.getProductStatus();
        this.estoque = produto.getEstoque();
        if (produto.getCategoria() != null) {
            this.categoriaId = produto.getCategoria().getId();
        }
        for (Tag tag : produto.getTags()) {
            this.tags.add(new TagResponseDTO(tag));
        }
    }
}

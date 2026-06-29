package com.webservices.ecommerce.dto.response;

import com.webservices.ecommerce.entities.Categoria;
import com.webservices.ecommerce.entities.Produto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
public class CategoriaResponseDTO {

    private Long id;
    private String nome;
    private String descricao;
    private Set<ProdutoResponseDTO> produtos =  new HashSet<>();

    public CategoriaResponseDTO(Categoria categoria) {
        this.id = categoria.getId();
        this.nome = categoria.getNome();
        this.descricao = categoria.getDescricao();

        if (categoria.getProdutos()!=null) {
            for (Produto produto : categoria.getProdutos()) {
                this.produtos.add(new ProdutoResponseDTO(produto));
            }
        }
    }
}

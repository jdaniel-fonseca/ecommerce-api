package com.webservices.ecommerce.services;

import com.webservices.ecommerce.dto.request.ProdutoRequestDTO;
import com.webservices.ecommerce.dto.response.ProdutoResponseDTO;
import com.webservices.ecommerce.dto.response.TagResponseDTO;
import com.webservices.ecommerce.entities.Categoria;
import com.webservices.ecommerce.entities.Produto;
import com.webservices.ecommerce.entities.Tag;
import com.webservices.ecommerce.repositories.CategoriaRepository;
import com.webservices.ecommerce.repositories.ProdutoRepository;
import com.webservices.ecommerce.repositories.TagRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final TagRepository tagRepository;
    private final CategoriaRepository categoriaRepository;

    public ProdutoService(ProdutoRepository produtoRepository, TagRepository tagRepository, CategoriaRepository categoriaRepository) {
        this.produtoRepository = produtoRepository;
        this.tagRepository = tagRepository;
        this.categoriaRepository = categoriaRepository;
    }

    public List<ProdutoResponseDTO> findAll(){
        List<Produto> produtos = produtoRepository.findAll();
        List<ProdutoResponseDTO> produtosResponseDTO = new ArrayList<>();
        for (Produto produto : produtos) {
            produtosResponseDTO.add(new ProdutoResponseDTO(produto));
        }
        return produtosResponseDTO;
    }

    public ProdutoResponseDTO findById(Long id) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não cadastrado."));
        return new ProdutoResponseDTO(produto);
    }

    public void deleteById(Long id) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado."));
        produtoRepository.delete(produto);
    }

    public ProdutoResponseDTO update(ProdutoRequestDTO produtoRequestDTO, Long id) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado."));
        updateData(produtoRequestDTO, produto);

        return new  ProdutoResponseDTO(produto);
    }

    public ProdutoResponseDTO create(ProdutoRequestDTO produtoRequestDTO) {
        Produto produto = produtoRepository.save(convertRequestDTOtoEntity(produtoRequestDTO));
        return new ProdutoResponseDTO(produto);
    }

    private Produto convertRequestDTOtoEntity(ProdutoRequestDTO produtoRequestDTO) {
        Produto produto = new Produto();
        produto.setDescricao(produtoRequestDTO.getDescricao());
        produto.setNome(produtoRequestDTO.getNome());
        produto.setPreco(produtoRequestDTO.getPreco());
        produto.setProductStatus(produtoRequestDTO.getStatus());
        produto.setEstoque(produtoRequestDTO.getEstoque());

        Set<Tag> tags = new HashSet<>();

        for (Long tagIds : produtoRequestDTO.getTagId()) {
            Tag tag = tagRepository.findById(tagIds)
                    .orElseThrow(() -> new RuntimeException("Tag não encontrada."));
            tags.add(tag);
        }

        produto.getTags().addAll(tags);

        Categoria categoria = categoriaRepository.findById(produtoRequestDTO.getCategoriaId())
                .orElseThrow(() -> new RuntimeException("Categoria nã o encontrada."));

        produto.setCategoria(categoria);

        return produto;
    }

    private void updateData(ProdutoRequestDTO produtoRequestDTO, Produto produto) {
        produto.setDescricao(produtoRequestDTO.getDescricao());
        produto.setNome(produtoRequestDTO.getNome());
        produto.setPreco(produtoRequestDTO.getPreco());
        produto.setEstoque(produtoRequestDTO.getEstoque());
        produto.setProductStatus(produtoRequestDTO.getStatus());

        Set<Tag> tags = new HashSet<>();

        for (Long tagIds : produtoRequestDTO.getTagId()) {
            Tag tag = tagRepository.findById(tagIds)
                    .orElseThrow(() -> new RuntimeException("Tag não encontrada."));
                    tags.add(tag);
        }

        Categoria categoria = categoriaRepository.findById(produtoRequestDTO.getCategoriaId())
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada,"));

        produto.setCategoria(categoria);
    }

}

package com.webservices.ecommerce.services;

import com.webservices.ecommerce.dto.request.ProdutoRequestDTO;
import com.webservices.ecommerce.dto.response.ProdutoResponseDTO;
import com.webservices.ecommerce.dto.response.TagResponseDTO;
import com.webservices.ecommerce.entities.Categoria;
import com.webservices.ecommerce.entities.Produto;
import com.webservices.ecommerce.entities.Tag;
import com.webservices.ecommerce.exceptions.DatabaseException;
import com.webservices.ecommerce.exceptions.ResourceNotFoundException;
import com.webservices.ecommerce.repositories.CategoriaRepository;
import com.webservices.ecommerce.repositories.ProdutoRepository;
import com.webservices.ecommerce.repositories.TagRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
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
                .orElseThrow(() -> new ResourceNotFoundException(id));
        return new ProdutoResponseDTO(produto);
    }

    public void deleteById(Long id) {
        try {
            Produto produto = produtoRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException(id));

            produtoRepository.delete(produto);
        }
        catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public ProdutoResponseDTO update(ProdutoRequestDTO produtoRequestDTO, Long id) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));
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

        if (produtoRequestDTO.getTagId() != null) {

            Set<Tag> tags = new HashSet<>();

            for (Long tagIds : produtoRequestDTO.getTagId()) {
                Tag tag = tagRepository.findById(tagIds)
                        .orElseThrow(() -> new ResourceNotFoundException(tagIds));
                tags.add(tag);
            }

            produto.getTags().addAll(tags);
        }

        Categoria categoria = categoriaRepository.findById(produtoRequestDTO.getCategoriaId())
                .orElseThrow(() -> new ResourceNotFoundException(produtoRequestDTO.getCategoriaId()));

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
                    .orElseThrow(() -> new ResourceNotFoundException(tagIds));
                    tags.add(tag);
        }

        Categoria categoria = categoriaRepository.findById(produtoRequestDTO.getCategoriaId())
                .orElseThrow(() -> new ResourceNotFoundException(produtoRequestDTO.getCategoriaId()));

        produto.setCategoria(categoria);
    }

}

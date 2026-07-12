package com.webservices.ecommerce.services;

import com.webservices.ecommerce.dto.request.CategoriaRequestDTO;
import com.webservices.ecommerce.dto.response.CategoriaResponseDTO;
import com.webservices.ecommerce.entities.Categoria;
import com.webservices.ecommerce.exceptions.DatabaseException;
import com.webservices.ecommerce.exceptions.ResourceNotFoundException;
import com.webservices.ecommerce.repositories.CategoriaRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }


    @Transactional(readOnly = true)
    public Page<CategoriaResponseDTO> findAll(Pageable pageable) {
        return categoriaRepository
                .findAll(pageable)
                .map(this::responseDtoConverter);
    }

    public CategoriaResponseDTO findById(Long id) {
        Categoria categoria = this.categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrado."));

        return new CategoriaResponseDTO(categoria);
    }

    public CategoriaResponseDTO create(CategoriaRequestDTO categoriaRequestDTO) {
        Categoria categoria = requestDtoConverter(categoriaRequestDTO);
        Categoria categoriaSalvo = categoriaRepository.save(categoria);
        return responseDtoConverter(categoriaSalvo);
    }

    public void deleteById(Long id) {
        try {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));
        }
        catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }

    }

    public CategoriaResponseDTO update(CategoriaRequestDTO categoriaRequestDTO, Long id){
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));
        updateData(categoriaRequestDTO, categoria);
        Categoria categoriaSalvo = categoriaRepository.save(categoria);
        return responseDtoConverter(categoriaSalvo);
    }

    private void updateData(CategoriaRequestDTO categoriaRequestDTO, Categoria categoria) {
        categoria.setDescricao(categoriaRequestDTO.getDescricao());
        categoria.setNome(categoriaRequestDTO.getNome());
    }

    private CategoriaResponseDTO responseDtoConverter(Categoria categoria) {
        return new CategoriaResponseDTO(categoria);
    }

    private Categoria requestDtoConverter(CategoriaRequestDTO categoriaRequestDTO) {
        Categoria categoria = new Categoria();
        categoria.setDescricao(categoriaRequestDTO.getDescricao());
        categoria.setNome(categoriaRequestDTO.getNome());
        return categoria;
    }
}

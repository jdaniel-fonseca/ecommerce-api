package com.webservices.ecommerce.services;

import com.webservices.ecommerce.dto.request.CategoriaRequestDTO;
import com.webservices.ecommerce.dto.response.CategoriaResponseDTO;
import com.webservices.ecommerce.entities.Categoria;
import com.webservices.ecommerce.repositories.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public List<CategoriaResponseDTO> findAll() {
        List<Categoria> categorias = categoriaRepository.findAll();
        List<CategoriaResponseDTO> categoriaResponseDTO = new ArrayList<>();

        for(Categoria categoria : categorias) {
            categoriaResponseDTO.add(responseDtoConverter(categoria));
        }
        return categoriaResponseDTO;
    }

    public CategoriaResponseDTO findById(Long id) {
        Categoria categoria = this.categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrado."));

        return new CategoriaResponseDTO(categoria);
    }

    public CategoriaResponseDTO save(CategoriaRequestDTO categoriaRequestDTO) {
        Categoria endereco = requestDtoConverter(categoriaRequestDTO);
        Categoria categoriaSalvo = categoriaRepository.save(endereco);
        return responseDtoConverter(categoriaSalvo);
    }

    public void deleteById(Long id) {
        this.categoriaRepository.deleteById(id);
    }

    public CategoriaResponseDTO update(CategoriaRequestDTO categoriaRequestDTO, Long id){
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Endereço não encontrado"));
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

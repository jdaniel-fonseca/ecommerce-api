package com.webservices.ecommerce.services;
import com.webservices.ecommerce.dto.request.EnderecoRequestDTO;
import com.webservices.ecommerce.dto.response.EnderecoResponseDTO;
import com.webservices.ecommerce.entities.Endereco;
import com.webservices.ecommerce.exceptions.DatabaseException;
import com.webservices.ecommerce.exceptions.ResourceNotFoundException;
import com.webservices.ecommerce.repositories.EnderecoRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;

    public EnderecoService(EnderecoRepository enderecoRepository) {
        this.enderecoRepository = enderecoRepository;
    }

    @Transactional(readOnly = true)
    public Page<EnderecoResponseDTO> findAll(Pageable pageable) {
        return  enderecoRepository.findAll(pageable)
                .map(EnderecoResponseDTO::new);
    }

    @Transactional(readOnly = true)
    public EnderecoResponseDTO findById(Long id) {
        Endereco endereco = this.enderecoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));

        return new EnderecoResponseDTO(endereco);
    }

    @Transactional
    public EnderecoResponseDTO create(EnderecoRequestDTO enderecoRequestDTO) {
        Endereco endereco = requestDtoConverter(enderecoRequestDTO);
        Endereco enderecoSalvo = enderecoRepository.save(endereco);
        return new EnderecoResponseDTO(enderecoSalvo);
    }

    @Transactional
    public void deleteById(Long id) {
        try {
            Endereco endereco = enderecoRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException(id));
            enderecoRepository.delete(endereco);
        }
        catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    @Transactional
    public EnderecoResponseDTO update(EnderecoRequestDTO enderecoRequestDTO, Long id){
        try {
            Endereco endereco = enderecoRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException(id));
            updateData(enderecoRequestDTO, endereco);

            return new EnderecoResponseDTO(endereco);
        }
        catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    private void updateData(EnderecoRequestDTO enderecoRequestDTO, Endereco endereco) {
        endereco.setCep(enderecoRequestDTO.getCep());
        endereco.setNumero(enderecoRequestDTO.getNumero());
        endereco.setRua(enderecoRequestDTO.getRua());
        endereco.setCidade(enderecoRequestDTO.getCidade());
        endereco.setEstado(enderecoRequestDTO.getEstado());
    }

    private Endereco requestDtoConverter(EnderecoRequestDTO enderecoRequestDTO) {
        Endereco endereco = new Endereco();
        endereco.setCep(enderecoRequestDTO.getCep());
        endereco.setRua(enderecoRequestDTO.getRua());
        endereco.setNumero(enderecoRequestDTO.getNumero());
        endereco.setCidade(enderecoRequestDTO.getCidade());
        endereco.setEstado(enderecoRequestDTO.getEstado());
        return endereco;
    }
}

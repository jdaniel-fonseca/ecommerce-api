package com.webservices.ecommerce.services;
import com.webservices.ecommerce.dto.request.EnderecoRequestDTO;
import com.webservices.ecommerce.dto.response.EnderecoResponseDTO;
import com.webservices.ecommerce.entities.Endereco;
import com.webservices.ecommerce.repositories.EnderecoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;

    public EnderecoService(EnderecoRepository enderecoRepository) {
        this.enderecoRepository = enderecoRepository;
    }

    public List<EnderecoResponseDTO> findAll() {
        List<Endereco> enderecos = enderecoRepository.findAll();
        List<EnderecoResponseDTO> enderecosResponseDTO = new ArrayList<>();

        for(Endereco endereco : enderecos) {
            enderecosResponseDTO.add(new EnderecoResponseDTO(endereco));
        }
        return enderecosResponseDTO;
    }

    public EnderecoResponseDTO findById(Long id) {
        Endereco endereco = this.enderecoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Endereço não encontrado."));

        return new EnderecoResponseDTO(endereco);
    }

    public EnderecoResponseDTO save(EnderecoRequestDTO enderecoRequestDTO) {
        Endereco endereco = requestDtoConverter(enderecoRequestDTO);
        Endereco enderecoSalvo = enderecoRepository.save(endereco);
        return new EnderecoResponseDTO(enderecoSalvo);
    }

    public void deleteById(Long id) {
        this.enderecoRepository.deleteById(id);
    }

    public EnderecoResponseDTO update(EnderecoRequestDTO enderecoRequestDTO, Long id){
        Endereco endereco = enderecoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Endereço não encontrado"));
        updateData(enderecoRequestDTO, endereco);

        return new EnderecoResponseDTO(endereco);
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

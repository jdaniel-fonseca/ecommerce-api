package com.webservices.ecommerce.services;

import com.webservices.ecommerce.dto.request.ClienteRequestDTO;
import com.webservices.ecommerce.dto.request.EnderecoRequestDTO;
import com.webservices.ecommerce.dto.response.ClienteResponseDTO;
import com.webservices.ecommerce.entities.Cliente;
import com.webservices.ecommerce.entities.Endereco;
import com.webservices.ecommerce.exceptions.DatabaseException;
import com.webservices.ecommerce.exceptions.ResourceNotFoundException;
import com.webservices.ecommerce.repositories.ClienteRepository;
import com.webservices.ecommerce.repositories.EnderecoRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final EnderecoRepository enderecoRepository;

    public ClienteService(ClienteRepository clienteRepository,  EnderecoRepository enderecoRepository) {
        this.clienteRepository = clienteRepository;
        this.enderecoRepository = enderecoRepository;
    }

    @Transactional(readOnly = true)
    public Page<ClienteResponseDTO> findAll(Pageable pageable) {
        return clienteRepository.findAll(pageable)
                .map(ClienteResponseDTO::new);
    }

    @Transactional(readOnly = true)
    public ClienteResponseDTO findById(Long id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        if (cliente.isPresent()) {
            return new ClienteResponseDTO(cliente.get());
        }
        return null;
    }

    @Transactional
    public ClienteResponseDTO create(ClienteRequestDTO clienteRequestDTO) {
        Cliente cliente = convertClientRequest(clienteRequestDTO);
        Cliente clienteSalvo = clienteRepository.save(cliente);
        return new ClienteResponseDTO(clienteSalvo);
    }

    @Transactional
    public ClienteResponseDTO update(ClienteRequestDTO clienteRequestDTO, Long id) {
        Cliente cliente = clienteRepository.findById(id).orElse(null);
        updateData(clienteRequestDTO, cliente);
        Cliente clienteSalvo = clienteRepository.save(cliente);
        return new ClienteResponseDTO(clienteSalvo);
    }

    @Transactional
    public void deleteById(Long id) {
        try {
            Cliente cliente = clienteRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException(id));

            clienteRepository.delete(cliente);
        }
        catch (DataIntegrityViolationException e){
            throw new DatabaseException(e.getMessage());
        }
    }

    private ClienteResponseDTO convertClientResponse(Cliente cliente) {
        return new ClienteResponseDTO(cliente);
    }

    private Cliente convertClientRequest(ClienteRequestDTO clienteRequestDTO) {
        Cliente cliente = new Cliente();

        cliente.setName(clienteRequestDTO.getNome());
        cliente.setEmail(clienteRequestDTO.getEmail());
        cliente.setTelefone(clienteRequestDTO.getTelefone());
        cliente.setPassword(clienteRequestDTO.getSenha());

        if (clienteRequestDTO.getEnderecoRequestDTO() != null) {
            Endereco endereco = convertEnderecoRequest(
                    clienteRequestDTO.getEnderecoRequestDTO()
            );

            cliente.setEndereco(endereco);
            endereco.setCliente(cliente);
        }

        return cliente;
    }

    private Endereco convertEnderecoRequest(EnderecoRequestDTO enderecoRequestDTO) {
        Endereco endereco = new Endereco();
        endereco.setCep(enderecoRequestDTO.getCep());
        endereco.setCidade(enderecoRequestDTO.getCidade());
        endereco.setEstado(enderecoRequestDTO.getEstado());
        endereco.setRua(enderecoRequestDTO.getRua());
        endereco.setNumero(enderecoRequestDTO.getNumero());
        return endereco;
    }

    private void updateData(ClienteRequestDTO clienteRequestDTO, Cliente cliente) {
        cliente.setName(clienteRequestDTO.getNome());
        cliente.setEmail(clienteRequestDTO.getEmail());
        cliente.setTelefone(clienteRequestDTO.getTelefone());
        cliente.setEndereco(convertEnderecoRequest(clienteRequestDTO.getEnderecoRequestDTO()));
    }
}

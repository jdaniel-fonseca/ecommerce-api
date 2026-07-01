package com.webservices.ecommerce.services;

import com.webservices.ecommerce.dto.request.ClienteRequestDTO;
import com.webservices.ecommerce.dto.request.EnderecoRequestDTO;
import com.webservices.ecommerce.dto.response.ClienteResponseDTO;
import com.webservices.ecommerce.entities.Cliente;
import com.webservices.ecommerce.entities.Endereco;
import com.webservices.ecommerce.repositories.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public List<ClienteResponseDTO> findAll() {
        List<Cliente> clientes = clienteRepository.findAll();
        List<ClienteResponseDTO> clienteResponseDTOS = new ArrayList<>();

        for (Cliente cliente : clientes) {
            clienteResponseDTOS.add(new ClienteResponseDTO(cliente));
        }
        return clienteResponseDTOS;
    }

    public ClienteResponseDTO findClienteById(Long id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        if (cliente.isPresent()) {
            return new ClienteResponseDTO(cliente.get());
        }
        return null;
    }

    public ClienteResponseDTO createCliente(ClienteRequestDTO clienteRequestDTO) {
        Cliente cliente = convertClientRequest(clienteRequestDTO);
        Cliente clienteSalvo = clienteRepository.save(cliente);
        return new ClienteResponseDTO(clienteSalvo);
    }

    public ClienteResponseDTO updateCliente(ClienteRequestDTO clienteRequestDTO, Long id) {
        Cliente cliente = clienteRepository.findById(id).orElse(null);
        updateData(clienteRequestDTO, cliente);
        Cliente clienteSalvo = clienteRepository.save(cliente);
        return new ClienteResponseDTO(clienteSalvo);
    }

    public void deleteClienteById(Long id) {
        clienteRepository.deleteById(id);
    }

    private ClienteResponseDTO convertClientResponse(Cliente cliente) {
        return new ClienteResponseDTO(cliente);
    }

    private Cliente convertClientRequest(ClienteRequestDTO clienteRequestDTO) {
        Cliente cliente = new Cliente();
        cliente.setName(clienteRequestDTO.getNome());
        cliente.setEmail(clienteRequestDTO.getEmail());
        cliente.setEndereco(convertEnderecoRequest(clienteRequestDTO.getEnderecoRequestDTO()));
        cliente.setTelefone(clienteRequestDTO.getTelefone());
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

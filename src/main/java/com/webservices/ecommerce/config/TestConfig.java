package com.webservices.ecommerce.config;

import com.webservices.ecommerce.dto.request.ClienteRequestDTO;
import com.webservices.ecommerce.dto.request.EnderecoRequestDTO;
import com.webservices.ecommerce.entities.Cliente;
import com.webservices.ecommerce.services.ClienteService;
import com.webservices.ecommerce.services.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    @Autowired
    private EnderecoService enderecoService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        EnderecoRequestDTO enderecoRequestDTO = new EnderecoRequestDTO();
        enderecoRequestDTO.setCidade("Barueri");
        enderecoRequestDTO.setEstado("São Paulo");
        enderecoRequestDTO.setCep("94566-192");
        enderecoRequestDTO.setNumero("356");
        enderecoRequestDTO.setRua("Rua Cristal");

        ClienteRequestDTO clienteRequestDTO = new ClienteRequestDTO();
        clienteRequestDTO.setEmail("jose@gmail.com");
        clienteRequestDTO.setNome("Jose Daniel");
        clienteRequestDTO.setSenha("senha@123");
        clienteRequestDTO.setTelefone("11955949439");
        clienteRequestDTO.setEnderecoRequestDTO(enderecoRequestDTO);

        clienteService.create(clienteRequestDTO);
    }
}

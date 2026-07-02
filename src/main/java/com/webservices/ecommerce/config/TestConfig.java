package com.webservices.ecommerce.config;

import com.webservices.ecommerce.dto.request.EnderecoRequestDTO;
import com.webservices.ecommerce.services.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    @Autowired
    private EnderecoService enderecoService;

    @Override
    public void run(String... args) throws Exception {
        EnderecoRequestDTO enderecoRequestDTO = new EnderecoRequestDTO();
        enderecoRequestDTO.setCidade("Barueri");
        enderecoRequestDTO.setEstado("São Paulo");
        enderecoRequestDTO.setCep("94566-192");
        enderecoRequestDTO.setNumero("356");
        enderecoRequestDTO.setRua("Rua Cristal");
        enderecoService.save(enderecoRequestDTO);

        EnderecoRequestDTO enderecoRequestDTO2 = new EnderecoRequestDTO();
        enderecoRequestDTO2.setCidade("Osasco");
        enderecoRequestDTO2.setEstado("São Paulo");
        enderecoRequestDTO2.setCep("64566-192");
        enderecoRequestDTO2.setNumero("226");
        enderecoRequestDTO2.setRua("Rua Getúlio Vargas");
        enderecoService.save(enderecoRequestDTO2);
    }
}

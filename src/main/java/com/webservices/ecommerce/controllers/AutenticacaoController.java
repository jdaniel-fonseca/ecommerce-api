package com.webservices.ecommerce.controllers;

import com.webservices.ecommerce.config.security.TokenJson;
import com.webservices.ecommerce.config.security.TokenService;
import com.webservices.ecommerce.dto.autenticacao.AutenticacaoDTO;
import com.webservices.ecommerce.entities.Cliente;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {

    @Autowired
    TokenService tokenService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping
    public ResponseEntity efetuarLogin(@RequestBody @Valid AutenticacaoDTO autenticacaoDTO) {
        var token = new UsernamePasswordAuthenticationToken(autenticacaoDTO.getEmail(), autenticacaoDTO.getSenha());
        var auth = authenticationManager.authenticate(token);

        Cliente cliente = (Cliente) auth.getPrincipal();

        var tokenJWT = tokenService.generateToken(cliente.getEmail());

        return ResponseEntity.ok(new TokenJson(tokenJWT));
    }

}

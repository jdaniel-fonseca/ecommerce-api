package com.webservices.ecommerce.config.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.webservices.ecommerce.dto.autenticacao.AutenticacaoDTO;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;

@Service
public class TokenService {

    public String generateToken(String email) {

        try {
            var algorithm = Algorithm.HMAC256("1234567");
            return JWT.create()
                    .withIssuer("auth0")
                    .withSubject(email)
                    .withExpiresAt(Date.from(Instant.now().plusSeconds(7200)))
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            throw new RuntimeException("Erro ao gerar o token JWT", exception);
        }
    }
}

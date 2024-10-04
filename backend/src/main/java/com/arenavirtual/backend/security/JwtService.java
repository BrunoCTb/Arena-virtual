package com.arenavirtual.backend.security;

import com.arenavirtual.backend.model.entity.user.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class JwtService {

    @Value("${api.security.token.secret}")
    private String secretToken;

    public String generateToken(User user) {
        try {
            // criar um algoritmo para gerar "algo a mais" para dificultar a quebra do hash da senha
            Algorithm algorithm = Algorithm.HMAC384(secretToken);

            return JWT.create()
                    .withIssuer("backend-app") // algo como o nome de quem cria o token (um nome de identificacao)
                    .withSubject(user.getEmail()) // user que ira receber o token
                    .withExpiresAt(generateExpirationTokenTime())
                    .sign(algorithm);
        } catch (JWTCreationException e) {
            throw new RuntimeException("Erro ao gerar o token!");
        }
    }

    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC384(secretToken);
            return JWT.require(algorithm)
                    .withIssuer("backend-app")
                    .build() // criar novamente
                    .verify(token) // descriptografar o token
                    .getSubject(); // pega o subject que foi passado ao gerar o token
        } catch (JWTVerificationException e) {
            System.out.println("exception: " +e);
            return "";
        }
    }

    public Instant generateExpirationTokenTime() {
//        return LocalDateTime.now().plusHours(1).toInstant(ZoneOffset.of("-03:00"));
        return LocalDateTime.now().plusHours(1).toInstant(ZoneOffset.of("-03:00"));
    }

}

package br.com.fincofre.api.services;

import br.com.fincofre.api.models.entities.user.User;
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
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    // Esse método gera o token
    public String generateToken(User user) {
        var algorithm = Algorithm.HMAC256(secret);

        try {
            return JWT.create()
                    .withIssuer("API FinCofre") // Nome da aplicação que gera o token
                    .withSubject(user.getLogin()) // Pega o usuário que é proprietário do token
                    .withExpiresAt(expirationDate()) // Validade do token
                    .sign(algorithm);
        } catch (JWTCreationException ex) {
            throw new RuntimeException("Erro ao gerar token JWT", ex);
        }
    }

    // Esse método pega o token, verifica se está correto e devolve o usuário correspondente
    public String getSubject(String token) {
        var algorithm = Algorithm.HMAC256(secret);

        try {
            return JWT.require(algorithm)
                    .withIssuer("API FinCofre")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Token JWT inválido ou expirado");
        }
    }

    // Esse método recupera o token do cabeçalho
    public String recoverToken(String token) {
        if (token != null) return token.replace("Bearer ", "");

        return null;
    }

    private Instant expirationDate() {
        return LocalDateTime.now().plusHours(24).toInstant(ZoneOffset.of("-03:00"));
    }

}

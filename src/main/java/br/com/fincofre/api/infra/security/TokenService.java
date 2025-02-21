package br.com.fincofre.api.infra.security;

import br.com.fincofre.api.domain.user.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

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

    private Instant expirationDate() {
        return LocalDateTime.now().plusHours(24).toInstant(ZoneOffset.of("-03:00"));
    }

}

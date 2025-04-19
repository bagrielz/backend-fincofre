package br.com.fincofre.api.infra.security;

import br.com.fincofre.api.exceptions.JwtTokenException;
import br.com.fincofre.api.exceptions.ValidationException;
import br.com.fincofre.api.models.dtos.ErrorDTO;
import br.com.fincofre.api.repositories.UserRepository;
import br.com.fincofre.api.services.TokenService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    private final TokenService tokenService;
    private final UserRepository repository;

    public SecurityFilter(TokenService tokenService, UserRepository repository) {
        this.tokenService = tokenService;
        this.repository = repository;
    }

    // Esse método filtra as chamadas à API
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            // Recupera o token da requisição
            var authHeader = request.getHeader("Authorization");
            var token = tokenService.recoverToken(authHeader);

            if (token != null) {
                var subject = tokenService.getSubject(token);
                var user = repository.findByLogin(subject);

                if (user == null) throw new JwtTokenException("Token JWT inválido ou expirado");

                var auth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

                SecurityContextHolder.getContext().setAuthentication(auth);
            }

            filterChain.doFilter(request, response);

        } catch (JwtTokenException ex) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");

            var err = new ErrorDTO(HttpServletResponse.SC_UNAUTHORIZED, ex.getMessage());
            var mapper = new ObjectMapper();
            var json = mapper.writeValueAsString(err);

            response.getWriter().write(json);
        }
    }

}

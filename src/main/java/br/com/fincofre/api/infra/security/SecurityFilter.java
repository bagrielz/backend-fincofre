package br.com.fincofre.api.infra.security;

import br.com.fincofre.api.domain.user.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
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
        // Recupera o token da requisição
        var authHeader = request.getHeader("Authorization");
        var token = tokenService.recoverToken(authHeader);

        if (token != null) {
            var subject = tokenService.getSubject(token);
            var user = repository.findByLogin(subject);
            var auth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(auth);
        }

        filterChain.doFilter(request, response);
    }

}

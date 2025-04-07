package br.com.fincofre.api.controllers;

import br.com.fincofre.api.models.dtos.AuthDTO;
import br.com.fincofre.api.models.entities.user.User;
import br.com.fincofre.api.infra.security.TokenDTO;
import br.com.fincofre.api.services.TokenService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager manager;
    private final TokenService tokenService;

    public AuthController(AuthenticationManager manager, TokenService tokenService) {
        this.manager = manager;
        this.tokenService = tokenService;
    }

    @PostMapping
    public ResponseEntity login(@RequestBody @Valid AuthDTO data) {
        // O método authenticate() do AuthenticationManager espera um objeto do tipo Authentication e UsernamePasswordAuthenticationToken é uma implementação concreta desse contrato
        var authToken = new UsernamePasswordAuthenticationToken(data.login(), data.password()); // Cria o DTO UsernamePasswordAuthenticationToken e passa as informações de acesso
        var auth = manager.authenticate(authToken); // Dispara o processo de autenticação
        var token = tokenService.generateToken((User) auth.getPrincipal()); // Gera o token

        return ResponseEntity.ok(new TokenDTO(token));
    }

}

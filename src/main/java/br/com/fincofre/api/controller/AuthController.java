package br.com.fincofre.api.controller;

import br.com.fincofre.api.domain.user.AuthDTO;
import br.com.fincofre.api.domain.user.User;
import br.com.fincofre.api.infra.security.TokenDTO;
import br.com.fincofre.api.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity login(@RequestBody @Valid AuthDTO data) {
        // O método authenticate() do AuthenticationManager espera um objeto do tipo Authentication e UsernamePasswordAuthenticationToken é uma implementação concreta desse contrato
        var authToken = new UsernamePasswordAuthenticationToken(data.login(), data.password()); // Cria o DTO UsernamePasswordAuthenticationToken e passa as informações de acesso
        var auth = manager.authenticate(authToken); // Dispara o processo de autenticação
        var token = tokenService.generateToken((User) auth.getPrincipal()); // Gera o token

        return ResponseEntity.ok(new TokenDTO(token));
    }

}

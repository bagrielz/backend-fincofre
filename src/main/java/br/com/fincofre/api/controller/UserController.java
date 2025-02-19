package br.com.fincofre.api.controller;

import br.com.fincofre.api.domain.user.User;
import br.com.fincofre.api.domain.user.UserDetailsDTO;
import br.com.fincofre.api.domain.user.UserRepository;
import br.com.fincofre.api.domain.user.UserResponseDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/usuarios")
public class UserController {

    @Autowired
    private UserRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity register(@RequestBody @Valid UserResponseDTO response, UriComponentsBuilder uriBuilder) {
        var user = new User(response);
        repository.save(user);

        // Retorna a URI com as informações do novo usuário cadastrado
        var uri = uriBuilder.path("/cadastrar/{id}").buildAndExpand(user.getId()).toUri();

        return ResponseEntity.created(uri).body(new UserDetailsDTO(user));
    }

}

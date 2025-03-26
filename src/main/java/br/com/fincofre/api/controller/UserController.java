package br.com.fincofre.api.controller;

import br.com.fincofre.api.domain.user.*;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/usuarios")
public class UserController {

    private final UserRepository repository;

    public UserController(UserRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    @Transactional
    public ResponseEntity register(@RequestBody @Valid UserResponseDTO response, UriComponentsBuilder uriBuilder) {
        var user = new User(response);
        repository.save(user);

        // Retorna a URI com as informações do novo usuário cadastrado
        var uri = uriBuilder.path("/cadastrar/{id}").buildAndExpand(user.getId()).toUri();

        return ResponseEntity.created(uri).body(new UserDetailsDTO(user));
    }

    @PutMapping
    @Transactional
    public ResponseEntity update(@RequestBody @Valid UserUpdateDTO response) {
        var user = repository.getReferenceById(response.id()); // Carrega o objeto do banco de dados pegando o id da resposta
        user.updateData(response); // Atualiza os dados

        return ResponseEntity.ok(new UserDetailsDTO(user)); // Retorna o corpo do objeto para o front
    }

}

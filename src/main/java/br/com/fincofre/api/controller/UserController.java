package br.com.fincofre.api.controller;

import br.com.fincofre.api.domain.user.*;
import br.com.fincofre.api.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UserController {

    private UserRepository repository;
    private UserService userService;

    public UserController(UserRepository repository, UserService userService) {
        this.repository = repository;
        this.userService = userService;

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

    @GetMapping
    public ResponseEntity<List<UserListingDTO>> list() {
        var userList = repository.findAll().stream().map(UserListingDTO::new).toList();

        return ResponseEntity.ok(userList);
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity delete(@RequestHeader("Authorization") String auth) {
        System.out.println("Autorização de acesso: " + auth);
        userService.checkAuth(auth);

        return ResponseEntity.noContent().build();
    }

}

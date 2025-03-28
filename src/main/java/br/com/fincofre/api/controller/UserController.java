package br.com.fincofre.api.controller;

import br.com.fincofre.api.domain.user.*;
import br.com.fincofre.api.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UserController {

    private final UserRepository repository;
    private final UserService userService;

    public UserController(UserRepository repository, UserService userService) {
        this.repository = repository;
        this.userService = userService;

    }

    @PostMapping
    public ResponseEntity<UserDetailsDTO> register(@RequestBody @Valid UserResponseDTO response) {
        var user = userService.createUser(response);

        return ResponseEntity.ok().body(user);
    }

    @PutMapping
    public ResponseEntity<UserDetailsDTO> update(@RequestHeader("Authorization") String auth, @RequestBody @Valid UserUpdateDTO response) {
        System.out.println("Resposta do body: " + response);
        var user = userService.updateUser(auth, response);

        return ResponseEntity.ok().body(user); // Retorna o corpo do objeto para o front
    }

    @GetMapping
    public ResponseEntity<List<UserListingDTO>> list() {
        var userList = repository.findAll().stream().map(UserListingDTO::new).toList();

        return ResponseEntity.ok(userList);
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@RequestHeader("Authorization") String auth) {
        userService.deleteUser(auth);

        return ResponseEntity.noContent().build();
    }

}

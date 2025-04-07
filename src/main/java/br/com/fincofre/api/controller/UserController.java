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

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<UserDetailsDTO> register(@RequestBody @Valid UserResponseDTO response) {
        var user = userService.createUser(response);

        return ResponseEntity.ok().body(user);
    }

    @PutMapping("/atualizar")
    public ResponseEntity<UserDetailsDTO> update(@RequestHeader("Authorization") String auth, @RequestBody @Valid UserUpdateDTO response) {
        var user = userService.updateUser(auth, response);

        return ResponseEntity.ok().body(user); // Retorna o corpo do objeto para o front
    }

    @GetMapping("/listar")
    public ResponseEntity<UserDetailsDTO> list(@RequestHeader("Authorization") String auth, @RequestBody @Valid UserDetailsDTO response) {
        var userDetails = userService.listUserInformation(auth, response);

        return ResponseEntity.ok(userDetails);
    }

    @DeleteMapping("/excluir")
    public ResponseEntity<Void> delete(@RequestHeader("Authorization") String auth) {
        userService.deleteUser(auth);

        return ResponseEntity.noContent().build();
    }

}

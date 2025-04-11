package br.com.fincofre.api.controllers;

import br.com.fincofre.api.models.dtos.UserDetailsDTO;
import br.com.fincofre.api.models.dtos.UserResponseDTO;
import br.com.fincofre.api.models.dtos.UserUpdateDTO;
import br.com.fincofre.api.models.dtos.UserUpdateDetailsDTO;
import br.com.fincofre.api.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<UserUpdateDetailsDTO> update(@RequestBody @Valid UserUpdateDTO response) {
        var user = userService.updateUser(response);

        return ResponseEntity.ok().body(user); // Retorna o corpo do objeto para o front
    }

    @GetMapping("/detalhar")
    public ResponseEntity<UserDetailsDTO> list(@RequestHeader("Authorization") String auth, @RequestBody @Valid UserDetailsDTO response) {
        var userDetails = userService.getUserInformation(auth, response);

        return ResponseEntity.ok(userDetails);
    }

    @DeleteMapping("/excluir")
    public ResponseEntity<Void> delete(@RequestHeader("Authorization") String auth) {
        userService.deleteUser(auth);

        return ResponseEntity.noContent().build();
    }

}

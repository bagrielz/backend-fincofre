package br.com.fincofre.api.controllers;

import br.com.fincofre.api.models.dtos.UserDetailsDTO;
import br.com.fincofre.api.models.dtos.UserResponseDTO;
import br.com.fincofre.api.models.dtos.UserUpdateDTO;
import br.com.fincofre.api.models.dtos.UserUpdateDetailsDTO;
import br.com.fincofre.api.services.AuthenticatedUserService;
import br.com.fincofre.api.services.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
// Comentário teste
@RestController
@RequestMapping("/usuarios")
public class UserController {

    private final UserService userService;
    private final AuthenticatedUserService authenticatedUserService;

    public UserController(UserService userService, AuthenticatedUserService authenticatedUserService) {
        this.userService = userService;
        this.authenticatedUserService = authenticatedUserService;
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<UserDetailsDTO> register(@RequestBody @Valid UserResponseDTO response) {
        var user = userService.createUser(response);

        return ResponseEntity.ok().body(user);
    }

    @PutMapping("/atualizar")
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity<UserUpdateDetailsDTO> update(@RequestBody @Valid UserUpdateDTO response) {
        var subject = authenticatedUserService.getUsername();
        var user = userService.updateUser(subject, response);

        return ResponseEntity.ok().body(user); // Retorna o corpo do objeto para o front
    }

    @GetMapping("/detalhar")
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity<UserDetailsDTO> detail() {
        var subject = authenticatedUserService.getUsername();
        var userDetails = userService.getUserInformation(subject);

        return ResponseEntity.ok(userDetails);
    }

    @DeleteMapping("/excluir")
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity<Void> delete() {
        var subject = authenticatedUserService.getUsername();
        userService.deleteUser(subject);

        return ResponseEntity.noContent().build();
    }

}

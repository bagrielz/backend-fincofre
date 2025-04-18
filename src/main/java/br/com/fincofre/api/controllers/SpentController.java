package br.com.fincofre.api.controllers;

import br.com.fincofre.api.services.AuthenticatedUserService;
import br.com.fincofre.api.models.dtos.*;
import br.com.fincofre.api.services.SpentService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/gastos")
@SecurityRequirement(name = "bearer-key")
public class SpentController {

    private final SpentService spentService;
    private final AuthenticatedUserService authenticatedUserService;

    public SpentController(SpentService spentService, AuthenticatedUserService authenticatedUserService) {
        this.spentService = spentService;
        this.authenticatedUserService = authenticatedUserService;
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<SpentDetailsDTO> register(@RequestBody @Valid SpentResponseDTO response) {
        var subject = authenticatedUserService.getUsername();
        var spent = spentService.createSpent(subject, response);

        return ResponseEntity.ok().body(spent);
    }

    @GetMapping("/listar")
    public ResponseEntity<SpentsListWithTotalDTO> list() {
        var subject = authenticatedUserService.getUsername();
        var spentsList = spentService.getSpentsByUser(subject);

        return ResponseEntity.ok(spentsList);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<SpentDetailsDTO> update(@PathVariable Long id, @RequestBody @Valid SpentUpdateDTO response) {
        var subject = authenticatedUserService.getUsername();
        var spent = spentService.updateSpent(id, response, subject);

        return ResponseEntity.ok(new SpentDetailsDTO(spent));
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        var subject = authenticatedUserService.getUsername();
        spentService.deleteSpent(subject, id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/detalhar/{id}")
    public ResponseEntity<SpentDetailsDTO> detail(@PathVariable Long id) {
        var subject = authenticatedUserService.getUsername();
        var spent = spentService.detailSpent(subject, id);

        return ResponseEntity.ok(new SpentDetailsDTO(spent));
    }

}

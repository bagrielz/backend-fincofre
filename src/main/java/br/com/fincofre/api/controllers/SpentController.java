package br.com.fincofre.api.controllers;

import br.com.fincofre.api.models.dtos.*;
import br.com.fincofre.api.services.SpentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/gastos")
public class SpentController {

    private final SpentService spentService;

    public SpentController(SpentService spentService) {
        this.spentService = spentService;
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<SpentDetailsDTO> register(@RequestHeader("Authorization") String auth, @RequestBody @Valid SpentResponseDTO response) {
        var spent = spentService.createSpent(auth, response);

        return ResponseEntity.ok().body(spent);
    }

    @GetMapping("/listar")
    public ResponseEntity<SpentsListWithTotalDTO> list(@RequestHeader("Authorization") String auth) {
        var spentsList = spentService.getSpentsByUser(auth);

        return ResponseEntity.ok(spentsList);
    }

    @PutMapping("/atualizar")
    public ResponseEntity<SpentDetailsDTO> update(@RequestHeader("Authorization") String auth, @RequestBody @Valid SpentUpdateDTO response) {
        var spent = spentService.updateSpent(auth, response);

        return ResponseEntity.ok(new SpentDetailsDTO(spent));
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<Void> delete(@RequestHeader("Authorization") String auth, @PathVariable Long id) {
        spentService.deleteSpent(auth, id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/detalhar/{id}")
    public ResponseEntity<SpentDetailsDTO> detail(@RequestHeader("Authorization") String auth, @PathVariable Long id) {
        var spent = spentService.detailSpent(auth, id);

        return ResponseEntity.ok(new SpentDetailsDTO(spent));
    }

}

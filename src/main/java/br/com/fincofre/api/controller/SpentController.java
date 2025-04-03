package br.com.fincofre.api.controller;

import br.com.fincofre.api.domain.spent.*;
import br.com.fincofre.api.service.SpentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/listar/{userId}")
    public ResponseEntity<List<SpentListingDTO>> list(@PathVariable Long userId, @RequestHeader("Authorization") String auth) {
        var spentsList = spentService.getSpentsByUserId(userId, auth);

        return ResponseEntity.ok(spentsList);
    }

    @PutMapping("/atualizar")
    public ResponseEntity<SpentDetailsDTO> update(@RequestHeader("Authorization") String auth, @RequestBody @Valid SpentUpdateDTO response) {
        var spent = spentService.updateSpent(auth, response);

        return ResponseEntity.ok(new SpentDetailsDTO(spent));
    }
//
//    @DeleteMapping("/{id}")
//    @Transactional
//    public ResponseEntity delete(@PathVariable Long id) {
//        repository.deleteById(id);
//
//        return ResponseEntity.noContent().build();
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity detail(@PathVariable Long id) {
//        var spent = repository.getReferenceById(id);
//
//        return ResponseEntity.ok(new SpentDetailsDTO(spent));
//    }

}

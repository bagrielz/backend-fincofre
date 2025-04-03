package br.com.fincofre.api.controller;

import br.com.fincofre.api.domain.spent.*;
import br.com.fincofre.api.service.SpentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/gastos")
public class SpentController {

    private final SpentService spentService;

    public SpentController(SpentService spentService) {
        this.spentService = spentService;
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<SpentDetailsDTO> register(@RequestBody @Valid SpentResponseDTO response) {
        var spent = spentService.createSpent(response);

        return ResponseEntity.ok().body(spent);
    }

    @GetMapping("/listar/{userId}")
    public ResponseEntity<List<SpentListingDTO>> list(@PathVariable Long userId) {
        System.out.println("ID do usuário: " + userId);
        var spentsList = spentService.getSpentsByUserId(userId);

        return ResponseEntity.ok(spentsList);
    }
//
//    @PutMapping
//    @Transactional
//    public ResponseEntity update(@RequestBody @Valid SpentUpdateDTO response) {
//        var spent = repository.getReferenceById(response.id());
//        spent.updateData(response);
//
//        return ResponseEntity.ok(new SpentDetailsDTO(spent));
//    }
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

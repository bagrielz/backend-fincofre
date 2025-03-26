package br.com.fincofre.api.controller;

import br.com.fincofre.api.domain.spent.*;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/gastos")
public class SpentController {

    private final SpentRepository repository;

    public SpentController(SpentRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    @Transactional
    public ResponseEntity register(@RequestBody @Valid SpentResponseDTO response, UriComponentsBuilder uriBuilder) {
        var spent = new Spent(response);
        repository.save(spent);

        var uri = uriBuilder.path("/gastos/{id}").buildAndExpand(spent.getId()).toUri();

        return ResponseEntity.created(uri).body(new SpentDetailsDTO(spent));
    }

    @GetMapping
    public ResponseEntity<List<SpentListingDTO>> list() {
        // Converte uma lista de gastos para uma lista de listagem de gastos (SpentListingDTO)
        var spentsList = repository.findAll().stream().map(SpentListingDTO::new).toList();

        return ResponseEntity.ok(spentsList);
    }

    @PutMapping
    @Transactional
    public ResponseEntity update(@RequestBody @Valid SpentUpdateDTO response) {
        var spent = repository.getReferenceById(response.id());
        spent.updateData(response);

        return ResponseEntity.ok(new SpentDetailsDTO(spent));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable Long id) {
        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity detail(@PathVariable Long id) {
        var spent = repository.getReferenceById(id);

        return ResponseEntity.ok(new SpentDetailsDTO(spent));
    }

}

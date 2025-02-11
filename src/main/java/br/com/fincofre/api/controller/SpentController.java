package br.com.fincofre.api.controller;

import br.com.fincofre.api.spent.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/gastos")
public class SpentController {

    @Autowired // Injeta automaticamente uma instância de SpentRepository gerenciada pelo Spring
    private SpentRepository repository;

    @PostMapping
    @Transactional // Garante que todas as operações sejam feitas dentro de uma única transação
    public ResponseEntity register(@RequestBody @Valid SpentResponseDTO response, UriComponentsBuilder uriBuilder) {
        // O @RequestBody pega as informações do corpo da requisição
        // A validação será feita automaticamente antes do método ser executado
        // Se algum campo do DTO não for válido, uma exceção será lançada
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
    public ResponseEntity delete(@PathVariable Long id) { // Com essa anotação, o Spring entende que o id passado na url é o parâmetro do método
        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity detail(@PathVariable Long id) {
        var spent = repository.getReferenceById(id);

        return ResponseEntity.ok(new SpentDetailsDTO(spent));
    }

}

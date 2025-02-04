package br.com.fincofre.api.controller;

import br.com.fincofre.api.spent.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/gastos")
public class SpentController {

    @Autowired // Injeta automaticamente uma instância de SpentRepository gerenciada pelo Spring
    private SpentRepository repository;

    @PostMapping
    @Transactional // Garante que todas as operações sejam feitas dentro de uma única transação
    public void register(@RequestBody @Valid SpentResponseDTO response) {
        // O @RequestBody pega as informações do corpo da requisição
        // A validação será feita automaticamente antes do método ser executado
        // Se algum campo do DTO não for válido, uma exceção será lançada
        repository.save(new Spent(response));
    }

    @GetMapping
    public List<SpentListingDTO> list() {
        // Converte uma lista de gastos para uma lista de listagem de gastos (SpentListingDTO)
        return repository.findAll().stream().map(SpentListingDTO::new).toList();
    }

    @PutMapping
    @Transactional
    public void update(@RequestBody @Valid SpentUpdateDTO response) {
        var spent = repository.getReferenceById(response.id());
        spent.updateData(response);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void delete(@PathVariable Long id) { // Com essa anotação, o Spring entende que o id passado na url é o parâmetro do método
        repository.deleteById(id);
    }

}

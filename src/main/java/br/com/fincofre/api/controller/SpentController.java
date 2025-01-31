package br.com.fincofre.api.controller;

import br.com.fincofre.api.spent.Spent;
import br.com.fincofre.api.spent.SpentRepository;
import br.com.fincofre.api.spent.SpentResponseDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}

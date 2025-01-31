package br.com.fincofre.api.controller;

import br.com.fincofre.api.spent.Spent;
import br.com.fincofre.api.spent.SpentRepository;
import br.com.fincofre.api.spent.SpentResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
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
    public void register(@RequestBody /* Essa anotação pega as informações do corpo da requisição */ SpentResponseDTO response) {
        repository.save(new Spent(response));
    }

}

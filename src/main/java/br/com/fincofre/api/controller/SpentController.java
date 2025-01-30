package br.com.fincofre.api.controller;

import br.com.fincofre.api.spent.SpentResponseDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/gastos")
public class SpentController {

    @PostMapping
    public void register(@RequestBody /* Essa anotação pega as informações do corpo da requisição */ SpentResponseDTO response) {
        System.out.println(response);
    }

}

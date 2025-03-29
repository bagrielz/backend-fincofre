package br.com.fincofre.api.service;

import br.com.fincofre.api.domain.spent.SpentDetailsDTO;
import br.com.fincofre.api.domain.spent.SpentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SpentService {

    private final SpentRepository spentRepository;

    public SpentService(SpentRepository spentRepository) {
        this.spentRepository = spentRepository;
    }

//    @Transactional
//    public SpentDetailsDTO createSpent() {
//
//    }
}

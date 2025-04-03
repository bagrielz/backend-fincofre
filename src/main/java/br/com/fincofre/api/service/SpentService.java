package br.com.fincofre.api.service;

import br.com.fincofre.api.domain.spent.*;
import br.com.fincofre.api.domain.user.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SpentService {

    private final SpentRepository spentRepository;
    private final UserRepository userRepository;

    public SpentService(SpentRepository spentRepository, UserRepository userRepository) {
        this.spentRepository = spentRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public SpentDetailsDTO createSpent(SpentResponseDTO response) {
        if (!userRepository.existsById(response.userId())) throw new RuntimeException(); // Adicionar exception personalizado

        var user = userRepository.getReferenceById(response.userId());
        var spent = new Spent(response, user);
        spentRepository.save(spent);

        return new SpentDetailsDTO(spent);
    }

    @Transactional
    public List<SpentListingDTO> getSpentsByUserId(Long userId) {
        return spentRepository.findByUserId(userId).stream().map(SpentListingDTO::new).toList();
    }
}

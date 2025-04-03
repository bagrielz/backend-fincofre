package br.com.fincofre.api.service;

import br.com.fincofre.api.domain.spent.*;
import br.com.fincofre.api.domain.user.User;
import br.com.fincofre.api.domain.user.UserRepository;
import br.com.fincofre.api.exception.UserNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SpentService {

    private final SpentRepository spentRepository;
    private final UserRepository userRepository;
    private final UserService userService;

    public SpentService(SpentRepository spentRepository, UserRepository userRepository, UserService userService) {
        this.spentRepository = spentRepository;
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @Transactional
    public SpentDetailsDTO createSpent(String auth, SpentResponseDTO response) {
        if (!userRepository.existsById(response.userId())) throw new UserNotFoundException("Usuário com ID " + response.userId() + " não foi encontrado");

        var user = checksIfTheIdBelongsToTheUser(response.userId(), auth);
        var spent = new Spent(response, user);
        spentRepository.save(spent);

        return new SpentDetailsDTO(spent);
    }

    @Transactional
    public List<SpentListingDTO> getSpentsByUserId(Long userId, String auth) {
        var user = checksIfTheIdBelongsToTheUser(userId, auth);

        return spentRepository.findByUserId(user.getId()).stream().map(SpentListingDTO::new).toList();
    }

    private User checksIfTheIdBelongsToTheUser(Long userId, String auth) {
        var user = userRepository.getReferenceById(userId);
        var subject = userService.checkAuth(auth);

        if (!subject.equals(user.getLogin())) throw new UserNotFoundException("Login " + subject + " não corresponde ao ID " + userId);

        return user;
    }
}

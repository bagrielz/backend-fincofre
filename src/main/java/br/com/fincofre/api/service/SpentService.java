package br.com.fincofre.api.service;

import br.com.fincofre.api.domain.spent.*;
import br.com.fincofre.api.domain.user.UserRepository;
import br.com.fincofre.api.exception.SpentNotFoundException;
import br.com.fincofre.api.infra.security.TokenService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SpentService {

    private final SpentRepository spentRepository;
    private final UserRepository userRepository;
    private final UserService userService;

    public SpentService(SpentRepository spentRepository, UserRepository userRepository, UserService userService, TokenService tokenService) {
        this.spentRepository = spentRepository;
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @Transactional
    public SpentDetailsDTO createSpent(String auth, SpentResponseDTO response) {
        var subject = userService.checkAuth(auth);
        var user = userRepository.getReferenceByLogin(subject);
        var spent = new Spent(response, user);

        spentRepository.save(spent);

        return new SpentDetailsDTO(spent);
    }

    public List<SpentListingDTO> getSpentsByUser(String auth) {
        var subject = userService.checkAuth(auth);
        var user = userRepository.getReferenceByLogin(subject);

        return spentRepository.findByUserId(user.getId()).stream().map(SpentListingDTO::new).toList();
    }

    @Transactional
    public Spent updateSpent(String auth, SpentUpdateDTO response) {
        if (!spentRepository.existsById(response.id())) throw new SpentNotFoundException("Gasto com o ID " + response.id() + " não foi encontrado");

        var spent = checkSpentBelongsToUser(auth, response.id());

        spent.updateData(response);

        return spent;
    }

    @Transactional
    public void deleteSpent(String auth, Long id) {
        if (!spentRepository.existsById(id)) throw new SpentNotFoundException("Gasto com o ID " + id + " não foi encontrado");

        var spent = checkSpentBelongsToUser(auth, id);

        spentRepository.deleteById(spent.getId());
    }

    public Spent detailSpent(String auth, Long id) {
        if (!spentRepository.existsById(id)) throw new SpentNotFoundException("Gasto com o ID " + id + " não foi encontrado");

        var checkSpent = checkSpentBelongsToUser(auth, id);

        return spentRepository.getReferenceById(checkSpent.getId());
    }

    private Spent checkSpentBelongsToUser(String auth, Long id) {
        var subject = userService.checkAuth(auth);
        var spent = spentRepository.getReferenceById(id);

        if (!spent.getUser().getLogin().equals(subject)) throw new SpentNotFoundException("Gasto não encontrado para o login " + subject);

        return spent;
    }

}

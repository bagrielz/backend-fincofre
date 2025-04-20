package br.com.fincofre.api.services;

import br.com.fincofre.api.models.dtos.*;
import br.com.fincofre.api.models.entities.spent.*;
import br.com.fincofre.api.repositories.UserRepository;
import br.com.fincofre.api.exceptions.SpentNotFoundException;
import br.com.fincofre.api.repositories.SpentRepository;
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
    public SpentDetailsDTO createSpent(String subject, SpentResponseDTO response) {
        var user = userRepository.getReferenceByLogin(subject);
        var spent = Spent.fromDTO(response, user);

        spentRepository.save(spent);

        return new SpentDetailsDTO(spent);
    }

    public SpentsListWithTotalDTO getSpentsByUser(String subject) {
        var user = userRepository.getReferenceByLogin(subject);
        var spents = spentRepository.findByUserId(user.getId()).stream().map(SpentListingDTO::new).toList();

        return totalAmountSpents(spents);
    }

    @Transactional
    public Spent updateSpent(Long id, SpentUpdateDTO response, String subject) {
        if (!spentRepository.existsById(id)) throw new SpentNotFoundException("Gasto com o ID " + id + " não foi encontrado");
        var spent = checkSpentBelongsToUser(subject, id);

        spent.updateData(response);

        return spent;
    }

    @Transactional
    public void deleteSpent(String subject, Long id) {
        if (!spentRepository.existsById(id)) throw new SpentNotFoundException("Gasto com o ID " + id + " não foi encontrado");
        var spent = checkSpentBelongsToUser(subject, id);

        spentRepository.deleteById(spent.getId());
    }

    public Spent detailSpent(String subject, Long id) {
        if (!spentRepository.existsById(id)) throw new SpentNotFoundException("Gasto com o ID " + id + " não foi encontrado");
        var checkSpent = checkSpentBelongsToUser(subject, id);

        return spentRepository.getReferenceById(checkSpent.getId());
    }

    private Spent checkSpentBelongsToUser(String subject, Long id) {
        var spent = spentRepository.getReferenceById(id);
        if (!spent.getUser().getLogin().equals(subject)) throw new SpentNotFoundException("Gasto não encontrado para o login " + subject);

        return spent;
    }

    private SpentsListWithTotalDTO totalAmountSpents(List<SpentListingDTO> spents) {
        double total = 0.0;

        for (SpentListingDTO s : spents) {
            var value = s.value().replace(",", ".");

            try {
                double parsed = Double.parseDouble(value);
                total += parsed;
            } catch (NumberFormatException e) {
                throw new NumberFormatException(e.getMessage());
            }
        }

        return new SpentsListWithTotalDTO(spents, total);
    }

}

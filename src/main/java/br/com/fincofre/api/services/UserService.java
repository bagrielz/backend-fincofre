package br.com.fincofre.api.services;

import br.com.fincofre.api.exceptions.ValidationException;
import br.com.fincofre.api.models.dtos.UserUpdateDetailsDTO;
import br.com.fincofre.api.models.entities.user.User;
import br.com.fincofre.api.models.dtos.UserDetailsDTO;
import br.com.fincofre.api.models.dtos.UserResponseDTO;
import br.com.fincofre.api.models.dtos.UserUpdateDTO;
import br.com.fincofre.api.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final TokenService tokenService;

    public UserService(TokenService tokenService, UserRepository userRepository) {
        this.tokenService = tokenService;
        this.userRepository = userRepository;
    }

    @Transactional
    public UserDetailsDTO createUser(UserResponseDTO response) {
        checkIfTheLoginExists(response.login());

        var user = new User(response);
        userRepository.save(user);

        return new UserDetailsDTO(user);
    }

    @Transactional
    public UserUpdateDetailsDTO updateUser(String subject, UserUpdateDTO response) {
        if (!response.login().isBlank()) checkIfTheLoginExists(response.login());
        var user = userRepository.getReferenceByLogin(subject);

        user.updateData(response);
        var newTokenToUser = tokenService.generateToken(user);

        return new UserUpdateDetailsDTO(user, newTokenToUser);
    }

    @Transactional
    public UserDetailsDTO getUserInformation(String subject) {
        var user = userRepository.getReferenceByLogin(subject);

        return new UserDetailsDTO(user);
    }

    @Transactional
    public void deleteUser(String subject) {
        userRepository.deleteByLogin(subject);
    }

    private void checkIfTheLoginExists(String login) {
        if (userRepository.existsByLogin(login)) throw new ValidationException("Login " + login + " já existe");
    }

}

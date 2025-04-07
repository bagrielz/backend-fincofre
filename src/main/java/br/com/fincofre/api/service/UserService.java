package br.com.fincofre.api.service;

import br.com.fincofre.api.domain.user.*;
import br.com.fincofre.api.exception.UserNotFoundException;
import br.com.fincofre.api.infra.security.TokenService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
        var user = new User(response);
        userRepository.save(user);

        return new UserDetailsDTO(user);
    }

    @Transactional
    public UserDetailsDTO updateUser(String auth, UserUpdateDTO response) {
        var subject = checkAuth(auth);
        var user = userRepository.getReferenceByLogin(subject);

        user.updateData(response);

        return new UserDetailsDTO(user);
    }

    @Transactional
    public UserDetailsDTO listUserInformation(String auth, UserDetailsDTO response) {
        var subject = checkAuth(auth);

        if (!subject.equals(response.login())) throw new UserNotFoundException("Login " + response.login() + " está incorreto ou não foi encontrado");

        var user = userRepository.getReferenceByLogin(subject);

        return new UserDetailsDTO(user);
    }

    @Transactional
    public void deleteUser(String auth) {
        var subject = checkAuth(auth);

        userRepository.deleteByLogin(subject);
    }

    public String checkAuth(String auth) {
        var token = tokenService.recoverToken(auth);
        return tokenService.getSubject(token);
    }
}

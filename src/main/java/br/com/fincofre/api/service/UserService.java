package br.com.fincofre.api.service;

import br.com.fincofre.api.domain.user.*;
import br.com.fincofre.api.infra.security.TokenService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

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

        if (!subject.equals(response.login())) throw new RuntimeException(); // Escrever uma exception personalizável

        var user = userRepository.getReferenceById(response.id());
        user.updateData(response);

        return new UserDetailsDTO(user);
    }

    public String checkAuth(String auth) {
        if (auth == null) throw new RuntimeException(); // Escrever uma exception personalizável

        var token = tokenService.recoverToken(auth);
        return tokenService.getSubject(token);
//        userRepository.deleteByLogin(subject);
    }
}

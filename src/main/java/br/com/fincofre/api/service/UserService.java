package br.com.fincofre.api.service;

import br.com.fincofre.api.domain.user.UserRepository;
import br.com.fincofre.api.infra.security.TokenService;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;
    private TokenService tokenService;

    public UserService(TokenService tokenService, UserRepository userRepository) {
        this.tokenService = tokenService;
        this.userRepository = userRepository;
    }

    public void checkToken(String token) {
        if (token == null) throw new RuntimeException();

        var subject = tokenService.getSubject(token);
        userRepository.deleteByLogin(subject);
    }
}

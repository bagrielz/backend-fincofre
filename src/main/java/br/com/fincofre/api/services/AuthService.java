package br.com.fincofre.api.services;

import br.com.fincofre.api.exceptions.ValidationException;
import br.com.fincofre.api.models.dtos.AuthDTO;
import br.com.fincofre.api.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements UserDetailsService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void checkCredentials(AuthDTO data) {
        if (!userRepository.existsByLogin(data.login())) throw new ValidationException("Login ou senha incorretos");
        var user = userRepository.getReferenceByLogin(data.login());

        if (!passwordEncoder.matches(data.password(), user.getPassword())) throw new ValidationException("Login ou senha incorretos");
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByLogin(username);
    }

}

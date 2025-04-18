package br.com.fincofre.api.services;

import br.com.fincofre.api.models.interfaces.AuthenticatedUser;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class AuthenticatedUserService implements AuthenticatedUser {

    @Override
    public String getUsername() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) return authentication.getName();
        throw new UsernameNotFoundException("Login " + authentication.getName() + " não está autenticado ou não foi encontrado");
    }

}

package br.com.fincofre.api.services;

import br.com.fincofre.api.models.interfaces.AuthenticatedUser;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthenticatedUserService implements AuthenticatedUser {

    @Override
    public String getUsername() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

}

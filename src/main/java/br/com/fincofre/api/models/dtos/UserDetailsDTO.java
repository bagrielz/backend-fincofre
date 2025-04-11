package br.com.fincofre.api.models.dtos;

import br.com.fincofre.api.models.entities.user.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserDetailsDTO(@NotNull Long id, String login, String name, String email, String password) {

    public UserDetailsDTO(User user) {
        this(user.getId(), user.getLogin(), user.getName(), user.getEmail(), user.getPassword());
    }

}

package br.com.fincofre.api.domain.user;

import jakarta.validation.constraints.NotBlank;

public record UserDetailsDTO(Long id, @NotBlank String login, String name, String email, String password) {

    public UserDetailsDTO(User user) {
        this(user.getId(), user.getLogin(), user.getName(), user.getEmail(), user.getPassword());
    }

}

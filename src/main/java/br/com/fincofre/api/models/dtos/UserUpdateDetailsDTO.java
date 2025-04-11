package br.com.fincofre.api.models.dtos;

import br.com.fincofre.api.models.entities.user.User;

public record UserUpdateDetailsDTO(Long id, String login, String name, String email, String password, String token) {

    public UserUpdateDetailsDTO(User user, String token) {
        this(user.getId(), user.getLogin(), user.getName(), user.getEmail(), user.getPassword(), token);
    }

}

package br.com.fincofre.api.domain.user;

import jakarta.validation.constraints.NotNull;

public record UserUpdateDTO(@NotNull Long id, String login, String name, String email, String password) {
}

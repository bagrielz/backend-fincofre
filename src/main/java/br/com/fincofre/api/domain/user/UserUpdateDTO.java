package br.com.fincofre.api.domain.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserUpdateDTO(@NotNull Long id, @NotBlank String login, String name, String email, String password) {
}

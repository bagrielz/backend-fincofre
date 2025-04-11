package br.com.fincofre.api.models.dtos;

import jakarta.validation.constraints.NotNull;

public record UserUpdateDTO(@NotNull Long id, String login, String name, String email, String password) {
}

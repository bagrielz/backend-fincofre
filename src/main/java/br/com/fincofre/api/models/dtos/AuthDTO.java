package br.com.fincofre.api.models.dtos;

import jakarta.validation.constraints.NotBlank;

public record AuthDTO(@NotBlank String login, @NotBlank String password) {
}

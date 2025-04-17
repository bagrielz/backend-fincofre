package br.com.fincofre.api.models.dtos;

public record UserUpdateDTO(String login, String name, String email, String password) {
}

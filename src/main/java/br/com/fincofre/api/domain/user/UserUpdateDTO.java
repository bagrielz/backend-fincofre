package br.com.fincofre.api.domain.user;

public record UserUpdateDTO(String login, String name, String email, String password) {
}

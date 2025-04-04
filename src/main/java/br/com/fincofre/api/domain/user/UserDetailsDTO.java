package br.com.fincofre.api.domain.user;

public record UserDetailsDTO(Long id, String login, String name, String email, String password) {

    public UserDetailsDTO(User user) {
        this(user.getId(), user.getLogin(), user.getName(), user.getEmail(), user.getPassword());
    }

}

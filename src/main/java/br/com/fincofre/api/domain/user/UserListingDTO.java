package br.com.fincofre.api.domain.user;

public record UserListingDTO(Long id, String login, String name, String email, String password) {

    public UserListingDTO(User data) {
        this(data.getId(), data.getLogin(), data.getName(), data.getEmail(), data.getPassword());
    }

}

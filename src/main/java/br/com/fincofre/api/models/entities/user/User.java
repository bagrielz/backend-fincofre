package br.com.fincofre.api.models.entities.user;

import br.com.fincofre.api.models.dtos.UserCreateDTO;
import br.com.fincofre.api.models.dtos.UserUpdateDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collection;
import java.util.List;

@Table(name = "users")
@Entity(name = "User")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String login;

    @NotBlank
    private String name;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Pattern(regexp = "^.{8,}$")
    private String password;

    private User(String login, String name, String email, String password) {
        this.login = login;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public static User fromDTO(UserCreateDTO dto, BCryptPasswordEncoder encoder) {
        return new User(
                dto.login(),
                dto.name(),
                dto.email(),
                encoder.encode(dto.password())
        );
    }

    public void updateData(UserUpdateDTO response, BCryptPasswordEncoder encoder) {
        if (response.login() != null && !response.login().isBlank()) this.login = response.login();
        if (response.name() != null && !response.name().isBlank()) this.name = response.name();
        if (response.email() != null && !response.email().isBlank()) this.email = response.email();
        if (response.password() != null && !response.password().isBlank()) this.password = encoder.encode(response.password());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
